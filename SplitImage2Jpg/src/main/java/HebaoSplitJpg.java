import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.ghost4j.renderer.SimpleRenderer;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.ghost4j.document.PDFDocument;

import javax.imageio.ImageIO;

import static org.opencv.imgcodecs.Imgcodecs.imread;

public class HebaoSplitJpg {
    static int shrink_size = 4088950;

    static public ImageInfo Split2Jpg(ImageInfo info) {
        String image_path = info.m_image_path;
        String type = info.m_image_type;
        String output_dir = info.m_output_dir;
        String error_msg = "OK";
        int error_code = 0;
        int page_num = 0;

        ImageInfo ret = new ImageInfo(image_path, type, output_dir, page_num, error_code, error_msg);

        if (isImageTypeLegal(type) == false) {
            ret.SetReturns(-1001, "NOT_ACCEPATABLE_IMAGE_FOR_INPUT");
            return ret;
        }

        File image = new File(image_path);
        if (!image.isFile()) {
            ret.SetReturns(-1002, "FILE_PATH_NOT_EXISTS");
            return ret;
        }

        File dir = new File(output_dir);
        if (!dir.isDirectory()) {
            ret.SetReturns(-1003, "OUTPUT_DIR_NOT_EXISTS");
            return ret;
        }

        if (type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jpeg")) {
            Mat srcImg = imread(image_path);
            if (srcImg.empty()) {
                ret.SetReturns(-1004, "IMAGE_FILE_CORRUPT");
                return ret;
            }
            MatOfByte bytemat = new MatOfByte();
            if (false == Imgcodecs.imencode(".jpg", srcImg, bytemat)) {
                ret.SetReturns(-2001, "IMAGE_DECODE_ERROR");
                return ret;
            }

            byte[] bytes = bytemat.toArray();
            Mat dstImg = new Mat();
            Size zero_size = new Size();
            while (bytes.length > shrink_size) {
                Imgproc.resize(srcImg, dstImg, zero_size, 0.9, 0.9, Imgproc.INTER_AREA);
                if (false == Imgcodecs.imencode(".jpg", dstImg, bytemat)) {
                    ret.SetReturns(-2001, "IMAGE_DECODE_ERROR");
                    return ret;
                }
                srcImg = dstImg;
                bytes = bytemat.toArray();
                System.out.println(bytes.length);
            }

            // Get new filename
            String[] subDirs = image_path.split("\\\\");
            String[] subFileNames = subDirs[subDirs.length - 1].split("\\.");
            String newFileName = subFileNames[0] + "-001." + subFileNames[1];
            String newImagePath = output_dir + "\\" + newFileName;

            // Write image file
            Imgcodecs.imwrite(newImagePath, dstImg);
            page_num++;
        }

        return new ImageInfo(image_path, type, output_dir, page_num, error_code, error_msg);
    }

    static public boolean isImageTypeLegal(String type) {
        return type.equalsIgnoreCase("pdf")
                || type.equalsIgnoreCase("tiff") || type.equalsIgnoreCase("tif")
                || type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jpeg");
    }

    static public int getPdfPageCount(String filepath) {
        PDFDocument PDFDocument = new PDFDocument();
        try {
            PDFDocument.load(new File(filepath));
            return PDFDocument.getPageCount();
        } catch (Exception e) {
            System.out.print(e);
            return 0;
        }
    }


    static public int splitPdf(String filepath, String outDir) throws Split2JpgException {
        PDFDocument document = new PDFDocument();
        try {
            document.load(new File(filepath));
            SimpleRenderer renderer = new SimpleRenderer();
            renderer.setResolution(300);
            List<Image> images = renderer.render(document);
            int image_count = images.size();
            String baseName = SplitUtils.getBaseName(filepath);
            String newFile;
            for (int i = 0; i < image_count; i++) {
                newFile = String.format("%s-%s.jpg", baseName, SplitUtils.stringRightJust(String.valueOf(i + 1), 3, '0'));
                ImageIO.write((RenderedImage) images.get(i), "jpg", new File(Paths.get(outDir, newFile).toString()));
            }
            return image_count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Split2JpgException(-2001, "PDF_SPLIT_ERROR");
        }
    }

    static public int splitTiff(String filepath, String outDir) {
        List<Mat> mats = new ArrayList<Mat>();
        Imgcodecs.imreadmulti(filepath, mats);
        int image_count = mats.size();
        for (int i = 0; i < image_count; i++) {
            String newName = Paths.get(outDir, SplitUtils.getSplittedName(filepath, i, 3)).toString();
            Imgcodecs.imwrite(newName, mats.get(i));
        }
        return image_count;
    }
}
