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

package substance;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class SubstanceComboBox extends AbstractListModel implements ComboBoxModel
	{

	public SubstanceComboBox(List<Substance> substanceList)
	{
		this.selection = null;
		this.substanceTab = new Substance[substanceList.size()];

		for(int i = 0; i < substanceList.size(); i++)
		{
			substanceTab[i] = substanceList.get(i);
		}
	}

	@Override
	public Substance getElementAt(int index)
		{
		return substanceTab[index];
		}

	@Override
	public int getSize()
		{
		return substanceTab.length;
		}

	@Override
	public void setSelectedItem(Object substance)
		{
		selection = ((Substance)substance).getName();
		}

	@Override
	public Substance getSelectedItem()
		{
		return getSelectedSubstance(selection); // to add the selection to the combo box
		}

	public Substance getSelectedSubstance(String name)
	{
		for(int i = 0; i < substanceTab.length; i++)
		{
			if(substanceTab[i].getName() == name)
				{
				return substanceTab[i];
				}
		}
		return null;
	}

	private Substance[] substanceTab;
	private String selection;

	}
