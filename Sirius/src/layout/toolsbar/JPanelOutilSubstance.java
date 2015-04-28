package layout.toolsbar;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import substance.Substance;

public class JPanelOutilSubstance extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelOutilSubstance(Substance substance)
		{
		this.substance = substance;

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
			substance_name = new JLabel(substance.getName());
			substance_name.setForeground(Color.BLUE);

			buttonEdit = new JButton("Editer");
			// Layout
			Box boxH = Box.createHorizontalBox();
			boxH.add(substance_name);
			boxH.add(buttonEdit);

			setLayout(new FlowLayout());

			// JComponent : add
			add(boxH);
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
	private Substance substance;

	private JLabel substance_name;

	private JButton buttonEdit;
	}
