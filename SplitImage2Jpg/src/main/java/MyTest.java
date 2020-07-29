import com.sun.media.jai.codec.*;
//import org.apache.xmlgraphics.image.codec.tiff.TIFFEncodeParam;
//import org.apache.xmlgraphics.image.codec.tiff.TIFFField;
//import org.apache.xmlgraphics.image.codec.tiff.TIFFImageDecoder;
import com.sun.media.jai.codec.ImageDecoder;
//import org.apache.xmlgraphics.image.codec.util.SeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import org.opencv.core.Core;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

import java.io.*;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.sun.media.jai.codecimpl.TIFFImageDecoder;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Vector;

public class MyTest {
    static {
        //加载动态链接库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static public void test_splitPdf() {
        try {
            int count = TaikangSplitJpg.splitPdf("image\\pdf\\11707921_20200527095749_211915.pdf", "output_dir");
            System.out.println(String.format("Split into %d jpg files.", count));
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    static public void test_splitTiff() {
        String tiff_path = "image\\tiff\\55256951_20200702181946.tiff";
        TaikangSplitJpg.splitTiff(tiff_path, "output_dir");
    }

    public static void main(String[] args) {


        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        test_splitPdf();

        test_splitTiff();


//        splitTiff(tiff_path, "test0729.jpg");
//        try {
//            InputStream is = new FileInputStream(tiff_path);
//            SeekableStream ss = SeekableStream.wrapInputStream(is, false);
//            ImageDecodeParam imageDecodeParam = new TIFFDecodeParam();
//            ImageDecoder decodedImage = ImageCodec.createImageDecoder("tiff", ss, imageDecodeParam);
//            int page_count = decodedImage.getNumPages();
//            for (int i = 0; i < page_count; i++) {
//                RenderedImage ri = decodedImage.decodeAsRenderedImage(i);
//                BufferedImage bi = new BufferedImage(ri.getColorModel(), (WritableRaster) ri.getData(), false, null);
//                ImageIO.write(bi, "jpg", new File(SplitUtils.getSplittedName(tiff_path, i, 3)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        String[] subParts = SplitUtils.splitPath(filepath);

//        for (String i : subParts) {
//            System.out.println(i);
//        }

//        testFileSeparatorChar();

        // ImageInfo(String image_path, String image_type, String output_dir)
//        String image_path = "jpg\\63554677_20200724113557_15955617581649613-017.jpg";
//        String image_type = "jpg";
//        String output_dir = "output_dir";
//        ImageInfo imageInfo = new ImageInfo(image_path, image_type, output_dir);
//
//        imageInfo = TaikangSplitJpg.Split2Jpg(imageInfo);
//        System.out.println(imageInfo.toString());


//        String imagePath = "D:\\test0725\\07\\24\\63554677_b06ba84998994025acf35a879b02da97_15955617576773238\\63554677_20200724113557_15955617581649613\\63554677_20200724113557_15955617581649613-017.jpg"; //设置图片的路径
//        String[] subDirs = imagePath.split("\\\\");
//        for (int i = 0; i < subDirs.length; i++) {
//            System.out.println(subDirs[i]);
//        }
//        System.out.println(subDirs[subDirs.length - 1]);
//        System.out.println("\n====\n");
//        String[] subFileNames = subDirs[subDirs.length - 1].split("\\.");
//        for (int i = 0; i < subFileNames.length; i++) {
//            System.out.println(subFileNames[i]);
//        }
//        String newFileName = subFileNames[0] + "-001." + subFileNames[1];
//        System.out.println(newFileName);
    }
}
