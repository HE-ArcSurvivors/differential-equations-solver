
package substance;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class SubstanceComboBox extends AbstractListModel<Object> implements ComboBoxModel<Object>
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
	public Object getElementAt(int index)
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
	public Object getSelectedItem()
		{
		return selection; // to add the selection to the combo box
		}

	private Substance[] substanceTab;
	private String selection;

	}
