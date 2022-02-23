import java.util.Arrays;

public class ques {
    public static int longestPalindromicSubseqence(String str,int i,int j,int [][]dp){
        System.out.println("hey");
        
        if(i>=j)
            return dp[i][j]=(i==j)?1:0;
        if(dp[i][j]!=-1)
            return dp[i][j];
        if(str.charAt(i)==str.charAt(j))
            return dp[i][j]=2+longestPalindromicSubseqence(str, i+1, j-1, dp);
        return dp[i][j]=Math.max(longestPalindromicSubseqence(str,i+1,j,dp),longestPalindromicSubseqence(str, i, j-1, dp));
    }
    public static void main(String[] args) {
        String str="geeksforgeeks";
        System.out.println(str);
        int n=str.length();
        int [][]dp=new int[n+1][n+1];
        for(int []d:dp)
            Arrays.fill(d, -1);
        System.out.println(longestPalindromicSubseqence(str, 0, n-1, dp));
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
