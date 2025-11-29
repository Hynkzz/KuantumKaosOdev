using System;

namespace KuantumKaos
{
    public class AntiMadde : KuantumNesnesi, IKritik
    {
        public AntiMadde(string id) : base(id, 10) // Maksimum tehlike
        {
        }

        public override void AnalizEt()
        {
            Stabilite -= 25;
            Console.WriteLine("Evrenin dokusu titriyor...");
        }

        public void AcilDurumSogutmasi()
        {
            Stabilite += 50;
            Console.WriteLine($"{ID} üzerine acil durum soğutucusu sıkıldı!");
        }
    }
}