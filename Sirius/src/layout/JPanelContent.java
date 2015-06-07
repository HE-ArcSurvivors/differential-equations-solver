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

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import layout.atom.JPanelInformationSimulation;
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
		listPanelTank = new LinkedList<JPanelTank>();

		informationSimulation = new JPanelInformationSimulation();
		informationSimulation.setLocation(100,100);

		scrolX = 0;
		scrolY = 0;
		startPosX = 0;
		startPosY = 0;
		t=null;
		geometry();
		control();
		appearance();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public List<JPanelTank> constructPannelsTank()
		{
		Tank mainTank = SimulationSingleton.getInstance().getMainTank();

		List<Tank> pileTank = new LinkedList<Tank>();
		pileTank.add(mainTank);

		//tout les tanks a afficher et leur panels
		HashMap<Tank, JPanelTank> mapAffTank = new HashMap<Tank, JPanelTank>();
		HashMap<Tank, Integer> mapIParentTank = new HashMap<Tank, Integer>();

		//Construction de l'arbre
		int xPosChild = 0;
		int yPosChild = 0;
		int iParent = 0;

		while(!pileTank.isEmpty())
			{
			int i = pileTank.size() - 1;

			Tank tank = pileTank.get(i);
			pileTank.remove(i);

			if (tank != null)
				{

				pileTank.addAll(tank.getlistTankParent());
				panelTank = new JPanelTank(tank, false);

				int xPos = 0;
				int yPos = 0;

				if (xPosChild == 0 && yPosChild == 0)
					{
					//main
					System.out.println("---maintank---");
					xPos = xPosChild = this.getWidth() / 2 - (panelTank.getWidth() / 2);
					yPos = yPosChild = this.getHeight() - panelTank.getHeight();
					}
				else
					{
					System.out.println("---childtank---");
					//he is a child
					if (mapAffTank.keySet().contains(tank.getTankChild()))
						{
						JPanelTank panelTankChild = mapAffTank.get(tank.getTankChild());
						int xChild = panelTankChild.getX();
						int yChild = panelTankChild.getY();

						iParent = 0;
						if (mapIParentTank.keySet().contains(tank.getTankChild()))
							{
							iParent = mapIParentTank.get(tank.getTankChild());
							}

						int widthtotaltank = tank.getlistTankParent().size() * panelTank.getWidth();
						int decalage = widthtotaltank;

						if (tank.getlistTankParent().size() == 0)
							{
							decalage += panelTank.getWidth() / 2;
							}
						else
							{
							decalage -= panelTank.getWidth() / 2;
							}

						System.out.println("nb parent : " + tank.getlistTankParent().size() + " , total width : " + widthtotaltank);
						System.out.println("pos X parent : " + xChild);

						xPos = xChild + iParent * panelTank.getWidth() - decalage + 20;
						yPos = yChild - panelTank.getHeight() - 20;

						if (xPos > xChild)
							{
							panelTank = new JPanelTank(tank, true);
							}

						iParent++;
						}
					else
						{
						System.out.println("error");
						}
					}

				panelTank.setLocation(xPos, yPos);

				mapAffTank.put(tank, panelTank);
				mapIParentTank.put(tank.getTankChild(), iParent);

				System.out.println("Tank => pos : x: " + xPos + " , y: " + yPos);
				}
			}

		mainTank = null;

		//centrage de la simulation
		int minPosX = xPosChild;
		int maxPosX = xPosChild;
		int minPosY = yPosChild;
		int maxPosY = yPosChild;
		for(JPanelTank panelTank:mapAffTank.values())
			{
			if (panelTank.getX() + panelTank.getWidth() > maxPosX)
				{
				maxPosX = panelTank.getX() + panelTank.getWidth();
				}
			else if (panelTank.getX() < minPosX)
				{
				minPosX = panelTank.getX();
				}

			if (panelTank.getY() + panelTank.getHeight() > maxPosY)
				{
				maxPosY = panelTank.getY() + panelTank.getHeight();
				}
			else if (panelTank.getY() < minPosY)
				{
				minPosY = panelTank.getY();
				}
			}
		int centerSimulationX = minPosX + (maxPosX - minPosX) / 2;
		int centerWindowX = this.getWidth() / 2;
		int decalageX = (centerWindowX - centerSimulationX);

		int centerSimulationY = minPosY + (maxPosY - minPosY) / 2;
		int centerWindowY = this.getHeight() / 2;
		int decalageY = (centerWindowY - centerSimulationY);

		System.out.println("centre Fenetre "+ centerWindowX);
		System.out.println("min : "+ minPosX + "  max : " + maxPosX+ "  centre simulation :"+centerSimulationX+ " centre ecran:"+centerWindowX);
		System.out.println("-----declage :  " + decalageX);

		//affichage sur la scene
		for(JPanelTank panelTank:mapAffTank.values())
			{
			panelTank.setLocation(panelTank.getX() + decalageX + scrolX, panelTank.getY() + decalageY + scrolY);
			add(panelTank);
			listPanelTank.add(panelTank);
			}

		add(informationSimulation);

		return listPanelTank;
		}

	public void affTime(double time)
		{
		for(JPanelTank paneltank:listPanelTank)
			{
			paneltank.affTime(time);
			}
		repaint();
		this.t = time;
		}

	public void refresh()
		{
		removeAll();

		listPanelTank = constructPannelsTank();

		if(t!=null)
			{
			affTime(t);
			}

		repaint();
		updateUI();
		}

	public void resetTime()
		{
		this.t = null;
		}

	public void centerSimulation()
		{
		scrolX = scrolY = 0;
		refresh();
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

		addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseReleased(MouseEvent e)
					{
					startPosX = 0;
					startPosY = 0;
					getParent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}

				@Override
				public void mousePressed(MouseEvent e)
					{
					startPosX = e.getX();
					startPosY = e.getY();
					refresh();
					}
			});

		addMouseMotionListener(new MouseMotionListener()
			{
				@Override
				public void mouseDragged(MouseEvent e)
					{
					scrolX += e.getX() - startPosX;
					scrolY += e.getY() - startPosY;
					startPosX = e.getX();
					startPosY = e.getY();

					getParent().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
					refresh();
					}

				@Override
				public void mouseMoved(MouseEvent e)
					{
					//rien
					}
			});
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JPanelTank panelTank;
	private List<JPanelTank> listPanelTank;
	private JPanelInformationSimulation informationSimulation;

	private Double t;

	private int scrolX;
	private int scrolY;
	private int startPosX;
	private int startPosY;

	}
