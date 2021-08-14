package heap_Construction;

import java.util.ArrayList;

public class heapSort {
    public static boolean compareTo(ArrayList<Integer>arr,boolean isIncreasing,int i,int j){
        if(isIncreasing)
            return arr.get(i)>arr.get(j);
        return arr.get(i)<arr.get(j);
    }

    public static void downheapify(ArrayList<Integer>arr,boolean isIncreasing ,int pi,int li){
        int maxIdx=pi;
        int lci=2*pi+1;
        int rci=2*pi+2;

        if(lci<=li && compareTo(arr, isIncreasing, lci, maxIdx))
            maxIdx=lci;
        if(lci<=li && compareTo(arr, isIncreasing, rci, maxIdx))
            maxIdx=rci;
        if(maxIdx!=pi){
            downheapify(arr, isIncreasing,maxIdx, li);
            swap(arr, pi, maxIdx);
        }
    }
    public static void swap(ArrayList<Integer>arr,int i,int j){
        int temp=arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void heapSort_(ArrayList<Integer>arr,boolean isIncreasing){
        int li=arr.size()-1;
        for(int i=li;i>=0;i--){
            downheapify(arr, isIncreasing, i, li);
        }
        while(li>=0){
            swap(arr, 0, li);
            li--;
            downheapify(arr, isIncreasing, 0, li);
        }
    }
    public static void main(String[] args) {
        
    }
}
