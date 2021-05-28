import java.util.*;

import javax.sound.midi.SysexMessage;

public class intro{
//Dynamic Programming is a way to save time and get results faster. it is done by saving the previous 
//found results in a storage unit like array, linkedList, hashmap, hashset, tree , queue,stack. the basic 
//criteria to chose the storage based on convenicece and least time for retrieval. 
//It is usually recursive based.
//Must use steps to find dp solution of a problem
//1-faith
//2-recursive tree diagram
//3-code
//4-memorization
//5-memorization output study (OBSERVATION)
//6-Tabulation
//7-optimisation
    public static void print1D(int []arr){
        for(int ele:arr)
            System.out.print(ele+" ");
        System.out.println();
    }
    public static void print2D(int [][]arr){
        for(int []a:arr)
            print1D(a);
    }
    public static int fib_m1(int n,int []qb){
        if(n<=1)
            return n;
        if(qb[n]!=0)return qb[n];
        return qb[n]=(fib_m1(n-1, qb)+ fib_m1(n-2, qb));
    }
    public static int grid_m1(int sr,int sc,int dr,int dc,int [][]qb){
        if(sr==dr && sc==dc)
            return 1;
        if(qb[sr][sc]!=-1)
            return qb[sr][sc];
        int count =0;
        if(sr+1<=dr)
            count+=grid_m1(sr+1, sc, dr, dc, qb);
        if(sc+1<=dc)
            count+=grid_m1(sr, sc+1, dr, dc, qb);
        if(sr+1<=dr && sc+1<=dc)
            count+=grid_m1(sr+1, sc+1, dr, dc, qb);
        return qb[sr][sc]=count;
    }
    public static int grid_t1(int SR,int SC,int dr,int dc,int [][]dp){
        for(int sr=dr;sr>=SR;sr--){
            for(int sc=dc;sc>=SC;sc--){
                if(sr==dr && sc==dc){
                    dp[sr][sc]=1;
                    continue;
                }
                int count=0;
                if(sr+1<=dr)
                    count+=dp[sr+1][sc];
                if(sc+1<=dc)
                    count+=dp[sr][sc+1];
                if(sr+1<=dr && sc+1<=dc)
                    count+=dp[sr+1][sc+1];
                dp[sr][sc]=count;
            }
        }
        return dp[0][0];
    }
    public static int grid_m2jump(int sr,int sc,int dr,int dc,int [][]qb){
        if(sr==dr && sc==dc)
            qb[sr][sc]=1;
        if(qb[sr][sc]!=-1)
            return qb[sr][sc];
        int count=0;
        for(int i=1;sr+i<=dr;i++)
            count+=grid_m2jump(sr+i, sc, dr, dc, qb);
        for(int i=1;sc+i<=dc;i++)
            count+=grid_m2jump(sr, sc+i, dr, dc, qb);
        for(int i=1;sr+i<=dr && sc+i<=dc ;i++)
            count+=grid_m2jump(sr+i, sc+i, dr, dc, qb);
        return qb[sr][sc]=count;
    }
    public static int grid_t2jump(int SR,int SC,int dr,int dc,int [][]dp){
        for(int sr=dr;sr>=SR;sr--){
            for(int sc=dc;sc>=SC;sc--){
                if(sc==dc && dr==sr){
                    dp[sr][sc]=1;
                    continue;
                }
                int count=0;
                for(int jump=1;jump+sr<=dr;jump++)
                    count+=dp[sr+jump][sc];
                for(int jump=1;jump+sc<=dc;jump++){
                    count+=dp[sr][sc+jump];
                }
                for(int jump=1;sr+jump<=dr && sc+jump<=dc;jump++)
                    count+=dp[sr+jump][sc+jump];
                dp[sr][sc]=count;
            }
        }
        return dp[0][0];
    }
    public static void grid(int n,int m){
        int [][]qb=new int [n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                qb[i][j]=-1;
        }
        System.out.println(grid_m1(0, 0, n-1, m-1, qb));
        // System.out.println(grid_m2jump(0, 0, n-1, m-1, qb));
        // System.out.println(grid_t1(0, 0, n-1, n-1, qb));
        // System.out.println(grid_t2jump(0, 0, n-1, m-1, qb));
        print2D(qb);
    }
    public static int boardpath_01m(int si,int ei,int []qb){
        if(si==ei)
            return 1;
        if(qb[si]!=-1)
            return qb[si];
        int count=0;
        for(int i=1;i<=6;i++){
            if(si+i<=ei)
                count+=boardpath_01m(si+i, ei, qb);
        }
        return qb[si]=count;
    }
    public static int boardpath_01t(int SI,int ei,int []dp){
        for(int si=ei;si>=0;si--){
            if(si==ei){
                dp[si]=1;
                continue;
            }
            int count=0;
            for(int i=1;i<=6;i++){
                if(si+i<=ei)
                    count+=dp[si+i];
            }
            dp[si]=count;
        }
        return dp[0];
    }
    public static int boardpath_02opti(int SI,int ei){
        LinkedList<Integer>list=new LinkedList<>();
        for(int si=ei;si>=SI;si--){
            if(si>=ei-1)
                list.addFirst(1);
            else{
                if(list.size()<=6)
                    list.addFirst(list.getFirst()*2);
                else list.addFirst(list.getFirst()*2-list.removeLast());
            }
        }
        return list.getFirst();
    }
    public static void boardpath(int n){
        int []dp=new int [n+1];
        Arrays.fill(dp,-1);
        // System.out.println(boardpath_01m(0, 10, dp));
        // System.out.println(boardpath_01t(0, 10, dp));
        // print1D(dp);
        System.out.println(boardpath_02opti(1, 100));
    }
    public static int climbStairs_m1(int n,int []dp){
        if(n<=1)
            return dp[n]=1;
        if(dp[n]!=-1)
            return dp[n];
        return dp[n]=climbStairs_m1(n-1, dp)+climbStairs_m1(n-2, dp);
    }
    public static int climbStairs_t1(int N,int []dp){
        for(int n=0;n<=N;n++){
            if(n<=1){
                dp[n]=1;
                continue;
            }
            dp[n]=dp[n-1]+dp[n-2];
        }
        return dp[N];
    }
    public static int minCostStairs_m1(int []cost,int idx,int []dp){
        if(idx<=1)
            return dp[idx]=cost[idx];
        int minCost=Math.min(minCostStairs_m1(cost, idx-1, dp), minCostStairs_m1(cost, idx-2, dp));
        return dp[idx]=minCost+ (idx==cost.length?0:cost[idx]);
    }
    public static int minCostStairs_t1(int []cost){
        int []dp=new int [cost.length+1];
        for(int idx=0;idx<=cost.length ;idx++){
            if(idx<=1){
                dp[idx]=cost[idx];
                continue;
            }
            int minCost=Math.min(dp[idx-1], dp[idx-2]);
            dp[idx]=minCost+(idx==cost.length?0:cost[idx]);
        }
        return dp[cost.length];
    }
    public static int minCostStairs_opti(int []cost){
        int a=cost[0];
        int b=cost[1];
        for(int i=2;i<cost.length;i++){
            int minCost=Math.min(a, b)+cost[i];
            a=b;
            b=minCost;
        }
        return Math.min(a, b);
    }
    public static void climbStair(int n){
        int []dp=new int [n+1];
        Arrays.fill(dp, -1);
        System.out.println(climbStairs_m1(n, dp));
        // System.out.println(climbStairs_t1(n, dp));
        print1D(dp);
    }
    static long mod = (int) 1e9 + 7;
    public static long friendPairing_m1(int n,long[]dp){
        if(n<=1)
            return dp[n]=1;
        if(dp[n]!=0)
            return dp[n];
        return dp[n]=(friendPairing_m1(n-2, dp)*(n-1)%mod)+(friendPairing_m1(n-1, dp)%mod)%mod;
    }
    public static long friendPairing_t1(int N,long []dp){
        for(int n=0;n<=N;n++){
            if(n<=1){
                dp[n]=1;
                continue;
            }
            dp[n]=(dp[n-2]*(n-1)%mod)+(dp[n-1]%mod)%mod;
        }
        return dp[N];
    }
    public static int printfriendPairing(String friend,String ans){
        if(friend.length()==0){
            System.out.println(ans);
            return 1;
        }
        char ch=friend.charAt(0);
        int count=0;
        count+=printfriendPairing(friend.substring(1), ans+" "+ch);
        for(int i=1;i<friend.length();i++){
            String rstr=friend.substring(1, i)+friend.substring(i+1);
            count+=printfriendPairing(rstr, ans+" "+ch+friend.charAt(i));
        }
        return count;
    }

