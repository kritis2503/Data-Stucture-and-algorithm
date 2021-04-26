import java.util.*;
public class unionfind {
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
    public static void unionFind(int N,int [][]edges){
        ArrayList<Edge>[]graph=new ArrayList[N];
        for(int i=0;i<N;i++)
            graph[i]=new ArrayList<>();
        
        par=new int [N];
        size=new int [N];
        for(int i=0;i<N;i++){
            par[i]=i;
            size[i]=1;
        }

        boolean cycle=false;
        for(int []edge:edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];

            int p1=findPar(u);
            int p2=findPar(v);

            if(p1!=p2){
                merge(p1, p2);
                addEdge(graph, u, v, w);
            }
            else{
                cycle=true;
            }
        }
        System.out.println(cycle);
        display(N,graph);
    }
    //spanning treee
    //leetcode 1061 important
    public static void main(String[] args) {
        
    }
}
