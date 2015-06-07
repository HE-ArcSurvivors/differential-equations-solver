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

		if(max != 0)
		{
			Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();

			setMajorTickSpacing(max / 5);
			setMinorTickSpacing(max / 20);

			for(int i = 0; i <= max; i+=max / 5)
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

