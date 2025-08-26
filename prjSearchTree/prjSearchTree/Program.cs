using prjSearchTree;

namespace prjSearchTree
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Tree<String> orgChart = new Tree<string>();
            orgChart.Root = new TreeNode<string> { Data = "CEO" };

            var Vp = new TreeNode<string> { Data = "VP", Parent = orgChart.Root };
            var Manager = new TreeNode<string> { Data = "Manager", Parent = orgChart.Root };
            orgChart.Root.Children.AddRange(new[] { Vp, Manager });

            var Jeff = new TreeNode<string> { Data = "Director of Jeff", Parent = Vp };
            Vp.Children.Add(Jeff);

            var John = new TreeNode<string> { Data = "John", Parent = Vp };
            Vp.Children.Add(John);

            var Kimn = new TreeNode<string> { Data = "Kimn", Parent = Manager };
            Manager.Children.Add(Kimn);

            Console.WriteLine("-----------------------------Alpha_Structure-----------------------------");
            orgChart.PrintTree(orgChart.Root);
            Console.WriteLine("-----------------------------------------------------------------------\n");

            Console.WriteLine("------------------------------------BFS----------------------------------");
            List<string> bfsResultOriginal = orgChart.BreadthFirstSearch();
            Console.WriteLine(string.Join(" -> ", bfsResultOriginal));
            Console.WriteLine("-----------------------------------------------------------------------\n");

            Console.WriteLine("----------------------------------DFS------------------------------------");
            List<string> dfsResultOriginal = orgChart.DepthFirstSearch();
            Console.WriteLine(string.Join(" -> ", dfsResultOriginal));
            Console.WriteLine("-------------------------------------------------------------------------");
            Console.ReadLine();


            Tree<string> alphaChart = new Tree<string>();
            alphaChart.Root = new TreeNode<string> { Data = "A" };

            // Level 1 children
            var J = new TreeNode<string> { Data = "J", Parent = alphaChart.Root };
            var B = new TreeNode<string> { Data = "B", Parent = alphaChart.Root };
            var T = new TreeNode<string> { Data = "T", Parent = alphaChart.Root };
            var H = new TreeNode<string> { Data = "H", Parent = alphaChart.Root };
            var G = new TreeNode<string> { Data = "G", Parent = alphaChart.Root };
            alphaChart.Root.Children.AddRange(new[] { J, B, T, H, G });

            // J's children
            var Z = new TreeNode<string> { Data = "Z", Parent = J };
            var C = new TreeNode<string> { Data = "C", Parent = J };
            var K = new TreeNode<string> { Data = "K", Parent = J };
            J.Children.AddRange(new[] { Z, C, K });

            // C's children
            var X = new TreeNode<string> { Data = "X", Parent = C };
            var N = new TreeNode<string> { Data = "N", Parent = C };
            C.Children.AddRange(new[] { X, N });

            // K's child
            var L = new TreeNode<string> { Data = "L", Parent = K };
            K.Children.Add(L);

            // B's children
            var D = new TreeNode<string> { Data = "D", Parent = B };
            var F = new TreeNode<string> { Data = "F", Parent = B };
            B.Children.AddRange(new[] { D, F });

            // D's child
            var E = new TreeNode<string> { Data = "E", Parent = D };
            D.Children.Add(E);

            // F's child
            var I = new TreeNode<string> { Data = "I", Parent = F };
            F.Children.Add(I);

            // G's child
            var M = new TreeNode<string> { Data = "M", Parent = G };
            G.Children.Add(M);

            Console.WriteLine("-----------------------------Alpha_Structure-----------------------------");
            alphaChart.PrintTree(alphaChart.Root);
            Console.WriteLine("-----------------------------------------------------------------------\n");

            Console.WriteLine("------------------------------------BFS----------------------------------");
            List<string> bfsResult = alphaChart.BreadthFirstSearch();
            Console.WriteLine(string.Join(" -> ", bfsResult));
            Console.WriteLine("-----------------------------------------------------------------------\n");

            Console.WriteLine("----------------------------------DFS------------------------------------");
            List<string> dfsResult = alphaChart.DepthFirstSearch();
            Console.WriteLine(string.Join(" -> ", dfsResult));
            Console.WriteLine("-------------------------------------------------------------------------");
            Console.ReadLine();
        }
    }
}
