namespace KuantumKaos
{
    public class KaranlikMadde : KuantumNesnesi, IKritik
    {
        public KaranlikMadde(string id) : base(id, 5) // Orta tehlike
        {
        }

        public override void AnalizEt()
        {
            Stabilite -= 15;
        }

        public void AcilDurumSogutmasi()
        {
            Stabilite += 50;
            System.Console.WriteLine($"{ID} soğutuldu. Stabilite arttı.");
        }
    }
}