namespace GenericQueue
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Queue<string> numbers = new Queue<string>();
            numbers.Enqueue("One");
            numbers.Enqueue("Five");
            numbers.Enqueue("Two");
            numbers.Enqueue("Six");
            numbers.Enqueue("three");
            
            foreach (var number in numbers)
            {
                Console.WriteLine(number.ToString());
            }
            Console.WriteLine("Dequeueing {0}", numbers.Dequeue());
            Console.WriteLine("Peek at next item to dequeue: {0}", numbers.Peek());
            Console.WriteLine("Dequeueing {0}", numbers.Dequeue());
            Console.WriteLine();


            String[] test = { "One", "Two", "Three", "Four", "Five" };
            Queue<string> newQueue = new Queue<string>(test);
            Console.WriteLine("new Queue from an array:");
            foreach (var nan in numbers)
            {
                Console.WriteLine(nan.ToString());
            }
            Console.WriteLine("Was four found {0}",
                newQueue.Contains("three"));
            Console.WriteLine("Clear the queue");
            newQueue.Clear();
            Console.WriteLine("new Queue :{0}", newQueue.Count());

        }
    }
}
