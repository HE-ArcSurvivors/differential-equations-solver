package layout.tank;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import tools.DoubleDocumentFilter;

public class JPanelParamLine extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Co-nstructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelParamLine(String labelString, double defaultValue)
		{
		label = new JLabel(labelString);
		label.setMaximumSize(new Dimension(70,15));
		label.setMinimumSize(new Dimension(70,15));
		label.setPreferredSize(new Dimension(70,15));

		textfield = new JTextField("" + defaultValue);
		textfield.setMaximumSize(new Dimension(50,20));
		textfield.setMinimumSize(new Dimension(50,20));
		textfield.setPreferredSize(new Dimension(50,20));
		((AbstractDocument)((JTextComponent)textfield).getDocument()).setDocumentFilter(new DoubleDocumentFilter());

		Box boxH = Box.createHorizontalBox();
		boxH.add(label);
		boxH.add(textfield);

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public double getValue()
	{
		return Double.parseDouble(textfield.getText());
	}

	public void setValue(Double value)
	{
		textfield.setText(""+value);
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

	}
