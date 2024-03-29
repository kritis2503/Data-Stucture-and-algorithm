import java.util.*;
public class directedgraph {
    public static class Edge{
        int v=0;
        int w=0;
        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }
    
    static int N=11;
    static ArrayList<Edge>[]graph=new ArrayList[N];
    
    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v, w));
    }

    public static void constructGraph(){
        for(int i=0;i<N;i++)
            graph[i]=new ArrayList<>();
        
        addEdge(5, 0, 10);
        addEdge(4, 0, 10);
        addEdge(5, 1, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(0, 6, 10);
        addEdge(6, 7, 10);
        addEdge(7, 3, 10);
        addEdge(4, 8, 10);
        addEdge(8, 9, 10);
        addEdge(9, 10, 10);
        addEdge(10, 3, 10);
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
    //================TOPOLOGICAL SORT====================
    //it is used to tell the order in a directed graph where one node is dependent upon other
    //it is both in dfs and bfs
    //the starting point in topological sort doesn't matter
    //algo for dfs 1-mark 2-for all the unvisited neighbours call dfs 3-store the node in a vector/arraylist/stack

    public static void topoDFS(){
        ArrayList<Integer>al=new ArrayList<>();
        boolean []vis=new boolean[N];
        for(int i=0;i<N;i++){
            if(!vis[i])
                topo_DFS_helper(i, vis, al);
        }
        System.out.println(al);
    }
    public static void topo_DFS_helper(int src,boolean []vis,ArrayList<Integer>list){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                topo_DFS_helper(e.v, vis, list);
            }
        }
        list.add(src);
    }
    //for BFS we use kahn's algo. for this you need to know what is indegree 
    //and outdegree. the arrow coming in is indegree
    //step-1 :fill the indegree array by counting each indegree arrow
    //step-2:add all the elements with indegree 0 to queue
    //step-3: then removefirst add to answer arraylist 
    //and decrease their indegree,if it is zero add it to the answer.
    //if the answer arraylist has less than n elements then directed graph has CYCLE and it is impossible to get all dependencies

    public static void kahnsAlgo(){
        LinkedList<Integer>que=new LinkedList<>();
        ArrayList<Integer>ans=new ArrayList<>();
        int []indegree=new int [N];
        int level=0;

        for(int i=0;i<N;i++){
            for(Edge e:graph[i])
                indegree[e.v]++;
        }
        for(int i=0;i<N;i++){
            if(indegree[i]==0)
                que.addLast(i);
        }

        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                int vtx=que.removeFirst();
                ans.add(vtx);

                for(Edge e:graph[vtx]){
                    if(--indegree[e.v]==0){
                        que.addLast(e.v);
                    }
                }
            }
            level++;
        }
        System.out.println(ans);
        System.out.println(level);
    }
    public static void isCycle_dfs(){
        int []vis=new int [N];
        for(int i=0;i<N;i++)
            vis[i]=-1;
        ArrayList<Integer>list=new ArrayList<>();
        boolean res=false;

        for(int i=0;i<N;i++){
            if(vis[i]==-1)
                res=res||cycle_dfsTopo(i, vis, list);
        }
        if(res)
            list.clear();
        System.out.println(list);
        System.out.println(res);

    }
    public static boolean cycle_dfsTopo(int src,int []vis,ArrayList<Integer>list){
        vis[src]=0;
        boolean res=false;
        for(Edge e:graph[src]){
            if(vis[e.v]==-1)
                res=res||cycle_dfsTopo(e.v, vis, list);
            else if(vis[e.v]==0)
                return true;
        }
        vis[src]=1;
        list.add(src);
        return res;
    }
    //--------------------------------------KOSARAJU'S ALGO---------------------------------------------
    //Using this algo, we find strongly connected component.
    //A directed graph is strongly connected if there is a path between all pairs of vertices.
    //A strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.
    //a stronglt connected component means if each vertex has path to every other vertex.
    //this thing is only possible if the component all of them are in a cycle.
    //pseudo code:
    //step1:find the topological order using DFS.
    //step2:find the inverse of graph, ie change the direction of directed graph.
    //Step3:following the topo order found in step1 FROM BACK , using DFS apply toposort again and save the elements
    //these elements are a SCC.
    //The basic logic about it ,scc are usually a cycle ans if we invert the cycle, it still remains cycle(conditions apply)
    //no matter what. those elements which aren't cycle are not part of that particular componennt
    //Note:This algo is used to change DIRECTED CYCLIC GRAPH TO DIRECTED ACYCLIC GRAPH.
    //------------------------------------------------------------------------------------------------------
    public static void kosaraju(){
        //step-1
        boolean []vis=new boolean [N];
        ArrayList<Integer>topo=new ArrayList<>();

        for(int i=0;i<N;i++){
            if(!vis[i])
                topo_DFS_helper(i, vis, topo);
        }
        //end of step-1
        //step-2 graph inverse
        ArrayList<Edge>[]ngraph=new ArrayList[N];
        for(int i=0;i<N;i++){
            ngraph[i]=new ArrayList<>();
        }
        for(int i=0;i<N;i++){
            for(Edge e:graph[i]){
                ngraph[e.v].add(new Edge(i, 0));
            }
        }
        //end of step-2
        //step-3 
        vis=new boolean [N];
        ArrayList<Integer>al=new ArrayList<>();
        for(int i=topo.size()-1;i>=0;i--){
            if(!vis[i]){
                al=new ArrayList<>();
                topo_SCC(ngraph, i, vis,al);
                System.out.println(al);
            }
        }
    }
    public static void topo_SCC(ArrayList<Edge>[]ngraph,int src,boolean []vis,ArrayList<Integer>list){
        vis[src]=true;
        for(Edge e:ngraph[src]){
            if(!vis[e.v]){
                topo_SCC(ngraph,e.v, vis, list);
            }
        }
        list.add(src);
    }
    public static void main(String[] args) {
        constructGraph();
        // display();
        //  topoDFS();
        //  System.out.println();
        // kahnsAlgo();
        isCycle_dfs();
    }
    
}
