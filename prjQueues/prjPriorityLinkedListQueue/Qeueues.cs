using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prjPriorityLinkedListQueue
{
    internal class Qeueues
    {
        public QPNode node = new QPNode();
        public QPNode newNode(int iData, int iRating)
        {
            QPNode temp = new QPNode();
            temp.data = iData;
            temp.ratings = iRating;
            temp.next = null;
            return temp;
        }
        public int peek(QPNode head)
        {
            return head.data;
        }
        public QPNode pop(QPNode head)
        {
            return head.next;
        }
        public QPNode push(QPNode head, int iData, int iRating)
        {
            QPNode start = head;
            QPNode temp = newNode(iData, iRating);
            if (head.ratings < iRating)
            {
                temp.next = head;
                head = temp;
            }
            else
            {
                while (start.next != null && start.next.ratings >= iRating)
                {
                    start = start.next;
                }
                temp.next = start.next;
                start.next = temp;
            }
            return head;
        }
        public int isEmpty(QPNode head)
        {
            return (head == null) ? 1 : 0;
        }
    }
}
