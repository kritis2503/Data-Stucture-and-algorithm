import java.util.*;
public class basic{
    public static void display1D(int []arr){
        for(int a:arr)
            System.out.print(a+" ");
    }
    //array rotation
    public static void rotate(int []arr,int r){
        int n=arr.length;
        r=r%n;
        if(r<0)
            r+=n;
        reverse(arr, 0, n - 1);
        reverse(arr, n - r, n - 1);
        reverse(arr, 0, n - r - 1);

        
        // reverse(arr, 0, n-1);
        // reverse(arr, 0, r-1);
        // reverse(arr, r, n-1);
        display1D(arr);
    }
    public static void reverse(int []arr,int si,int ei){
        while(ei>=si){
            swap(arr,si,ei);
            si++;
            ei--;
        }
    }
    public static void swap(int []arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    //segregate positive and negatve numbers
    public static void segregatenegandpos(int []arr){
        int n=arr.length;
        int si=0,ptr=-1;
        while(si<n){
            if(arr[si]<0)
                swap(arr, ++ptr,si);
            si++;
        }
    }
    //segregate zeros and ones
    public static void segregateZerosandOnes(int []arr){
        int n=arr.length;
        int si=0,ptr=-1;
        while(si<n){
            if(arr[si]==0)
                swap(arr,++ptr,si);
            si++;
        }
    }

    //segregate zeros, ones and twos
    public static void segregateZerosOnesAndTwos(int []arr){
        int n=arr.length;
        int i=0,ptr1=-1,ptr2=n-1;
        while(i<=ptr2){
            if(arr[i]==0){
                swap(arr,++ptr1,i++);
            }
            else if(arr[i]==2)
                swap(arr,ptr2--,i);
            else
            i++;
        }
        display1D(arr);
    }
    public static void solve(){
        // int []arr=new int []{1,2,3,4,5,6,7,8,9,10};
        // rotate(arr, 7);
        // int []arr=new int []{2,8,-5,3,-4,5};
        // segregatenegandpos(arr);
        // int []arr=new int []{0,0,0,1,0,1,1,1,0,0,0};
        // segregateZerosandOnes(arr);
        // display1D(arr);
        int []arr=new int []{0,1,2,2,1,0,1,2,1,0,0,0};
        segregateZerosOnesAndTwos(arr);
    }
    public static void main(String[] args) {
        solve();       
    }
}