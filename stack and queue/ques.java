import java.util.*;
public class ques {
    public static void ngol(int []arr){
        int n=arr.length;
        Stack<Integer>st=new Stack<>();
        int []ans=new int [n];
        ans[n-1]=-1;
        st.push(n-1);
        for(int i=n-2;i>=0;i--){
            while(st.size()>0 && arr[st.peek()]<=arr[i])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(i);
        }
        //return ans;
        display(ans);
    }
    public static void ngor(int []arr){
        int n=arr.length;
        Stack<Integer>st=new Stack<>();
        st.push(0);
        int []ans=new int [n];
        ans[0]=-1;
        for(int i=1;i<n;i++){
            while(st.size()>0 && arr[st.peek()]<=arr[i]){
                st.pop();
            }
            if(st.isEmpty())
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(i);
        }
        display(ans);
    }
    public static void nsor(int []arr){
        int n=arr.length;
        Stack<Integer>st=new Stack<>();
        st.push(0);
        int []ans=new int [n];
        ans[0]=-1;
        for(int i=1;i<n;i++){
            while(st.size()>0 && arr[st.peek()]>=arr[i]){
                st.pop();
            }
            if(st.isEmpty())
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(i);
        }
        display(ans);
    }
    public static void nsol(int []arr){
        int n=arr.length;
        Stack<Integer>st=new Stack<>();
        int []ans=new int [n];
        ans[n-1]=-1;
        st.push(n-1);
        for(int i=n-2;i>=0;i--){
            while(st.size()>0 && arr[st.peek()]>=arr[i])
                st.pop();
            if(st.size()==0)
                ans[i]=-1;
            else
                ans[i]=st.peek();
            st.push(i);
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
        ngor(arr);
        //ngol(arr);
    }
}
