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
    public static void solve(){
        int []arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node node=construct(arr);
        //display(node);
        //System.out.println(size(node));
        //System.out.println(height(node));
        // System.out.println(find(node, 87));
        // System.out.println(find(node, 110));
        ArrayList<Integer>al=new ArrayList<>();
        boolean ans=nodeToRoot(node, 90, al);
        System.out.println(al);
    }
    public static void main(String[] args) {
        solve();
    }
}
