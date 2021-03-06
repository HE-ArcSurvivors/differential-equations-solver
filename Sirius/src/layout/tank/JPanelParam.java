/*********************************************************************************
# Copyright © Haute-Ecole ARC - All Rights Reserved
# Copyright © Banana Rocket - All Rights Reserved
#
# This file is part of <P2 Java Project: GlouGlou - Problèmes de mélanges>.
#
# Unauthorized copying of this file, via any medium is strictly prohibited
# Proprietary and confidential
# Written by Claret-Yakovenko Roman <romain.claret@rocla.ch>
# Written by Divernois Margaux <margaux.divernois@gmail.com>
# Written by Visinand Steve <visinandst@gmail.com>
#
# Date : June 2015
**********************************************************************************/

package layout.tank;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import substance.Substance;
import tank.Tank;
import tools.MagasinImage;
import tools.SwingUtil;
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

		TitledBorder borderTitle = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),"Paramètres");
		borderTitle.setTitleJustification(TitledBorder.RIGHT);
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,20, 20, 20),borderTitle);
		setBorder(border);

		//tankIsInfini = new JCheckBox();
		//	sub_box1.add(new JXLabel("Infini: "));
		//	sub_box1.add(tankIsInfini);
		//	box.add(sub_box1);

		contenance = new JPanelParamLine("Capacité : ", tank.getCapacite());
		box.add(contenance);
		debit = new JPanelParamLine("Débit : ", tank.getDebit());
		box.add(debit);

		//box.add(new JLabel("Configuration"));
		for(Substance sub:SimulationSingleton.getInstance().getSubstanceList())
			{
			mapSubstanceParamLine.put(sub, new JPanelParamLine(sub.getName() + " : ", tank.getValueSubstance(sub)));
			box.add(mapSubstanceParamLine.get(sub));
			}

		validate = new JButton(MagasinImage.iconValidate);
		validate.setToolTipText("Valider les modifications");

		box.add(validate);

		setLayout(new BorderLayout());
		add(box, BorderLayout.CENTER);
		}

	private void control()
		{
		validate.addActionListener(new ActionListener()
			{
				@Override
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
		//tank.setInfini();

		if (contenance.getValue() < tank.getContent())
			{
			contenance.setWarning("Votre quantité de liquide est supérieur à la capacité de votre réservoir");
			SimulationSingleton.getInstance().simulationActive(false);
			}
		else
			{
			contenance.setWarning("");
			contenance.setIcon(null);
			SimulationSingleton.getInstance().simulationActive(true);
			}

		parent.affTime(0);
		SwingUtil.repaintAllParent(this);
		}

	public void update()
		{
		debit.setValue(tank.getDebit());

		for(Substance sub:mapSubstanceParamLine.keySet())
			{
			mapSubstanceParamLine.get(sub).setValue(tank.getValueSubstance(sub));
			}

		revalidate();
		repaint();
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
