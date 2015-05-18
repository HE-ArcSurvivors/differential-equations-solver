
package tools;



public class MathTools
	{

	/** @return the greatest common denominator */
	public static long gcm(long a, long b) {
	    return b == 0 ? a : gcm(b, a % b);
	}

	}

