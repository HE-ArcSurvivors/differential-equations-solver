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

package layout.tank;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
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

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);

		updateButtonSimulation();
		}

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
		jpanelparam.setVisible(tank.visibleParamsStatus);

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

		updateButtonSimulation();

		jpanelGraduationSolid = new JPanelGraduation(tank.getCapacite(), Substance.SOLID);
		jpanelGraduationLiquid = new JPanelGraduation(tank.getCapacite(), Substance.LIQUID);

		if (tank.isInfini())
			{
			initialWidth = 250;
			}
		else
			{
			jpanelContentTank = new JPannelContentTank(tank.getCapacite(), tank.getContent());
			}

		jpanelSolid = new JPanelSubstances(tank, null, jpanelparam, Substance.SOLID, false);
		jpanelLiquid = new JPanelSubstances(tank, jpanelContentTank, jpanelparam, Substance.LIQUID, tank.isInfini());

		jpanelTap = new JPannelTap(isLeftMode);
		setFixeSize(jpanelTap, 60, maxHeight);

		setFixeSize(jpanelGraduationSolid, 50, maxHeight);
		setFixeSize(jpanelGraduationLiquid, 50, maxHeight);

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

			add(jpanelContentTank);
			}

		add(jpanelDelete);

		if (!isLeftMode)
			{
			add(jpanelTap);
			}

		add(jpanelparam);
		}

	private void updateButtonSimulation()
	{
		boutonDelete.setEnabled(!SimulationSingleton.getInstance().isStarted());
		buttonSettings.setEnabled(!SimulationSingleton.getInstance().isStarted());
		boutonAddParent.setEnabled(!SimulationSingleton.getInstance().isStarted());

		// EMPECHER LA SUPPRESSION DU MAINTANK
		if (tank == SimulationSingleton.getInstance().getMainTank())
			{
			boutonDelete.setEnabled(false);
			}

		// EMPECHER L'AJOUT EN DESSUS DU 2ND NIVEAU
		if (tank.getTankChild() != null)
			{
			boutonAddParent.setEnabled(false);
			}

		if(SimulationSingleton.getInstance().isStarted())
			{
			jpanelparam.setVisible(false);
			}
	}

	private void control()
		{
		final JPanelTank panelTank = this;

		buttonSettings.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent arg0)
					{
					if (!SimulationSingleton.getInstance().isStarted())
						{
						tank.visibleParamsStatus = !tank.visibleParamsStatus;
						jpanelparam.setVisible(tank.visibleParamsStatus);
						}
					}
			});

		boutonDelete.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mousePressed(MouseEvent arg0)
					{
					if (tank != SimulationSingleton.getInstance().getMainTank())
						{
						if (!SimulationSingleton.getInstance().isStarted())
							{
							Container tmp = panelTank.getParent();
							deleteTank();
							((JPanelContent)panelTank.getParent()).refresh();
							tmp.repaint();
							}
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
						if (!SimulationSingleton.getInstance().isStarted())
							{
							Tank parentTank = new Tank(false, 500, 2);

							parentTank.setName("Parent Tank");
							parentTank.addSubstance(SimulationSingleton.getInstance().getSubstanceAt(0), 500);
							parentTank.addSubstance(SimulationSingleton.getInstance().getSubstanceAt(1), 0);

							tank.addTankParent(parentTank);

							((JPanelContent)panelTank.getParent()).refresh();
							}
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
