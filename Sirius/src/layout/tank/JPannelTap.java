package layout.tank;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class JPannelTap extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPannelTap()
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

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

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
		setBackground(Color.black);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools


	}
