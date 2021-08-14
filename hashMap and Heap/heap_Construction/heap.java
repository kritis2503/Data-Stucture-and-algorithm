import java.util.*;
public class heap{
    private ArrayList<Integer>arr;
    private boolean isMaxHeap=true;

    void constructHeap(){   //O(nlogn)->O(n)
        for(int i=this.arr.size()-1;i>=0;i--)
            downheapify(i);
    }
    //for every level say l, in a heap tree of height h, we traverse 2^(h-l)*l.so when we sum
    //S =2^h(0)    +2^(h-1)*1+2^(h-2)*2+2^(h-3)*3_ _ _ _ _ _
    //2S=2^(h+1)(0)+2^h*1    +2^(h-1)*2+2^(h-3)*3_ _ _ _ _ _
    //s=2^h(1/2+1/4+1/8+1/16+1/32+.....................)
    //s=2^h*2(infinte gp)
    //s=2n~O(n)
    //Java doesn't have O(n) when elements are added uniquely, but O(nlogn).it is only O(n) 
    // when an entire array is added. 
    void defaultValue(boolean isMaxHeap){
        this.arr=new ArrayList<>();
        this.isMaxHeap=isMaxHeap;
    }

    heap(){
        defaultValue(true);
    }

    heap(boolean isMaxHeap){
        defaultValue(isMaxHeap);
    }

    heap(int []a,boolean isMaxHeap){
        defaultValue(isMaxHeap);
        for(int ele:a)
            this.arr.add(ele);
        constructHeap();
    }

    int size(){
        return this.arr.size();
    }

    boolean isEmpty(){
        return this.arr.size()==0;
    }

    boolean compareTo(int a,int b){
        if(isMaxHeap)
            return this.arr.get(a)>this.arr.get(b);
        return this.arr.get(a)<this.arr.get(b);
    }

    void add(int data){
        this.arr.add(data);
        int n=this.arr.size();
        upheapify(n-1);
    }

    int remove(){   //O(logn)
        int n=this.arr.size();
        int ele=this.arr.get(n-1);
        swap(n-1, 0);
        this.arr.remove(n-1);
        downheapify(0);
        return ele;
    }
    int top(){
        return this.arr.get(0);
    }

    void downheapify(int pi){
        int maxIdx=pi;
        int lci=2*pi+1;
        int rci=2*pi+2;

        if(lci<this.arr.size() && compareTo(lci, maxIdx))
            maxIdx=lci;
        if(rci<this.arr.size() &&   compareTo(rci, maxIdx))
            maxIdx=rci;
        if(maxIdx!=pi){
            swap(maxIdx, pi);
            downheapify(maxIdx);
        }
        
    }
    void upheapify(int ci){
        int pi=(ci-1)/2;
        if(pi>=0 && compareTo(ci,pi)){
            swap(ci,pi);
            upheapify(pi);
        }
    }

    void swap(int a,int b){
        int temp=this.arr.get(a);
        this.arr.set(a, this.arr.get(b));
        this.arr.set(b,temp);
    }


}