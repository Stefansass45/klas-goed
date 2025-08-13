namespace prjTreeExample
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Tree<int> tree = new Tree<int>();
            tree.Root = new TreeNode<int>();

            tree.Root.Children = new List<TreeNode<int>>
            {
                new TreeNode<int> { Data = 100, Parent = tree.Root },
                new TreeNode<int> { Data = 1, Parent = tree.Root },
                new TreeNode<int> { Data = 150, Parent = tree.Root }
            };
            tree.Root.Children[2].Children = new List<TreeNode<int>>
            {
                new TreeNode<int> { Data = 30, Parent = tree.Root.Children[2] },
            };
            tree.Root.Children[0].Children = new List<TreeNode<int>>
            {
                new TreeNode<int> { Data = 8, Parent = tree.Root.Children[0] },
                new TreeNode<int> { Data = 11, Parent = tree.Root.Children[0] }
            };
            tree.Root.Children.Add(new TreeNode<int>() { Data = 70, Parent = tree.Root });

            tree.PrintTree(tree.Root);
            Console.WriteLine("Data of child: " + tree.Root.Children[0].Children[1].Data.ToString());
            Console.WriteLine(" ");
            Console.WriteLine(" ");
            TreeNode<int> Node = tree.FindNode(tree.Root, 1);
            tree.PrintTree(tree.Root, Node);
        }
    }
}
