using System;

namespace KuantumKaos
{
    public class VeriPaketi : KuantumNesnesi
    {
        public VeriPaketi(string id) : base(id, 1) // Tehlike seviyesi düşük
        {
        }

        public override void AnalizEt()
        {
            Stabilite -= 5;
            Console.WriteLine("Veri içeriği okundu.");
        }
    }
}