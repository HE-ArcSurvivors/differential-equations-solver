
package tank;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import substance.Substance;
import tools.MathTools;
import differentialEquationSolving.SimulationSingleton;

public class Tank implements Iterable<Tank> ,Serializable
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

		this.name = "";
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|* 	  Résolution d'équation 	*|
	\*------------------------------*/

	public double equaDiff(double t, Substance substance)
		{
		in = getInflowTotal(substance);
		out = debit / getContent(t);
		C = this.getValueSubstance(substance) - Math.pow(out, -1) * in;

		if (out == 0)
			{
			return in * t;
			}
		else
			{
			return Math.pow(out, -1) * in + C * Math.exp(-t * out);
			}
		}

	public double timeQuantityQ(double quantity, Substance substance)
		{
		double quantityIn = getInflowTotal(substance);
		double quantityOut = debit / getContent();
		double quantityC = this.getValueSubstance(substance) - Math.pow(quantityOut, -1) * quantityIn;

		if (quantityOut == 0)
			{
			return quantity / quantityIn;
			}
		else
			{
			return -Math.log((quantity - Math.pow(quantityOut, -1) * quantityIn) / quantityC) / quantityOut;
			}
		}

	public double timeOverflow()
		{
		return (capacite - getContent()) / (getInflow() - getDebit());
		}

	public double timeEmpty()
		{
		return -getContent() / (getInflow() - getDebit());
		}

	/*------------------------------*\
	|*	      Other Methods			*|
	\*------------------------------*/

	public void printTank(double t, Substance substance)
		{
		for(Tank element:listTankParent)
			{
			element.printTank(t, substance);
			}
		System.out.println();
		System.out.println("Contenu : " + getContent(t));
		System.out.println("Quantit� de " + substance.getName() + " : " + equaDiff(t, substance));
		System.out.println();
		}

	public boolean addTankParent(Tank tankParent)
		{
		tankParent.tankChild = this;
		return listTankParent.add(tankParent);
		}

	@Override
	public Iterator<Tank> iterator()
		{
		return this.listTankParent.iterator();
		}

	public String getFormula(double t)
		{

		DecimalFormat dfsigned = new DecimalFormat("+#,##0.00;-#");

		long gcm = MathTools.gcm((long)getDebit(), (long)getContent());
		String outSimplifyFraction = "\\frac{" + getDebit() / gcm + "}{" + getContent() / gcm + "}";

		Substance sub = SimulationSingleton.getInstance().getSubstanceAt(1);
		Double inflow = getInflowTotal(sub);
		Double value = inflow * getContent() / getDebit();

		return "y(" + t + ") = " + value + " " + dfsigned.format(C) + " * e^{-" + t + "*" + outSimplifyFraction + "}";
		}

	public void delete()
		{
		//si il se déverse dans un tank il s'enlève de la liste de parent de ce dernier
		if (tankChild != null)
			{
			System.out.println("remove a child");

			tankChild.listTankParent.remove(this);
			tankChild = null;
			}

		for(Tank tank:listTankParent)
			{
			tank = null;
			}
		}

	/*------------------------------*\
	|*	    List of Substances		*|
	\*------------------------------*/

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

	public void setName(String name)
		{
		this.name = name;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public boolean isInfini()
		{
		return this.infini;
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
		else if (substance.getState() == Substance.LIQUID)
			{
			return mapLiquide.get(substance);
			}
		return 0.0;
		}

	public double getCapacite()
		{
		return this.capacite;
		}

	public double getDebit()
		{
		return this.debit;
		}

	public List<Tank> getlistTankParent()
		{
		return listTankParent;
		}

	public Map<Substance, Double> getTypeSubstances(double t, int substanceType)
		{
		Map<Substance, Double> mapTypeSubstances = new HashMap<Substance, Double>();

		if (substanceType == Substance.LIQUID)
			{
			//TODO : For many substances it will not work !
			Set<Substance> listLiquid = mapLiquide.keySet();
			for(Substance substance:listLiquid)
				{
				mapTypeSubstances.put(substance, getContent(t)); //change with equaDiff(t, substance)
				}
			}
		else if (substanceType == Substance.SOLID)
			{
			Set<Substance> listSolid = mapSubstance.keySet();
			for(Substance substance:listSolid)
				{
				mapTypeSubstances.put(substance, equaDiff(t, substance));
				}
			}

		return mapTypeSubstances;
		}

	public boolean isOverflowPossible()
		{
		double in = getInflow();

		if (this.debit >= in) { return false; }

		return true;
		}

	public boolean isEmptyPossible()
		{
		double in = getInflow();

		if (this.debit <= in) { return false; }

		return true;
		}

	public double getInflow()
		{
		double in = 0;
		for(Tank element:listTankParent)
			{
			in += element.getDebit();
			}
		return in;
		}

	public double getInflowTotal(Substance substance)
		{
		double inflow = 0;

		for(Tank element:listTankParent)
			{
			inflow += element.getDebit() * element.getValueSubstance(substance);
			}

		return inflow;
		}

	public double getInflowSubstance(Substance substance)
		{
		double total = 0;

		for(Tank element:listTankParent)
			{
			total += element.getValueSubstance(substance);
			}

		return total;
		}

	public String getName()
		{
		return this.name;
		}

	public Tank getTankChild()
		{
		return this.tankChild;
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

	private Tank tankChild;

	private String name;

	//Tools
	private double in;
	private double out;
	private double C;

	}
