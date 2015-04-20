
package tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import substance.Substance;


public class Tank implements Iterable<Tank>
{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Tank(boolean infini, double capacite, double debit)
	{
		mapSubstance = new  HashMap<Substance,Double>();
		mapLiquide = new HashMap<Substance,Double>();
		listTankParent = new ArrayList<Tank>();

		this.infini = infini;
		this.capacite = capacite;
		this.debit = debit;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void printTank(double t,Substance substance)
	{
		for(Tank element : listTankParent)
		{
			element.printTank(t,substance);
		}
		System.out.println();
		System.out.println("Contenu : "+getContent(t));
		System.out.println("D�bit : "+getDebit());
		System.out.println("Quantit� de sel : "+equaDiff(t,substance));
		System.out.println();
	}

	public double equaDiff(double t, Substance substance)
	{
		in = 0;

		for(Tank element : listTankParent)
		{
			in += element.getDebit() * element.getValueSubstance(substance);
		}

		out = debit / getContent(t);
		C = this.getValueSubstance(substance) - Math.pow(out, -1) * in;

		return Math.pow(out, -1) * in + C * Math.exp(-t*out);
	}

	public double getContent()
	{
		double contenanceTemps0 = 0;
		for(Double element : mapLiquide.values())
		{
			contenanceTemps0 += element;
		}
		return contenanceTemps0;
	}

	public double getContent(double t)
	{
		double contenance = this.getContent();
		for(Tank tank : listTankParent)
		{
			contenance += tank.getDebit()*t;
		}
		contenance -= this.debit * t;

		return contenance;
	}

	public double getValueSubstance(Substance substance)
	{
		return mapSubstance.get(substance);
	}

	public boolean addSubstance(Substance substance, double quantity)
	{
		if(substance.getState() == Substance.SOLID)
		{
			return mapSubstance.put(substance, quantity) != null;
		}
		else if(substance.getState() == Substance.LIQUID)
		{
			return mapLiquide.put(substance, quantity) != null;
		}

		return false;
	}

	public boolean editSubstance(Substance substance, double quantity)
	{
		if(substance.getState() == Substance.SOLID)
		{
			return mapSubstance.put(substance, quantity) != null;
		}
		else if(substance.getState() == Substance.LIQUID)
		{
			return mapLiquide.put(substance, quantity) != null;
		}

		return false;
	}

	public boolean removeSubstance(Substance substance)
	{
		return mapSubstance.remove(substance) != null || mapLiquide.remove(substance) != null;
	}

	public boolean addTankParent(Tank tankParent)
	{
		return listTankParent.add(tankParent);
	}

	@Override
	public Iterator<Tank> iterator()
	{
		return this.listTankParent.iterator();
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Input
	private boolean infini;
	private double capacite;
	private double debit;

	private Map<Substance,Double> mapSubstance;
	private Map<Substance,Double> mapLiquide;
	private List<Tank> listTankParent;

	//Tools
	private double in;
	private double out;
	private double C;

}