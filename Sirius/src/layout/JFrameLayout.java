
package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
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
		setNewTitle("[Nouveau Fichier]*");

		eau = new Substance("Eau", (float)0.6, Substance.LIQUID);
		sel = new Substance("Sel", (float)1.0, Substance.SOLID);
		SimulationSingleton.getInstance().addSubstance(eau);
		SimulationSingleton.getInstance().addSubstance(sel);

//		Tank r1 = new Tank(false, 500, 0);
//		r1.setName("MainTank");
//		r1.addSubstance(eau, 500);
//		r1.addSubstance(sel,5);
//		SimulationSingleton.getInstance().setMainTank(r1);
//
//		Tank r2 = new Tank(false, 600, 0);
//		r2.setName("Tank");
//		r2.addSubstance(eau, 500);
//		r2.addSubstance(sel,5);
//
//		Tank r3 = new Tank(false, 700, 0);
//		r3.setName("Tank2");
//		r3.addSubstance(eau, 500);
//		r3.addSubstance(sel, 5);
//
//		r1.addTankParent(r2);
//		r2.addTankParent(r3);

		Tank r1 = new Tank(false, 500, 5);
		r1.setName("ParentTank");
		Tank r2 = new Tank(false, 200, 5);
		r2.setName("MainTank");

		r1.addSubstance(sel, 2);
		r1.addSubstance(eau, 500);

		r2.addSubstance(sel, 4);
		r2.addSubstance(eau, 100);
		r2.addTankParent(r1);

		SimulationSingleton.getInstance().setMainTank(r2);

		geometry();
		control();
		appearance();

		//Update with size

		panelSimulation.getJpanelContent().refresh();

		//TODO make the slider work

		panelSimulation.scrollPaneContent.setBounds(50, 30, 300, 50);
		panelSimulation.scrollPaneContent.setPreferredSize(new Dimension(10, 10));

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

		tabbedPane.addTab("Simulation", panelSimulation);
		tabbedPane.addTab("Résolution", panelResolution);

		createMenu();

			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);
			}

		// JComponent : add
		add(tabbedPane, BorderLayout.CENTER);
		}

	private void createMenu()
		{
		//		String filename = "test.glou";

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Fichiers");
		menuBar.add(menu);

		JMenuItem newBoard = new JMenuItem("Nouveau");
		newBoard.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					newBoard();
					}
			});
		newBoard.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));

		JMenuItem save = new JMenuItem("Enregistrer");
		save.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					save();
					}
			});
		save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

		JMenuItem saveAs = new JMenuItem("Enregistrer sous...");
		saveAs.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					saveAs();
					}
			});
		saveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));

		JMenuItem load = new JMenuItem("Ouvrir");
		load.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					open();
					}
			});
		load.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));

		JMenuItem exit = new JMenuItem("Quitter l'application");
		exit.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					System.exit(0);
					}
			});
		exit.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));

		menu.add(newBoard);
		menu.add(load);
		menu.add(save);
		menu.add(saveAs);
		menu.add(exit);
		this.setJMenuBar(menuBar);
		}

	private void control()
		{
		tabbedPane.addChangeListener(new ChangeListener()
			{
				@Override
				public void stateChanged(ChangeEvent e)
					{
					System.out.println("Changement");
					if (tabbedPane.getSelectedIndex() == tabbedPane.indexOfComponent(panelResolution))
						{
						panelResolution.reload();
						}
					panelSimulation.changeTabEvent();
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
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	private void setNewTitle(String name)
	{
		setTitle(title+name);
	}

	private void newBoard()
	{
		if (!SimulationSingleton.getInstance().isStarted())
		{
			String name = "[Nouveau Fichier]*";
			setNewTitle(name);
			SimulationSingleton.getInstance().deleteMainTank();

			Tank r1 = new Tank(false, 500, 5);

			r1.addSubstance(sel, 2);
			r1.addSubstance(eau, 500);

			SimulationSingleton.getInstance().setMainTank(r1);

			panelSimulation.getJpanelContent().refresh();

		}
		else
		{
			System.out.println("simulation en cours, creation non disponible");
		}

	}


	private void saveAs()
		{
		FileDialog fd = new FileDialog(JFrameLayout.this, "Sauvegarder dans un nouveau fichier", FileDialog.SAVE);
		fd.setDirectory("C:\\");
		fd.setFile("*.glou");
		fd.setVisible(true);
		String name = fd.getDirectory()+fd.getFile();

		if (name != null)
			{
				String extension;
				if(name.length() > 5) { extension = name.substring(name.length()-5); } else { extension = "KO"; }
				if (!extension.equals(".glou"))
					{
					name += (".glou");
					}
				SimulationSingleton.save(name);
				setNewTitle("["+name+"]");
				}
		}

	private void open()
		{
		FileDialog fd = new FileDialog(JFrameLayout.this, "Ouvrir un fichier existant", FileDialog.LOAD);
		fd.setDirectory("C:\\"); //TODO add test of OS and change path
		fd.setFile("*.glou");
		fd.setVisible(true);
		String name = fd.getDirectory()+fd.getFile();

		if (name != null)
			{
			SimulationSingleton.load(name);
			setNewTitle("["+name+"]");
			panelSimulation.getJpanelContent().refresh();
			}
		}

	private void save()
		{
		if (SimulationSingleton.getInstance().getCurrentFileName().equals(""))
			{
			saveAs();
			}
		else
			{
			SimulationSingleton.save(SimulationSingleton.getInstance().getCurrentFileName());
			panelSimulation.getJpanelContent().refresh();
			setNewTitle("["+SimulationSingleton.getInstance().getCurrentFileName()+"]");
			}
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

	public static final String title = "GlouGlou - Problèmes de Mélanges ";
	}
