
package margauxV2.equation;

import margauxV2.conteneur.Reservoir;


public class EquationDifferentielle
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public EquationDifferentielle(double capacite, double content, double content_val, double in_val, double in_debit, double out_debit)
		{
		this.capacite = capacite;
		this.content = content;
		this.content_val = content_val;
		this.in_val = in_val;
		this.in_debit = in_debit;
		this.out_debit = out_debit;

		init();
		}

	public EquationDifferentielle()
		{
		this.capacite = 0;
		this.content = 0;
		this.content_val = 0;
		this.in_val = 0;
		this.in_debit = 0;
		this.out_debit = 0;

		init();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public double quantiteTempsT(double t)
		{
			y = Math.pow(out, -1) * in + C * Math.exp(-t*out);
			return y;
		}

	public double tempsQuantiteQ(double q)
		{
			return Math.log((q - Math.pow(out,-1)*in ) / C) / (-out);
		}

	public double tempsQuantitePourcent(double qPourcent)
		{
			//return Math.log((q - Math.pow(out,-1)*in ) / C) / (-out);
			return 0;
		}

	public double tempsDebordement()
		{
			return (capacite - content)/(in_debit - out_debit);
		}

	public double tempsVide()
		{
			return (- content)/(in_debit - out_debit);
		}

	public double quantiteDebordement()
		{
			return quantiteTempsT(tempsDebordement());
		}

	private void init()
		{
			in = in_val * in_debit;
			out = out_debit / content;
			C = content_val - Math.pow(out, -1) * in;
		}

	public String getEquation()
		{
			return Math.pow(out, -1) * in+" " +C +"* e^-t*"+out;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setSource(Reservoir container)
	{
		//this.in_val = container.getSubstancePourcent();
		this.in_debit = container.getDebit();

		init();
	}

	public void setContainer(Reservoir container)
	{
		this.capacite = container.getCapacite();
		//this.content = container.getContenance();
		//this.content_val = container.getSubstancePourcent();
		this.out_debit = container.getDebit();

		init();
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private double capacite;
	private double content;
	private double content_val;
	private double in_val;
	private double in_debit;
	private double out_debit;

	//Tools
	private double in;
	private double out;
	private double C;

	//Output
	private double y;
	}

