const readline = require('readline');

// Kullanıcıdan veri almak için arayüz
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function soruSor(soru) {
    return new Promise((resolve) => {
        rl.question(soru, (cevap) => {
            resolve(cevap);
        });
    });
}

// 1. Hata Yönetimi

class KuantumCokusuException extends Error {
    constructor(nesneID) {
        super(`${nesneID} kimlikli nesne çöktü! Kuantum dengesi bozuldu!`);
        this.name = "KuantumCokusuException";
    }
}

// 2. Soyut Ana Sınıf

class KuantumNesnesi {
    constructor(id, tehlikeSeviyesi) {
        // Abstract Class Kontrolü (JS'de abstract olmadığı için böyle simüle edilir)
        if (this.constructor === KuantumNesnesi) {
            throw new Error("Soyut sınıf (KuantumNesnesi) new ile türetilemez!");
        }
        this.id = id;
        this.tehlikeSeviyesi = tehlikeSeviyesi;
        this._stabilite = 100.0; // Private alan simülasyonu
    }

    // Encapsulation (Kapsülleme) - Getter

    get stabilite() {
        return this._stabilite;
    }

    // Encapsulation - Setter

    set stabilite(deger) {
        if (deger > 100) {
            this._stabilite = 100;
        } else if (deger <= 0) {
            this._stabilite = 0;
            // Hata fırlat
            throw new KuantumCokusuException(this.id);
        } else {
            this._stabilite = deger;
        }
    }

    // Soyut Metot Simülasyonu

    analizEt() {
        throw new Error("Bu metot alt sınıflarda override edilmelidir!");
    }

    durumBilgisi() {
        return `ID: ${this.id} | Stabilite: %${this._stabilite.toFixed(2)} | Tehlike: ${this.tehlikeSeviyesi}`;
    }
}


// 3. Alt Sınıflar (Inheritance)

// --- Veri Paketi ---
class VeriPaketi extends KuantumNesnesi {
    constructor(id) {
        super(id, 1); // Tehlike: 1
    }

    analizEt() {
        this.stabilite -= 5;
        console.log("Veri içeriği okundu.");
    }
}

// --- Karanlık Madde (IKritik Simülasyonu) ---
class KaranlikMadde extends KuantumNesnesi {
    constructor(id) {
        super(id, 5); // Tehlike: 5
    }

    analizEt() {
        this.stabilite -= 15;
    }

    // IKritik metodu (Interface implementasyonu)
    acilDurumSogutmasi() {
        try {
            this.stabilite += 50;
            console.log(`${this.id} soğutuldu. Stabilite arttı.`);
        } catch (e) {
        }
    }
}

// --- Anti Madde ---

class AntiMadde extends KuantumNesnesi {
    constructor(id) {
        super(id, 10); // Tehlike: 10
    }

    analizEt() {
        this.stabilite -= 25;
        console.log("Evrenin dokusu titriyor...");
    }

    // IKritik metodu
    acilDurumSogutmasi() {
        try {
            this.stabilite += 50;
            console.log(`${this.id} üzerine acil durum soğutucusu sıkıldı!`);
        } catch (e) {
            // Hata yok sayılabilir
        }
    }
}


// 4. Ana Program Döngüsü (Main Loop)

async function main() { // Async fonksiyon çünkü input bekleyeceğiz
    const envanter = []; // List yerine Array
    let sayac = 1;

    console.log("=== KUANTUM VERİ AMBARINA HOŞ GELDİNİZ (NODE.JS) ===");

    while (true) {
        console.log("\n--- KUANTUM AMBARI KONTROL PANELİ ---");
        console.log("1. Yeni Nesne Ekle");
        console.log("2. Tüm Envanteri Listele");
        console.log("3. Nesneyi Analiz Et");
        console.log("4. Acil Durum Soğutması Yap");
        console.log("5. Çıkış");

        // Kullanıcıdan seçim al (await ile bekler)
        const secim = await soruSor("Seçiminiz: ");

        try {
            switch (secim) {
                case "1":
                    // Rastgele seçim (1-3 arası)
                    const tur = Math.floor(Math.random() * 3) + 1;
                    let yeniNesne = null;
                    const yeniID = `NESNE-${sayac++}`;

                    if (tur === 1) yeniNesne = new VeriPaketi(yeniID);
                    else if (tur === 2) yeniNesne = new KaranlikMadde(yeniID);
                    else yeniNesne = new AntiMadde(yeniID);

                    envanter.push(yeniNesne);
                    console.log(`${yeniNesne.constructor.name} eklendi. ID: ${yeniID}`);
                    break;

                case "2":
                    console.log("\n--- ENVANTER RAPORU ---");
                    // Polymorphism: Her nesne kendi durumBilgisi metodunu kullanır
                    envanter.forEach(nesne => {
                        console.log(nesne.durumBilgisi());
                    });
                    break;

                case "3":
                    const analizID = await soruSor("Analiz edilecek nesne ID'si: ");
                    const analizNesnesi = envanter.find(n => n.id === analizID);

                    if (analizNesnesi) {
                        analizNesnesi.analizEt();
                        console.log(`Güncel Stabilite: ${analizNesnesi.stabilite}`);
                    } else {
                        console.log("Nesne bulunamadı!");
                    }
                    break;

                case "4":
                    const sogutmaID = await soruSor("Soğutulacak nesne ID'si: ");
                    const sogutmaNesnesi = envanter.find(n => n.id === sogutmaID);

                    if (sogutmaNesnesi) {
                        if (typeof sogutmaNesnesi.acilDurumSogutmasi === 'function') {
                            sogutmaNesnesi.acilDurumSogutmasi();
                        } else {
                            // VeriPaketi buraya düşer
                            console.log("HATA: Bu nesne soğutulamaz! Sadece kritik nesneler soğutulabilir.");
                        }
                    } else {
                        console.log("Nesne bulunamadı!");
                    }
                    break;

                case "5":
                    console.log("Sistemden çıkılıyor...");
                    rl.close();
                    return; // Programdan çık

                default:
                    console.log("Geçersiz seçim!");
                    break;
            }
        } catch (error) {
            // Game Over Senaryosu
            if (error instanceof KuantumCokusuException) {
                console.clear(); // Konsolu temizlemeye çalışır
                console.log("\n********************************************");
                console.log(`HATA: ${error.message}`);
                console.log("SİSTEM ÇÖKTÜ! TAHLİYE BAŞLATILIYOR...");
                console.log("********************************************\n");
                rl.close();
                process.exit(1); // Programı hata koduyla bitir
            } else {
                console.log(`Beklenmedik bir hata: ${error.message}`);
            }
        }
    }
}

// Programı başlat
main();