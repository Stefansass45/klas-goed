using System.Reflection.Metadata;
using System.Text;

namespace prjLinkList
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Node n = new Node(6);
            Node na = new Node(7);
            Node nb = new Node(8);
            n.next = na;
            na.next = nb;
            Console.WriteLine(CountFromNodes(n));
            Console.WriteLine(FindNode(8, n).value);
            Node New = new Node(90);
            nb = addNode(New, nb);
            Console.WriteLine(PrintOut(n));
            Console.WriteLine("oh naur there is " + CountFromNodes(n) + " ZOMBIES!");
        }
        public static int CountFromNodes(Node head) //or root
        {
            int count = 1;
            Node current = head;
            while (current != null)
            {
                count++;
                current = current.next;
            }
            return count;
        }
        // Find node here.
        public static Node FindNode(int vlaue, Node Head)
        {
            Node current = Head;
            while (current != null)
            {
                current = current.next;
                if (current.value == vlaue)
                {
                    return current;
                }
            }
            return null;
        }
        public static Node addNode(Node add, Node current)
        {
            current.next = add;
            return current;
        }
        public static String PrintOut(Node head)
        {
            StringBuilder sb = new StringBuilder();
            Node current = head;
            while (current != null)
            {
                sb.Append(current.value + " ");
                current = current.next;
            }
            return sb.ToString().Trim();
        }
    }
}
