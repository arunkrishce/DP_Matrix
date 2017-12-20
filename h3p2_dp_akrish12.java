import java.util.*;
/**
 * 
 * @author akrish12
 * Created on 10/26/2017
 */
public class h3p2_dp_akrish12 {
	static double num_scalar_mult = 0;
	public static void main(String args[]) {
		double num_recur_call = 0;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] mat_array = new int[n+1];
		for(int i=0;i<=n;i++)
			mat_array[i] = sc.nextInt();
		sc.close();
		long start_time = System.nanoTime();
		int result = dp_matrix_mul(mat_array);
		long end_time = System.nanoTime();

		System.out.println("Run time of the multiplication operation " + (end_time - start_time) + "\n" +
				"Number of recursive calls "	+num_recur_call + "\n" +
				"Number of scalar multiplication " +num_scalar_mult + "\n" +
				"Value " +result);
	} 
	private static int dp_matrix_mul(int[] A)
	{
		int [][] m = new int[A.length][A.length];
		int [][] n = new int[A.length][A.length];
		for(int i=0; i<A.length;i++)
			m[i][i] = 0;
		for(int l=2; l<A.length; l++)
			for(int i=1; i<A.length-l+1; i++){
				int j = i+l-1;
				m[i][j] = Integer.MAX_VALUE;
				for(int k=i; k<=j-1; k++) {
					int c = m[i][k] + m[k+1][j] + A[i-1] * A[j] * A[k];
					num_scalar_mult += 2;
					if(c < m[i][j]) {
						m[i][j] = c;
						n[i][j] = k;
					}
				}
			}
		return m[1][A.length-1];
	}
	  
}
