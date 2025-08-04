using System.Collections;

namespace prjQueues
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Queue BCADQueue = new Queue();
            BCADQueue.Enqueue("Ali");
            BCADQueue.Enqueue("Jess");
            BCADQueue.Enqueue("Chris");
            Console.Write("The BCADQu");
            Console.Write("\t Count: {0}", BCADQueue.Count);
            Console.Write("\t Values: \n");
            PrintValues(BCADQueue);
            Console.WriteLine(BCADQueue.Dequeue().ToString());
            PrintValues(BCADQueue);
            BCADQueue.Enqueue("Ali");
            PrintValues(BCADQueue);
            Console.WriteLine(BCADQueue.Dequeue().ToString());
            PrintValues(BCADQueue);

        }
        private static void PrintValues(Queue queue)
        {
            foreach (var item in queue)
            {
                Console.Write("\t{0}", item);
            }
            Console.WriteLine();
        }
    }
}
