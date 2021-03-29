import java.util.*;

public class intro {
    public static class Node{
        int data;
        Node left;
        Node right;

        Node (int data){
            this.data =data;
        }
    }
    static int idx=0;
    public static Node construct(int []arr){
        if(idx>=arr.length || arr[idx]==-1){
            idx++;
            return null;
        }  

        Node node=new Node(arr[idx++]);
        node.left=construct(arr);
        node.right=construct(arr);

        return node;
    }
    
    public static int size(Node node){
        return (node==null)?0:size(node.left)+size(node.right)+1;
    }

    public static int height(Node node){
        return node==null ? -1 : Math.max(height(node.left),height(node.right)) + 1;
    }

    public static boolean find(Node node,int data){
        if(node==null)
            return false;
        if(node.data==data)
            return true;
        return find(node.left, data)||find(node.right, data);
    }

    public static void display (Node node){
        if(node==null)
            return;
        
        StringBuilder str=new StringBuilder();
        str.append((node.left==null)?".":node.left.data+" ");
        str.append("<-"+node.data+"->");
        str.append((node.right==null)?".":node.right.data+" ");
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static boolean nodeToRoot(Node node, int val,ArrayList <Integer>al){
        if(node==null)
            return false;
        if(node.data==val)
        {
            al.add(val);
            return true;
        }
        boolean res=nodeToRoot(node.left, val, al)||nodeToRoot(node.right, val, al);
        if(res)
            al.add(node.data);
        return res;
    }

    public static ArrayList<Node> nodeToRoot2(Node node,int val){
        if(node==null){
            return new ArrayList<Node>();
        }

        if(node.data==val){
            ArrayList<Node>al=new ArrayList<>();
            al.add(node);
            return al;
        }
        ArrayList<Node>la=nodeToRoot2(node.left, val);
        if(la.size()!=0){
            la.add(node);
            return la;
        }
        ArrayList<Node>ra=nodeToRoot2(node.right, val);
        if(ra.size()!=0){
            ra.add(node);
            return ra;
        }
        return new ArrayList<Node>();
    }

    public static Node lowestCommonAncestor(Node node,int p,int q){
        if(node==null)
            return null;
        ArrayList<Node>pAl=nodeToRoot2(node, p);
        ArrayList<Node>qAl=nodeToRoot2(node, q);
        int c1=pAl.size()-1;
        int c2=qAl.size()-1;

        Node lca=null;
        while(c1>=0 && c2>=0){
            if(pAl.get(c1).data!=qAl.get(c2).data){
                break;
            }
            lca=pAl.get(c1);
            c1--;
            c2--;
        }
        return lca;
    }

    public static void kdown(Node node, Node block,int k,ArrayList<Integer>ans){
        if(node==null || block==node || k<0)
            return;

        if(k==0){
            ans.add(node.data);
            return;
        }

        kdown(node.left, block, k-1, ans);
        kdown(node.right, block, k-1, ans);
        return;
    }

    //time complexity O(2n)- every element iterated twice
    public static void kAway01(Node node,int data,int k){
        if(node==null)
            return ;
        ArrayList<Node>al=nodeToRoot2(node, data);
        Node block=null;
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<al.size();i++){
            kdown(al.get(i), block, k-i, ans);
            block=al.get(i);
        }
        System.out.println(ans);
    }
    //time complexity O(n)
    public static int kAway02(Node node,int data,int k,ArrayList<Integer>ans){
        if(node==null)
            return -1;
        
        if(node.data==data){
            kdown(node, null, k, ans);
            return 1;
        }
        
        int ld=kAway02(node.left, data, k, ans);
        if(ld!=-1){
            kdown(node, node.left, k-ld, ans);
            return ld+1;
        }
        int rd=kAway02(node.right, data, k, ans);
        if(rd!=-1){
            kdown(node, node.right, k-rd, ans);
            return rd+1;
        }
        return -1;
    }

    public static int diameter01(Node node){
        if(node==null)
            return 0;
        
        int lh=height(node.left);
        int rh=height(node.right);

        int ld=diameter01(node.left);
        int rd=diameter01(node.right);

        return Math.max(Math.max(ld, rd), lh+rh+2);
    }

    public static int[] diameter02(Node node){
        if(node==null)
            return new int []{0,-1};
        
        int []l=diameter02(node.left);
        int []r=diameter02(node.right);

        int dia=Math.max(Math.max(l[0], r[0]), l[1]+r[1]+2);
        int h=Math.max(l[1], r[1])+1;

        return new int []{dia,h};
    }

