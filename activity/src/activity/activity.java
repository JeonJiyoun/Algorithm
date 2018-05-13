package activity;
import java.util.*;


public class activity{
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
         int size = scan.nextInt();

         ArrayList result = new ArrayList<Integer>();

         int[][] Arr = new int[2][size + 1];
         int n1 = 1;
         int n2 = 1;
         //Arr[0][0] = 0;
         //Arr[1][0] = 0;
         for(int i = 0; i < size*2; i++){
            if(i%2 == 0){ 
               Arr[0][n1] = scan.nextInt();
               n1 = n1 + 1;
            }
            else{
               Arr[1][n2] = scan.nextInt();
               n2 = n2 + 1;
            }
         }

         Randomized_Quicksort(Arr, 1, size);
         int[] s = new int[size + 1];
         int[] f = new int[size + 1];
         s[0] = 0;
         for(int j = 1; j < size + 1; j++) {
        	 s[j] = Arr[0][j];
         }
         //int[] s = Arr[0];
         /*for(int j = 0; j < s.length; j++){
            System.out.println(s[j]);
         }*/
         f[0] = 0;
         for(int k = 1; k < size + 1; k++) {
        	 f[k] = Arr[1][k];
         }
         //int[] f = Arr[1];
         for(int p = 0; p < f.length; p++){
            System.out.println(s[p] + " , " + f[p]);
         }

         //int[][] result = new int[2][size + 1];
         //int c = 0;
         result = Recursive_Activity_Selector(result, s, f, 0, size);
         //int count = count(result, s, f, 0, size, c);

         //Print(fin, count);

         System.out.println(result.size()/2);
         for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
         }

   }
   public static void Randomized_Quicksort(int[][] A, int p, int r){
      if (p < r){
         int q = Randomized_Partition(A, p, r);
         Randomized_Quicksort(A, p, q-1);
         Randomized_Quicksort(A, q+1, r);
      }

      //return A;
   }
   public static int Randomized_Partition(int[][] A, int p, int r){
      Random random = new Random();
      int i = random.nextInt(r-p) + p;

      int temp1 = A[1][i];
      A[1][i] = A[1][r];
      A[1][r] = temp1;
      int temp2 = A[0][i];
      A[0][i] = A[0][r];
      A[0][r] = temp2;
      return Partition(A, p, r);
   }
   public static int Partition(int[][] A, int p, int r){
      int x = A[1][r];
      int i = p - 1;
      for(int j = p; j <= r-1; j++){
         if (A[1][j] <= x){
            i = i + 1;
            int temp1 = A[1][i];
            A[1][i] = A[1][j];
            A[1][j] = temp1;
            int temp2 = A[0][i];
            A[0][i] = A[0][j];
            A[0][j] = temp2;
         }
      }
      int tmp1 = A[1][i + 1];
      A[1][i + 1] = A[1][r];
      A[1][r] = tmp1;

      int tmp2 = A[0][i + 1];
      A[0][i + 1] = A[0][r];
      A[0][r] = tmp2;
      return i + 1;
   }

   public static ArrayList<Integer> Recursive_Activity_Selector(ArrayList<Integer> r, int[] s, int[] f, int k, int n){
      int m = k + 1;
      while((m <= n) && (s[m] < f[k])){
         m = m + 1;
      }
      if (m <= n){
         r.add(s[m]);
         r.add(f[m]);
        
         Recursive_Activity_Selector(r, s, f, m, n);
      }
      return r;
   }

   /*public static int count(int[][] r, int[] s, int[] f, int k, int n, int c){
      int m = k + 1;
      while((m <= n) && (s[m] < f[k])){
         m = m + 1;
      }
      if (m <= n){
         r[0][c] = s[m];
         r[1][c] = f[m];
         c = c + 1;
         Recursive_Activity_Selector(r, s, f, m, n, c);
      }
      return c;
   }*/
   /*public static void Print(int[][] r, int c){
      System.out.println(c + 1);
      for(int i = 0; i <= c; i++){
         System.out.println(r[0][i]);
         System.out.println(r[1][i]);
      }
   }*/
}