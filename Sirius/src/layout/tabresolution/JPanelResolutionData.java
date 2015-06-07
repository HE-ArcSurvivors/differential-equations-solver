
package layout.tabresolution;

import java.awt.FlowLayout;

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

		latex = "\\begin{array}{l}";
		latex += "\\boldsymbol{\\text{DONNÉES}}\\\\ \\\\ \\\\";
		latex += "\\boldsymbol{\\text{Caractéristiques du réservoir: " + tank.getName() + "}}\\\\";
		latex += "\\text{Capacité du réservoir:" + tank.getCapacite() + "}\\\\";
		latex += "\\text{Débit sortant: " + tank.getDebit() + "}\\\\";
		latex += "\\text{Quantité de substance au temps 0: } y(0) = " + tank.getValueSubstance(substance) + "\\\\";

		for(Substance sub:SimulationSingleton.getInstance().getSubstanceList())
			{
			latex += "\\text{Quantité de ``" + sub.getName() + "'' dans le réservoir principal: " + tank.getValueSubstance(sub) + "}\\\\";
			System.out.println(sub.getName() + " : " + tank.getValueSubstance(sub));
			}

		for(Tank t:tank.getlistTankParent())
			{
			latex += "\\\\ \\\\";
			latex += "\\boldsymbol{\\text{Caractéristiques du réservoir parent: " + t.getName() + "}}\\\\";
			latex += "\\text{Contenu du réservoir:" + t.getContent() + "}\\\\";
			latex += "\\text{Débit sortant: " + t.getDebit() + "}\\\\";

			for(Substance sub:SimulationSingleton.getInstance().getSubstanceList())
				{
				latex += "\\text{Quantité de ``" + sub.getName() + "'' dans le réservoir " + t.getName() + ": " + t.getValueSubstance(sub) + "}\\\\";
				}
			}

		latex += "\\\\ \\\\ \\\\ \\boldsymbol{\\text{CALCULS PRÉALABLES}}\\\\ \\\\ \\\\";
		latex += "\\text{Entrée de ``" + substance.getName() + "'': }" + tank.getInflow() + " * " + tank.getInflowSubstance(substance) + " = " + tank.getInflowTotal(substance) + "\\\\";

		latex += "\\text{Sortie de ``" + substance.getName() + "'':} \\frac{\\text{Débit sortant}}{\\text{Contenu}} \\Rightarrow ";
		latex += "\\frac{" + tank.getDebit() + "}{" + tank.getContent() + "} * y(t)\\\\";
		latex += "\\text{Vitesse de variation: Entrée - Sortie(t)} \\Rightarrow ";
		latex += "y'(t) = " + SimulationSingleton.getInstance().getMainTank().getInflowTotal(substance) + " - \\frac{" + tank.getDebit() + "}{" + tank.getContent() + "} * y(t)\\\\";

		latex += "\\end{array}";
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private String latex;
	private JLabelFormula label;

	}
