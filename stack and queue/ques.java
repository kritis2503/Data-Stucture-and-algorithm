import java.util.*;
public class ques {
    public static void ngor(int []arr){
        int n=arr.length-1;
        Stack<Integer>st=new Stack<>();
        int []ans=new int [n];
        ans[n]=-1;
        st.push(n);
        for(int i=n-1;i>=0;i--){
            while(st.size()>0 && arr[st.peek()]<=arr[i])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
        }
        //return ans;
        display(ans);
    }
    public static void display(int []arr){
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
    }
    public static void main(String[] args) {
        int arr[]= {11, 13, 21, 3};
        display(arr);
    
    }
}
