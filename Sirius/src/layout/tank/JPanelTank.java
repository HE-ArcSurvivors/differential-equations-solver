
package layout.tank;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXCollapsiblePane.Direction;

import substance.Substance;
import tank.Tank;
import tools.MagasinImage;
import differentialEquationSolving.SimulationSingleton;

public class JPanelTank extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

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
	public JButton getBoutonDelete() {		return this.boutonDelete;
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
		{		int maxHeight = 500;

		collapsiblePane = new JXCollapsiblePane(Direction.TRAILING);
		collapsiblePane.setAlignmentX(LEFT_ALIGNMENT);
		jpanelparam = new JPanelParam(this, tank);
		collapsiblePane.add(jpanelparam);
		collapsiblePane.setCollapsed(true);

		Action toggleAction = collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
		buttonSettings = new JButton();
		buttonSettings.setAction(toggleAction);
		buttonSettings.setIcon(MagasinImage.iconSettings);
		buttonSettings.setText("");

		boutonDelete = new JButton("X");
		boutonAddParent = new JButton("+");
		jpanelSolid = new JPanelSubstances(tank, null, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank,Substance.LIQUID, tank.isInfini());

		jpanelgraduation = new JPanelGraduation(tank.getCapacite());

		if (tank.isInfini())
			{
			initialWidth = 250;

jpanelSolid = new JPanelSubstances(tank, null, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank, Substance.LIQUID, tank.isInfini());

		setFixeSize(jpanelgraduation, 50, maxHeight);

		jpanelTap = new JPannelTap();
		setFixeSize(jpanelTap, 60, maxHeight);

		jpanelDelete = createSeparation(20);
		jpanelDelete.add(boutonAddParent);
		jpanelDelete.add(boutonDelete);
		jpanelDelete.add(buttonSettings);
		setFixeSize(jpanelDelete, 30, maxHeight);

		{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

			add(jpanelSolid);

			add(jpanelLiquid);

			if (!tank.isInfini())
				{
				setFixeSize(jpanelSolid, 60, maxHeight);
				setFixeSize(jpanelLiquid, 60, maxHeight);

				add(createSeparation(20));
				add(jpanelContentTank);
			}

		add(jpanelDelete);
		add(jpanelTap);
		add(collapsiblePane);

		}

final JPanelTank panelTank = this;
		boutonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Container tmp = panelTank.getParent();
				deleteTank();
				panelTank.getParent().remove(panelTank);
				tmp.repaint();
			}
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
		
		boutonAddParent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				Tank parentTank = new Tank(false, 0, 0);
				Substance eau = new Substance("Eau", (float)0.6, Substance.LIQUID);

				parentTank.setName("Added Parent by user");
				parentTank.addSubstance(eau, 500);

				tank.addTankParent(parentTank);

				SimulationSingleton.getInstance().setMainTank(tank);
				
				System.out.println(""+parentTank.getName());
				
				Container tmp = panelTank.getParent();
				tmp.repaint();
				
			}

		});
	public void deleteTank()
		{

		if (SimulationSingleton.getInstance().getMainTank() == tank) {			System.out.println("delete main");
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
	private JButton boutonAddParent;

	private int initialWidth;

	private JXCollapsiblePane collapsiblePane;
	private JPanelParam jpanelparam;
	private JButton buttonSettings;

	Box boxTank;

	// in
	private Tank tank;

	}
