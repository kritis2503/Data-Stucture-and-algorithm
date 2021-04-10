import java.util.*;

public class introo{
    public static class Edge{
        int v=0;
        int w=0;

        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }

    static int N=7;
    static ArrayList<Edge>[]graph=new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void constructGraph(){
        for(int i=0;i<N;i++)
            graph[i]=new ArrayList<>();
        
        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
    }
    
    public static void display(){
        for(int i=0;i<N;i++){
            System.out.print(i+"->");
            for(Edge e:graph[i]){
                System.out.print("("+e.v+","+e.w+")");
            }
            System.out.println();
        }
    }

    public static int findEdge(int u,int v){
        int idx=-1;
        for(int i=0;i<graph[u].size();i++){
            if(graph[u].get(i).v==v){
                idx=i;
                break;
            }
        }
        return idx;
    }

    public static void removeEdge(int u,int v){
        int idx1=findEdge(u, v);
        int idx2=findEdge(v, u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }

    public static void removeVtx(int u){
        for(int i=graph[u].size()-1;i>=0;i--){
            removeEdge(u, graph[u].get(i).v);
        }
    }

    public static boolean hasPath(int src,int dest,boolean []vis){
        if(src==dest)
            return true;
        vis[src]=true;
        boolean res=false;

        for(Edge e:graph[src]){
            if(!vis[e.v])
                res=res||hasPath(e.v, dest, vis);
        }
        return res;
    }

    public static int printAllpath(int src,int dest,boolean[]vis,String psf){
        if(src==dest){
            System.out.println(dest+psf+"");
            return 1;
        }
        vis[src]=true;
        int ans=0;
        for(Edge e:graph[src]){
            if(!vis[e.v])
            ans+=printAllpath(e.v, dest, vis, psf+" "+src+"");
        }
        vis[src]=false;
        return ans;
    }

    public static class pair{
        int w=0;
        String str="";

        pair(int w,String str){
            this.w=w;
            this.str=str;
        }
    }

    public static pair heavyPath(int src,int dest, boolean []vis){
        if(src==dest){
            pair base=new pair(0,dest+"");
            return base;
        }
        vis[src]=true;
        pair myAns=new pair((int)-1e8,"");

        for(Edge e:graph[src]){
            if(!vis[e.v]){
                pair recAns=heavyPath(e.v, dest, vis);
                if(recAns.w!=(int)-1e8 && recAns.w+e.w>myAns.w){
                    myAns.w=recAns.w+e.w;
                    myAns.str=src+" "+recAns.str;
                }
            }
        }
        vis[src]=false;
        return myAns;
    }

    public static void solve(){
        constructGraph();
        //isplay();
        boolean []vis=new boolean [N];
        //System.out.println(hasPath(0, 6, vis));
        // vis=new boolean [N];
        // System.out.println(printAllpath(0, 3, vis,""));
        pair ans=heavyPath(0, 6, vis);
        System.out.println(ans.str);
    }
    public static void main(String[] args) {
        solve();
    }

}