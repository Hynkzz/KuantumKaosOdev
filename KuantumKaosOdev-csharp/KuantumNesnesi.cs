namespace KuantumKaos
{
    public abstract class KuantumNesnesi
    {
        public string ID { get; set; }

        private double _stabilite;

        public double Stabilite
        {
            get { return _stabilite; }
            set
            {
                if (value > 100)
                {
                    _stabilite = 100;
                }
                else if (value <= 0)
                {
                    _stabilite = 0;
                    throw new KuantumCokusuException(ID);
                }
                else
                {
                    _stabilite = value;
                }
            }
        }

        public int TehlikeSeviyesi { get; set; }

        public KuantumNesnesi(string id, int tehlikeSeviyesi)
        {
            ID = id;
            Stabilite = 100; // Başlangıçta Stabilite 100 olur
            TehlikeSeviyesi = tehlikeSeviyesi;
        }

        public abstract void AnalizEt();

        public virtual string DurumBilgisi()
        {
            return $"ID: {ID} | Stabilite: %{Stabilite:F2} | Tehlike: {TehlikeSeviyesi}";
        }
    }
}