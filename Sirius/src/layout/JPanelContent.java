package layout;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import layout.tank.JPanelTank;
import substance.Substance;
import tank.Tank;

public class JPanelContent extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelContent()
		{
		listPanelTank = new LinkedList<JPanelTank>();
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

//	public Tank getMainContainer()
//	{
//		Tank mainContainer = panelParametresContainer.getConteneur();
//		mainContainer.addTankParent(panelParametresSource.getConteneur());
//		return mainContainer;
//	}

	public void affTime(double t)
	{
		for(JPanelTank paneltank:listPanelTank)
			{
			paneltank.affTime(t);
			}
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
			// JComponent : Instanciation

			Substance eau = new Substance("Eau",(float)0.6,Substance.LIQUID);
			Substance sel = new Substance("Sel",(float)1.0,Substance.SOLID);

			Tank r1 = new Tank(false, 150, 2);
			r1.addSubstance(eau, 100);
			r1.addSubstance(sel,1);

			Tank r2 = new Tank(false, 500, 2);
			r2.addSubstance(eau, 50);
			r2.addSubstance(sel, 2);


			r2.addTankParent(r1);

			JPanelTank panelTank1 = new JPanelTank(r1);
			JPanelTank panelTank2 = new JPanelTank(r2);

			// Layout : Specification
			setLayout(null);
			add(panelTank1);
			add(panelTank2);


			listPanelTank.add(panelTank1);
			listPanelTank.add(panelTank2);


			panelTank1.setLocation(10, 10);
			panelTank2.setLocation(200, 250);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private List<JPanelTank> listPanelTank;

	}
