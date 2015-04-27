
package layout.tank;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import substance.Substance;
import tank.Tank;

public class JPanelTank extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTank()
		{
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

	private JPanel createSeparation(int width)
		{
		int maxHeight = 500;
		JPanel jpanelSeparation;
		jpanelSeparation = new JPanel();
		jpanelSeparation.setBackground(Color.LIGHT_GRAY);
		jpanelSeparation.setPreferredSize(new Dimension(width, 0));
		jpanelSeparation.setMinimumSize(new Dimension(width, 0));
		jpanelSeparation.setMaximumSize(new Dimension(width, maxHeight));
		return jpanelSeparation;
	}
	private void geometry()
		{
		// JComponent : Instanciation
		int maxHeight = 500;

		//Double t = 0.0;


		Tank tank = new Tank(false, 100, 5);
		tank.addSubstance(new Substance("eau", (float) 0.6, Substance.LIQUID), 40);
		tank.addSubstance(new Substance("sel", (float) 1.0, Substance.SOLID), 10);

		jpanelgraduation = new JPanelGraduation(tank.getCapacite());
		jpanelgraduation.setPreferredSize(new Dimension(50, 0));
		jpanelgraduation.setMinimumSize(new Dimension(50, 0));
		jpanelgraduation.setMaximumSize(new Dimension(50, maxHeight));

		jpanelContentTank = new JPannelContentTank(tank.getCapacite(), 50); //TODO : getCapacite t = 0

		jpanelSubstances = new JPanelSubstances(tank, jpanelContentTank);
		jpanelSubstances.setPreferredSize(new Dimension(60, 0));
		jpanelSubstances.setMinimumSize(new Dimension(60, 0));
		jpanelSubstances.setMaximumSize(new Dimension(60, maxHeight));

		jpanelTap = new JPannelTap();
		jpanelTap.setPreferredSize(new Dimension(60, 0));
		jpanelTap.setMinimumSize(new Dimension(60, 0));
		jpanelTap.setMaximumSize(new Dimension(60, maxHeight));

			// Layout : Specification
			{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(jpanelgraduation);
			add(jpanelSubstances);
			add(createSeparation(20));
			add(jpanelContentTank);
			add(createSeparation(20));
			add(jpanelTap);
			}

		// JComponent : add

		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		setBackground(Color.LIGHT_GRAY);
		setSize(500, 300);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelGraduation jpanelgraduation;
	private JPanelSubstances jpanelSubstances;
	private JPannelContentTank jpanelContentTank;
	private JPannelTap jpanelTap;

	}
