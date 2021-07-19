import java.util.*;
public class cutType {
    public static void print1D(int []arr){
        for(int ele:arr)
            System.out.print(ele+" ");
        System.out.println();
    }
    public static void print2D(int [][]arr){
        for(int []a:arr)
            print1D(a);
    }
    public static void print2Dboolean(boolean [][]dp){
        for(boolean[] b:dp)
        {
            for(boolean a:b)
               System.out.print(a+" ");
                System.out.println();
        }
    }
    public static int mcm_rec(int []arr,int si,int ei,int [][]dp){
        if(si+1==ei)
            return dp[si][ei]=0;
        if(si==ei)
            return dp[si][ei]=0;
        if(dp[si][ei]!=-1)
            return dp[si][ei];
        int min=(int)1e9;
        for(int cut=si+1;cut<ei;cut++){
            int lans=mcm_rec(arr, si, cut, dp);
            int rans=mcm_rec(arr,cut,ei,dp);
            int ans=lans+arr[si]*arr[cut]*arr[ei]+rans;
            min=Math.min(min,ans);
        }
        return dp[si][ei]=min;
    }
    public static int mcm_dp(int []arr){
        int n=arr.length;
        int [][]dp=new int[n][n];
        for(int gap=2;gap<n;gap++){ //see the dp for explaation
            for(int si=0,ei=gap;ei<n;si++,ei++){
                int min=(int)1e9;
                for(int cut=si+1;cut<arr.length;cut++){
                    int lans=dp[si][cut];
                    int rans=dp[cut][ei];
                    min=Math.min(min, lans+rans+arr[si]*arr[cut]*arr[ei]);
                }
                dp[si][ei]=min;
            }
        }
        print2D(dp);
        return dp[0][arr.length-1];
    }
    //cost of multiplication is $3 and sum of addition is $5
    //so in order to get one position of res matrix, we do q operations, in a multiplication of
    //pxq and qxr, q-1 additions. so for this ques we have (q*3+(q-1)*5)*p*r.
    public static int mcm_dpwithCost(int []arr){
        int n=arr.length;
        int [][]dp=new int[n][n];
        for(int gap=2;gap<n;gap++){ //see the dp for explaation
            for(int si=0,ei=gap;ei<n;si++,ei++){
                int min=(int)1e9;
                for(int cut=si+1;cut<arr.length;cut++){
                    int lans=dp[si][cut];
                    int rans=dp[cut][ei];
                    int p=arr[si],q=arr[cut],r=arr[ei];
                    min=Math.min(min, lans+rans+p*r*((q-1)*5+q*3));
                }
                dp[si][ei]=min;
            }
        }
        print2D(dp);
        return dp[0][arr.length-1];
    }
    public static void mcm(){
        int []arr=new int []{1,2,3,4};
        int [][]dp=new int [arr.length][arr.length];
        for(int []d:dp)
            Arrays.fill(d,-1);
        //System.out.println(mcm_rec(arr, 0, arr.length-1, dp));
        System.out.println(mcm_dp(arr));
        //print2D(dp);
    }

    public static class pair{
        int minValue=(int)1e9;
        int maxValue=-(int)1e9;
        String minExpression="";
        String maxExpression="";
        pair(){

        }
        pair(int minValue,int maxValue,String minExpression,String maxExpression){
            this.minValue=minValue;
            this.maxValue=maxValue;
            this.minExpression=minExpression;
            this.maxExpression=maxExpression;
        }
    }
    public static int eval(int a,int b,char ch){
        if(ch=='+')
            return a+b;
        return a*b;
    }

