using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinkedListQueues
{
    class Queues
    {
        // set both front and rear pointer to null
        // indicating the queue in empty at initialization
        public QNode front, rear;
        public Queues()
        {
            this.front = this.rear = null;
        }
        public void Enqueue(int key)
        {
            QNode temp = new QNode(key);
            if (this.rear == null)
            {
                // if queue is empty, set both front and rear to new node(temp)
                // making it the dirst and only element in the queue
                this.front = this.rear = temp;
                return;
            }
            this.rear.next = temp;
            this.rear = temp;
        }
        public void Dequeue()
        {
            // if queue is empty, return null
            if (this.front == null)
            {
                // store the front node in a temporary variable
                return;
            }
            this.front = this.front.next;
            if (this.front == null)
            {
                // if front is null, set rear to null as well
                this.rear = null;
            }
        }
    }
}
