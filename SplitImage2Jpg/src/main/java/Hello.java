import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

import java.io.File;

import static org.opencv.imgcodecs.Imgcodecs.*;


public class Hello {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  //加载动态链接库
    }

    public static void main(String[] args) {


        String fileName = "D:\\test0725\\07\\24\\63554677_b06ba84998994025acf35a879b02da97_15955617576773238\\63554677_20200724113557_15955617581649613\\63554677_20200724113557_15955617581649613-017.jpg"; //设置图片的路径
        if (!new File(fileName).exists()) {
            System.out.println("文件不存在");
        } else {

            Mat srcImg = imread(fileName);  //opencv读取
            if (srcImg.empty()) {
                System.out.println("加载图片失败！");
            } else {
                System.out.println(Integer.toString(srcImg.height()) + ", " + Integer.toString(srcImg.width()) + ", " + Integer.toString(srcImg.channels()));
                System.out.println(srcImg);

                MatOfByte bytemat = new MatOfByte();
                Imgcodecs.imencode(".jpg", srcImg, bytemat);
                byte[] bytes = bytemat.toArray();
                System.out.println(bytes.length);

                Mat dstImg = new Mat();
                Size zero_size = new Size();
                Imgproc.resize(srcImg, dstImg, zero_size, 0.9, 0.9, Imgproc.INTER_AREA);
                System.out.println(Imgcodecs.imwrite("new01_" + "63554677_20200724113557_15955617581649613-017.jpg", dstImg));
            }
        }

    }

}