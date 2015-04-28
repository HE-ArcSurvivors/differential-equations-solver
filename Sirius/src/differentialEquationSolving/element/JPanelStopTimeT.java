
package differentialEquationSolving.element;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import tools.DoubleDocumentFilter;

import differentialEquationSolving.JPanelStopCondition;

public class JPanelStopTimeT extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopTimeT(JPanelStopCondition jpanelstopcondition)
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
		stopTimeT = new JRadioButton("Arrêt temps t");
		stopTimeT.setSelected(true);
		stopTimeT.setPreferredSize(new Dimension(150, 20));


		paramTime = new JTextField("0");
		paramTime.setPreferredSize(new Dimension(50, 10));
        ((AbstractDocument)paramTime.getDocument()).setDocumentFilter(new DoubleDocumentFilter());

		labelCheck = new JLabel("");

		Box boxH = Box.createHorizontalBox();
		boxH.add(stopTimeT);
		boxH.add(paramTime);
		boxH.add(labelCheck);

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	private void control()
		{
		stopTimeT.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					jpanelstopcondition.setSelectedElement(JPanelStopCondition.TIME);
					}
			});
		}

	private void appearance()
		{
		}

	@Override
	public void setEnabled(boolean value)
		{
		paramTime.setEnabled(value);
		}

	public void setSelected(boolean value)
		{
		stopTimeT.setSelected(value);
		setEnabled(value);
		}

	public double getTime()
		{
		return Double.parseDouble(paramTime.getText());
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JRadioButton stopTimeT;
	private JTextField paramTime;
	private JLabel labelCheck;

	private JPanelStopCondition jpanelstopcondition;
	}
