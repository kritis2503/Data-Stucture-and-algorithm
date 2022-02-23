import java.util.*;

import javax.lang.model.element.Element;
public class string {
    public static void print1D(int []arr){
        for(int ele:arr)
            System.out.print(ele+" ");
        System.out.println();
    }
    public static void print2D(int [][]arr){
        for(int []a:arr)
            print1D(a);
    }
    //QUESTION-1 LONGEST PALINDROMIC SUBSEQUENCE
    //we need to find the longest palindronice subsequence, so we take two indices i and j.
    //i points to the start of string and j to the end. if both char at i and j are equal 
    //we add them to our subsequence else we take turn in adding them.
    public static int longestPalidromeSubsequence_m1(String s,int i,int j,int [][]dp){
        if(i>=j)
            return dp[i][j]=(i==j)?1:0;
        if(dp[i][j]!=0)
            return dp[i][j];
        if(s.charAt(i)==s.charAt(j))
            return dp[i][j]=longestPalidromeSubsequence_m1(s, i+1, j-1, dp)+2;
        return dp[i][j]=Math.max(longestPalidromeSubsequence_m1(s, i+1, j, dp), longestPalidromeSubsequence_m1(s, i, j-1, dp));
    }
    public static int longestPalidromeSubsequence_t1(String s,int [][]dp){
        int n=s.length();
        for(int gap=0;gap<n;gap++){
            for(int i=0, j=gap;j<n;i++,j++){
                if(i==j){
                    dp[i][j]=1;
                    continue;
                }
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                    continue;
                }
                dp[i][j]=Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }
    
    public static String printlongestPalindromeSubsequence(String s){
        int n=s.length();
        String[][]dp=new String[n][n];
        for (String[] d : dp)
            Arrays.fill(d, "");

        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(i==j){
                    dp[i][j]=s.charAt(i)+"";
                    continue;
                }
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=s.charAt(i)+dp[i+1][j-1]+s.charAt(j);
                }else{
                    dp[i][j] = (dp[i + 1][j].length() > dp[i][j - 1].length()) ? dp[i + 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[0][n-1];
    }
    //in this approach rather than amking making a 2-D dp of string we use the dp already made and
    //traverse according to the way it was made but in the reverse ordeer. something like reverse 
    //engineering.
    public static String printlongestPalindromeSubsequence_02(String s,int i,int j, int [][]dp){
        if(dp[i][j]==0)
            return "";
        if(dp[i][j]==1)
            return s.charAt(i)+"";
        String ans="";
        if(s.charAt(i)==s.charAt(j))
            ans=s.charAt(i)+printlongestPalindromeSubsequence_02(s, i+1, j-1, dp)+s.charAt(i);
        else{
            if(dp[i+1][j]>dp[i][j-1])
                ans=printlongestPalindromeSubsequence_02(s, i+1, j, dp);
            else    
                ans=printlongestPalindromeSubsequence_02(s, i, j-1, dp);
        }
        return ans;
    }

    public static void longestPalidromeSubsequence(String s){
        int n=s.length();
        int [][]dp=new int [n][n];
        System.out.println(longestPalidromeSubsequence_m1(s, 0, n-1, dp));
        // System.out.println(longestPalidromeSubsequence_t1(s, dp));
        // print2D(dp);
        System.out.println(printlongestPalindromeSubsequence(s));
        // System.out.println(longestPalindromeSubseq_String(s));
        System.out.println(printlongestPalindromeSubsequence_02(s, 0, n-1, dp));

    }

    //*****************************************************************************


    //QUESTION -2 DISTINCT SUBSEQUENCE
    //in this question,we have to find how many distict subsequence of one string say "s" is equal to
    //string 't'.
    //For doing this, we check if both the char of the string are same, either we can include them 
    //or leave them. if it is not same is move on and check for next element in string s.
    //the faith we are using is that the call which is being called is returning number of susequence till
    //that point and furthurmore.
    public static int distinctSubsequence_m1(String s,String t,int i,int j,int [][]dp){
        if(j==0)
            return dp[i][j]=1;
        if(i<j)
            return dp[i][j]=0;
        if(dp[i][j]!=-1)
            return dp[i][j];//since zero is part of answer, so we check this so that tle is avoided.
        if(s.charAt(i-1)==t.charAt(j-1))
            return distinctSubsequence_m1(s, t, i-1, j-1, dp)+distinctSubsequence_m1(s, t, i-1, j, dp);
        return dp[i][j]=distinctSubsequence_m1(s, t, i-1, j, dp);
    }
    public static int distinctSubsequence_t1(String s,String t,int [][]dp){
        int m = s.length(), n = t.length();
        for(int i = 0; i < m; i ++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= m; i ++){
            for(int j = 1; j <= n; j ++){
                //in both cases, the subsequence in String t should be ending with character t.charAt(j - 1)
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    // when two pointers pointing to same character
                    // if we take these two characters simultaneously, we should have dp[i-1][j-1] subsequences
                    // otherwise if we overlook current i (moving back for one step) and keeping the current j we have another dp[i -1][j]
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    // when two pointers pointing to difference characters
                    //we cannot take these two characters but we still should make j ending with pointing to current position
                    // then we should move i backward
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
    //in this question, memorization goes better this is because in some testcase many points are not visited.
    //this saves time.in tabulation, all the points are visited irrespective any condition.
    public static void distinctSubsequence(String s,String t){
        int n=s.length();
        int m=t.length();
        int [][]dp=new int [n+1][m+1];
        for(int []a:dp)
            Arrays.fill(a, -1);
        // System.out.println(distinctSubsequence_m1(s, t, n, m, dp));
        System.out.println(distinctSubsequence_t1(s, t, dp));
        print2D(dp);
    }

    //QUESTION-3:LONGEST COMMON SUBSEQUENCE
    //in this question,we need to find the longest common subsequnce of two strings. since we need to find
    //the longest we take include every same element there is two both strings.and make max of both substrings
    //once both the elements are different.
    public static int longestCommonSubsequence_m1(String s,String t,int n,int m,int [][]dp){
        if(n==0 || m==0)
            return dp[n][m]=0;
        if(dp[n][m]!=-1)
            return dp[n][m];
        if(s.charAt(n-1)==t.charAt(m-1))
            return 1+longestCommonSubsequence_m1(s, t, n-1, m-1, dp);
        return Math.max(longestCommonSubsequence_m1(s, t, n-1, m, dp),
                                 longestCommonSubsequence_m1(s, t, n, m-1, dp));
    }
    public static int longestCommonSubsequence_t1(String s, String t,int [][]dp){
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=t.length();j++){
                if(s.charAt(i-1)==t.charAt(j-1))
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        print2D(dp);
        return dp[s.length()][t.length()];
    }
    public static String printLongestCommonSubsequence(String s,String t,int [][]dp){
        return "";
    }
    public static void longestCommonSubsequence(String s,String t){
        int n=s.length(),m=t.length();
        int [][]dp=new int [n+1][m+1];
        for(int []a:dp)
            Arrays.fill(a, -1);
        System.out.println(longestCommonSubsequence_t1(s, t, dp));
        // System.out.println(longestCommonSubsequence_m1(s, t, n, m, dp));
        // print2D(dp);
    }
    //************************************************************************************
    //LEETCODE 1035 Same longest common subsequence
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n=nums1.length,m=nums2.length;
        int [][]dp=new int [n+1][m+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(i==0 || j==0)
                    dp[i][j]=0;
                else
                if(nums1[i-1]==nums2[j-1])
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
    //********************************************************************************************



    //===========================================================================================
    //count palindromic subsequence
    //let's have three calls,(i+1,j)=x,(i,j-1)=y and (i+1,j-1)=z.the call (i+1,j) gives me all the palindrome excluding the
    //first character, Similarly (i,j-1) all the palindromes excluding the last character and in (i+1,j-1) excluding
    //both first and last character.Now if the charcter are same,
    // then (x+y-z(sutracting because of duplicacy))+z(palindrome made by adding both the character in front and back)+1=x+y+1
    //if character aren't same, (x+y-z)
    //******************************************************************************************
    public static int countPS(String s,int i,int j,int [][]dp){
        if(i==j)
            return dp[i][j]=1;
        if(dp[i][j]!=0)
            return dp[i][j];
        int a=countPS(s, i+1, j, dp);
        int b=countPS(s, i, j-1, dp);
        int c=countPS(s, i+1, j-1, dp);
        if(s.charAt(i)==s.charAt(j))
            return dp[i][j]=a+b+1;
        return dp[i][j]=a+b-c;
    }



    //===========================================================================================
    //Leetcode 1458. Max Dot Product of Two Subsequences
    //in this quetion we have four possiblity. either we take both, or take either or the leave both or the val starts 
    //from that number. the neither taking both call is handled in taking either. if we put that also. it would 
    //be redundant.
    public static int maxDotPro_m1(int []arr1,int []arr2,int n,int m,int [][]dp){
        if(n==0 ||m==0)
            return dp[n][m]=(int)-1e8;
        if(dp[n][m]!=(int)-1e8)
            return dp[n][m];
        int val=arr1[n-1]*arr2[m-1];
        int accboth=maxDotPro_m1(arr1, arr2, n-1, m-1, dp)+val;
        int a=maxDotPro_m1(arr1, arr2, n-1, m, dp);
        int b=maxDotPro_m1(arr1, arr2, n, m-1, dp);
        return Math.max(Math.max(a, b),Math.max(val, accboth));
    }
    public static int maxDotPro_t1(int []arr1,int[]arr2,int [][]dp){
        for(int i=0;i<=arr1.length;i++){
            for(int j=0;j<=arr2.length;j++){
                if(i==0 ||j==0){
                    dp[i][j]=(int)-1e8;
                    continue;
                }
                int val=arr1[i-1]*arr2[j-1];
                int accboth=dp[i-1][j-1];
                int a=dp[i-1][j];
                int b=dp[i][j-1];
                dp[i][j]=Math.max(Math.max(a, b), Math.max(val,accboth));
            }
        }
        return dp[arr1.length][arr2.length];
    }
    public static void maxDotPro(int []arr1,int []arr2){
        int n=arr1.length;
        int m=arr2.length;
        int [][]dp=new int [n+1][m+1];
        for(int []a:dp)
            Arrays.fill(a, (int)-1e8);
        System.out.println(maxDotPro_m1(arr1, arr2, n, m, dp));
    }
    //**********************************************************************************




    //==================================================================================
    //Leetcode 72 EDIT DISTANCE
    //in this question we need to find the minimum steps to convert one number to another. we can do this by 
    //inserting,replacing and deleting. 
    //So, when we insert an element to the word we want to change we weren't changing this number of index , so call(i,j+1). 
    //when we delete, we delete the enter element and so the index changes to (i+1,j).
    //when we replace, we change the letter compleltely to match the word. so Index is (i+1,j+1)
    public static int editDistance_m1(String word1,String word2,int n,int m,int [][]dp){
        if(n==0 ||m==0)
            return dp[n][m]=(n==0)?m:n;
        if(dp[n][m]!=0)
            return dp[n][m];
        if(word1.charAt(n-1)==word2.charAt(m-1))
            return dp[n][m]=editDistance_m1(word1, word2, n-1, m-1, dp);
        int insert=editDistance_m1(word1, word2, n, m-1, dp);
        int delete=editDistance_m1(word1, word2, n-1, m, dp);
        int replace=editDistance_m1(word1, word2, n-1, m-1, dp);
        return dp[n][m]=Math.min(Math.min(insert, delete), replace)+1;
    }
    public static int editDistance_m2Cost(String word1,String word2,int []cost,int n,int m,int [][]dp){
        if(n==0 ||m==0)
            return dp[n][m]=(n==0)?m*cost[0]:n*cost[1];
        if(dp[n][m]!=0)
            return dp[n][m];
        if(word1.charAt(n-1)==word2.charAt(m-1))
            return dp[n][m]=editDistance_m2Cost(word1, word2,cost, n-1, m-1, dp);
        int insert=editDistance_m2Cost(word1, word2,cost, n, m-1, dp)+cost[0];
        int delete=editDistance_m2Cost(word1, word2, cost,n-1, m, dp)+cost[1];
        int replace=editDistance_m2Cost(word1, word2,cost, n-1, m-1, dp)+cost[2];
        return dp[n][m]=Math.min(Math.min(insert, delete), replace);
    }
    public static int editDistance_t1(String word1,String word2,int [][]dp){
        for(int i=0;i<=word1.length();i++){
            for(int j=0;j<=word2.length();j++){
                if(i==0||j==0){
                    dp[i][j]=(i==0)?j:i;
                    continue;
                }
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else
                    {
                        int insert=dp[i][j-1];
                        int delete=dp[i-1][j];
                        int replace=dp[i-1][j-1];
                        dp[i][j]=Math.min(Math.min(insert, delete), replace)+1;
                    }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void editDistance(String word1,String word2){
        int n=word1.length();
        int m=word2.length();
        int [][]dp=new int [n+1][m+1];
        System.out.println(editDistance_m1(word1, word2, n, m, dp));
    }

    //************************************************************************



    //=======================================================================
    //LEETCODE 44-WILDCARD MATCHING
    public static int isMatch_m1(String s,String p,int n,int m,int [][]dp){
        if(n==0 || m==0){
            if(m==1 && p.charAt(m-1)=='*')
                return dp[n][m]=1;
            if(n==0 && m==0)
                return dp[n][m]=1;
            return dp[n][m]=0;
        }
        if(dp[n][m]!=-1)
            return dp[n][m];
        char ch1=s.charAt(n-1);
        char ch2=p.charAt(m-1);

        if(ch1==ch2 || ch2=='?')
            return dp[n][m]=isMatch_m1(s, p, n-1, m-1, dp);
        if(ch2=='*'){
            boolean res=false;
            res=res|| (isMatch_m1(s, p, n-1, m, dp)==1)||(isMatch_m1(s, p, n, m-1, dp)==1);
            if(res)
                return dp[n][m]=1;
            return dp[n][m]=0;
        }
        return dp[n][m]=0;
    }
    public static boolean isMatch_t1(String s,String p){
        p=removeStar(p);
        int N=s.length();
        int M=p.length();
        int [][]dp=new int [N+1][M+1];
        for(int []a:dp)
            Arrays.fill(a, -1);
        for(int n=0;n<=N;n++){
            for(int m=0;m<=M;m++){
                if(n==0 || m==0){
                    if(m==1 && p.charAt(m-1)=='*'){}
                         dp[n][m]=1;
                    if(n==0 && m==0)
                         dp[n][m]=1;
                    else
                        dp[n][m]=0;
                    continue;
                }
                char ch1=s.charAt(n-1);
                char ch2=p.charAt(m-1);
        
                if(ch1==ch2 || ch2=='?')
                     dp[n][m]=dp[n-1][m-1];
                else
                if(ch2=='*'){
                    boolean res=false;
                    res=res|| (dp[n-1][m]==1)||(dp[n][m-1]==1);
                    if(res)
                        dp[n][m]=1;
                    else
                    dp[n][m]=0;
                }
                else 
                    dp[n][m]=0;
            }
        }
        return dp[N][M]==1;
    }
    public static String removeStar(String str){
        if(str.length()==0)
            return "";
        StringBuilder sb=new StringBuilder();
        sb.append(str.charAt(0)+"");
        int i=1;
        while(i<str.length()){
            while(i<str.length() && str.charAt(i-1)=='*' && str.charAt(i)=='*')
                i++;
            if(i<str.length())
                sb.append(str.charAt(i));
            i++;
        }
        return sb.toString();
    }
    public static boolean isMatch(String s,String p){
        p=removeStar(p);
        int n=s.length();
        int m=p.length();
        int [][]dp=new int [n+1][m+1];
        for(int []a:dp)
            Arrays.fill(a, -1);
        return (isMatch_m1(s, p, n, m, dp)==1);
    }

    //************************************************************************
    public static void main(String[] args) {
        // longestPalidromeSubsequence("BBABCBCAB");
        // distinctSubsequence("geksfgeks", "gks");
        longestCommonSubsequence("abcde", "ace");
        //maxDotPro(arr1, arr2);
        // editDistance(word1, word2);
    }    
}
