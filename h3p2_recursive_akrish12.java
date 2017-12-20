import java.util.Scanner;

/**
 * 
 * @author akrish12
 * Created on 10/28/2017
 */
public class h3p2_recursive_akrish12 {
	static double num_scalar_mult = 0;
	static double num_recur_call = 0;
	private static int recursive_matrix_mul(int[] A, int i, int j, int m[][])
	{
		num_recur_call++;
		if (i==j)
			return 0;
		m[i][j] = Integer.MAX_VALUE;
		for(int k=i; k<j; k++){
			int q = recursive_matrix_mul(A, i, k, m) + recursive_matrix_mul(A, k+1, j, m) + A[i-1] * A[j] * A[k];
			num_scalar_mult += 2;
			if(q < m[i][j]) {
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
		int x[][] = new int[mat_array.length][mat_array.length];
		long start_time = System.nanoTime();
		int result = recursive_matrix_mul(mat_array, 1, mat_array.length-1, x);
		long end_time = System.nanoTime();

		System.out.println("Run time of the multiplication operation " + (end_time - start_time) + "\n" +
				"Number of recursive calls "	+num_recur_call + "\n" +
				"Number of scalar multiplication " +num_scalar_mult + "\n" +
				"Value " +result);
	}

}

