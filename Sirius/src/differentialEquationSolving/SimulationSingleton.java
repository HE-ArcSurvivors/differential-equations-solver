
package differentialEquationSolving;

import java.util.ArrayList;
import java.util.List;

import substance.Substance;
import tank.Tank;

public class SimulationSingleton
	{

	//create an object of SingleObject
	private static SimulationSingleton instance;

	//make the constructor private so that this class cannot be
	//instantiated
	private SimulationSingleton()
		{
		listSubstance = new ArrayList<Substance>();
		}

	//Get the only object available
	public static SimulationSingleton getInstance()
		{
		if (instance == null)
			{
			instance = new SimulationSingleton();
			}
		return instance;
		}

	public List<Substance> getSubstanceList()
	{
		return listSubstance;
	}

	public boolean addSubstance(Substance substance)
	{
		return listSubstance.add(substance);
	}

	public Tank getMainTank()
	{
		return mainTank;
	}

	public void setMainTank(Tank tank)
	{
		mainTank = tank;
	}

	private Tank mainTank;
	private List<Substance> listSubstance;

	}
