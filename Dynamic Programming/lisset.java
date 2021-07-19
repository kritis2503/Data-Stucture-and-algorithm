import java.util.*;
public class lisset {
    public static void print1D(int []arr){
        for(int a:arr)
            System.out.print(a+" ");
        System.out.println();
    }

    public static void print2D(int [][]arr){
        for(int []a:arr)
            System.out.println(a);
    }

    //LONGEST INCREASING SUBSEQUENCE============================================
    public static int longestIncreasingSubsequence(int []arr){
        int []dp=new int [arr.length];
        int ans=0;
        for(int i=0;i<arr.length;i++)
            ans=Math.max(ans, lis_LRm1(arr, i, dp));
        return ans;
    }
    public static int lis_LRm1(int []arr,int i,int[]dp){
        if(dp[i]!=0)
            return dp[i];
        int len=1;
        for(int j=i-1;j>=0;j--){
            if(arr[i]>arr[j]){
                int l=lis_LRm1(arr, j, dp);
                len=Math.max(l+1, len);
            }
        }
        return dp[i]=len;
    }
    public static int lis_dp(int []arr){
        int []dp=new int [arr.length];
        int ans=0;
        for(int i=0;i<arr.length;i++){
            int len=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    len=Math.max(len,dp[j]+1);
                }
            }
            dp[i]=len;
            ans=Math.max(dp[i],ans);
        }
        return ans;
    }
    //IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!
    //waf to calculate min no of deletion to make array sorted
    public static int minDeletion(int []arr){
        int []dp=new int [arr.length];
        int ans=0;
        for(int i=0;i<arr.length;i++){
            int len=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    len=Math.max(len,dp[j]+1);
                }
            }
            dp[i]=len;
            ans=Math.max(dp[i],ans);
        }
        return arr.length-ans;
    }
    public static int maxIncreasingSumSubsequence(int []arr,int n){
        int []dp=new int [n];
        int ans=0;
        for(int i=0;i<arr.length;i++){
            dp[i]=arr[i];
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j])
                    dp[i]=Math.max(dp[i],dp[j]+arr[i]);
            }
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }
    //
    public static int []lis_LRt1(int []arr){
        int []dp=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    dp[i]=Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }
    public static int[] lis_RLm2(int []arr){
        int []dp=new int [arr.length];
        for(int i=arr.length-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j])
                    dp[i]=Math.max(dp[i], dp[j]+1);
            }
        }
        return dp;
    }
    public static int longestBiotinicSequence_uphill(int []arr){
        int []lis=lis_LRt1(arr);
        int []lds=lis_RLm2(arr);
        int len=1;
        for(int i=0;i<lis.length;i++){
            len=Math.max(len, lis[i]+lds[i]-1);
        }
        return len;
    }

    public static int[] longestDecreasingSubsequence_LR(int []arr){
        int []dp=new int [arr.length];
        for(int i=0;i<arr.length;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]<arr[j])
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        return dp;
    }

    public static int[] longestDecreasingSubsequence_RL(int []arr){
        int []dp=new int [arr.length];
        for(int i=arr.length-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]<arr[j])
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        return dp;
    }
    public static int longestBiotinicSequence_downhill(int []arr){
        int []a=longestDecreasingSubsequence_LR(arr);
        int []b=longestDecreasingSubsequence_RL(arr);
        int len=1;
        for(int i=0;i<arr.length;i++){
            len=Math.max(len, a[i]+b[i]-1);
        }
        return len;
    }
    public static int numberoflis(int []arr){
        int n=arr.length;
        int []dp=new int [n];
        int []count=new int[n];
        int maxLen=0;
        int maxCount=0;

        for(int i=0;i<n;i++){
            dp[i]=1;
            count[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    if(dp[i]<dp[j]+1){
                        dp[i]=dp[j]+1;
                        count[i]=count[j];
                    }
                    else if(dp[i]==dp[j]+1){
                        count[i]+=count[j];
                    }
                }
            }
            if(dp[i]>maxLen){
                maxLen=dp[i];
                maxCount=count[i];
            }else
            if(dp[i]==maxLen)
                maxCount+=count[i];
        }
        return maxCount;
    }

    //USING REVERSE ENGINEERING METHOD
    public static void allLis(int []arr,ArrayList<Integer>[]mapping,int i,int len,String ans){
        if(len==1){
            System.out.println(arr[i]+ans+"");
            return;
        }
        for(Integer idx:mapping[len-1]){
            if(idx<i && arr[idx]<arr[i])
                allLis(arr, mapping, idx, len-1, arr[idx]+", "+ans);
        }
    }
    public static void printAlllis(int []arr){
        int n=arr.length;
        int []dp=new int [n];
        int maxLen=0;

        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                   dp[i]=Math.max(dp[i], dp[j]+1);
                }
        }
        maxLen=Math.max(maxLen, dp[i]);
    }
        ArrayList<Integer>[] mapping =new ArrayList[maxLen+1];
        for(int i=0;i<=maxLen;i++)
            mapping[i]=new ArrayList<>();
        
        for(int i=0;i<dp.length;i++){
            mapping[dp[i]].add(i);
        }
        for(Integer i:mapping[maxLen])
            allLis(arr, mapping, i, maxLen, "");
        System.out.println(maxLen);

    }
    //geeksforgeeks question building bridges and Leetcode 354============
    public static int buildBridges(int [][]arr){
        Arrays.sort(arr,(a,b)->{
            return a[0]-b[0];
        });
        int n=arr.length;
        int []dp=new int [n];
        int len=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j++){
                if(arr[i][0]>arr[j][0] && arr[i][1]>arr[i][1])
                    dp[i]=Math.max(dp[i], dp[j]+1);
            }
            len=Math.max(len, dp[i]);
        }
        return len;
    }
    public static void main(String[] args) {
        int []arr={0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14};
        printAlllis(arr);        
    }
}

