public class stack {
    private int []arr;
    int maxNoele;
    int size;
    stack(){
        arr=new int [maxNoele];
        maxNoele=20;
        size=-1;
    }
    stack(int s){
       arr=new int [s];
       this.maxNoele=s; 
       size=-1;
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        if(this.size==-1)
            return true;
        return false;
    }
    public void push(int x){
        if(size<maxNoele)
        this.arr[++size]=x;
        else System.out.println("overflow");
    }
    public int pop(){
        if(size<0){
            System.out.println("underflow");
            return -1;
        }
        int r=arr[size];
        arr[size]=0;
        size--;
        return r;
    }
    public int top(){
        if(isEmpty()){
            System.out.println("Empty Stack");
            return -1;
        }
        return arr[size];
    }
}
