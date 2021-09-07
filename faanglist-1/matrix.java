import java.util.*;
public class matrix{
    public static void tugOfWars(int []arr){
        Arrays.sort(arr);
        int sum1=0,sum2=0;
        ArrayList<Integer>al1=new ArrayList<>();
        ArrayList<Integer>al2=new ArrayList<>(); 
        for(int i=0;i<arr.length;i++){
            if(sum1<=sum2){
                sum1+=arr[i];
                al1.add(arr[i]);
            }
            else    {
                sum2+=arr[i];
                al2.add(arr[i]);
            }
        }
        System.out.println(sum1 + "    "+ sum2);
        System.out.println(al1);
        System.out.println(al2);
    }
    public static void main(String[] args) {
        int []arr={23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
        tugOfWars(arr);
    }
}