    public static void friendPairing(int n){
        long []dp=new long[n+1];
        // System.out.println(friendPairing_m1(n, dp));
        // System.out.println(friendPairing_t1(n, dp));
        // for(long ele:dp)
        //     System.out.print(ele+" ");
        System.out.println(printfriendPairing("ABCD", ""));
    }

    public static int goldmine_m1(int r,int c,int [][]mat,int [][]dp,int [][]dir){
        if(c==mat[0].length-1){
            return dp[r][c]=mat[r][c];
        }
        if(dp[r][c]!=0)
            return dp[r][c];
        int maxGold=0;
        for(int d=0;d<3;d++){
            int x=r+dir[d][0];
            int y=c+dir[d][1];
            if(x>=0 && x<mat.length)
                maxGold=Math.max(maxGold, goldmine_m1(x, y, mat, dp, dir));
        }
        return dp[r][c]=maxGold+mat[r][c];
    }
    public static int goldmine_t1(int [][]mat,int [][]dir){
        int n=mat.length;
        int m=mat[0].length;
        int [][]dp=new int [n][m];
        for(int c=m-1;c>=0;c--){
            for(int r=n-1;r>=0;r--){
                if(c==mat[0].length-1){
                    dp[r][c]=mat[r][c];
                    continue;
                }
                int maxGold=0;
                for(int d=0;d<3;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0 && x<mat.length)
                        maxGold=Math.max(maxGold, dp[x][y]);
                }
                dp[r][c]=maxGold;
            }
        }
        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, dp[i][0]);
        }
        return maxGold;
    }
    public static void goldMine() {
        int[][] mat = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        int[][] dp = new int[mat.length][mat[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, goldmine_m1(i, 0, mat, dp, dir));
        }

        System.out.println(goldmine_t1(mat, dir));
        print2D(dp);
        // System.out.println(maxGold);
    }

    public static void main(String[] args) {
        // int []qb=new int [11];
        // System.out.println(fib_m1(10, qb));
        // grid(5, 5);
        // boardpath(10);
        // climbStair(10); 
        // friendPairing(20);      
    }
}