package layout;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

public class JFrameLayout extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameLayout()
		{

		/* TEST */
		eau = new Substance("Eau", (float)0.6, Substance.LIQUID);
		sel = new Substance("Sel", (float)1.0, Substance.SOLID);

		SimulationSingleton.getInstance().addSubstance(eau);
		SimulationSingleton.getInstance().addSubstance(sel);

		Tank r1 = new Tank(false, 500, 5);
		r1.setName("ParentTank");
		Tank r2 = new Tank(false, 200, 5);
		r2.setName("MainTank");
		Tank r3 = new Tank(false, 200, 5);
		r2.setName("Parent2");

		r1.addSubstance(sel, 2);
		r1.addSubstance(eau, 500);

		r2.addSubstance(sel, 4);
		r2.addSubstance(eau, 100);

		r3.addSubstance(sel, 4);
		r3.addSubstance(eau, 100);

		r2.addTankParent(r1);
		r2.addTankParent(r3);

		SimulationSingleton.getInstance().setMainTank(r2);

		r1 = null;
		r2 = null;
		r3 = null;
		geometry();
		control();
		appearance();

		//Update with size
		panelSimulation.getJpanelContent().refresh();
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

	private void geometry()
		{
			// JComponent : Instanciation
			tabbedPane = new JTabbedPane();

			panelSimulation = new JPanelTabSimulation();
			panelResolution = new JPanelTabResolution();

			tabbedPane.addTab("Simulation",panelSimulation);
			tabbedPane.addTab("Résolution",panelResolution);

			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);
			}

			// JComponent : add
			add(tabbedPane, BorderLayout.CENTER);
		}

	private void control()
		{
		tabbedPane.addChangeListener(new ChangeListener()
			{
				@Override
				public void stateChanged(ChangeEvent e)
					{
					System.out.println("Changement");
					if(tabbedPane.getSelectedIndex() == tabbedPane.indexOfComponent(panelResolution))
						{
							panelResolution.reload();
						}
					}
			});
		this.addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent arg0)
					{
					panelSimulation.getJpanelContent().refresh();
					}

			});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(1120, 600);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JTabbedPane tabbedPane;
	private JPanelTabSimulation panelSimulation;
	private JPanelTabResolution panelResolution;

	// TEST
	private Substance eau, sel;
	}
