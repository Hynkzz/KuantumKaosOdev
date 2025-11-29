public class KuantumCokusuException extends Exception {
    public KuantumCokusuException(String nesneID) {
        super(nesneID + " kimlikli nesne çöktü! Kuantum dengesi bozuldu!");
    }
}