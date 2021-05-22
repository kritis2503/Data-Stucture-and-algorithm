import java.util.*;
public class kruskal {
    public static class Edge{
        int v;
        int w;
        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }
    public static void addEdge(ArrayList<Edge>[]graph,int u,int v,int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }
    public static void display(int N,ArrayList<Edge>[]graph){
        for(int i=0;i<N;i++){
            System.out.print(i+"->");
            for(Edge e:graph[i]){
                System.out.print("("+e.v+","+e.w+")");
            }
            System.out.println();
        }
    }
    public static int []par;
    public static int []size;
    public static int findPar(int u){
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
        
    public static void merge(int p1,int p2){
        if(size[p1]>size[p2]){
            par[p2]=p1;
            size[p1]+=size[p2];
        }else{
            par[p1]=p2;
            size[p2]+=size[p1];
        }
    }
    //time complexity of kruskal algo is:
    //1-Elog2E+V+Elog2*V
    //2-Elog2E+V+Ealpha(V)
    //3-O(Elog2E)
    //4-e=v2(for dense graph= every vertex is connected to every other vertex.)
    //kruskal algo is used to find the minimum/maximum spanning tree.
    //spanning tree is same as hamiltion path but without cycle.
    //For finding MST,the graph should be connected.
    public static void kruskalAlgo(int N,int [][]edges){
        ArrayList<Edge>[]graph=new ArrayList[N];
        for(int i=0;i<N;i++)
            graph[i]=new ArrayList<>();
        
        par=new int [N];
        size=new int [N];
        int wt=0;
        for(int i=0;i<N;i++){
            par[i]=i;
            size[i]=1;
        }
        for(int []edge:edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
            int p1=findPar(u);
            int p2=findPar(v);
            if(p1!=p2){
                merge(p1, p2);
                addEdge(graph, u, v, w);
                wt+=w;
            }
        }
        System.out.println(wt);
        display(N,graph);
    }
    public static void constructGraph(ArrayList<Edge>[]graph){
        graph[0].add(new Edge(1, 4));
        graph[0].add(new Edge(7, 8));
        graph[1].add(new Edge(2, 8));
        graph[1].add(new Edge(7, 11));
        graph[2].add(new Edge(5, 4));
        graph[2].add(new Edge(8, 2));
        graph[3].add(new Edge(4, 9));
        graph[3].add(new Edge(5, 14));
        graph[4].add(new Edge(5, 10));
        graph[5].add(new Edge(6, 2));
        graph[6].add(new Edge(7, 1));
        graph[6].add(new Edge(6, 8));
        graph[7].add(new Edge(8, 7));
    }
    public static void main(String[] args) {
        int N=9;
        // ArrayList<Edge>[]graph=new ArrayList[N];
        // for(int i=0;i<N;i++)
            // graph[i]=new ArrayList<>();
        int [][]edges={ {0,1,4},{1,7,9},
                        {1,2,8},{1,7,11},
                        {3,4,9},{3,5,14},
                        {2,5,4},{2,8,2},
                        {4,5,10},
                        {5,6,2},
                        {6,7,1},{6,8,6},
                        {7,8,7}};
        // constructGraph(graph);

        //by default c++ sort is decreasing but we have a comparator that makes it increseing so we when we write
        //comparator we have to write of we wrote for java. it also needs boolean as compareed to java which
        //requires a integer.
        //-1 smaller,0 equal, 1 greater
        Arrays.sort(edges,(a,b)->{ //this-other
            return a[2]-b[2];
        });
        kruskalAlgo(N, edges);
    }
}
