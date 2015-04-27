
package differentialEquationSolving;

import tank.Tank;

public class Solve
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static double timeQuantityQ(Tank tank, double quantite)
		{
			return 0;
		}

	public static double timeOverflow(Tank tank)
		{
			return tank.getCapacite() / (tank.getInflow() - tank.getDebit());
		}

	public static double timeEmpty(Tank tank)
		{
			return 0;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