    public static int diameter03(Node node,int diaAns){
        if(node==null)
            return -1;
        
        int lh=diameter03(node.left, diaAns);
        int rh=diameter03(node.right, diaAns);
        diaAns=Math.max(lh+rh+2, diaAns);
        return Math.max(lh, rh)+1;
    }

    //LEVEL ORDER-----------------------------------------------------------------------------
    public static void BFS01(Node node){
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(node);

        while(que.size()!=0){
            Node vtx=que.removeFirst();
            System.out.println(vtx.data);
            if(vtx.left!=null)
                que.addLast(vtx.left);
            if(vtx.right!=null)
                que.addLast(vtx.right);
        }
    }

    public static void BFS02(Node node){
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(node);
        que.addLast(null);

        while(que.size()!=1){
            Node vtx=que.removeFirst();
            System.out.print(vtx.data+" ");

            if(vtx.left!=null)
                que.addLast(vtx.left);
            if(vtx.right!=null)
                que.addLast(vtx.right);
            
            if(que.getFirst()==null){
                que.removeFirst();
                System.out.println();
                que.addLast(null);
            }
        }
    }

    public static void BFS03(Node node){
        LinkedList<Node>que=new LinkedList<>();
        que.addLast(node);
        int level=1;
        while(que.size()!=0){
            int size=que.size();
            System.out.print(level+"->");
            while(size-->0){
                Node vtx=que.removeFirst();
                System.out.print(vtx.data+" ");
                if(vtx.left!=null)
                    que.addLast(vtx.left);
                if(vtx.right!=null)
                    que.addLast(vtx.right);
            }
            System.out.println();
            level++;
            
        }
    }

    public static ArrayList<Integer> leftView(Node node){
        LinkedList<Node>que=new LinkedList<>();
        ArrayList<Integer>al=new ArrayList<>();
        que.addLast(node);
       
        while(que.size()!=0){
            int size=que.size();
            boolean save=true;
            while(size-->0){
                Node vtx=que.removeFirst();
                if(save){
                    al.add(vtx.data);
                    save=false;
                }
                if(vtx.left!=null)
                    que.addLast(vtx.left);
                if(vtx.right!=null)
                    que.addLast(vtx.right);
            }
        }
        return al;
    }
    public static void leftViewRec(Node node){
        int level=height(node);
        System.out.println(level);
        boolean []vis=new boolean [level+1];
        for(int i=0;i<level;i++)
            vis[i]=false;
        //leftViewHelper(node,vis,1);
    }
    public static void leftViewHelper(Node node,boolean[]vis,int level){
        if(node==null)
            return;
        if(!vis[level])
            System.out.println(node.data);
        vis[level]=true;
        leftViewHelper(node.left, vis, level+1);
        leftViewHelper(node.right, vis, level+1);
    }

    public static ArrayList<Integer> rightView(Node node){
        LinkedList<Node>que=new LinkedList<>();
        ArrayList<Integer>al=new ArrayList<>();
        que.addLast(node);
       int prev=0;
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                Node vtx=que.removeFirst();
                if(vtx.left!=null)
                    que.addLast(vtx.left);
                if(vtx.right!=null)
                    que.addLast(vtx.right);
                prev=vtx.data;
            }
            al.add(prev);
        }
        return al;
    }
    public static void solve(){
        int []arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node node=construct(arr);
        display(node);
        //System.out.println(size(node));
        //System.out.println(height(node));
        // System.out.println(find(node, 87));
        // System.out.println(find(node, 110));
        //ArrayList<Integer>al=new ArrayList<>();
        //boolean ans=nodeToRoot(node, 90, al);
        //al=nodeToRoot2(node, 90);
        //System.out.println(al);
        // Node lca=lowestCommonAncestor(node, 100, 80);
        // System.out.println(lca.data);
        // kAway01(node, 90, 6);
        // int a=kAway02(node, 90, 6, al);
        // System.out.println(al);
        // System.out.println(diameter01(node));
        // int []ans=diameter02(node);
        // System.out.println(ans[0]);
        // int dia=0;
        // int ans=diameter03(node, dia);
        // System.out.println(dia);
        // BFS01(node);
        // BFS02(node);
        // BFS03(node);
        System.out.println(leftView(node));
        // System.out.println(rightView(node));
        leftViewRec(node);
    }
    public static void main(String[] args) {
        solve();
    }
}
