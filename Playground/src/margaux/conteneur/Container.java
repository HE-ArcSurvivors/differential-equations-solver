
package margaux.conteneur;




public class Container
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Container(boolean infini, double capacite, double contenance, double liquidePourcent, double substancePourcent, double debit)
		{
		this.infini = infini;
		this.capacite = capacite;
		this.contenance = contenance;
		this.liquidePourcent = liquidePourcent;
		this.substancePourcent = substancePourcent;
		this.debit = debit;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public boolean isInfini()
		{
		return this.infini;
		}

	public double getCapacite()
		{
		return this.capacite;
		}

	public double getContenance()
		{
		return this.contenance;
		}

	public double getSubstancePourcent()
		{
		return this.substancePourcent;
		}

	public double getDebit()
		{
		return this.debit;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private boolean infini;
	private double capacite;
	private double contenance;
	private double liquidePourcent;
	private double substancePourcent;
	private double debit;

	}

