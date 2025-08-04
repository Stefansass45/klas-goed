using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace priorityQueueArray
{
    internal class Queue
    {
        public item[] pr = new item[1000000];
        int size = -1;
        public void Enqueue(int value, int priority)
        {
            size++;
            pr[size] = new item();
            pr[size].value = value;
            pr[size].priority = priority;
        }
        public int peek()
        {
            int HighestPriority = int.MinValue;
            int iPos = -1;

            for (int i = 0; i <= size; i++)
            {
                if (HighestPriority == pr[i].priority && iPos > -1 && pr[iPos].value < pr[i].value)
                {
                    HighestPriority = pr[i].priority;
                    iPos = i;
                }
                else if (HighestPriority < pr[i].priority)
                {
                    HighestPriority = pr[i].priority;
                    iPos = i;
                }
            }
            return iPos;
        }
        public void Dequeue()
        {
            int iPos = peek();
            for (int i = 0; i < size; ++i)
            {
                pr[i] = pr[i + 1];
            }
            size--;
        }
    }
}