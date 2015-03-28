
package margaux.equation;




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
			//Capacite, content, content_val, in_val, in_debit, out_debit
			EquationDifferentielle equadiff = new EquationDifferentielle(500, 100, 4, 2, 5, 5);
			double y = equadiff.quantiteTempsT(10);
			double t = equadiff.tempsQuantiteQ(81.11999069632385);
			equadiff.getEquation();

			System.out.println(y+" "+t);

			double yD = equadiff.quantiteDebordement();
			double tD = equadiff.tempsDebordement();
			System.out.println(yD+" "+tD);
			
//			double tVide = equadiff.tempsVide();
//			System.out.println(tVide);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

