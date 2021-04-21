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

    public static int hamlitonPath(int osrc,int src,int noofEdges,boolean[]vis,String psf){
        if(noofEdges==1){
            System.out.println(psf+" "+src);
            return 1;
        }
        vis[src]=true;
        int count=0;
        for(Edge e:graph[src]){
            if(!vis[e.v])
                count+=hamlitonPath(osrc, e.v, noofEdges-1, vis, psf+" "+src+"");
        }
        vis[src]=false;
        return count;
    }
    public static int gcc(){
        ArrayList<ArrayList<Integer>>ans=new ArrayList<>();
        boolean[]vis=new boolean [N];
        int component=0;
        for(int i=0;i<N;i++){
            if(!vis[i]){
            ArrayList<Integer>al=new ArrayList<>();
            dfs(i, vis, al);
            ans.add(al);
            component++;
        }
    }
        for(ArrayList<Integer>al:ans)
            System.out.println(al);
        System.out.println(vis[6]);
        return component;
    }
    public static void dfs(int src,boolean []vis,ArrayList<Integer>ele){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                dfs(e.v, vis, ele);
            }
        }
        ele.add(src);
    }
    //BFS-------------------------------------------------------------------------------------------------
    //1-It is used to get the shortest path
    //2-used to detect cycle. it gives just yes and no answer
    public static void BFS_cycle(int src){
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);

        int dest=6;
        int atLevel=-1;

        boolean isCycle=false;
        int level=0;
        boolean []vis=new boolean [graph.length];

        while(que.size()!=0){
            int size=que.size();
            System.out.print("level "+level+"->");

            while(size-->0){
                int vtx=que.removeFirst();
                System.out.print(vtx+" ");

                if(vis[vtx]){
                    isCycle=true;
                    continue;
                }
                vis[vtx]=true;
                if(vtx==dest){
                    atLevel=level;
                }
                for(Edge e:graph[vtx]){
                    if(!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
        System.out.println(isCycle+" at level "+atLevel);
    }

    public static void BFS_shortestpath(int src){
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);

        int dest=6;
        int atLevel=-1;
        
        int level=0;
        boolean []vis=new boolean [graph.length];
        while(que.size()!=0){
            int size=que.size();
            System.out.print("level "+level+"->");
            while(size-->0){
                int vtx=que.removeFirst();
                System.out.print(vtx+" ");
                
                for(Edge e:graph[vtx]){
                    if(!vis[e.v]){
                        vis[vtx]=true;
                        que.addLast(e.v);
                    }
                    if(vtx==dest){
                        atLevel=level+1;
                    }
                }
            }
            System.out.println();
            level++;
        }
        System.out.println("dest at "+atLevel);
    }

    public static void BFS_printshortestpath(int src){
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        int dest=6;
        int atLevel=-1;
        
        int level=0;
        boolean []vis=new boolean [graph.length];
        int []par=new int [graph.length];
        for(int i=0;i<par.length;i++)
            par[i]=-1;

        while(que.size()!=0){
            int size=que.size();
            System.out.print("level "+level+"->");
            while(size-->0){
                int vtx=que.removeFirst();
                System.out.print(vtx+" ");
                
                for(Edge e:graph[vtx]){

                    if(!vis[e.v]){
                        vis[vtx]=true;
                        que.addLast(e.v);
                        par[e.v]=vtx;
                    }

                    if(e.v==dest){
                        atLevel=level+1;
                    }
                }
            }
            System.out.println();
            level++;
        }
        System.out.println("dest at "+atLevel);
        int idx=atLevel;
        while(idx!=-1){
            System.out.print(idx+" ");
            idx=par[idx];
        }

    }
//================TOPOLOGICAL SORT====================
//it is used to tell the order in a directed graph where one node is dependent upon other
//it is both in dfs and bfs
//the starting point in topological sort doesn't matter
//algo for dfs 1-mark 2-for all the unvisited neighbours call dfs 3-store the node in a vector/arraylist/stack
    
    public static void solve(){
        constructGraph();
        //display();
        //boolean []vis=new boolean [N];
        //System.out.println(hasPath(0, 6, vis));
        // vis=new boolean [N];
        // System.out.println(printAllpath(0, 3, vis,""));
        // pair ans=heavyPath(0, 6, vis);
        // System.out.println(ans.str);
        // System.out.println(hamlitonPath(0, 0, N, vis, ""));
        // System.out.println(gcc());
       // BFS_cycle(0);
        // BFS_shortestpath(0);
        BFS_printshortestpath(0);
    }
    public static void main(String[] args) {
        solve();
    }

}