
package margaux;




public class UseEquationDifferentielle
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
			EquationDifferentielle equadiff = new EquationDifferentielle(50, 25, 4, 2, 2);
			double y = equadiff.quantiteTempsT(25);
			double t = equadiff.tempsQuantiteQ(135.62109779499758);
//			equadiff.getEquation();

			System.out.println(y+" "+t);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
