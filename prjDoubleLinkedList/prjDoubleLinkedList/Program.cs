using System.Text;

namespace prjDoubleLinkedList
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Blob blobgen = new Blob();
            blobgen.Add(6);
            blobgen.Add(5);
            blobgen.Add(4);
            blobgen.Add(3);
            blobgen.Add(2);
            blobgen.Add(1);
            blobgen.Add(0);
            Node head = blobgen.head;
            Node newNode = head.next.next.next.next;
            Console.WriteLine("Head Node value : " + head.value);
            Console.WriteLine("New node vlaue : " + newNode.value);
            Console.WriteLine("Total Nodes : " + CountNode(head));
            Node lastNode = GetLast(head);
            Console.WriteLine("Last Node value : " + lastNode.value);
            Node firstNode = GetFirst(head);
            Console.WriteLine("First Node value : " + firstNode.value);
            FindNodePrintAll(newNode);

        }
        public static int CountNode(Node head)
        {
            int count = 0;
            Node current = head;
            while (current != null)
            {
                count++;
                current = current.next;
            }
            return count;
        }
        public static Node GetLast(Node head)
        {
            Node current = head;
            while (current != null && current.next != null)
            {
                current = current.next;
            }
            return current;
        }
        public static Node GetFirst(Node middle)
        {
            Node current = middle;
            while (current.prev != null)
            {
                current = current.prev;
            }
            return current;
        }
        //find inside the list and indicate that spot and show whole list
        public static void FindNodePrintAll(Node current)
        {
            if (current == null)
            {
                Console.WriteLine("No {0} not found in the list", current.value);
                return;
            }
            StringBuilder result = new StringBuilder("(" + current.value + ")");
            Node NodeP = current.prev;
            while (NodeP != null)
            {
                result.Insert(0, NodeP.value + " ");
                NodeP = NodeP.prev;
            }
            current = current.next;
            while (current != null)
            {
                result.Append(" " + current.value);
                current = current.next;
            }
            Console.WriteLine(result);
            Console.WriteLine();
        }
    }
}
