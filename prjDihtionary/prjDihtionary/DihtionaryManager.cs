using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prjDihtionary
{
    internal class DihtionaryManager
    {
        private Dictionary<string, int> _dihPROG7311;
        public DihtionaryManager()
        {
            _dihPROG7311 = new Dictionary<string, int>();
        }
        public void Add(string Name, int Mark)
        {
            if (_dihPROG7311.ContainsKey(Name))
            {
                _dihPROG7311.Add(Name, Mark);
            }
            else
            {
                Console.WriteLine($"Student '{Name}' already exists with value {_dihPROG7311}");
            }
        }
        public int? GetMark(string Name)
        {
            if (_dihPROG7311.TryGetValue(Name, out int Mark))
            {
                return Mark;
            }
            else
            {
                Console.WriteLine($"Student '{Name}' not found.");
                return null;
            }
        }
        public bool RemoveEntry(string Name)
        {
            if (_dihPROG7311.Remove(Name))
            {
                Console.WriteLine($"Student '{Name}' removed successfully");
                return true;
            }
            else
            {
                Console.WriteLine($"Student '{Name}' not found.");
                return false;
            }
        }
        public void DisplayEntries()
        {
            if (_dihPROG7311.Count == 0)
            {
                Console.WriteLine("No entries in the dictionary.");
                return;
            }
            Console.WriteLine("Current entries in the dictionary:");
            foreach (var entry in _dihPROG7311)
            {
                Console.WriteLine($"Name: {entry.Key}, Mark: {entry.Value}");
            }
        }
    }
}
