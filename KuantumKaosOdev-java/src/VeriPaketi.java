public class VeriPaketi extends KuantumNesnesi {

    public VeriPaketi(String id) {
        super(id, 1);
    }

    @Override
    public void AnalizEt() throws KuantumCokusuException {
        // Stabiliteyi getter/setter ile yönetiriz
        double yeniDeger = getStabilite() - 5;
        setStabilite(yeniDeger);
        System.out.println("Veri içeriği okundu.");
    }
}