from abc import ABC, abstractmethod

# 1. Özel Hata Sınıfı
class KuantumCokusuException(Exception):
    def __init__(self, nesne_id):
        super().__init__(f"{nesne_id} kimlikli nesne çöktü! Kuantum dengesi bozuldu!")

# 2. Interface
class IKritik(ABC):
    @abstractmethod
    def acil_durum_sogutmasi(self):
        pass

# 3. Soyut Ana Sınıf
class KuantumNesnesi(ABC):
    def __init__(self, id, tehlike_seviyesi):
        self.id = id
        self.tehlike_seviyesi = tehlike_seviyesi
        self._stabilite = 100.0  # Private değişken (_ ile başlar)

    # Encapsulation (Kapsülleme) - Getter
    @property
    def stabilite(self):
        return self._stabilite

    # Encapsulation - Setter
    @stabilite.setter
    def stabilite(self, deger):
        if deger > 100:
            self._stabilite = 100
        elif deger <= 0:
            self._stabilite = 0

            raise KuantumCokusuException(self.id)     # Hata fırlatır
        else:
            self._stabilite = deger

    # Soyut Metot
    @abstractmethod
    def analiz_et(self):
        pass

    def durum_bilgisi(self):
        return f"ID: {self.id} | Stabilite: %{self.stabilite:.2f} | Tehlike: {self.tehlike_seviyesi}"

# 4. Somut Sınıflar
class VeriPaketi(KuantumNesnesi):
    def __init__(self, id):
        super().__init__(id, 1) # Tehlike: 1

    def analiz_et(self):
        # Property setter tetiklenir
        self.stabilite -= 5
        print("Veri içeriği okundu.")

class KaranlikMadde(KuantumNesnesi, IKritik):
    def __init__(self, id):
        super().__init__(id, 5) # Tehlike: 5

    def analiz_et(self):
        self.stabilite -= 15

    def acil_durum_sogutmasi(self):
        # Setter hatası yakalanmasa da olur ama mantıken try blok içinde artırmak güvenli
        try:
            self.stabilite += 50
            print(f"{self.id} soğutuldu. Stabilite arttı.")
        except KuantumCokusuException:
            pass

class AntiMadde(KuantumNesnesi, IKritik):
    def __init__(self, id):
        super().__init__(id, 10) # Tehlike: 10

    def analiz_et(self):
        self.stabilite -= 25
        print("Evrenin dokusu titriyor...")

    def acil_durum_sogutmasi(self):
        try:
            self.stabilite += 50
            print(f"{self.id} üzerine acil durum soğutucusu sıkıldı!")
        except KuantumCokusuException:
            pass