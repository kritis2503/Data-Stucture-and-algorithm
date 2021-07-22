import java.util.*;
public class lastclass {

    //cathalan number is a pattern of numbers just like fibonacci numbers. what intrest us most that it 
    //follows the pattern of the CUT TYPE QUESTION. so the cathaln number can be found out by few ways.
    public static int cathalanNum01(int n){
        int []dp=new int [n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0,k=i-1;k>=0;j++,k--){
                dp[i]+=(dp[j]*dp[k]);
            }
        }
        return dp[n-1];
    }
    //the other way is to calculate by formula 2nCn/(n+1). you can either store factorials or 2nCk 
    //Now there are applications to it and you can read it in applications of cathalan number gfg.
    //for better understanding dry run cathlan number01 on unique bst
    public static void main(String[] args) {
        System.out.println(cathalanNum01(5));
    }
}
