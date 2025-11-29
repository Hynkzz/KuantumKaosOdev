import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<KuantumNesnesi> envanter = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();
        int sayac = 1;

        System.out.println("=== KUANTUM VERİ AMBARINA HOŞ GELDİNİZ (JAVA) ===");

        while (true) {
            System.out.println("\n--- KUANTUM AMBARI KONTROL PANELİ ---");
            System.out.println("1. Yeni Nesne Ekle");
            System.out.println("2. Tüm Envanteri Listele");
            System.out.println("3. Nesneyi Analiz Et");
            System.out.println("4. Acil Durum Soğutması Yap");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");

            String secim = scanner.nextLine();

            try {
                switch (secim) {
                    case "1":
                        int tur = rnd.nextInt(3) + 1; // 1, 2, 3
                        KuantumNesnesi yeniNesne;
                        String yeniID = "NESNE-" + sayac++;

                        if (tur == 1) yeniNesne = new VeriPaketi(yeniID);
                        else if (tur == 2) yeniNesne = new KaranlikMadde(yeniID);
                        else yeniNesne = new AntiMadde(yeniID);

                        envanter.add(yeniNesne);
                        System.out.println(yeniNesne.getClass().getSimpleName() + " eklendi. ID: " + yeniID);
                        break;

                    case "2":
                        System.out.println("\n--- ENVANTER RAPORU ---");
                        for (KuantumNesnesi nesne : envanter) {
                            System.out.println(nesne.DurumBilgisi());
                        }
                        break;

                    case "3":
                        System.out.print("Analiz edilecek nesne ID'si: ");
                        String analizID = scanner.nextLine();

                        // Java'da Find işlemi stream ile yapılır ya da döngüyle
                        KuantumNesnesi analizNesnesi = null;
                        for (KuantumNesnesi n : envanter) {
                            if (n.getId().equals(analizID)) {
                                analizNesnesi = n;
                                break;
                            }
                        }

                        if (analizNesnesi != null) {
                            analizNesnesi.AnalizEt();
                            System.out.println("Güncel Stabilite: " + analizNesnesi.getStabilite());
                        } else {
                            System.out.println("Nesne bulunamadı!");
                        }
                        break;

                    case "4":
                        System.out.print("Soğutulacak nesne ID'si: ");
                        String sogutmaID = scanner.nextLine();

                        KuantumNesnesi sogutmaNesnesi = null;
                        for (KuantumNesnesi n : envanter) {
                            if (n.getId().equals(sogutmaID)) {
                                sogutmaNesnesi = n;
                                break;
                            }
                        }

                        if (sogutmaNesnesi != null) {
                            // Type Checking (instanceof)
                            if (sogutmaNesnesi instanceof IKritik) {
                                ((IKritik) sogutmaNesnesi).AcilDurumSogutmasi();
                            } else {
                                System.out.println("HATA: Bu nesne soğutulamaz! Sadece kritik nesneler soğutulabilir.");
                            }
                        } else {
                            System.out.println("Nesne bulunamadı!");
                        }
                        break;

                    case "5":
                        System.out.println("Sistemden çıkılıyor...");
                        return;

                    default:
                        System.out.println("Geçersiz seçim!");
                        break;
                }
            } catch (KuantumCokusuException ex) {
                // Ekran temizleme Java konsolunda zordur, sadece mesajı basalım
                System.out.println("\n********************************************");
                System.out.println("HATA: " + ex.getMessage());
                System.out.println("SİSTEM ÇÖKTÜ! TAHLİYE BAŞLATILIYOR...");
                System.out.println("********************************************\n");
                return; // Programı sonlandır
            } catch (Exception e) {
                System.out.println("Beklenmedik bir hata: " + e.getMessage());
            }
        }
    }
}