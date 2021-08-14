import java.util.PriorityQueue;

public class basicPQ {
    public static void test1_minPQ(int []arr){
        PriorityQueue<Integer>pq=new PriorityQueue<>(); //by default it is a min priority queue
        for(int ele:arr)
            pq.add(ele);
        while(pq.size()!=0){
            System.out.println(pq.poll());
        }
    }
    public static void test2minPQ(int []arr){
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
            return b-a;//this-other default behavior
            //return a-b;//other-this reveerse max priority queue
        });
        for(int ele:arr)
            pq.add(ele);
        while(pq.size()!=0){
            System.out.println(pq.remove());
        }
    }
    public static void test3(int [][]arr){
        PriorityQueue<int []>pq=new PriorityQueue<>((a,b)->{
            return b[0]-a[0];//other-this reverse default ie max pq
        });
        for(int []a:arr)
            pq.add(a);
        while(pq.size()!=0){
            int []a=pq.remove();
            int p=a[0];
            int q=a[1];
            System.out.println(p+","+q);
        }
    }
    public static class pair implements Comparable<pair>{
        int p=0;
        int q=0;
        pair(int p,int q){
            this.p=p;
            this.q=q;
        }
        public int compareTo(pair o){
            return this.p-o.p;//default behavior 
        }
    }
    public static void test4(int [][]arr){
        PriorityQueue<pair> pq = new PriorityQueue<>();

        for (int[] a : arr)
            pq.add(new pair(a[0], a[1]));

        while(pq.size()!=0){
            pair a=pq.remove();
            int p=a.p;
            int q=a.q;
            System.out.println(p+","+q);
        }

    }
    public static void main(String[] args) {
        
    }
}
