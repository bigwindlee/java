public class ImageInfo {
    public String m_image_path;
    public String m_image_type;
    public String m_output_dir;
    public int m_page_num;
    public int m_error_code;
    public String m_error_msg;

    public ImageInfo(String image_path, String image_type, String output_dir) {
        m_image_path = image_path;
        m_image_type = image_type;
        m_output_dir = output_dir;
        m_page_num = 0;
        m_error_code = 0;
        m_error_msg = "";
    }

    public ImageInfo(String image_path, String image_type, String output_dir, int page_num, int error_code, String error_msg) {
        m_image_path = image_path;
        m_image_type = image_type;
        m_output_dir = output_dir;
        m_page_num = page_num;
        m_error_code = error_code;
        m_error_msg = error_msg;
    }

    public void SetReturns(int error_code, String error_msg) {
        m_error_code = error_code;
        m_error_msg = error_msg;
    }

    public String toString() {
        return "image_path: " + m_image_path + "\n"
                + "image_type: " + m_image_type + "\n"
                + "output_dir: " + m_output_dir + "\n"
                + "page_num: " + m_page_num + "\n"
                + "error_code: " + m_error_code + "\n"
                + "error_msg: " + m_error_msg + "\n";
    }

}
