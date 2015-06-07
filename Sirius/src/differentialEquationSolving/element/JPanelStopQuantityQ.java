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

package differentialEquationSolving.element;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import substance.Substance;
import substance.SubstanceComboBox;
import tools.DoubleDocumentFilter;
import tools.MagasinImage;
import differentialEquationSolving.JPanelStopCondition;
import differentialEquationSolving.SimulationSingleton;

public class JPanelStopQuantityQ extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopQuantityQ(JPanelStopCondition jpanelstopcondition)
		{
		this.jpanelstopcondition = jpanelstopcondition;
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
		stopQuantityQ = new JRadioButton("Arrêt quantité Q");
		stopQuantityQ.setSelected(false);
		stopQuantityQ.setPreferredSize(new Dimension(150, 20));

		paramQuantity = new JTextField("0");
		paramQuantity.setEnabled(false);
		paramQuantity.setPreferredSize(new Dimension(30, 10));
		((AbstractDocument)paramQuantity.getDocument()).setDocumentFilter(new DoubleDocumentFilter());

		substancecombobox = new SubstanceComboBox(SimulationSingleton.getInstance().getSubstanceList());
		paramSubstance = new JComboBox(substancecombobox);
		paramSubstance.setEnabled(false);
		paramSubstance.setPreferredSize(new Dimension(100, 10));
		paramSubstance.setSelectedIndex(1);

		labelType = new JLabel("");
		setLabelType();
		labelWarning = new JLabel("");

		Box boxH = Box.createHorizontalBox();
		boxH.add(stopQuantityQ);
		boxH.add(paramSubstance);
		boxH.add(Box.createHorizontalStrut(10));
		boxH.add(paramQuantity);
		boxH.add(Box.createHorizontalStrut(5));
		boxH.add(labelType);
		boxH.add(labelWarning);

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

		paramSubstance.addItemListener(new ItemListener()
			{
				@Override
				public void itemStateChanged(ItemEvent e)
					{
					setLabelType();
					}
			});
		}

	public void setWarning(String string)
		{
		labelWarning.setIcon(MagasinImage.iconWarning);
		labelWarning.setToolTipText(string);
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

	private void setLabelType()
	{
		if (((Substance)paramSubstance.getSelectedItem()).getState() == Substance.LIQUID)
			{
			labelType.setText("l");
			}
		else if (((Substance)paramSubstance.getSelectedItem()).getState() == Substance.SOLID)
			{
			labelType.setText("g");
			}
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JRadioButton stopQuantityQ;
	private JTextField paramQuantity;
	private JComboBox paramSubstance;
	private SubstanceComboBox substancecombobox;
	private JLabel labelType;
	private JLabel labelWarning;
	private JPanelStopCondition jpanelstopcondition;

	}
