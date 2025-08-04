using System.Threading.Channels;

namespace LinkedListQueues
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Queues q = new Queues();
            q.Enqueue(10);
            q.Enqueue(20);
            q.Enqueue(30);
            q.Enqueue(40);
            q.Dequeue();
            // print the key at the front of the queue id it exists; otherwuse, print -1
            Console.WriteLine("Queue front : " + ((q.front != null) ? (q.front).Key: -1));
            // print the key at the rear of the queue if it exists; otherwise, print -1
            Console.WriteLine("Queue front : " + ((q.rear != null) ? (q.rear).Key : -1));
        }
    }
}