    public static pair minmaxValue(String str,int si,int ei,pair[][]dp){
        if (si == ei) {
            int val = str.charAt(si) - '0';
            return dp[si][ei] = new pair(val, val, val + "", val + "");
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        pair res = new pair();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pair lans = minmaxValue(str, si, cut - 1, dp); // (si,i-1)
            pair rans = minmaxValue(str, cut + 1, ei, dp); // (i+1,ei);, first number = (si, i-1), second number =
                                                                // (i +1, ii - 1), i = ii

            int minValue = eval(lans.minValue, rans.minValue, str.charAt(cut));
            int maxValue = eval(lans.maxValue, rans.maxValue, str.charAt(cut));

            // res.minValue = Math.min(res.minValue, minValue);
            // res.maxValue = Math.max(res.maxValue, maxValue);

            if (minValue < res.minValue) {
                res.minValue = minValue;
                res.minExpression = "(" + lans.minExpression + " " + str.charAt(cut) + " " + rans.minExpression + ")";
            }

            if (maxValue > res.maxValue) {
                res.maxValue = maxValue;
                res.maxExpression = "(" + lans.maxExpression + " " + str.charAt(cut) + " " + rans.maxExpression + ")";
            }

        }

        return dp[si][ei] = res;
    }  
    public static void minmaxValue_main(){
        String str="1+2*3+4*5";
        int n=str.length();
        pair [][]dp=new pair[n][n];
        pair ans=minmaxValue(str, 0, n-1, dp);
        System.out.println("maximum value "+ans.maxValue+" at "+ans.maxExpression);
        System.out.println("minimum value "+ans.minValue+" at "+ans.minExpression);
    }
    //leetcode 132
    public static int minCut_memo(String str,int si,boolean [][]isPalindrome,int []dp){
        if(isPalindrome[si][str.length()-1])
            return dp[si]=0;
        if(dp[si]!=-1)
            return dp[si];

        int min=(int)1e9;
        for(int cut=si;cut<str.length();cut++){
            if(isPalindrome[si][cut])
                min=Math.min(min,minCut_memo(str, cut+1, isPalindrome, dp)+1);
        }
        return dp[si]=min;
    }
    public static int minCut_dp(String str,boolean [][]isPalindrome){
        int n=str.length();
        int []dp=new int [n+1];
        for(int i=n-1;i>=0;i--){
            if(isPalindrome[i][n-1]){
                dp[i]=0;
                continue;
            }
            int min=(int)1e9;
            for(int cut=i;cut<n;cut++){
                if(isPalindrome[i][cut])
                    min=Math.min(min,dp[cut+1]+1);
            }
            dp[i]=min;
        }
        print1D(dp);
        return dp[0];
    }
    public static void minCut(String str){
        int n=str.length();
        boolean[][]isPalindrome=new boolean [n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0)
                    isPalindrome[i][j]=true;
                else 
                    if(gap==1 && str.charAt(i)==str.charAt(j))
                        isPalindrome[i][j]=true;
                else
                    if(str.charAt(i)==str.charAt(j) && isPalindrome[i+1][j-1])
                        isPalindrome[i][j]=true;
            }
        }
        int []dp=new int [n];
        Arrays.fill(dp,-1);
        //print2Dboolean(isPalindrome);
        System.out.println(minCut_memo(str, 0, isPalindrome, dp));
        print1D(dp);
        System.out.println(minCut_dp(str, isPalindrome));
        
    }
   public static int maxCoins_memo(int []nums,int si,int ei,int [][]dp){
       if(dp[si][ei]!=-1){
           return dp[si][ei];
       }
       int lval=si-1==-1?1:nums[si-1];
       int rval=ei+1==nums.length?1:nums[ei+1];

       int maxAns=0;
       for(int cut=si;cut<=ei;cut++){
            int lans = (cut == si) ? 0 : maxCoins_memo(nums, si, cut - 1, dp);
            int rans = (cut == ei) ? 0 : maxCoins_memo(nums, cut + 1, ei, dp);

           maxAns=Math.max(maxAns, lans+lval*nums[cut]*rval+rans);
       }
       return dp[si][ei]=maxAns;
   }
   
   public static void maxCoins(){
       int []nums=new int []{3,1,5,8};
       int [][]dp=new int [nums.length][nums.length];
       for(int []d:dp)
            Arrays.fill(d, -1);
        System.out.println(maxCoins_memo(nums, 0,nums.length-1, dp));
        print2D(dp);
   }

   public static class pairBoolean{
       int trueWays=0;
       int falseWays=0;

        pairBoolean(int trueWays,int falseWays){
            this.trueWays=trueWays;
            this.falseWays=falseWays;
        }
   }
   
   public static pairBoolean eval (pairBoolean left,pairBoolean right,char operator){
       int mod=1003;
       int totalWays=((left.trueWays+left.falseWays)%mod*(right.trueWays+right.falseWays)%mod)%mod;
       pairBoolean ans=new pairBoolean(0,0);

       if(operator=='&'){
           ans.trueWays=(ans.trueWays+ans.falseWays)%mod;
           ans.falseWays=(totalWays-ans.trueWays)%mod;
       }else
       if(operator=='|'){
           ans.falseWays=(left.falseWays*right.falseWays)%mod;
           ans.trueWays=(totalWays-ans.falseWays)%mod;
       }else{
           ans.trueWays=((left.falseWays*right.trueWays)%mod+(left.trueWays*right.falseWays)%mod)%mod;
           ans.falseWays= (totalWays-ans.trueWays)%mod;
       }
       return ans;
   }

   public static pairBoolean booleanPare(String str, int si,int ei,pairBoolean[][]dp){
       if(si==ei){
           char ch=str.charAt(si);
           return new pairBoolean(ch=='T'?1:0, ch=='F'?1:0);
       }
       if(dp[si][ei]!=null)
            return dp[si][ei];
        pairBoolean myAns=new pairBoolean(0, 0);
        for(int cut=si+1;cut<ei;cut+=2){
            char operator=str.charAt(cut);
            pairBoolean lans=booleanPare(str, si, cut-1, dp);
            pairBoolean rans=booleanPare(str, cut+1, ei, dp);

            pairBoolean recAns=eval(lans, rans, operator);
            myAns.trueWays=(myAns.trueWays+recAns.trueWays)%1003;
            myAns.falseWays=(myAns.falseWays+recAns.falseWays)%1003;
        }
        return dp[si][ei]=myAns;
   }

    public static int countWays(int N,String str){
        pairBoolean[][]dp=new pairBoolean[N][N];
        pairBoolean ans=booleanPare(str, 0, N, dp);
        return ans.trueWays;
    }
    public static void main(String[] args) {
        //mcm();
        //minmaxValue_main();
        //minCut("ppabccbadd");
        maxCoins();
    }
}
