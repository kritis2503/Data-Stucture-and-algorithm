import java.util.*;

public class questions {
    public static class Node{
        int data;
        Node left;
        Node right;
        Node (int data){
            this.data =data;
        }
    }
    //helper function
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
    //helper function
    public static void kdown_(Node node, Node block,int k,ArrayList<ArrayList<Integer>>ans){
        if(node==null || block==node || k<0)
            return;
        if(ans.size()-1>=k){
            ans.get(k).add(node.data);
        }
        else{
            ArrayList<Integer>al=new ArrayList<>();
            al.add(node.data);
            ans.add(al);
        }
        kdown_(node.left, block, k+1, ans);
        kdown_(node.right, block, k+1, ans);
        return;
    }
    //geeksforgeeks Amazon
    public static void burningTree01(Node node,int target,ArrayList<ArrayList<Integer>>ans){
        if(node==null)
            return ;
        ArrayList<Node>ntr=nodeToRoot2(node, target);
        Node block=null;
        for(int i=0;i<ntr.size();i++){
            kdown_(ntr.get(i), block, i, ans);
            block=ntr.get(i);
        }
        return;
    }
    //geeksforgeeks Amazon
    public static int burningTree02(Node node,int target,int k,ArrayList<ArrayList<Integer>>ans){
        if(node==null)
            return -1;
        if(node.data==target){
            kdown_(node,null,0,ans);
            return 1;
        }
        int ld=burningTree02(node.left, target,k, ans);
        if(ld!=-1){
            kdown_(node, node.left, k+ld, ans);
            return ld+1;
        }
        int rd=burningTree02(node.right, target, k, ans);
        if(rd!=-1){
            kdown_(node, node.right, k+rd, ans);
            return rd+1;
        }
        return -1;
    }
    public static Node construct(){
        // Node root = new Node(12);
        // root.left = new Node(13);
        // root.right = new Node(10);
        // root.right.left = new Node(14);
        // root.right.right = new Node(15);
        // Node left = root.right.left;
        // Node right = root.right.right;
        // left.left = new Node(21);
        // left.right = new Node(24);
        // right.left = new Node(22);
        // right.right = new Node(23);
        // return root;
       
        Node root = new Node(-15);
        root.left = new Node(5);
        root.right = new Node(6);
        root.left.left = new Node(-8);
        root.left.right = new Node(1);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(6);
        root.right.left = new Node(3);
        root.right.right = new Node(9);
        root.right.right.right = new Node(0);
        root.right.right.right.left = new Node(4);
        root.right.right.right.right = new Node(-1);
        root.right.right.right.right.left = new Node(10);
        return root;
        // System.out.println("Max pathSum of the given binary tree is "
                // + tree.maxPathSum(root));
    }

    public static int maxPathSumBwLeaveNode(Node node,maxdist maxSoFar){
        if(node==null)
            return 0;
        if(node.left==null && node.right==null)
            return node.data;

        int l=maxPathSumBwLeaveNode(node.left,maxSoFar);
        int r=maxPathSumBwLeaveNode(node.right,maxSoFar);

        if(node.left!=null && node.right!=null)
            {
                maxSoFar.dist=Math.max(maxSoFar.dist, l+r+node.data);
                return Math.max(l, r)+node.data;
            } 
        return (node.left==null)?r+node.data:l+node.data;
    }
    public static class maxdist{
        int dist;
    }
    public static void solve(){
        Node node=construct();
        // ArrayList<ArrayList<Integer>>ans=new ArrayList<>();
        //burningTree01(node, 14, ans);
        // burningTree02(node, 14, 0, ans);
        // System.out.println(ans);
        maxdist d=new maxdist();
        int ans=maxPathSumBwLeaveNode(node, d);
        System.out.println(d.dist);

    }
    public static void main(String[] args) {
        
        solve();
    }
}
