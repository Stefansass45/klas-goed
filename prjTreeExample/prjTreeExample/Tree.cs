using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace prjTreeExample
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
        public void PrintTree(TreeNode<T> node, TreeNode<T> highlightNode, string indent="", bool last = true)
        {
            if(node == null) return;
            if (node.Equals(highlightNode))
                    Console.WriteLine($"{indent} +- [{node.Data}]");
            else
                Console.WriteLine($"{indent} +- {node.Data}");
            indent += last ? "   " : "|  ";
            for(int i = 0;i< node.Children.Count; i++)
            {
                PrintTree(node.Children[i], highlightNode, indent, i == node.Children.Count - 1);
            }
        }
    }
}
