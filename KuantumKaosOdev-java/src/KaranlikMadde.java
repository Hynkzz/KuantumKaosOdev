public class KaranlikMadde extends KuantumNesnesi implements IKritik {

    public KaranlikMadde(String id) {
        super(id, 5);
    }

    @Override
    public void AnalizEt() throws KuantumCokusuException {
        double yeniDeger = getStabilite() - 15;
        setStabilite(yeniDeger);
    }

    @Override
    public void AcilDurumSogutmasi() {
        try {
            // Soğutma sırasında hata çıkmaz ama setter hata fırlattığı için try-catch lazım
            // Gerçi artırırken 0 altına düşmez ama Java kuralcıdır.
            double yeniDeger = getStabilite() + 50;
            setStabilite(yeniDeger);
            System.out.println(getId() + " soğutuldu. Stabilite arttı.");
        } catch (KuantumCokusuException e) {
            // Buraya düşmesi imkansız ama Java syntax hatası vermesin diye koyduk
        }
    }
}