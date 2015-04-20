
package layout.tank;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class JPanelTank extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTank()
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

		jpanelgraduation = new JPanelGraduation();
		jpanelSubstances = new JPanelSubstances();
		jpanelContentTank = new JPannelContentTank();
		jpanelTap = new JPannelTap();

			// Layout : Specification
			{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(jpanelgraduation);
			add(jpanelSubstances);
			add(jpanelContentTank);
			add(jpanelTap);
			}

		// JComponent : add

		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		setBackground(Color.blue);
		setSize(300, 300);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelGraduation jpanelgraduation;
	private JPanelSubstances jpanelSubstances;
	private JPannelContentTank jpanelContentTank;
	private JPannelTap jpanelTap;

	}
