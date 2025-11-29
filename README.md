
# ⚛️ Kuantum Kaos Yönetimi (Omega Sektörü) ⚛️



Kuantum Kaos Yönetimi, "Omega Sektörü"ndeki Kuantum Veri Ambarı'nın simülasyonunu yapan, Nesne Yönelimli Programlama (OOP) prensiplerini pekiştirmek amacıyla 4 farklı programlama dili (C#, Java, Python, JavaScript) ile geliştirilmiş bir projedir.

Projenin amacı; kararsız ve tehlikeli maddeleri (Veri Paketi, Karanlık Madde, Anti Madde) analiz ederek, "Kuantum Çöküşü"ne (Stabilite <= 0) sebep olmadan gün sonunu getirmektir.

# 🚀 Kurulum ve Çalıştırma adımları

Bu repository tek bir çatı altında 4 farklı dili barındırır. Çalıştırmak istediğiniz dilin klasörüne giderek aşağıdaki adımları izleyebilirsiniz.

## C#

Projeyi Rider veya Visual Studio ile açıp Run diyebilirsiniz. Terminalden çalıştırmak için:

```bash
 cd KuantumKaosOdev-csharp/
 dotnet run
```

## Java 

Projeyi IntelliJ IDEA ile açıp Main sınıfını çalıştırabilirsiniz. Terminalden çalıştırmak için:

```bash
cd KuantumKaosOdev-java/src
javac Main.java
java Main
```

## Python

Projeyi PyCharm ile açabilirsiniz. moduller.py dosyasının main.py ile aynı dizinde olduğundan emin olun.
Terminalden çalıştırmak için:

```bash
cd KuantumKaosOdev-python/
python main.py
```

## JavaScript

Projeyi WebStorm veya VS Code ile açabilirsiniz.Terminalden çalıştırmak için:

```bash
cd KuantumKaosOdev-javascript/
node app.js
```


# 🧩 Proje Yapısı ve Açıklamaları

## KuantumKaosOdev

- `KuantumKaosOdev-csharp`                      --- C# Proje Dosyaları (.NET Console)
  - `Program.cs`
  - `KuantumNesnesi.cs`
  - `IKritik.cs`
  - `KuantumCokusuException.cs`
  - `VeriPaketi.cs`
  - `KaranlikMadde.cs`
  - `AntiMadde.cs`
---
- `KuantumKaosOdev-java`                        --- Java Proje Dosyaları (IntelliJ src)
  - `src/Main.java`
  - `src/KuantumNesnesi.java`
  - `src/IKritik.java`
  - `src/KuantumCokusuException.java`
  - `src/VeriPaketi.java`
  - `src/KaranlikMadde.java`
  - `src/AntiMadde.java`
---
- `KuantumKaosOdev-python`                      --- Python Script Dosyaları
  - `main.py`
  - `moduller.py`
---
- `KuantumKaosOdev-javascript`                  --- Node.js Uygulama Dosyası
  - `app.js`
---

# 🧪 Gereksinimler


  .NET SDK: 9.0+ (C# için)

  Java JDK: 21+

   Python: 3.1+

   Node.js: v18+
