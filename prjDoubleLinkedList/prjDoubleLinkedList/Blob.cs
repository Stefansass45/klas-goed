using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prjDoubleLinkedList
{
    internal class Blob
    {
        public Node head;
        public void Add(int number)
        {
            Node newNode = new Node(number);
            if (number == null)
            {
                return;
            }
            else if (head == null)
            {
                head = new Node(number);
                return;
            }
            else
            {
                Node n = new Node(number);
                Node current = head;
                while (current.next != null)
                {
                    current = current.next;
                }
                n.prev = current;
                current.next = n;
            }
        }
    }
}
