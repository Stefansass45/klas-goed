namespace prjDihtionary
{
    internal class Program
    {
        static void Main(string[] args)
        {
            DihtionaryManager dihtionaryManager = new DihtionaryManager();
            bool exit = false;
            while (!exit)
            {
                Console.WriteLine("\nMenu:");
                Console.WriteLine("\n1. Add Entry");
                Console.WriteLine("\n2. Search for an entry");
                Console.WriteLine("\n3. Remove an entry");
                Console.WriteLine("\n4. Display all entries");
                Console.WriteLine("\n5. Exit");
                Console.Write("\nChoose an option (1-5): ");

                string choice = Console.ReadLine();
                switch (choice)
                {
                    case "1":
                        Console.Write("Enter student name: ");
                        string nameToAdd = Console.ReadLine();
                        Console.Write("Enter student mark: ");
                        if (int.TryParse(Console.ReadLine(), out int markToAdd))
                        {
                            dihtionaryManager.Add(nameToAdd, markToAdd);
                        }
                        else
                        {
                            Console.WriteLine("Invalid mark. Please enter a valid integer.");
                        }
                        break;
                    case "2":
                        Console.Write("Enter student name to search: ");
                        string nameToSearch = Console.ReadLine();
                        int? mark = dihtionaryManager.GetMark(nameToSearch);
                        if (mark.HasValue)
                        {
                            Console.WriteLine($"Mark for {nameToSearch}: {mark.Value}");
                        }
                        break;
                    case "3":
                        Console.Write("Enter student name to remove: ");
                        string nameToRemove = Console.ReadLine();
                        dihtionaryManager.RemoveEntry(nameToRemove);
                        break;
                    case "4":
                        dihtionaryManager.DisplayEntries();
                        break;
                    case "5":
                        exit = true;
                        Console.WriteLine("Exiting the program");
                        break;
                    default:
                        Console.WriteLine("Invalid choice. Please choose a valid option (1-5).");
                        break;
                }
            }
        }
    }
}
