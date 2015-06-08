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

package layout.tabresolution;

import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.JLabelFormula;
import differentialEquationSolving.SimulationSingleton;

public class JPanelResolutionData extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelResolutionData()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void reload()
		{
		loadLatexString();
		label.setFormula(latex);
		updateUI();
		}

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
		loadLatexString();
		label = new JLabelFormula(latex);

		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		setLayout(layout);
		add(label);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		setAlignmentY(TOP_ALIGNMENT);
		}

	private void loadLatexString()
		{
		Substance substance = SimulationSingleton.getInstance().getSubstanceAt(1);
		Tank tank = SimulationSingleton.getInstance().getMainTank();

		DecimalFormat df = new DecimalFormat("0.00");

		latex = "\\begin{array}{l}";
		latex += "\\boldsymbol{\\text{DONNÉES}}\\\\ \\\\ \\\\";
		latex += "\\boldsymbol{\\text{Caractéristiques du réservoir: " + tank.getName() + "}}\\\\";
		latex += "\\text{Capacité du réservoir:" + df.format(tank.getCapacite()) + "}\\\\";
		latex += "\\text{Débit sortant: " + df.format(tank.getDebit()) + "}\\\\";
		latex += "\\text{Quantité de substance au temps 0: } y(0) = " + df.format(tank.getValueSubstance(substance)) + "\\\\";

		for(Substance sub:SimulationSingleton.getInstance().getSubstanceList())
			{
			latex += "\\text{Quantité de ``" + sub.getName() + "'' dans le réservoir principal: " + df.format(tank.getValueSubstance(sub)) + "}\\\\";
			System.out.println(sub.getName() + " : " + df.format(tank.getValueSubstance(sub)));
			}

		int i = 1;
		for(Tank t:tank.getlistTankParent())
			{
			latex += "\\\\ \\\\";
			latex += "\\boldsymbol{\\text{Caractéristiques du réservoir parent: " + t.getName() + " "+i+"}}\\\\";
			latex += "\\text{Contenu du réservoir:" + df.format(t.getContent()) + "}\\\\";
			latex += "\\text{Débit sortant: " + df.format(t.getDebit()) + "}\\\\";

			for(Substance sub:SimulationSingleton.getInstance().getSubstanceList())
				{
				latex += "\\text{Quantité de ``" + sub.getName() + "'' dans le réservoir " + t.getName() + ": " + df.format(t.getValueSubstance(sub)) + "}\\\\";
				}
			i++;
			}

		latex += "\\\\ \\\\ \\\\ \\boldsymbol{\\text{CALCULS PRÉALABLES}}\\\\ \\\\ \\\\";
		latex += "\\text{Entrée de ``" + substance.getName() + "'': }" + df.format(tank.getInflow()) + " * " + df.format(tank.getInflowSubstance(substance)) + " = " + df.format(tank.getInflowTotal(substance)) + "\\\\";

		latex += "\\text{Sortie de ``" + substance.getName() + "'':} \\frac{\\text{Débit sortant}}{\\text{Contenu}} \\Rightarrow ";
		latex += "\\frac{" + df.format(tank.getDebit()) + "}{" + df.format(tank.getContent()) + "} * y(t)\\\\";
		latex += "\\text{Vitesse de variation: Entrée - Sortie(t)} \\Rightarrow ";
		latex += "y'(t) = " + df.format(tank.getInflowTotal(substance)) + " - \\frac{" + df.format(tank.getDebit()) + "}{" + df.format(tank.getContent()) + "} * y(t)\\\\";

		latex += "\\end{array}";
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private String latex;
	private JLabelFormula label;

	}
