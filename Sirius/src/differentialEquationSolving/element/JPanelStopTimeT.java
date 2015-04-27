package differentialEquationSolving.element;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class JPanelStopTimeT extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopTimeT()
		{
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
			paramTime = new JTextField();

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

			add(stopTimeT);
			add(paramTime);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	@Override
	public void setEnabled(boolean value)
	{
		paramTime.setEnabled(value);
	}

	public void setSelected(boolean value)
	{
		stopTimeT.setSelected(value);
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JRadioButton stopTimeT;
	private JTextField paramTime;

	}
