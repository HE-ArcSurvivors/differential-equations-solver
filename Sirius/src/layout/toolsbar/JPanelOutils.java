
package layout.toolsbar;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

public class JPanelOutils extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelOutils()
		{

		eau = new Substance("Eau", (float)5.0, Substance.LIQUID);
		sel = new Substance("Sel", (float)1.0, Substance.SOLID);

		SimulationSingleton.getInstance().addSubstance(eau);
		SimulationSingleton.getInstance().addSubstance(sel);

		this.r1 = new Tank(false, 500, 5);
		this.r2 = new Tank(false, 200, 5);
		this.r1.addSubstance(sel, 2);
		this.r1.addSubstance(eau, 500);

		this.r2.addSubstance(sel, 4);
		this.r2.addSubstance(eau, 100);
		r2.addTankParent(r1);

		SimulationSingleton.getInstance().setMainTank(r2);

		int size = SimulationSingleton.getInstance().getSubstanceList().size();
		tabSubstance = new JPanelOutilSubstance[size];

		geometry();
		control();
		appearance();
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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
			// JComponent : Instanciation
			for(int i = 0; i < tabSubstance.length; i++)
			{
			Substance substance = SimulationSingleton.getInstance().getSubstanceAt(i);
			tabSubstance[i] = new JPanelOutilSubstance(substance);
			}

			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			Box boxV = Box.createVerticalBox();

			for(int i = 0; i < tabSubstance.length; i++)
			{
				boxV.add(tabSubstance[i]);
			}

			add(boxV);

			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Substance"));
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		int height = this.getHeight();
		setPreferredSize(new Dimension(150,height));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JPanelOutilSubstance[] tabSubstance;

	//	TEST
	private Tank r1, r2;
	private Substance eau, sel;

	}
