
package layout.atom;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;



public class JSliderSimulation extends JSlider
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JSliderSimulation(int min, int max)
	{
		super(min, max);
		setPaintTicks(true);
		setPaintLabels(true);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void setMaximum(int max)
	{
		super.setMaximum(max);
		setMajorTickSpacing(max/ 10);
		setMinorTickSpacing(max);

		if(max != 0)
		{
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
			for(int i = 0; i <= max; i+=max/10)
				{
					table.put(i, new JLabel(i/10+""));
				}
			setLabelTable(table);
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}

