package layout;

import java.awt.BorderLayout;

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

		this.r1 = new Tank(false, 500, 5);
		this.r1.setName("ParentTank");
		this.r2 = new Tank(false, 200, 5);
		this.r2.setName("MainTank");

		this.r1.addSubstance(sel, 2);
		this.r1.addSubstance(eau, 500);

		this.r2.addSubstance(sel, 4);
		this.r2.addSubstance(eau, 100);
		r2.addTankParent(r1);

		SimulationSingleton.getInstance().setMainTank(r2);

		r1 = null;
		r2 = null;
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(800, 600);
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
	private Tank r1, r2;
	private Substance eau, sel;
	}
