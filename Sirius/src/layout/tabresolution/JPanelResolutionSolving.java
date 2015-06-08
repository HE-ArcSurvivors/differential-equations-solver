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
import tools.MathTools;
import differentialEquationSolving.JLabelFormula;
import differentialEquationSolving.SimulationSingleton;

public class JPanelResolutionSolving extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelResolutionSolving()
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

		// JComponent : Instanciation

		long gcm = MathTools.gcm((long)tank.getDebit(), (long)tank.getContent());
		String outFraction = "\\frac{" + df.format(tank.getDebit()) + "}{" + df.format(tank.getContent()) + "}";
		String outSimplifyFraction = "\\frac{" + df.format(tank.getDebit() / gcm) + "}{" + df.format(tank.getContent() / gcm) + "}";

		Double inflow = tank.getInflowTotal(substance);
		Double value = inflow * tank.getContent() / tank.getDebit();
		Double C = tank.getValueSubstance(substance) - value;

		latex = "\\begin{array}{l}";
		latex += "\\boldsymbol{\\text{RÉSOLUTION}}\\\\ \\\\ \\\\";
		latex += "\\frac{dy}{dt} = y'(t) = " + df.format(inflow) + " - " + outFraction + " * y(t) = " + df.format(tank.getInflowTotal(substance)) + " - " + outSimplifyFraction + " * y(t)\\\\";
		latex += "y'(t) + " + outSimplifyFraction + " * y(t) = " + df.format(inflow) + " \\Rightarrow ";
		latex += "\\text{Il s'agit d'une équation différentielle linéaire d'ordre 1} \\\\";
		latex += "μ = e^{\\int{(" + outSimplifyFraction + ")dt}} = e^{t*" + outSimplifyFraction + "} \\Rightarrow ";
		latex += "\\frac{d}{dt} * μ = " + df.format(inflow) + " * μ\\\\";
		latex += "\\frac{d}{dt}(e^{t*" +  outSimplifyFraction + "}) = " + df.format(inflow) + " * e^{t*" + outSimplifyFraction + "}\\\\";

		latex += "e^{t*" + outSimplifyFraction + "}*y = \\int{" + df.format(inflow) + "*e^{t*" + outSimplifyFraction + "}dt} = " + df.format(value) + "*e^{t*" + outSimplifyFraction + "}+C\\\\";

		latex += "\\doublebox{y(t) = " + df.format(value) + " + C * e^{-t*" + outSimplifyFraction + "}} \\\\ \\\\";
		latex += "\\\\ \\text{D'après la condition initiale : } \\\\ ";

		DecimalFormat dfsigned = new DecimalFormat("+#,##0.00;-#");

		latex += "y(0) = " + df.format(tank.getValueSubstance(substance)) + " = " + df.format(value) + " + C \\Rightarrow  C = " + df.format(C) + "\\\\";
		latex += "y(t) = " + df.format(value) + " " + dfsigned.format(C) + " * e^{-t*" + outSimplifyFraction + "}\\\\";
		latex += "\\end{array}";
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private String latex;
	private JLabelFormula label;
	}
