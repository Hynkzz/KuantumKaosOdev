using System;

namespace KuantumKaos
{
    public class KuantumCokusuException : Exception
    {
        public KuantumCokusuException(string nesneID) 
            : base($"{nesneID} kimlikli nesne çöktü! Kuantum dengesi bozuldu!")
        {
        }
    }
}