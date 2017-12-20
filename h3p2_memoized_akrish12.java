import java.util.Scanner;
/**
 * 
 * @author akrish12
 * Created on 10/28/2017
 */
public class h3p2_memoized_akrish12 {
	static double num_scalar_mult = 0;
	static double num_recur_call = 0;
	private static int memoized_matrix_mul(int[] arr) {
		int a = arr.length;
		int m[][] = new int[a][a];
		for (int i=1; i<a; i++) {
			for(int j=i; j<a; j++) {
				m[i][j] = Integer.MAX_VALUE;
			}
		}
		return lookUpChain(m, arr, 1, a-1);
	}
	private static int lookUpChain(int[][]m, int[]arr, int i, int j) {
		num_recur_call++;
		if(m[i][j] < Integer.MAX_VALUE)
			return m[i][j];

		if(i==j)
			m[i][j] = 0;
		else {
			for(int k=i; k<j; k++) {
				int q = lookUpChain(m, arr, i, k) + lookUpChain(m, arr, k+1, j) + arr[i-1]*arr[k]*arr[j];
				num_scalar_mult += 2;
				if(q < m[i][j])
					m[i][j] = q;
			}
		}
		return m[i][j];
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] mat_array = new int[n+1];
		for(int i=0;i<=n;i++)
			mat_array[i] = sc.nextInt();
		sc.close();
		long start_time = System.nanoTime();
		int result = memoized_matrix_mul(mat_array);
		long end_time = System.nanoTime();

		System.out.println("Run time of the multiplication operation " + (end_time - start_time) + "\n" +
				"Number of recursive calls "	+num_recur_call + "\n" +
				"Number of scalar multiplication " +num_scalar_mult + "\n" +
				"Value " +result);
	}
}
