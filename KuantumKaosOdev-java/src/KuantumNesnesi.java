public abstract class KuantumNesnesi {
    private String id;
    private double stabilite;
    private int tehlikeSeviyesi;

    public KuantumNesnesi(String id, int tehlikeSeviyesi) {
        this.id = id;
        this.tehlikeSeviyesi = tehlikeSeviyesi;
        this.stabilite = 100.0; // Başlangıç full
    }

    // ID için Getter
    public String getId() {
        return id;
    }

    // Stabilite için Getter
    public double getStabilite() {
        return stabilite;
    }

    // Stabilite için Setter (Encapsulation burada yapılıyor)
    public void setStabilite(double deger) throws KuantumCokusuException {
        if (deger > 100) {
            this.stabilite = 100;
        } else if (deger <= 0) {
            this.stabilite = 0;
            // Hata fırlat
            throw new KuantumCokusuException(this.id);
        } else {
            this.stabilite = deger;
        }
    }

    // Tehlike Seviyesi Getter
    public int getTehlikeSeviyesi() {
        return tehlikeSeviyesi;
    }

    // Soyut Metot
    public abstract void AnalizEt() throws KuantumCokusuException; // Hata fırlatabilir

    // Durum Bilgisi
    public String DurumBilgisi() {
        return "ID: " + id + " | Stabilite: %" + String.format("%.2f", stabilite) + " | Tehlike: " + tehlikeSeviyesi;
    }
}