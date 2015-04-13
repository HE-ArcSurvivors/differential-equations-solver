package margauxV2.layout;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class JPanelOngletSimulation extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelOngletSimulation()
		{
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
			jpaneloutil = new JPanelOutils();
			jpanelcontent = new JPanelContent();
			jpanelbottom = new JPanelBottom(jpanelcontent);

			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			// borderLayout.setHgap(20);
			// borderLayout.setVgap(20);
			}

			// JComponent : add
			add(jpanelcontent,BorderLayout.CENTER);
			//add(jpaneloutil,BorderLayout.WEST);
			add(jpanelbottom,BorderLayout.SOUTH);
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
	private JPanelOutils 	jpaneloutil;
	private JPanelContent 	jpanelcontent;
	private JPanelBottom	jpanelbottom;

	}
