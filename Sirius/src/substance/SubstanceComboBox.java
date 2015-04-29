
package substance;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class SubstanceComboBox extends AbstractListModel<Substance> implements ComboBoxModel<Substance>
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
