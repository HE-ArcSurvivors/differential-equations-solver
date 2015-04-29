
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

	public JPanelTank(Tank tank)
		{
		this.initialWidth = 400;
		this.tank = tank;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void affTime(double t)
		{
		jpanelSolid.updateFromTank(t);
		jpanelLiquid.updateFromTank(t);
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


		jpanelSolid = new JPanelSubstances(tank, null, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank, Substance.LIQUID, tank.isInfini());

		jpanelgraduation = new JPanelGraduation(tank.getCapacite());

		if (tank.isInfini())
			{
			initialWidth = 250;
			}
		else
			{
			jpanelContentTank = new JPannelContentTank(tank.getCapacite(), tank.getContent());

			jpanelSolid.setPreferredSize(new Dimension(60, 0));
			jpanelSolid.setMinimumSize(new Dimension(60, 0));
			jpanelSolid.setMaximumSize(new Dimension(60, maxHeight));
			jpanelLiquid.setPreferredSize(new Dimension(60, 0));
			jpanelLiquid.setMinimumSize(new Dimension(60, 0));
			jpanelLiquid.setMaximumSize(new Dimension(60, maxHeight));
			}

		jpanelgraduation.setPreferredSize(new Dimension(50, 0));
		jpanelgraduation.setMinimumSize(new Dimension(50, 0));
		jpanelgraduation.setMaximumSize(new Dimension(50, maxHeight));


		jpanelTap = new JPannelTap();
		jpanelTap.setPreferredSize(new Dimension(60, 0));
		jpanelTap.setMinimumSize(new Dimension(60, 0));
		jpanelTap.setMaximumSize(new Dimension(60, maxHeight));

			// Layout : Specification
			{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			add(jpanelgraduation);
			add(jpanelSolid);

			add(createSeparation(10));
			add(jpanelLiquid);
			add(createSeparation(20));

			if (!tank.isInfini())
				{
				add(jpanelContentTank);
				add(createSeparation(20));
				}

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
		setSize(initialWidth, 200);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelGraduation jpanelgraduation;
	private JPanelSubstances jpanelSolid;
	private JPanelSubstances jpanelLiquid;
	private JPannelContentTank jpanelContentTank;

	private JPannelTap jpanelTap;

	private int initialWidth;

	//in
	private Tank tank;

	}
