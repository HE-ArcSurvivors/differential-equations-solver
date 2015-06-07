/*********************************************************************************
# Copyright © Haute-Ecole ARC - All Rights Reserved
# Copyright © Banana Rocket - All Rights Reserved
#
# This file is part of <P2 Java Project: GlouGlou - Problèmes de mélanges>.
#
# Unauthorized copying of this file, via any medium is strictly prohibited
# Proprietary and confidential
# Written by Claret-Yakovenko Roman <romain.claret@rocla.ch>
# Written by Divernois Margaux <margaux.divernois@gmail.com>
# Written by Visinand Steve <visinandst@gmail.com>
#
# Date : June 2015
**********************************************************************************/

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

