
package tank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import substance.Substance;

public class Tank implements Iterable<Tank>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Tank(boolean infini, double capacite, double debit)
		{
		mapSubstance = new HashMap<Substance, Double>();
		mapLiquide = new HashMap<Substance, Double>();
		listTankParent = new ArrayList<Tank>();

		this.infini = infini;
		this.capacite = capacite;
		this.debit = debit;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void printTank(double t, Substance substance)
		{
		for(Tank element:listTankParent)
			{
			element.printTank(t, substance);
			}
		System.out.println();
		System.out.println("Contenu : " + getContent(t));
		System.out.println("D�bit : " + getDebit());
		System.out.println("Quantit� de " + substance.getName() + " : " + equaDiff(t, substance));
		System.out.println();
		}

	public double equaDiff(double t, Substance substance)
		{
		in = 0;

		for(Tank element:listTankParent)
			{
			in += element.getDebit() * element.getValueSubstance(substance);
		}

		out = debit / getContent(t);
		C = this.getValueSubstance(substance) - Math.pow(out, -1) * in;

		System.out.println(Math.pow(out, -1)+" * "+in+" + "+C+" * "+Math.exp(-t*out));

		return Math.pow(out, -1) * in + C * Math.exp(-t*out);
	}

	public double getContent()
		{
		double contenanceTemps0 = 0;
		for(Double element:mapLiquide.values())
			{
			contenanceTemps0 += element;
			}
		return contenanceTemps0;
		}

	public double getContent(double t)
		{
		double contenance = this.getContent();
		for(Tank tank:listTankParent)
			{
			contenance += tank.getDebit() * t;
			}
		contenance -= this.debit * t;

		return contenance;
		}

	public double getValueSubstance(Substance substance)
		{
		if (substance.getState() == Substance.SOLID)
			{
			return mapSubstance.get(substance);
			}
		else if (substance.getState() == Substance.LIQUID) { return mapLiquide.get(substance); }
		return 0.0;
		}

	public boolean addSubstance(Substance substance, double quantity)
		{
		if (substance.getState() == Substance.SOLID)
			{
			return mapSubstance.put(substance, quantity) != null;
			}
		else if (substance.getState() == Substance.LIQUID) { return mapLiquide.put(substance, quantity) != null; }

		return false;
		}

	public boolean editSubstance(Substance substance, double quantity)
		{
		if (substance.getState() == Substance.SOLID)
			{
			return mapSubstance.put(substance, quantity) != null;
			}
		else if (substance.getState() == Substance.LIQUID) { return mapLiquide.put(substance, quantity) != null; }

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

	public Map<Substance, Double> getAllSubstances(double t)
		{
		Map<Substance, Double> mapAllSubstances = new HashMap<Substance, Double>();
		mapAllSubstances.putAll(mapLiquide);
		mapAllSubstances.putAll(mapSubstance);

		Map<Substance, Double> mapSubstanceTime = new HashMap<Substance, Double>();

		Set<Substance> listSubstances = mapAllSubstances.keySet();

		for(Substance substance:listSubstances)
			{
			//Only 2 substances now
			if (substance.getState() == Substance.LIQUID) //remove the if when we are ready for +2 substances
				{
				mapSubstanceTime.put(substance, getContent(t));
				}
			else
				{
				mapSubstanceTime.put(substance, equaDiff(t, substance));
				}
			}

		return mapSubstanceTime;
		}

	public boolean isOverflowPossible()
	{
		double in = getInflow();

		if(this.debit >= in) { return false; }

		return true;
	}

	public boolean isEmptyPossible()
	{
		double in = getInflow();

		if(this.debit <= in) { return false; }

		return true;
	}

	public double getInflow()
	{
		double in = 0;
		for(Tank element : listTankParent)
			{
				in += element.getDebit();
			}
		return in;
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

	private Map<Substance, Double> mapSubstance;
	private Map<Substance, Double> mapLiquide;
	private List<Tank> listTankParent;

	//Tools
	private double in;
	private double out;
	private double C;

	}
