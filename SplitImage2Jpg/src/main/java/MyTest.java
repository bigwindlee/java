import org.opencv.core.Core;

public class MyTest {
    static {
        //加载动态链接库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static public void test_Split2Jpg_1() {
        ImageInfo ii_in = new ImageInfo("image\\jpg\\63554677_20200724113557_15955617581649613-017.jpg", "jpg", "output_dir");
        ImageInfo ii_out = HebaoSplitJpg.Split2Jpg(ii_in);
        System.out.println(ii_out);
    }

    static public void test_Split2Jpg_2() {
        ImageInfo ii_in = new ImageInfo("image\\tiff\\55256951_20200702181946.tiff", "tiff", "output_dir");
        ImageInfo ii_out = HebaoSplitJpg.Split2Jpg(ii_in);
        System.out.println(ii_out);
    }

    static public void test_Split2Jpg_3() {
        ImageInfo ii_in = new ImageInfo("image\\pdf\\11707921_20200527095749_211915.pdf", "pdf", "output_dir");
        ImageInfo ii_out = HebaoSplitJpg.Split2Jpg(ii_in);
        System.out.println(ii_out);
    }

    public static void main(String[] args) {


        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        test_Split2Jpg_1();

        test_Split2Jpg_2();

        test_Split2Jpg_3();

    }
}
