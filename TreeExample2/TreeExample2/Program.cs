using static System.Runtime.InteropServices.JavaScript.JSType;

namespace TreeExample2
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Tree<string> FileSystemTree = new Tree<string>();
            FileSystemTree.Root = new TreeNode<string>() { Data = "C:" };

            var users = new TreeNode<string> { Data = "Users", Parent = FileSystemTree.Root };
            var programFiles = new TreeNode<string> { Data = "Program Files", Parent = FileSystemTree.Root };
            var windows = new TreeNode<string> { Data = "Windows", Parent = FileSystemTree.Root };

            FileSystemTree.Root.Children.Add(users);
            FileSystemTree.Root.Children.Add(programFiles);
            FileSystemTree.Root.Children.Add(windows);

            var alice = new TreeNode<string> { Data = "Alice", Parent = users };
            users.Children.Add(alice);

            var documents = new TreeNode<string> { Data = "Documents", Parent = alice };
            var report = new TreeNode<string> { Data = "report.docx", Parent = documents };
            users.Children.Add(report);
            users.Children.Add(documents);

            Console.WriteLine("--- File System Sructure ---");
            FileSystemTree.PrintTree(FileSystemTree.Root);
            Console.WriteLine("\n");

            Console.WriteLine("--- Finding 'report.docx' and highlighting it ---");
            TreeNode<string> foundNode = FileSystemTree.FindNode(FileSystemTree.Root, "report.docx");

            if(foundNode != null)
            {
                FileSystemTree.PrintTree(FileSystemTree.Root, foundNode);
            }
            else
            {
                Console.WriteLine("file not found.");
            }
        }
    }
}