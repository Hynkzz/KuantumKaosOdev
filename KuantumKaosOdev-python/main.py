import random
# moduller.py dosyasındaki her şeyi içeri alıyoruz
from moduller import *


def main():
    envanter = []  # List yapısı
    sayac = 1

    print("=== KUANTUM VERİ AMBARINA HOŞ GELDİNİZ (PYTHON) ===")

    while True:
        print("\n--- KUANTUM AMBARI KONTROL PANELİ ---")
        print("1. Yeni Nesne Ekle")
        print("2. Tüm Envanteri Listele")
        print("3. Nesneyi Analiz Et")
        print("4. Acil Durum Soğutması Yap")
        print("5. Çıkış")

        secim = input("Seçiminiz: ")

        try:
            if secim == "1":
                tur = random.randint(1, 3)
                yeni_id = f"NESNE-{sayac}"
                sayac += 1

                yeni_nesne = None
                if tur == 1:
                    yeni_nesne = VeriPaketi(yeni_id)
                elif tur == 2:
                    yeni_nesne = KaranlikMadde(yeni_id)
                else:
                    yeni_nesne = AntiMadde(yeni_id)

                envanter.append(yeni_nesne)
                print(f"{yeni_nesne.__class__.__name__} eklendi. ID: {yeni_id}")

            elif secim == "2":
                print("\n--- ENVANTER RAPORU ---")
                for nesne in envanter:
                    print(nesne.durum_bilgisi())

            elif secim == "3":
                analiz_id = input("Analiz edilecek nesne ID'si: ")
                bulunan = next((n for n in envanter if n.id == analiz_id), None)

                if bulunan:
                    bulunan.analiz_et()
                    print(f"Güncel Stabilite: {bulunan.stabilite}")
                else:
                    print("Nesne bulunamadı!")

            elif secim == "4":
                sogutma_id = input("Soğutulacak nesne ID'si: ")
                bulunan = next((n for n in envanter if n.id == sogutma_id), None)

                if bulunan:
                    if isinstance(bulunan, IKritik):
                        bulunan.acil_durum_sogutmasi()
                    else:
                        print("HATA: Bu nesne soğutulamaz! Sadece kritik nesneler soğutulabilir.")
                else:
                    print("Nesne bulunamadı!")

            elif secim == "5":
                print("Sistemden çıkılıyor...")
                break

            else:
                print("Geçersiz seçim!")

        except KuantumCokusuException as ex:
            print("\n" + "*" * 44)
            print(f"HATA: {ex}")
            print("SİSTEM ÇÖKTÜ! TAHLİYE BAŞLATILIYOR...")
            print("*" * 44 + "\n")
            break  # Programı sonlandır

        except Exception as e:
            print(f"Beklenmedik bir hata: {e}")


if __name__ == "__main__":
    main()