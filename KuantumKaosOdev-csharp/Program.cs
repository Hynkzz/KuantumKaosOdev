using System;
using System.Collections.Generic;

namespace KuantumKaos
{
    class Program
    {
        static void Main(string[] args)
        {
            List<KuantumNesnesi> envanter = new List<KuantumNesnesi>();
            Random rnd = new Random();
            int sayac = 1; // ID üretmek için basit sayaç

            Console.WriteLine("=== KUANTUM VERİ AMBARINA HOŞ GELDİNİZ ===");

            while (true)
            {
                Console.WriteLine("\n--- KUANTUM AMBARI KONTROL PANELİ ---");
                Console.WriteLine("1. Yeni Nesne Ekle");
                Console.WriteLine("2. Tüm Envanteri Listele");
                Console.WriteLine("3. Nesneyi Analiz Et");
                Console.WriteLine("4. Acil Durum Soğutması Yap");
                Console.WriteLine("5. Çıkış");
                Console.Write("Seçiminiz: ");

                string secim = Console.ReadLine();

                try
                {
                    switch (secim)
                    {
                        case "1":
                            int tur = rnd.Next(1, 4); // 1, 2 veya 3
                            KuantumNesnesi yeniNesne = null;
                            string yeniID = "NESNE-" + sayac++;

                            if (tur == 1) yeniNesne = new VeriPaketi(yeniID);
                            else if (tur == 2) yeniNesne = new KaranlikMadde(yeniID);
                            else yeniNesne = new AntiMadde(yeniID);

                            envanter.Add(yeniNesne);
                            Console.WriteLine($"{yeniNesne.GetType().Name} eklendi. ID: {yeniID}");
                            break;

                        case "2":
                            Console.WriteLine("\n--- ENVANTER RAPORU ---");
                            foreach (var nesne in envanter)
                            {
                                Console.WriteLine(nesne.DurumBilgisi());
                            }
                            break;

                        case "3":
                            Console.Write("Analiz edilecek nesne ID'si: ");
                            string analizID = Console.ReadLine();
                            var analizNesnesi = envanter.Find(n => n.ID == analizID);

                            if (analizNesnesi != null)
                            {
                                analizNesnesi.AnalizEt();
                                Console.WriteLine($"Güncel Stabilite: {analizNesnesi.Stabilite}");
                            }
                            else
                            {
                                Console.WriteLine("Nesne bulunamadı!");
                            }
                            break;

                        case "4":
                            Console.Write("Soğutulacak nesne ID'si: ");
                            string sogutmaID = Console.ReadLine();
                            var sogutmaNesnesi = envanter.Find(n => n.ID == sogutmaID);

                            if (sogutmaNesnesi != null)
                            {
                                if (sogutmaNesnesi is IKritik kritikNesne)
                                {
                                    kritikNesne.AcilDurumSogutmasi();
                                }
                                else
                                {
                                    Console.WriteLine("HATA: Bu nesne soğutulamaz! Sadece kritik nesneler soğutulabilir.");
                                }
                            }
                            else
                            {
                                Console.WriteLine("Nesne bulunamadı!");
                            }
                            break;

                        case "5":
                            Console.WriteLine("Sistemden çıkılıyor...");
                            return;

                        default:
                            Console.WriteLine("Geçersiz seçim!");
                            break;
                    }
                }
                catch (KuantumCokusuException ex)
                {
                    Console.Clear(); // Ekranı temizle
                    Console.BackgroundColor = ConsoleColor.Red;
                    Console.ForegroundColor = ConsoleColor.White;
                    Console.WriteLine("\n********************************************");
                    Console.WriteLine($"HATA: {ex.Message}");
                    Console.WriteLine("SİSTEM ÇÖKTÜ! TAHLİYE BAŞLATILIYOR...");
                    Console.WriteLine("********************************************\n");
                    Console.ResetColor();
                    return; // Programı sonlandır
                }
                catch (Exception genelHata)
                {
                    Console.WriteLine($"Beklenmedik bir hata oluştu: {genelHata.Message}");
                }
            }
        }
    }
}