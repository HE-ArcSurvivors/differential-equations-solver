package layout;

import javax.swing.JPanel;

import layout.tank.JPanelTank;

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

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

//	public Tank getMainContainer()
//	{
//		Tank mainContainer = panelParametresContainer.getConteneur();
//		mainContainer.addTankParent(panelParametresSource.getConteneur());
//		return mainContainer;
//	}

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
			JPanelTank tank1 = new JPanelTank();

			// Layout : Specification
			setLayout(null);
			add(tank1);

			tank1.setLocation(10, 150); //pos fixe
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

	}
