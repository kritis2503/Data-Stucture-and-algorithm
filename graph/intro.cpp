#include<iostream>
#include<vector>
#include<queue>

using namespace std;

class Edge{
    public:
        int v;
        int w;

        Edge(int v,int w){
            this->w=w;
            this->v=v;
        }
};

const int N=7;
vector<vector<Edge>>graph(N);

void addEdge(int u,int v,int w){
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}

void display(){
    for(int i=0;i<N;i++){
        cout<<to_string(i)+"->";
        for(Edge e: graph[i]){
            cout<<"("+to_string(e.v)+","+to_string(e.w)+")";
        }
        cout<<endl;
    }
}

int findedge(int u,int v){
    int idx=-1;
    for(int i=0;i<graph[u].size();i++){
        if(graph[u][i].v==v){
            idx=i;
            break;
        }
    }
    return idx;
}

void removeEdge(int u,int v){
    int idx1=findedge(u,v);
    int idx2=findedge(v,u);

    graph[u].erase(graph[u].begin()+idx1);
    graph[v].erase(graph[v].begin()+idx2);
}

void removeVtx(int u){
    for(int i=graph[u].size()-1;i>=0;i--){
        removeEdge(u,graph[u][i].v);
    }
}

bool hasPath(int src,int dest,vector<bool> &vis){
    if(src==dest)
        return true;
    vis[src]=true;
    bool res=false;

    for(Edge e:graph[src]){
        if(!vis[e]){
            res=res||hasPath(e.v,dest,vis);
        }
    }
    return res;
}
int printAllpath(int src,int dest,vector<bool> &vis,string psf){
    if(src==dest){
        cout<<psf<<dest<<endl;
        return 1;
    }
    vis[src]=true;
    int ans=0;
    for(Edge e:graph[u]){
        ans+=printAllpath(e.v,dest,vis,psf+to_string(src)+"");
    }
    vis[src]=false;
    return ans;
}
void constructGraph(){
    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);

    // addEdge(0, 6, 3);
    // addEdge(7, 2, 3);
    // addEdge(6, 2, 3);
    // addEdge(6, 7, 3);
}

int main(){
    
    constructGraph();
    display();
    bool vis[N];
    cout<<hasPath(0,6,vis)<<endl;
    bool vis1[N];
    cout<<printAllpath(0,6,vis1,"");
    return 0;
}