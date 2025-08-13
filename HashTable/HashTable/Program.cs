namespace HashTable
{
    internal class Program
    {
        static void Main(string[] args)
        {
            HashTable infTable = new HashTable(10);
            infTable.Add("Cool_tech", 
                new Influencer("Cool_Tech", 500, 7.5, "check out my latest insta post!"));
            infTable.Add("Tech-guru", new Influencer("Tech-guru", 1500000, 0.11113, "bEST TECH ON EARTH AND THE...."));
            Influencer influencer = infTable.Get("Tech-guru");
            if (influencer != null)
            {
                Console.WriteLine(influencer);
            }
            else
            {
                Console.WriteLine("Influencer not found.");
            }
        }
    }
}
