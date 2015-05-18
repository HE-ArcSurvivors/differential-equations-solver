
package layout.tank;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

public class JPanelParam extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelParam(JPanelTank jpaneltank, Tank tank)
		{
		this.parent = jpaneltank;
		this.tank = tank;
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
		box = Box.createVerticalBox();
		box.setAlignmentX(LEFT_ALIGNMENT);
		mapSubstanceParamLine = new HashMap<Substance, JPanelParamLine>();

		//tankIsInfinit = new JCheckBox();
		//	sub_box1.add(new JXLabel("Infini: "));
		//	sub_box1.add(tankIsInfinit);
		//	box.add(sub_box1);

		contenance = new JPanelParamLine("Capacité : ", tank.getCapacite());
		box.add(contenance);
		debit = new JPanelParamLine("Débit : ", tank.getDebit());
		box.add(debit);

		box.add(new JLabel("Configuration"));
		for(Substance sub:SimulationSingleton.getInstance().getSubstanceList())
			{
			mapSubstanceParamLine.put(sub, new JPanelParamLine(sub.getName() + " : ", tank.getValueSubstance(sub)));
			box.add(mapSubstanceParamLine.get(sub));
			}

		validate = new JButton("Valider");
		box.add(validate);

		setLayout(new BorderLayout());
		add(box, BorderLayout.CENTER);
		}

	private void control()
		{
		validate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
					{
					updateSimulationFromUser();
					}
			});
		}

	private void appearance()
		{
		// rien
		}

	private void updateSimulationFromUser()
		{
		tank.setCapacite(contenance.getValue());

		for(Substance sub:mapSubstanceParamLine.keySet())
			{
			System.out.println(mapSubstanceParamLine.get(sub).getValue());
			tank.editSubstance(sub, mapSubstanceParamLine.get(sub).getValue());
			}

		tank.setDebit(debit.getValue());
		//		tank.setInfini(getTankIsInfinit);

		parent.affTime(0);
		}

	public void update()
	{
		contenance.setValue(tank.getCapacite());
		debit.setValue(tank.getDebit());

		for(Substance sub : mapSubstanceParamLine.keySet())
		{
			mapSubstanceParamLine.get(sub).setValue(tank.getValueSubstance(sub));
		}
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private JPanelTank parent;
	private Tank tank;

	// Tools
	private JPanelParamLine contenance;
	private JPanelParamLine debit;
	private Map<Substance, JPanelParamLine> mapSubstanceParamLine;
	private JButton validate;
	private Box box;

	}
