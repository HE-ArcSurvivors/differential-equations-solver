
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
		isStarted = false;
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

	public Substance getSubstanceAt(int i)
		{
		return listSubstance.get(i);
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

	public boolean isStarted()
		{
		return isStarted;
		}

	public void setisStarted(boolean started)
		{
		isStarted = started;
		}

	public void deleteMainTank()
		{
		mainTank = null;
		}

	private Tank mainTank;
	private List<Substance> listSubstance;
	private boolean isStarted;

	}
