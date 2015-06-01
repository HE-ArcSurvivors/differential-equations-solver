
package differentialEquationSolving;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import substance.Substance;
import tank.Tank;

public class SimulationSingleton implements Serializable
	{

	//create an object of SingleObject
	private static SimulationSingleton instance;

	//make the constructor private so that this class cannot be
	//instantiated
	private SimulationSingleton()
		{
		listSubstance = new ArrayList<Substance>();
		isStarted = false;
		fileName = "";
		simulationActive = true;
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

	public static void save(String filename)
		{

		try
			{
			FileOutputStream fos = new FileOutputStream(filename);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(instance);
			fileName = filename;

			oos.close();
			bos.close();
			fos.close();
			}
		catch (Exception e)
			{
			System.err.println(e);
			System.err.println("Fichier non sauvegardé");
			}
		}

	public static void load(String filename)
		{
		try
			{
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);

			instance = (SimulationSingleton)ois.readObject();
			fileName = filename;

			ois.close();
			bis.close();
			fis.close();
			}
		catch (Exception e)
			{
			System.err.println(e);
			System.err.println("Fichier non chargé");

			JOptionPane.showMessageDialog(null,
				    "Le fichier n'a pas été chargé correctement.",
				    "Fichier incorrect",
				    JOptionPane.WARNING_MESSAGE);
			}
		}

	public void simulationActive(boolean active)
	{
		this.simulationActive = active;
	}

	public boolean isActive()
	{
		return this.simulationActive;
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

	public String getCurrentFileName()
	{
		return fileName;
	}
	public void setMainTank(Tank tank)
		{
		mainTank = tank;
		}

	public boolean isStarted()
		{
		return isStarted;
		}

	public void setStarted(boolean started)
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
	private static String fileName;
	private boolean simulationActive;

	}
