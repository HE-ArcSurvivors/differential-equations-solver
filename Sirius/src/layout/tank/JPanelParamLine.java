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

package layout.tank;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import tools.DoubleDocumentFilter;
import tools.MagasinImage;

public class JPanelParamLine extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Co-nstructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelParamLine(String labelString, double defaultValue)
		{
		label = new JLabel(labelString);
		label.setMaximumSize(new Dimension(70, 15));
		label.setMinimumSize(new Dimension(70, 15));
		label.setPreferredSize(new Dimension(70, 15));

		labelWarning = new JLabel("");

		textfield = new JTextField("" + defaultValue);
		textfield.setMaximumSize(new Dimension(50, 20));
		textfield.setMinimumSize(new Dimension(50, 20));
		textfield.setPreferredSize(new Dimension(50, 20));

		((AbstractDocument)((JTextComponent)textfield).getDocument()).setDocumentFilter(new DoubleDocumentFilter());

		Box boxH = Box.createHorizontalBox();
		boxH.add(label);
		boxH.add(textfield);
		boxH.add(labelWarning);

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public double getValue()
		{
		if (!textfield.getText().equals(""))
			{
			return Double.parseDouble(textfield.getText());
			}
		else
			{
			textfield.setText(0.0+"");
			return 0.0;
			}
		}

	public void setValue(Double value)
		{
		textfield.setText("" + value);
		}

	public void setLabel(String string)
	{
		label.setText(string);
	}

	public void setWarning(String string)
		{
		labelWarning.setIcon(MagasinImage.iconWarning);
		labelWarning.setToolTipText(string);
		}

	public void setIcon(ImageIcon img)
	{
		labelWarning.setIcon(img);
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

	// Tools
	private JLabel label;
	private JTextField textfield;
	private JLabel labelWarning;

	}
