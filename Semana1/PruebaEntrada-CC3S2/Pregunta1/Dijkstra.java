import java.util.*;

public class Dijkstra {

    private int distancia[];
    private Set<Integer> settled;
    private PriorityQueue<Node> cola_prioridad; // Se usara una cola de prioridad para almacenar la distancia mas corta
    private int vertice; // Cantidad de vertices que se utilizaran durante el algoritmo
    List<List<Node> > adj;

    // Constructor de la clase Dijkstra
    public Dijkstra(int vertice)
    {
        this.vertice = vertice; // Asignamos la cantidad de vertices
        distancia = new int[vertice]; //
        settled = new HashSet<Integer>();
        cola_prioridad = new PriorityQueue<Node>(vertice, new Node());
    }

    // Method 1
    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < vertice; i++) // todos los nodos comiezan con la distancia maxima
            distancia[i] = Integer.MAX_VALUE; // Empieza el camino con el manimo maximo, se modificara esto en la ejeucion del codigo

        cola_prioridad.add(new Node(src, 0)); //PAra añadir el nuevo nodo a la cola de prioridad

        distancia[src] = 0;

        while (settled.size() != vertice) {

            if (cola_prioridad.isEmpty())
                return;

            int u = cola_prioridad.remove().node; // Removemos el nodo de cola de prioridad

            if (settled.contains(u))
                continue;

            settled.add(u);

            e_Neighbours(u);
        }
    }

    private void e_Neighbours(int u)
    {

        int edgeDistance = -1;
        int newDistance = -1;

        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = distancia[u] + edgeDistance; // Para  ir sumando las distancias de los nodos encontrados

                if (newDistance < distancia[v.node]) // Si la existe otra distancia menor se reeemplaza a este nuevo camino con la nueva distancia
                    distancia[v.node] = newDistance;

                cola_prioridad.add(new Node(v.node, distancia[v.node]));// se actualiza la cola de prioridad
            }
        }
    }

    public static void main(String arg[])
    {

        int V = 5;
        int source = 0;

        List<List<Node> > adj
                = new ArrayList<List<Node> >();

        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Nodos y vertices especificos
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));
        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        Dijkstra dpq = new Dijkstra(V);
        dpq.dijkstra(adj, source);

        System.out.println("El camino corto desde el nodo :");

        for (int i = 0; i < dpq.distancia.length; i++)
            System.out.println(source + " a " + i + " es "
                    + dpq.distancia[i]);
    }
}

class Node implements Comparator<Node> {

    public int node;
    public int cost;

    // Constructor 1
    public Node() {}

    // Constructor 2
    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost) // para agregar el nodo de menor costo
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }
}

