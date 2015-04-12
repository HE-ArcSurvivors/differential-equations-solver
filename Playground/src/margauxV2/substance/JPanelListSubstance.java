package margauxV2.substance;

import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JPanel;

public class JPanelListSubstance extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelListSubstance()
		{

		listSubstance = new LinkedList<JPanelElementSubstance>();

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
			listSubstance.add(new JPanelElementSubstance("Eau", 0.7));
			listSubstance.add(new JPanelElementSubstance("Sel", 0.9));

			// Layout : Specification
			Box boxV = Box.createVerticalBox();
			boxV.add(Box.createVerticalStrut(5));

			// JComponent : add
			for(JPanelElementSubstance element : listSubstance)
			{
				boxV.add(element);
			}

			add(boxV);

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
	private List<JPanelElementSubstance> listSubstance;

	}
