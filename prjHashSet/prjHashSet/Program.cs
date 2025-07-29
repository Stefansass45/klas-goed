namespace prjHashSet
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //create a HashSet to store unique superhero names
            HashSet<string> superheroes = new HashSet<string>();

            //add superhero names to the HashSet
            superheroes.Add("Super?");
            superheroes.Add("Wonder IT");
            superheroes.Add("Batthey");
            superheroes.Add("It Flash");
            superheroes.Add("Aquait");

            //attempt to add a duplicate superhero name
            bool added = superheroes.Add("Super?");
            Console.WriteLine($"Was Super? added again? {added}");

            //Displa all superhero in the set
            Console.WriteLine("\nList of Superheroes:");
            foreach (string hero in superheroes)
            {
                Console.WriteLine(hero);
            }

            //Check if a specific superhero exists in the set
            bool containsFlash = superheroes.Contains("The Flash");
            Console.WriteLine($"\nIs It Flash in the set? {containsFlash}");

            //Clear all superheroes from the set
            superheroes.Clear();
            Console.WriteLine("\n All Heroes have been removed");

            //Check the count of superheroes in the set
            Console.WriteLine($"Number of superheroes in the set: {superheroes.Count}");
        }
    }
}
