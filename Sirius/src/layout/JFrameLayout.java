/*********************************************************************************
# Copyright © Haute-Ecole ARC - All Rights Reserved
# Copyright © Banana Rocket - All Rights Reserved
#
# This file is part of <P2 Java Project: GlouGlou - Problèmes de mélanges>.
#
# Unauthorized copying of this file, via any medium is strictly prohibited
# Proprietary and confidential
# Written by Claret-Yakovenko Roman <romain.claret@rocla.ch>
# Written by Divernois Margaux <margaux.divernois@gmail.com>
# Written by Visinand Steve <visinandst@gmail.com>
#
# Date : June 2015
**********************************************************************************/

package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
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

		newBoard();

		geometry();
		control();
		appearance();

		panelSimulation.getJPanelContent().refresh();
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

		JMenuBar menuBar = new JMenuBar();
		JMenu menuFiles = new JMenu("Fichiers");
		JMenu menuHelp = new JMenu("Aide");

		menuBar.add(menuFiles);
		menuBar.add(menuHelp);

		JMenuItem newBoard = new JMenuItem("Nouveau");
		newBoard.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					newBoard();
					panelSimulation.getJPanelContent().refresh();
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

		JMenuItem about = new JMenuItem("A propos de GlouGlou");
		about.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{

					JPanelAbout aboutPanel = new JPanelAbout();
					JDialog dialog = new JDialog();
					dialog.setTitle("A propos de GlouGlou");
					dialog.setPreferredSize(new Dimension(500,500));
					dialog.setMinimumSize(new Dimension(500,500));
					dialog.setMaximumSize(new Dimension(500,500));
					dialog.setLocationRelativeTo(null);
					dialog.add(aboutPanel);
					dialog.setVisible(true);

					}
			});
		about.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));

		JMenuItem help = new JMenuItem("Aide");
		help.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{

					JPanelHelp helpPanel = new JPanelHelp();
					JDialog dialog = new JDialog();
					dialog.setTitle("Aide");
					dialog.setPreferredSize(new Dimension(500,500));
					dialog.setMinimumSize(new Dimension(500,500));
					dialog.setMaximumSize(new Dimension(500,500));
					dialog.setLocationRelativeTo(null);
					dialog.add(helpPanel);
					dialog.setVisible(true);

					}
			});
		
		JMenuItem videoHelp = new JMenuItem("Aide Video");
		videoHelp.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{

					JPanelVideoHelp helpVideoPanel = new JPanelVideoHelp();
					JDialog dialog = new JDialog();
					dialog.setTitle("Aide Video");
					dialog.setPreferredSize(new Dimension(500,500));
					dialog.setMinimumSize(new Dimension(500,500));
					dialog.setMaximumSize(new Dimension(500,500));
					dialog.setLocationRelativeTo(null);
					dialog.add(helpVideoPanel);
					dialog.setVisible(true);

					}
			});

		menuFiles.add(newBoard);
		menuFiles.add(load);
		menuFiles.add(save);
		menuFiles.add(saveAs);
		menuFiles.add(exit);

		menuHelp.add(about);
//		menuHelp.add(videoHelp);
		menuHelp.add(help);

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
					panelSimulation.getJPanelContent().refresh();
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
		}
		else
		{
			System.out.println("Simulation en cours, Création non disponible");
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
		fd.setFile("*.glou");
		fd.setVisible(true);
		String name = fd.getDirectory()+fd.getFile();

		if (name != null)
			{
			SimulationSingleton.load(name);
			setNewTitle("["+name+"]");
			panelSimulation.getJPanelContent().refresh();
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
			panelSimulation.getJPanelContent().refresh();
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
