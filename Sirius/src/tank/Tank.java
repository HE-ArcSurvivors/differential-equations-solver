
package tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import substance.Substance;


public class Tank
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Tank(boolean infini, double capacite, double debit)
		{
		mapSubstance = new  HashMap<Substance,Double>();
		mapLiquide = new HashMap<Substance,Double>();
		listReservoirParent = new ArrayList<Tank>();

		this.infini = infini;
		this.capacite = capacite;
		this.debit = debit;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public double equaDiff(double t)
		{
			init();
			return Math.pow(out, -1) * in + C * Math.exp(-t*out);
		}

	public double getContent(double t)
	{
		double contenance = 0;
		for(Double element : mapLiquide.values())
		{
			contenance += element;
		}
		//contenance -= t *
		return contenance;
	}

	public double getValueSubstance()
	{
		double contentSubstance = 0;
		for(Double element : mapSubstance.values())
		{
		contentSubstance += element;
		}
		//contenance -= t *
		return contentSubstance;
	}

	public boolean addSubstance(Substance substance, double pourcentage)
	{
		return mapSubstance.put(substance, pourcentage) != null;
	}

	public boolean editSubstance(Substance substance)
	{
		return false;
	}

	public boolean removeSubstance(Substance substance)
	{
		return false;
	}

	public void addLiquid(Substance substance, double pourcentage)
	{
		mapLiquide.put(substance, pourcentage);
	}

	public boolean addReservoirParent(Tank reservoirParent)
	{
		return listReservoirParent.add(reservoirParent);
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setInfini(boolean infini)
		{
		this.infini = infini;
		}


	public void setCapacite(double capacite)
		{
		this.capacite = capacite;
		}


	public void setDebit(double debit)
		{
		this.debit = debit;
		}

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

	public double getDebit()
		{
		return this.debit;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void init()
		{
			in = 0;

			for(Tank reservoir : listReservoirParent)
			{
				in += reservoir.getDebit() * reservoir.getValueSubstance();
			}

			out = debit / getContent(0);
			C = this.getValueSubstance() - Math.pow(out, -1) * in;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private boolean infini;
	private double capacite;
	private double debit;

	private Map<Substance,Double> mapSubstance;
	private Map<Substance,Double> mapLiquide;
	private List<Tank> listReservoirParent;

	//Tools
	private double in;
	private double out;
	private double C;

	}
