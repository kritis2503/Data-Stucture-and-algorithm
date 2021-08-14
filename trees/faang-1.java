//https://www.geeksforgeeks.org/clone-binary-tree-random-pointers/
class Solution{
    public static Tree cloneTree(Tree tree){
       // add code here.
       if(tree==null)
        return null;
       HashMap<Tree,Tree>map=new HashMap<>();
       Tree root= clone(tree,map);
       copyrandom(root,map);
       return root;
     }
     public static Tree clone(Tree root,HashMap<Tree,Tree>map){
         if(root==null)
            return null;
        
        Tree node=new Tree(root.data);
        map.put(root,node);
        node.left=clone(root.left,map);
        node.right=clone(root.right,map);
        return node;
     }
     public static void copyrandom(Tree root,HashMap<Tree,Tree>map){
         if(root==null)
            return;
            if(root.random!=null)
        map.get(root).random=map.get(root.random);
        copyrandom(root.left,map);
        copyrandom(root.right,map);
     }
}