/**
 * Created on Oct 16, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package decorator;

import java.awt.image.Kernel;

/**
 * @author cskim
 *
 */
public class KernelFactory {

	public static Kernel createBlurKernel(int n){
		float[] matrix = new float[n*n];
		for (int i=0; i<n*n; ++i){
			matrix[i] = 1f / (float)(n*n);
		}
		return new Kernel(n, n, matrix);
	}
	public static Kernel createSharpenKernel(){
		int n = 3;
		float[] matrix = 
			{ 0, -1, 0,
			  -1, 5, -1,
			  0, -1, 0
			};
		return new Kernel(n, n, matrix);
	}
	public static Kernel createEdgeKernel(){
		int n = 3;
		float[] matrix = 
			{ 0, 1, 0,
			  1, -4, 1,
			  0, 1, 0
			};
		return new Kernel(n, n, matrix);
	}
	public static Kernel createEmbossKernel(){
		int n = 3;
		float[] matrix = 
			{ -2, -1, 0,
			  -1, 1, 1,
			  0, 1, 2
			};
		return new Kernel(n, n, matrix);
	}

}
