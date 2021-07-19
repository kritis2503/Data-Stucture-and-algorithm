import java.util.*;
public class targetsum {
    public static void print1D(int []arr){
        for(int a:arr)
            System.out.print(a+" ");
        System.out.println();
    }
    public static void print2D(int [][]arr){
        for(int []a:arr)
            print1D(a);
    }
    public static int coinChangePermuatation_memo(int []coin,int tar,int []dp,String ans){
        if(tar==0){
            System.out.println(ans);
            return dp[tar]=1;
        }
        if(dp[tar]!=0)
            return dp[tar];
        int count=0;
        for(int ele:coin){
            if(tar-ele>=0)
            count+=coinChangePermuatation_memo(coin, tar-ele, dp,ele+","+ans);
        }
        return dp[tar]=count;
    }
    public static int coinChangePermuatation_tab(int []coin,int tar,int []dp){
        dp[0]=1;
        for(int i=1;i<=tar;i++){
            int count=0;
            for(int ele:coin){
                if(i-ele>=0)
                    count+=dp[i-ele];
            }
            dp[i]=count;
        }
        return dp[tar];
    }
    public static int coinChangeCombination_memo(int []coin,int tar,int idx,int[][]dp,String ans){
        if(tar==0){
            System.out.println(ans);
            return dp[tar][idx]=1;
        }
        if(dp[tar][idx]!=0)
            return dp[tar][idx];
        int count=0;
        for(int i=idx;i<coin.length;i++){
            if(tar-coin[i]>=0)
                count+=coinChangeCombination_memo(coin, tar-coin[i], i, dp,coin[i]+","+ans);
        }
        return dp[tar][idx]=count;
    }
    public static int coinChangeCombination_2DP(int[] arr, int Tar, int LI, int[][] dp) {

        for (int li = 0; li <= LI; li++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (tar == 0) {
                    dp[li][tar] = 1;
                    continue;
                }

                for (int i = li; i >= 0; i--)
                    if (tar - arr[i] >= 0) {
                        dp[li][tar] += dp[i][tar - arr[i]];
                    }
            }
        }

