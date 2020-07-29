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
import java.util.List;

import com.sun.media.jai.codecimpl.TIFFImageDecoder;

import java.util.Vector;

public class MyTest {
    static {
        //加载动态链接库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static public String splitTiff(String tiff_path, String outTiffFileName, String[] pages) {
        // pageNums is a String array of 0-based page numbers.
        boolean debugOn = true;
        try {
            InputStream is = new FileInputStream(tiff_path);
            SeekableStream ss = SeekableStream.wrapInputStream(is, false);

            TIFFDirectory td = new TIFFDirectory(ss, 0);
            if (debugOn) {
                System.out.println("Directory has " + Integer.toString(td.getNumEntries()) + " entries");
                System.out.println("Getting TIFFFields");
                System.out.println("X resolution = " + Float.toString(td.getFieldAsFloat(TIFFImageDecoder.TIFF_X_RESOLUTION)));
                System.out.println("Y resolution = " + Float.toString(td.getFieldAsFloat(TIFFImageDecoder.TIFF_Y_RESOLUTION)));
                System.out.println("Resolution unit = " + Long.toString(td.getFieldAsLong(TIFFImageDecoder.TIFF_RESOLUTION_UNIT)));
            }
            ImageDecoder decodedImage = ImageCodec.createImageDecoder("tiff", ss, null);
            int count = decodedImage.getNumPages();
            if (debugOn) {
                System.out.println("Input image has " + count + " page(s)");
            }
            TIFFEncodeParam param = new TIFFEncodeParam();
            TIFFField tf = td.getField(259); // Compression as specified in the input file
            param.setCompression(tf.getAsInt(0)); // Set the compression of the output to be the same.
            param.setLittleEndian(false); // Intel
            param.setExtraFields(td.getFields());
            FileOutputStream fOut = new FileOutputStream(outTiffFileName);
            Vector<RenderedImage> vector = new Vector<RenderedImage>();
            RenderedImage page0 = decodedImage.decodeAsRenderedImage(Integer.parseInt(pages[0]));
            BufferedImage img0 = new BufferedImage(page0.getColorModel(), (WritableRaster) page0.getData(), false, null);
            int pgNum;
            // Adding the extra pages starts with the second one on the list.
            for (int i = 1; i < pages.length; i++) {
                pgNum = Integer.parseInt(pages[i]);
                if (debugOn) {
                    System.out.println("Page number " + pgNum);
                }
                RenderedImage page = decodedImage.decodeAsRenderedImage(pgNum);
                if (debugOn) {
                    System.out.println("Page is " + Integer.toString(page.getWidth()) + " pixels wide and " + Integer.toString(page.getHeight()) + " pixels high.");
                }
                if (debugOn) {
                    System.out.println("Adding page " + pages[i] + " to vector");
                }
                vector.add(page);
            }
            param.setExtraImages(vector.iterator());
            ImageEncoder encoder = ImageCodec.createImageEncoder("tiff", fOut, param);
            if (debugOn) {
                System.out.println("Encoding page " + pages[0]);
            }
            encoder.encode(decodedImage.decodeAsRenderedImage(Integer.parseInt(pages[0])));
            fOut.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            return ("Not OK " + e.getMessage());
        }
        return ("OK");
    }

    static public void tiff2jpg(String filepath_in, String filepath_out) {
        try {
            final BufferedImage tif = ImageIO.read(new File(filepath_in));
            ImageIO.write(tif, "jpg", new File(filepath_out + "\\test0729.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void test_splitPdf() {
        try {
            int count = TaikangSplitJpg.splitPdf("image\\pdf\\11707921_20200527095749_211915.pdf", "output_dir");
            System.out.println(String.format("Split into %d jpg files.", count));
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    static public void testGetBaseName(String filepath) {
        System.out.println(TaikangSplitJpg.getBaseName(filepath));
    }

    public static void main(String[] args) {


        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
//        test_splitPdf();

        String tiff_path = "image\\tiff\\55256951_20200702181946.tiff";
        try {
            InputStream is = new FileInputStream(tiff_path);
            SeekableStream ss = SeekableStream.wrapInputStream(is, false);
            ImageDecoder decodedImage = ImageCodec.createImageDecoder("tiff", ss, null);
            System.out.println(decodedImage.getNumPages());

            //TIFFDirectory td = new TIFFDirectory(ss, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
