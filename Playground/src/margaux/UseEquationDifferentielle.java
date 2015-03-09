
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
			EquationDifferentielle equadiff = new EquationDifferentielle(1000, 300, 50, 0, 10, 40);
//			double y = equadiff.quantiteTempsT(25);
//			double t = equadiff.tempsQuantiteQ(135.62109779499758);
//			equadiff.getEquation();

			double y = equadiff.quantiteDebordement();
			double t = equadiff.tempsDebordement();

			double tVide = equadiff.tempsVide();

			System.out.println(y+" "+t);

			System.out.println(tVide);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

