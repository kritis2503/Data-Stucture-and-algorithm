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
    //IMP leetcode 1061,305
    //leetcode 305
    public static ArrayList<Integer> numIsland(int m,int n,int [][]positions){
        ArrayList<Integer>ans=new ArrayList<>();
        int count=0;
        par=new int [n*m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                par[i*m+j]=i*m+j;
        }
        // for(int i=0;i<n*m;i++){
                // System.out.print(par[i]+" ");
        // }
        int [][]dir={{1,0},{-1,0},{0,1},{0,-1}};
        int [][]grid=new int [n][m];
        for(int []pos:positions){
            int r=pos[0];
            int c=pos[1];
            if(grid[r][c]!=1){
                grid[r][c]=1;
                count++;

                for(int d=0;d<4;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0 && y>=0 && x<n && y<m && grid[x][y]==1){
                        int p1=findPar(x*m+y);
                        int p2=findPar(r*m+c);
                        if(p1!=p2){
                            par[p1]=p2;
                            count--;
                        }
                    }
                }
                ans.add(count);
            }
           
        }
        return ans;
    }
    //Leetcode 1168
    public static int minCost(int n,int []well,int [][]pipes){
        par=new int [n+1];
        for(int i=0;i<=n;i++)
            par[i]=i;
        
        int wt=0;
        for(int []p:pipes){
            int u=pipes[0];
            int v=pipes[1];
            int w=pipes[2];
            
            int p1=findPar(u);
            int p2=findPar(v);

            if(p1!=p2){
                if(well[p1+1]>well[p2+1])
                    par[p1]=p2;
                else
                    par[p2]=p1;
                wt+=w;
            }
        }
        for(int i=0;i<n;i++){
            if(par[i]==i+1)
                wt+=well[i];
        }
    }
    //1168
    public static int minWaterSupply(int n,int []pipes,int []wells){
       ArrayList<int []>p=new ArrayList<>();
       for(int i=0;i<wells.length;i++)
        p.add(new int []{0,i+1,wells[i]});

        for(int []a:pipes)
            p.add(a);
        Collections.sort(p, (a,b)->{
            return a[2]-b[2];
        });
        return minWaterSupply_(p);
    }
    public static int minWaterSupply_(ArrayList<int[]>pipes){
        par=new int [pipes.size()];
        for(int i=0;i<pipes.size();i++)
            par[i]=i;
        int cost=0;
        for(int []a:pipes){
            int u=a[0];
            int v=a[1];
            int w=a[2];

            int p1=findPar(u);
            int p2=findPar(v);
            if(p1!=p2){
                par[p1]=p2;
                cost+=w;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        int [][]pos={{0,0}, {0,1}, {1,2}, {2,1}};      
        System.out.println(numIsland(3, 3, pos));  
    }
}
