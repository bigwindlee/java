import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
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
    static int SHRINK_SIZE = 4088950;

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
            try {
                shrinkJpg(image_path, output_dir, SHRINK_SIZE);
            } catch (Split2JpgException e) {
                ret.SetReturns(e.error_code, e.error_msg);
                return ret;
            }
            page_num = 1;
        } else if (type.equalsIgnoreCase("pdf")) {
            try {
                page_num = splitPdf(image_path, output_dir);
            } catch (Split2JpgException e) {
                ret.SetReturns(e.error_code, e.error_msg);
                return ret;
            }
        } else {
            // TIFF
            try {
                page_num = splitTiff(image_path, output_dir);
            } catch (Split2JpgException e) {
                ret.SetReturns(e.error_code, e.error_msg);
                return ret;
            }
        }

        ret.m_page_num = page_num;

        return ret;
    }

    static public boolean isImageTypeLegal(String type) {
        return type.equalsIgnoreCase("pdf")
                || type.equalsIgnoreCase("tiff") || type.equalsIgnoreCase("tif")
                || type.equalsIgnoreCase("jpg") || type.equalsIgnoreCase("jpeg");
    }

    static public void shrinkJpg(String filepath, String outDir, int shrink_size) throws Split2JpgException {
        Mat srcImg = imread(filepath);
        if (srcImg.empty()) {
            throw new Split2JpgException(-1004, "IMAGE_FILE_CORRUPT");
        }
        MatOfByte bytemat = new MatOfByte();
        if (false == Imgcodecs.imencode(".jpg", srcImg, bytemat)) {
            throw new Split2JpgException(-2001, "IMAGE_DECODE_ERROR");
        }

        byte[] bytes = bytemat.toArray();
        Mat dstImg = new Mat();
        Size zero_size = new Size();
        while (bytes.length > shrink_size) {
            Imgproc.resize(srcImg, dstImg, zero_size, 0.9, 0.9, Imgproc.INTER_AREA);
            if (false == Imgcodecs.imencode(".jpg", dstImg, bytemat)) {
                throw new Split2JpgException(-2001, "IMAGE_DECODE_ERROR");
            }
            srcImg = dstImg;
            bytes = bytemat.toArray();
        }

        // Write image file
        Imgcodecs.imwrite(SplitUtils.getFullSplittedName(outDir, filepath, 0, 3), dstImg);
    }


    static public int splitPdf(String filepath, String outDir) throws Split2JpgException {
        PDFDocument document = new PDFDocument();
        try {
            document.load(new File(filepath));
            SimpleRenderer renderer = new SimpleRenderer();
            renderer.setResolution(300);
            List<Image> images = renderer.render(document);
            int image_count = images.size();
            String newFile;
            for (int i = 0; i < image_count; i++) {
                newFile = SplitUtils.getFullSplittedName(outDir, filepath, i, 3);
                ImageIO.write((RenderedImage) images.get(i), "jpg", new File(newFile));
            }
            return image_count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Split2JpgException(-2001, "IMAGE_DECODE_ERROR");
        }
    }

    static public int splitTiff(String filepath, String outDir) throws Split2JpgException {
        List<Mat> mats = new ArrayList<>();
        if (false == Imgcodecs.imreadmulti(filepath, mats)) {
            throw new Split2JpgException(-2001, "IMAGE_DECODE_ERROR");
        }
        int image_count = mats.size();
        for (int i = 0; i < image_count; i++) {
            Imgcodecs.imwrite(SplitUtils.getFullSplittedName(outDir, filepath, i, 3), mats.get(i));
        }
        return image_count;
    }
}
