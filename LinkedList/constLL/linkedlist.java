import java.util.*;
public class linkedlist{

    private class Node{
        private int data=0;
        private Node next=null;

        Node(int data){
            this.data=data;
        }
    }

        private Node head=null;
        private Node tail=null;
        private int numberOfNode=0;

        public int size(){
            return this.numberOfNode;
        }

        public boolean isEmpty(){
            return (numberOfNode==0)?true:false;
        }

        protected void handleZeroSize(Node node){
            this.head=node;
            this.tail=node;
        }
//========================================================================================================================
        protected void addFirstNode(Node node){
            if(size()==0){
                handleZeroSize(node);
            }
            else{
                node.next=this.head;
                this.head=node;
            }
            numberOfNode++;
        } 

        public void addFirst(int data){
            Node node=new Node(data);
            addFirstNode(node);
        }
//========================================================================================================================
        public void addLastNode(Node node){
            if(size()==0)
                handleZeroSize(node);
            else{
                tail.next=node;
                this.tail=node;
            }
            numberOfNode++;
        }

        public void addLast(int data){
            Node node=new Node(data);
            addLastNode(node);
        }

//====================================================================================================================
        public void addAt(int data,int idx) throws Exception{
            if(idx<0 || idx>size())
                throw new Exception("Invalid index");
            
                Node node=new Node(data);
                addAtNode(node,idx);
        }
        
        protected void addAtNode(Node node,int idx){
            if(idx==0)
                addFirstNode(node);
            else
                if(idx==size())
                    addLastNode(node);
            else{
                Node nodeAt=getNodeAt(idx-1);
                Node forw=nodeAt.next;

                nodeAt.next=node;
                node.next=forw;
            }
            numberOfNode++;
        }
//===================================================================================================================
        protected Node getNodeAt(int idx){
            Node temp=this.head;
            while(idx>0){
                temp=temp.next;
                idx--;
            }
            return temp;
        }
//=======================================================================================================================
        private void handleZeroSize1(){
            this.head=null;
            this.tail=null;
        }
        
        public int removeFirst() throws Exception{
            if(size()==0)
                throw new Exception("List is empty");
            Node node=removeFirstNode();
            return node.data;
        }

        protected Node removeFirstNode(){
            if(size()==1)
                handleZeroSize1();
            else{
                Node temp=head;
                this.head=this.head.next;
                temp.next=null;
                
            }
            numberOfNode--;
            return head;
        }
//======================================================================================================================
        public int removeLast() throws Exception{
            if(size()==0)
                throw new Exception("List is empty");
            Node node=removeLastNode();
            return node.data;
        }
        
        protected Node removeLastNode(){
            if(size()==1)
                handleZeroSize1();
            else{
                Node secondLast=getNodeAt(size()-2);
                Node t=this.tail;

                tail=secondLast;
                tail.next=null;
            }
            numberOfNode--;
            return tail;
        }
//==========================================================================================================================
        protected Node removeAtNode(int idx) {
            if(idx==0)
                removeFirstNode();
            else if(idx==size()-1)
                removeLastNode();
            else{
                Node temp=getNodeAt(idx-1);
                Node remove=temp.next;
                temp.next=temp.next.next;
                remove.next=null;
                numberOfNode--;
                return remove;
            }
            return null;
        }
        
        public int removeAt(int idx) throws Exception{
            if(idx<0 || idx<=size())
                throw new Exception("Invalid index");
            if(size()==0)
                throw new Exception("Empty list");
            Node node=removeAtNode(idx);
            return node.data;
        }
//==========================================================================================================================
        public int getFirst() throws Exception{
            if(size()==0)
                throw new Exception("Empty list");
            return this.head.data;
        }
        
        public int getLast() throws Exception{
            if(size()==0)
                throw new Exception("Empty list");
            return this.tail.data;
        }
        
        public int getAt(int idx) throws Exception{
            if(idx<0 || idx<=size())
                throw new Exception("invalid index");
            if(size()==0)
                throw new Exception("empty list");
            return getNodeAt(idx).data;
        }
    }

