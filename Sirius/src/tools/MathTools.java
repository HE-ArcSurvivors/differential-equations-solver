
package tools;



public class MathTools
	{

	/** @return the greatest common denominator */
	public static long gcm(long a, long b) {
	    return b == 0 ? a : gcm(b, a % b);
	}

	/**
	 * Exemple: <b>epsilon</b> = 1E-6
	 */
	public static boolean isEquals(double a, double b, double epsilon)
		{
		if (a == 0 || b == 0)
			{
			return Math.abs(a - b) <= epsilon;
			}
		else
			{
			return Math.abs((a - b) / a) <= epsilon;
			}
		}

	/**
	 * Exemple: <b>epsilon</b> = 1E-6
	 */
	public static boolean isEquals(long a, long b, long epsilon)
		{
		return Math.abs(a-b) <= epsilon;
		}

	}

