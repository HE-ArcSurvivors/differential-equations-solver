
package layout.tabresolution;

import java.awt.BorderLayout;
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

		layout = new BorderLayout();
		setLayout(layout);
		add(label, BorderLayout.CENTER);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		setAlignmentX(LEFT_ALIGNMENT);
		setAlignmentY(TOP_ALIGNMENT);
		}

	private void loadLatexString()
		{
		Substance substance = SimulationSingleton.getInstance().getSubstanceAt(1);
		Tank tank = SimulationSingleton.getInstance().getMainTank();
		// JComponent : Instanciation

		long gcm = MathTools.gcm((long)tank.getDebit(), (long)tank.getContent());
		String outFraction = "\\frac{" + tank.getDebit() + "}{" + tank.getContent() + "}";
		String outSimplifyFraction = "\\frac{" + tank.getDebit() / gcm + "}{" + tank.getContent() / gcm + "}";

		Double inflow = tank.getInflowTotal(substance);
		Double value = inflow * tank.getContent() / tank.getDebit();
		Double C = tank.getValueSubstance(substance) - value;

		latex = "\\begin{array}{l}";
		latex += "\\boldsymbol{\\text{RÉSOLUTION}}\\\\ \\\\ \\\\";
		latex += "\\frac{dy}{dt} = y'(t) = " + inflow + " - " + outFraction + " * y(t) = " + tank.getInflowTotal(substance) + " - " + outSimplifyFraction + " * y(t)\\\\";
		latex += "y'(t) + " + outSimplifyFraction + " * y(t) = " + inflow + " \\Rightarrow ";
		latex += "\\text{Il s'agit d'une équation différentielle linéaire d'ordre 1} \\\\";
		latex += "μ = e^{\\int{(" + outSimplifyFraction + ")dt}} = e^{t*" + outSimplifyFraction + "} \\Rightarrow ";
		latex += "\\frac{d}{dt} * μ = " + inflow + " * μ\\\\";
		latex += "\\frac{d}{dt}(e^{t*" + outSimplifyFraction + "}) = " + inflow + " * e^{t*" + outSimplifyFraction + "}\\\\";

		latex += "e^{t*" + outSimplifyFraction + "}*y = \\int{" + inflow + "*e^{t*" + outSimplifyFraction + "}dt} = " + value + "*e^{t*" + outSimplifyFraction + "}+C\\\\";

		latex += "\\doublebox{y(t) = " + value + " + C * e^{-t*" + outSimplifyFraction + "}} \\\\ \\\\";
		latex += "\\\\ \\text{D'après la condition initiale : } \\\\ ";

		DecimalFormat dfsigned = new DecimalFormat("+#,##0.00;-#");

		latex += "y(0) = " + tank.getValueSubstance(substance) + " = " + value + " + C \\Rightarrow  C = " + C + "\\\\";
		latex += "y(t) = " + value + " " + dfsigned.format(C) + " * e^{-t*" + outSimplifyFraction + "}\\\\";
		latex += "\\end{array}";
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private String latex;
	private JLabelFormula label;
	private BorderLayout layout;

	}
