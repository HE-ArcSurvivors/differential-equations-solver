
package differentialEquationSolving.element;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import differentialEquationSolving.JPanelStopCondition;

public class JPanelStopEmpty extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopEmpty(JPanelStopCondition jpanelstopcondition)
		{
		this.jpanelstopcondition = jpanelstopcondition;
		this.possible = true;
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
		stopEmpty = new JRadioButton("Arrêt vide");
		stopEmpty.setSelected(false);
		stopEmpty.setPreferredSize(new Dimension(200, 20));

		jlabelPossible = new JLabel("");

		Box boxH = Box.createHorizontalBox();
		boxH.add(stopEmpty);
		boxH.add(Box.createHorizontalGlue());
		boxH.add(jlabelPossible);

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	private void control()
		{
		stopEmpty.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					jpanelstopcondition.setSelectedElement(JPanelStopCondition.EMPTY);
					}
			});
		}

	private void appearance()
		{
		}

	public void setSelected(boolean value)
		{
		stopEmpty.setSelected(value);
		}

	public void setPossible(boolean value)
	{
		if(!value)
			{
			stopEmpty.setEnabled(false);
			jlabelPossible.setText("Cet événement n'arrivera pas");
			possible = value;
			}
	}

	public boolean isPossible()
	{
		return possible;
	}



	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JRadioButton stopEmpty;
	private JLabel jlabelPossible;

	private JPanelStopCondition jpanelstopcondition;

	private boolean possible;

	}
