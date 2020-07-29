import org.opencv.core.Core;

public class MyTest {
    static {
        //加载动态链接库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static public void test_splitPdf() {
        try {
            int count = HebaoSplitJpg.splitPdf("image\\pdf\\11707921_20200527095749_211915.pdf", "output_dir");
            System.out.println(String.format("Split into %d jpg files.", count));
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    static public void test_splitTiff() {
        String tiff_path = "image\\tiff\\55256951_20200702181946.tiff";
        HebaoSplitJpg.splitTiff(tiff_path, "output_dir");
    }

    public static void main(String[] args) {


        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        test_splitPdf();

        test_splitTiff();

    }
}
