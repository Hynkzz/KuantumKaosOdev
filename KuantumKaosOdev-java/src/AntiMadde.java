public class AntiMadde extends KuantumNesnesi implements IKritik {

    public AntiMadde(String id) {
        super(id, 10);
    }

    @Override
    public void AnalizEt() throws KuantumCokusuException {
        double yeniDeger = getStabilite() - 25;
        setStabilite(yeniDeger);
        System.out.println("Evrenin dokusu titriyor...");
    }

    @Override
    public void AcilDurumSogutmasi() {
        try {
            double yeniDeger = getStabilite() + 50;
            setStabilite(yeniDeger);
            System.out.println(getId() + " üzerine acil durum soğutucusu sıkıldı!");
        } catch (KuantumCokusuException e) {
        }
    }
}