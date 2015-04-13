package layout;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tank.JPanelParametres;
import tank.Tank;

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

	public Tank getMainContainer()
	{
		Tank mainContainer = panelParametresContainer.getConteneur();
		mainContainer.addReservoirParent(panelParametresSource.getConteneur());
		return mainContainer;
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
			panelParametresSource = new JPanelParametres();
			panelParametresContainer = new JPanelParametres();

			// Layout : Specification
			{
			// Box contains a BoxLayout.
			Box boxV = Box.createVerticalBox();
			//boxV.add(Box.createVerticalStrut(10));

			boxV.add(new JLabel("Source"));
			boxV.add(panelParametresSource);

			boxV.add(Box.createVerticalGlue());

			boxV.add(new JLabel("Container"));
			boxV.add(panelParametresContainer);

			setLayout(new BorderLayout());
			add(boxV,BorderLayout.CENTER);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

			// JComponent : add

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
	private JPanelParametres panelParametresSource;
	private JPanelParametres panelParametresContainer;

	}
