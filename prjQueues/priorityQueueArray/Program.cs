namespace priorityQueueArray
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Queue e = new Queue();
            e.Enqueue(10, 20);
            e.Enqueue(20, 30);
            e.Enqueue(30, 40);
            e.Enqueue(45, 50);
            e.Enqueue(1, 60);

            int iPos = e.peek();
            Console.WriteLine(iPos);
            Console.WriteLine(e.pr[iPos].value);
        }
    }
}
