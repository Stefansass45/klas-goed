using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prjSearchTree
{
    class Tree<T>
    {
        public TreeNode<T> Root { get; set; }
        public void PrintTree(TreeNode<T> node, string indent = "", bool last = true)
        {
            if (node == null) return;
            Console.WriteLine($"{indent} +- {node.Data}");
            indent += last ? "   " : "|  ";
            for (int i = 0; i < node.Children.Count; i++)
            {
                PrintTree(node.Children[i], indent, i == node.Children.Count - 1);
            }
        }
        public TreeNode<T> FindNode(TreeNode<T> node, T value)
        {
            if (node == null) return null;
            if (node.Data.Equals(value))
                return node;
            foreach (var child in node.Children)
            {
                var result = FindNode(child, value);
                if (result != null)
                    return result;
            }
            return null;
        }
        public void PrintTree(TreeNode<T> node, TreeNode<T> highlightNode, string indent = "", bool last = true)
        {
            if (node == null) return;
            if (node.Equals(highlightNode))
                Console.WriteLine($"{indent} +- [{node.Data}]");
            else
                Console.WriteLine($"{indent} +- {node.Data}");
            indent += last ? "   " : "|  ";
            for (int i = 0; i < node.Children.Count; i++)
            {
                PrintTree(node.Children[i], highlightNode, indent, i == node.Children.Count - 1);
            }
        }
        // Breadth-First Search (BFS) implementation
        public List<T> BreadthFirstSearch()
        {
            if (Root == null)
            {
                return new List<T>();
            }
            var visited = new List<T>();
            var queue = new Queue<TreeNode<T>>();
            queue.Enqueue(Root);
            while (queue.Count > 0)
            {
                TreeNode<T> currentNode = queue.Dequeue();
                visited.Add(currentNode.Data);
                foreach (var child in currentNode.Children)
                {
                    queue.Enqueue(child);
                }
            }
            return visited;
        }
        // Depth-First Search (DFS) implementation
        public void DFSHelper(TreeNode<T> node, List<T> visited)
        {
            if (node == null) return;
            visited.Add(node.Data);
            foreach (var child in node.Children)
            {
                DFSHelper(child, visited);
            }
        }

        // Helper method to initiate DFS
        public List<T> DepthFirstSearch()
        {
            if (Root == null)
            {
                return new List<T>();
            }
            var visited = new List<T>();
            DFSHelper(Root, visited);
            return visited;
        }
    }
}
