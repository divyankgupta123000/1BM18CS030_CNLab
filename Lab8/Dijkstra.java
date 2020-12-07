package CNLab;
import java.util.*;
 
class Edge
{
    int source, dest, weight;
 
    public Edge() {
        Scanner s = new Scanner(System.in);
        source = s.nextInt();
        dest = s.nextInt();
        weight = s.nextInt();
    }
}

class Node {
    int vertex, weight;
 
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
 
class Graph
{
    List<List<Edge>> adjList = null;
 
    Graph(List<Edge> edges, int N)
    {
        adjList = new ArrayList<>();
 
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
 
        for (Edge edge: edges) {
            adjList.get(edge.source).add(edge);
        }
    }
}
 
class Dijkstra
{
    private static void getRoute(int[] prev, int i, List<Integer> route)
    {
        if (i >= 0) {
            getRoute(prev, prev[i], route);
            route.add(i);
        }
    }
 
    public static void shortestPath(Graph graph, int source, int N)
    {
        PriorityQueue<Node> minHeap;
        minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(source, 0));

        List<Integer> dist = new ArrayList<>(Collections.nCopies(N, Integer.MAX_VALUE));
 
        dist.set(source, 0);

        boolean[] done = new boolean[N];
        done[source] = true;
 
        int[] prev = new int[N];
        prev[source] = -1;
 
        List<Integer> route = new ArrayList<>();

        while (!minHeap.isEmpty())
        {
            Node node = minHeap.poll();

            int u = node.vertex;
 
            for (Edge edge: graph.adjList.get(u))
            {
                int v = edge.dest;
                int weight = edge.weight;
 
                if (!done[v] && (dist.get(u) + weight) < dist.get(v))
                {
                    dist.set(v, dist.get(u) + weight);
                    prev[v] = u;
                    minHeap.add(new Node(v, dist.get(v)));
                }
            }

            done[u] = true;
        }
 
        for (int i = 1; i < N; ++i)
        {
            if (i != source && dist.get(i) != Integer.MAX_VALUE) {
                getRoute(prev, i, route);
                System.out.printf("Path (%d -> %d): Minimum Cost = %d and Route is %s",
                                source, i, dist.get(i), route);
                System.out.println();
                route.clear();
            }
        }
    }
 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total number of vertex: ");
        int N = sc.nextInt();
        System.out.println("Enter total number of edges: ");
        int e = sc.nextInt();
        Edge a[] = new Edge[e]; 
        System.out.println("Enter vertex1, vertex2 and distance between both the vertices: ");
        for(int i = 0; i < e; i++)
        {
            a[i] = new Edge();
        }      
        List<Edge> edges = Arrays.asList(a);
        Graph graph = new Graph(edges, N);
        int source = 0;
        shortestPath(graph, source, N);
    }
}
