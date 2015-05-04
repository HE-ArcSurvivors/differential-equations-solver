
package layout.tank;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

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
	public JButton getBoutonDelete()
		{
		return this.boutonDelete;
		}

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

	private void setFixeSize(JPanel jpanel, int width, int maxHeight)
		{
		jpanel.setMinimumSize(new Dimension(width, 0));
		jpanel.setMaximumSize(new Dimension(width, maxHeight));
		jpanel.setPreferredSize(new Dimension(width, 0));
		}

	private void geometry()
		{
		// JComponent : Instanciation
		int maxHeight = 500;

		boutonDelete = new JButton("D");

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

			setFixeSize(jpanelSolid, 60, maxHeight);
			setFixeSize(jpanelLiquid, 60, maxHeight);
			}


		setFixeSize(jpanelgraduation, 50, maxHeight);

		jpanelTap = new JPannelTap();
		setFixeSize(jpanelTap, 60, maxHeight);

		jpanelDelete = createSeparation(20);
		jpanelDelete.add(boutonDelete);

			// Layout : Specification
			{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			add(jpanelgraduation);
			add(jpanelSolid);

			add(createSeparation(10));
			add(jpanelLiquid);

			if (!tank.isInfini())
				{
				add(createSeparation(20));
				add(jpanelContentTank);
				}

			add(jpanelDelete);

			add(jpanelTap);
			}

		// JComponent : add

		}

	private void control()
		{
		//		boutonDelete.addMouseListener(new MouseAdapter()
		//			{
		//
		//				@Override
		//				public void mousePressed(MouseEvent e)
		//					{
		//					tank.delete();
		//					tank = null;
		//					SimulationSingleton.getInstance().delete();
		//					repaint();
		//					}
		//
		//			});
		}

	public void deleteTank()
		{

		if (SimulationSingleton.getInstance().getMainTank() == tank)
			{
			System.out.println("delete main");
			SimulationSingleton.getInstance().deleteMainTank();
			}

		tank.delete();
		tank = null;

		repaint();
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
	private JPanel jpanelDelete;

	private JPannelTap jpanelTap;
	private JButton boutonDelete;

	private int initialWidth;

	//in
	private Tank tank;

	}
