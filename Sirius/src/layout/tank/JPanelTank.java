
package layout.tank;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import layout.JPanelContent;
import substance.Substance;
import tank.Tank;
import tools.MagasinImage;
import differentialEquationSolving.SimulationSingleton;

public class JPanelTank extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTank(Tank tank, boolean leftMode)
		{
		this.initialWidth = 510;
		this.tank = tank;
		this.isLeftMode = leftMode;

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
		int maxHeight = 500;

		jpanelparam = new JPanelParam(this, tank);
		jpanelparam.setVisible(false);

		buttonSettings = new JButton("");
		buttonSettings.setIcon(MagasinImage.iconSettings);
		buttonSettings.setContentAreaFilled(false);
		buttonSettings.setBorder(BorderFactory.createEmptyBorder());

		boutonDelete = new JButton(MagasinImage.iconDelete);
		boutonDelete.setContentAreaFilled(false);
		boutonDelete.setBorder(BorderFactory.createEmptyBorder());

		boutonAddParent = new JButton(MagasinImage.iconAdd);
		boutonAddParent.setContentAreaFilled(false);
		boutonAddParent.setBorder(BorderFactory.createEmptyBorder());

		jpanelGraduationSolid = new JPanelGraduation(tank.getCapacite());

		jpanelGraduationLiquid = new JPanelGraduation(tank.getCapacite());

		if (tank.isInfini())
			{
			initialWidth = 250;
			}
		else
			{
			jpanelContentTank = new JPannelContentTank(tank.getCapacite(), tank.getContent());
			}

		setFixeSize(jpanelGraduationLiquid, 35, maxHeight);

		jpanelSolid = new JPanelSubstances(tank, null, jpanelparam, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank, jpanelparam, Substance.LIQUID, tank.isInfini());
		jpanelTap = new JPannelTap(isLeftMode);
		setFixeSize(jpanelTap, 60, maxHeight);

		setFixeSize(jpanelGraduationSolid, 50, maxHeight);

		jpanelDelete = createSeparation(20);
		jpanelDelete.add(boutonAddParent);
		jpanelDelete.add(buttonSettings);
		jpanelDelete.add(boutonDelete);
		setFixeSize(jpanelDelete, 30, maxHeight);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		if (isLeftMode)
			{
			add(jpanelTap);
			}

		add(jpanelGraduationSolid);
		add(jpanelSolid);

		add(createSeparation(10));
		add(jpanelLiquid);

		add(jpanelGraduationLiquid);

		if (!tank.isInfini())
			{
			setFixeSize(jpanelSolid, 60, maxHeight);
			setFixeSize(jpanelLiquid, 60, maxHeight);

			add(createSeparation(20));
			add(jpanelContentTank);
			}

		add(jpanelDelete);

		if (!isLeftMode)
			{
			add(jpanelTap);
			}

		add(jpanelparam);
		}

	private void control()
		{
		final JPanelTank panelTank = this;

		buttonSettings.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mousePressed(MouseEvent arg0)
					{
					jpanelparam.setVisible(!jpanelparam.isVisible());
					}
			});

		boutonDelete.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mousePressed(MouseEvent arg0)
					{
					if (!SimulationSingleton.getInstance().isStarted())
						{
						Container tmp = panelTank.getParent();
						deleteTank();
						((JPanelContent)panelTank.getParent()).refresh();
						tmp.repaint();
						}
					}
			});

		boutonAddParent.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mousePressed(MouseEvent arg0)
					{

					if (tank.getTankChild() == null)
						{

						Tank parentTank = new Tank(false, 500, 0);

						parentTank.setName("Added Parent by user");
						parentTank.addSubstance(SimulationSingleton.getInstance().getSubstanceAt(0), 500);
						parentTank.addSubstance(SimulationSingleton.getInstance().getSubstanceAt(1), 0);

						tank.addTankParent(parentTank);

						System.out.println("Nouveau tank : " + parentTank.getName());

						((JPanelContent)panelTank.getParent()).refresh();
						}
					else
						{
						System.out.println("Ce niveau n'est pas encore disponible");
						}
					}

			});
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
	private JPanelGraduation jpanelGraduationSolid;
	private JPanelSubstances jpanelSolid;
	private JPanelSubstances jpanelLiquid;
	private JPanelGraduation jpanelGraduationLiquid;
	private JPannelContentTank jpanelContentTank;
	private JPanel jpanelDelete;

	private JPannelTap jpanelTap;
	private JButton boutonDelete;
	private JButton boutonAddParent;

	private int initialWidth;

	//private JXCollapsiblePane collapsiblePane;
	private JPanelParam jpanelparam;
	private JButton buttonSettings;

	Box boxTank;

	// in
	private Tank tank;
	private boolean isLeftMode;

	}
