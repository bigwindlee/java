public class Split2JpgException extends Exception {
    public int error_code;
    public String error_msg;

    public Split2JpgException(int error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }
}