        return dp[LI][Tar];
    }
    //better than memorisation.
    public static int coinChangeCombination_tab1(int []coin,int tar){
        int []dp=new int [tar+1];
        for(int ele:coin){
            for(int i=ele;i<=tar;i++){
                if(i-ele>=0)
                    dp[i]+=dp[i-ele];
            }
        }
        return dp[tar];
    }
    public static void coinChange(int []arr,int tar){
        int []dp=new int [tar+1];
        // System.out.println(coinChangePermuatation_memo(arr, tar, dp,""));
        // System.out.println(coinChangeCombination_memo(arr, tar, 0, dp,""));
        System.out.println(coinChangePermuatation_tab(arr, tar, dp));
        print1D(dp);

    }
    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/
    public static int numberOfSol(int []c,int rhs){
        int []dp=new int [rhs+1];
        for(int ele:c){
            for(int i=ele;i>=0;i--){
                if(i-ele>=0)
                    dp[i]+=dp[i-ele];
            }
        }
        return dp[rhs];
    }
    public static void printNumberofSol(int []c,int rhs,int idx,int tar,int []sol){
        if(rhs==0){
            for(int i=0;i<c.length;i++){
                System.out.print(c[i]+ "("+sol[i]+")"+"  ");
                if(i!=c.length-1){
                    System.out.print("+");
                }
            }
            System.out.println("="+tar);
            return;
        }

        for(int i=idx;i<c.length;i++){
            if(rhs-c[i]>=0){
                sol[i]++;
                //System.out.println("heyyy");
                printNumberofSol(c, rhs-c[i], i, tar, sol);
                //System.out.println("heyyy2");
                sol[i]--;
            }
        }
        return;
    }
    public static void numofsol(){
        int []c={2,2,3};
        int rhs=4;
        int []sol=new int [c.length];
        printNumberofSol(c, rhs, 0, rhs,sol );
    }
    public static void solve(){
        // int []arr={2,3,5,7};
        // coinChange(arr, 10);
        numofsol();
    }

    //https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
    //in this question we use the concet of lobgest common subsequence, ie call with and without the 
    //element.
    public static int subsetSum_1(int []arr,int tar,int n,int [][]dp){
        if(tar==0 || n==arr.length){
             return dp[n][tar]=(tar==0)?1:0;
        }
        if(dp[n][tar]!=-1)
            return dp[n][tar];
        boolean ans=false;
        if(tar-arr[n-1]>=0)
            ans=ans||(subsetSum_1(arr, tar-arr[n-1], n-1, dp)==1);
        ans=ans||(subsetSum_1(arr, tar, n-1, dp)==1);
        if(ans)
            return dp[n][tar]=1;
        return dp[n][tar]=0;
    }
    public static boolean subsetSum_2(int []arr,int Tar){
        int N=arr.length;
        boolean [][]dp=new boolean [N+1][Tar+1];
        for(int n=0;n<=N;n++){
            for(int tar=0;tar<=Tar;tar++){
                if(tar==0){
                    dp[n][tar]=true;
                    continue;
                }
                boolean ans=false;
                if(tar-arr[n-1]>=0)
                    ans=ans||dp[n-1][tar-arr[n-1]];
                ans=ans||dp[n-1][tar];
                dp[n][tar]=ans;
            }
        }
        return dp[N][Tar];
    }
    public static int totwaysofsubset(int []arr,int Tar){
        int N=arr.length;
        int [][]dp=new int [N+1][Tar+1];
        for(int n=0;n<=N;n++){
            for(int tar=0;tar<=Tar;tar++){
                if(tar==0){
                    dp[n][tar]=1;
                    continue;
                }
                if(tar-arr[n-1]>=0)
                    dp[n][tar]+=dp[n-1][tar-arr[n-1]];
                dp[n][tar]+=dp[n-1][tar];
            }
        }
        return dp[N][Tar];
    }
    public static void subsetSum(int []arr,int tar){
        int n=arr.length;
        int [][]dp=new int[n+1][tar+1];
        for(int []d:dp)
            Arrays.fill(d, -1);
        boolean a=subsetSum_1(arr, tar, n, dp)==1;
        System.out.println(a);
    }
    //knapsack 0-1 problem
    public static void knapsack(int []wt,int []val,int W){
        int n=wt.length;
        int [][]dp=new int [n+1][W+1];
        System.out.println(knapsack_rec(wt, val, W, n, dp));
    }
    public static int knapsack_rec(int []wt,int []val,int w,int n,int [][]dp){
        if(n==0||w==0)
            return dp[n][w]=0;
        if(dp[n][w]!=0)
            return dp[n][w];
        int max=0;
        if(w-wt[n-1]>=0)
            max=knapsack_rec(wt, val, w-wt[n-1], n-1, dp)+val[n-1];
        max=Math.max(max, knapsack_rec(wt, val, w, n-1, dp));
        return dp[n][w]=max;
    }
    //knapsack problem is just like target sum problem.
    //here greedy can't be used because in the greedy approach we will take 
    public static int knapsack_dp(int []wt,int []val,int W){
        int N=wt.length;
        int [][]dp=new int [N+1][W+1];
        for(int n=0;n<=N;n++){
            for(int w=0;w<=W;w++){
                if(n==0||w==0){
                    dp[n][w]=0;
                    continue;
                }
                int max=0;
                if(w-wt[n-1]>=0)
                    max=dp[n-1][w-wt[n-1]];
                max=Math.max(max, dp[n-1][w]);
                dp[n][w]=max;
            }
        }
        return dp[N][W];
    }
    //KNAPSACK UNBOUNDED
    //note in combination we find all the combiation of the particular index and them move to another 
    //index for the computations
    public static int knapsack_unbounded(int []wt,int []val,int weight){
        int []dp=new int [weight+1];
        for(int i=0;i<wt.length;i++){
            for(int w=wt[i];w<=weight;w++)
                dp[w]=Math.max(dp[w], dp[w-wt[i]]+val[i]);
        }
        return dp[weight];
    }

    public static void main(String[] args) {
        solve();
    }
    
}
