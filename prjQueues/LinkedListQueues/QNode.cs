using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedListQueues
{
    internal class QNode
    {
        public int Key;
        public QNode next;
        public QNode(int key)
        {
            this.Key = key;
            this.next = null;
        }
    }
}
