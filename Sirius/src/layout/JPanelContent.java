
package layout;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import layout.tank.JPanelTank;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

public class JPanelContent extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelContent()
		{

		geometry();
		control();
		appearance();

		}

	//	class MovingAdapter extends MouseAdapter {
	//
	//		private int x;
	//		private int y;
	//
	//		@Override
	//		public void mousePressed(MouseEvent e) {
	//			x = e.getX();
	//			y = e.getY();
	//		}
	//
	//		@Override
	//		public void mouseDragged(MouseEvent e) {
	//
	//			int dx = e.getX() - x;
	//			int dy = e.getY() - y;
	//
	//			if (tank1.contains(x, y)) {
	//				dx = tank1.getX() + dx;
	//				dy = tank1.getY() + dy;
	//				tank1.setLocation(dx, dy);
	//				repaint();
	//			}
	//			x += dx;
	//			y += dy;
	//		}
	//	}

	//	class ScaleHandler implements MouseWheelListener {
	//		public void mouseWheelMoved(MouseWheelEvent e) {
	//
	//			int x = e.getX();
	//			int y = e.getY();
	//
	//			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	//
	//				if (tank1.contains(x, y)) {
	//
	//					float amount = e.getWheelRotation() * 5f;
	//					int width = (int) (tank1.getWidth() + amount);
	//					int height = (int) (tank1.getHeight() + amount);
	//					tank1.setSize(width, height);
	//				}
	//			}
	//		}
	//	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	// public Tank getMainContainer()
	// {
	// Tank mainContainer = panelParametresContainer.getConteneur();
	// mainContainer.addTankParent(panelParametresSource.getConteneur());
	// return mainContainer;
	// }

	//	@Override
	//	public void paint(Graphics g) {
	//		super.paint(g);
	//
	//		// Layout : Specification
	//		setLayout(null);
	//		add(tank1);
	//		int tx = tank1.getX() + tank1.getWidth() - tank1.getHeight() / 6;
	//		int ty = tank1.getY() + tank1.getHeight() / 2;
	//		toggle.setLocation(tx, ty);
	//		collapsibelPane.setLocation(tx + toggle.getWidth() + 10, ty
	//				- collapsibelPane.getHeight() / 2);
	//	}

	public List<JPanelTank> constructPannelsTank()
		{
		Tank mainTank = SimulationSingleton.getInstance().getMainTank();

		List<Tank> pileTank = new LinkedList<Tank>();
		pileTank.add(mainTank);

		List<JPanelTank> listPanelTank = new LinkedList<JPanelTank>();

		//Technique d'affichage temporaire

		int h = 10;
		while(!pileTank.isEmpty())
			{
			int i = pileTank.size() - 1;

			Tank tank = pileTank.get(i);
			//tank.printTank(0, SimulationSingleton.getInstance().getSubstanceList().get(0));
			pileTank.remove(i);

			if (tank != null)
				{
				pileTank.addAll(tank.getlistTankParent());

				JPanelTank panelTank = new JPanelTank(tank);

				add(panelTank);
				listPanelTank.add(panelTank);

				panelTank.setLocation(0, h); //TODO !
				h += panelTank.getHeight() + 10;

				tank = null;

//				System.out.println("IT'S A TANK !");
				}
			}

		mainTank = null;
//		System.out.println(listPanelTank);
		return listPanelTank;
		}

	public void affTime(double t)
		{
		//removeAll();
		for(JPanelTank paneltank:listPanelTank)
			{
//			System.out.println("Aff a tank");
			paneltank.affTime(t);
			}

		repaint();
		}

	public void refresh()
	{
		removeAll();

		listPanelTank = constructPannelsTank();

		repaint();
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

		// Layout : Specification
		setLayout(null);

		// JComponent : Instanciation
		listPanelTank = constructPannelsTank();

		}

	private void control()
		{

		//addParameters();
		//addMouseMotionListener(ma);
		//addMouseListener(ma);
		//addMouseWheelListener(new ScaleHandler());
		}

	private void appearance()
		{
		// rien
		}

	//	private void addBoutonsAddParentAndChild() {
	//
	//		// JXCollapsiblePane
	//		box.setBorder(new LineBorder(Color.black));
	//
	//		Box sub_box1 = new Box(BoxLayout.X_AXIS);
	//		Box sub_box2 = new Box(BoxLayout.X_AXIS);
	//		Box sub_box3 = new Box(BoxLayout.X_AXIS);
	//		Box sub_box4 = new Box(BoxLayout.X_AXIS);
	//		Box sub_box5 = new Box(BoxLayout.X_AXIS);
	//
	//		sub_box1.add(new JXLabel("Infini: "));
	//		sub_box1.add(new JCheckBox());
	//
	//		sub_box2.add(new JXLabel("Contenance: "));
	//		sub_box2.add(new JXTextField("100"));
	//
	//		sub_box3.add(new JXLabel("Eau: "));
	//		sub_box3.add(new JXTextField("55"));
	//
	//		sub_box4.add(new JXLabel("Element: "));
	//		sub_box4.add(new JXTextField("45"));
	//
	//		sub_box5.add(new JXLabel("Debit: "));
	//		sub_box5.add(new JXTextField("50"));
	//
	//		box.add(sub_box1);
	//		box.add(sub_box2);
	//		box.add(new JXLabel("Configuration"));
	//		box.add(sub_box3);
	//		box.add(sub_box4);
	//		box.add(new JXButton("Ajouter"));
	//		box.add(sub_box5);
	//
	//		collapsibelPane.add(box);
	//
	//		toggle.setText("More");
	//
	//		this.add(toggle);
	//		this.add(collapsibelPane);
	//
	//	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private List<JPanelTank> listPanelTank;

	//private JPanelTank tank1;
	//
	//	JXCollapsiblePane collapsibelPane = new JXCollapsiblePane(
	//			Direction.TRAILING);
	//	JXButton toggle = new JXButton(collapsibelPane.getActionMap().get(
	//			JXCollapsiblePane.TOGGLE_ACTION));
	//	Box box = new Box(BoxLayout.Y_AXIS);
	//	MovingAdapter ma = new MovingAdapter();

	}
