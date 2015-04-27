
package differentialEquationSolving.element;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import substance.Substance;
import substance.SubstanceComboBox;
import differentialEquationSolving.JPanelStopCondition;

public class JPanelStopQuantityQ extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopQuantityQ(JPanelStopCondition jpanelstopcondition, List<Substance> listSubstance)
		{
		this.jpanelstopcondition = jpanelstopcondition;
		this.listSubstance = listSubstance;
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
		stopQuantityQ = new JRadioButton("Arrêt quantité t");
		stopQuantityQ.setSelected(false);
		stopQuantityQ.setPreferredSize(new Dimension(150, 20));

		paramQuantity = new JTextField();
		paramQuantity.setEnabled(false);
		paramQuantity.setPreferredSize(new Dimension(30, 10));

		SubstanceComboBox substancecombobox = new SubstanceComboBox(listSubstance);
		paramSubstance = new JComboBox(substancecombobox);
		paramSubstance.setEnabled(false);
		paramSubstance.setPreferredSize(new Dimension(100, 10));

		labelCheck = new JLabel("");
		labelType = new JLabel("");

		Box boxH = Box.createHorizontalBox();
		boxH.add(stopQuantityQ);
		boxH.add(paramSubstance);
		boxH.add(Box.createHorizontalStrut(10));
		boxH.add(paramQuantity);
		//		boxH.add(labelType);
		//		boxH.add(labelCheck);

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	private void control()
		{
		stopQuantityQ.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					jpanelstopcondition.setSelectedElement(JPanelStopCondition.QUANTITY);
					}
			});

		paramSubstance.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{

					}
			});
		}

	private void appearance()
		{
		}

	@Override
	public void setEnabled(boolean value)
		{
		paramQuantity.setEnabled(value);
		paramSubstance.setEnabled(value);
		}

	public void setSelected(boolean value)
		{
		stopQuantityQ.setSelected(value);
		setEnabled(value);
		}

	public Substance getSubstance()
		{
		return (Substance)paramSubstance.getSelectedItem();
		}

	public double getQuantity()
		{
		return Double.parseDouble(paramQuantity.getText());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JRadioButton stopQuantityQ;
	private JTextField paramQuantity;
	private JComboBox paramSubstance;
	private JLabel labelCheck;
	private JLabel labelType;

	private JPanelStopCondition jpanelstopcondition;
	private List<Substance> listSubstance;

	}
