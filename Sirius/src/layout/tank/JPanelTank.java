package layout.tank;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXCollapsiblePane.Direction;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTextField;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

public class JPanelTank extends JPanel {

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
		addMoreMenu();
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

	private void addMoreMenu() {
		tankIsInfinit = new JCheckBox();
		tankCapacity = new JXTextField("" + tank.getCapacite());
		tankDebit = new JXTextField(""+tank.getDebit());
		tankSubstanceNameList = new JXLabel[SimulationSingleton.getInstance().getSubstanceList().size()];
		tankSubstanceValueList = new JXTextField[SimulationSingleton.getInstance().getSubstanceList().size()];
		
		int i;

		// JXCollapsiblePane
		box.setBorder(new LineBorder(Color.black));

		Box sub_box1 = new Box(BoxLayout.X_AXIS);
		Box sub_box2 = new Box(BoxLayout.X_AXIS);
		Box sub_box5 = new Box(BoxLayout.X_AXIS);
		Box sub_box_substance = new Box(BoxLayout.X_AXIS);
		Box sub_box_substance_left = new Box(BoxLayout.Y_AXIS);
		Box sub_box_substance_right = new Box(BoxLayout.Y_AXIS);

		sub_box1.add(new JXLabel("Infini: "));
		sub_box1.add(tankIsInfinit);
		box.add(sub_box1);

		sub_box2.add(new JXLabel("Contenance: "));
		sub_box2.add(tankCapacity);
		box.add(sub_box2);

		box.add(new JXLabel("Configuration"));
		
		SimulationSingleton.getInstance()
		.getSubstanceList().size();
		i=0;

		for (Substance sub : SimulationSingleton.getInstance()
				.getSubstanceList()) {
			
			tankSubstanceNameList[i] = new JXLabel(sub.getName());
			sub_box_substance_left.add(tankSubstanceNameList[i]);
			i++;
		}
		i=0;
		
		for (Substance sub : SimulationSingleton.getInstance()
				.getSubstanceList()) {
			
			tankSubstanceValueList[i] = new JXTextField(""
					+ tank.getValueSubstance(sub));
			sub_box_substance_right.add(tankSubstanceValueList[i]);
			i++;
		}
		
		sub_box_substance.add(sub_box_substance_left);
		sub_box_substance.add(sub_box_substance_right);

		box.add(sub_box_substance);

		box.add(new JXButton("Ajouter"));

		sub_box5.add(new JXLabel("Debit: "));
		sub_box5.add(tankDebit);
		box.add(sub_box5);
		
		boutonValidateModifications = new JXButton("Valider");

		boutonValidateModifications.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 updateSimulationFromUser();
         }      
      });
		box.add(boutonValidateModifications);

		collapsibelPane.add(box);

		toggle.setText("More");
		
		collapsibelPane.setCollapsed(true);

		this.add(toggle);
		this.add(collapsibelPane);

	}
	
	private void updateSimulationFromUser() {
		
		double value = Double.parseDouble(tankSubstanceValueList[0].getText());
		double getTankDebit = Double.parseDouble(tankDebit.getText());
		double getTankCapacity = Double.parseDouble(tankCapacity.getText());
		boolean getTankIsInfinit = Boolean.parseBoolean(tankIsInfinit.getText());
		
		tank.setCapacite(getTankCapacity);
		tank.setDebit(getTankDebit);
		tank.setInfini(getTankIsInfinit);
	}  

	private JPanel createSeparation(int width) {
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

		boutonDelete = new JButton("X");

		boutonAddParent = new JButton("+");
		
		

		jpanelSolid = new JPanelSubstances(tank, null, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank,
				Substance.LIQUID, tank.isInfini());

		jpanelgraduation = new JPanelGraduation(tank.getCapacite());

		if (tank.isInfini())
			{
			initialWidth = 250;
			}
		else
			{
			jpanelContentTank = new JPannelContentTank(tank.getCapacite(), tank.getContent());
			}

		jpanelSolid = new JPanelSubstances(tank, null, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank, Substance.LIQUID, tank.isInfini());

		setFixeSize(jpanelgraduation, 50, maxHeight);

		jpanelTap = new JPannelTap();
		setFixeSize(jpanelTap, 60, maxHeight);

		jpanelDelete = createSeparation(20);
		jpanelDelete.add(boutonAddParent);
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
				setFixeSize(jpanelSolid, 60, maxHeight);
				setFixeSize(jpanelLiquid, 60, maxHeight);

				add(createSeparation(20));
				add(jpanelContentTank);
			}

			add(jpanelDelete);

			add(jpanelTap);
		}

		
		add(toggle);
		
//		int dy = getHeight();
//		int dx = getWidth();
//		
//		System.out.println(dx);

	}

	private void control()
		{
		final JPanelTank panelTank = this;
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
	private JButton boutonAddParent;
	private JButton boutonValidateModifications;
	
	
	private JCheckBox tankIsInfinit;
	private JXTextField tankCapacity;
	private JXTextField tankDebit;
	private JXLabel[] tankSubstanceNameList;
	private JXTextField[] tankSubstanceValueList; 
	 

	private int initialWidth;

	JXCollapsiblePane collapsibelPane = new JXCollapsiblePane(
			Direction.TRAILING);
	JXButton toggle = new JXButton(collapsibelPane.getActionMap().get(
			JXCollapsiblePane.TOGGLE_ACTION));
	Box box = new Box(BoxLayout.Y_AXIS);

	Box boxTank;

	// in
	private Tank tank;

}
