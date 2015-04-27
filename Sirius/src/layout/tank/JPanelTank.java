
package layout.tank;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import tank.Tank;

public class JPanelTank extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTank(Tank tank)
		{
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
		jpanelSubstances.updateFromTank(t);
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

		jpanelgraduation = new JPanelGraduation(tank.getCapacite());
		jpanelgraduation.setPreferredSize(new Dimension(50, 0));
		jpanelgraduation.setMinimumSize(new Dimension(50, 0));
		jpanelgraduation.setMaximumSize(new Dimension(50, maxHeight));

		jpanelContentTank = new JPannelContentTank(tank.getCapacite(), tank.getContent());

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
		setSize(350, 200);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelGraduation jpanelgraduation;
	private JPanelSubstances jpanelSubstances;
	private JPannelContentTank jpanelContentTank;
	private JPannelTap jpanelTap;

	//in
	private Tank tank;

	}
