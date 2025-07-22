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
        // Find node here please. (im doing this alone)
        public static Node FindNode(int vlaue, Node Head)
        {

        }
    }
}
