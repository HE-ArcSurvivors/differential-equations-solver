package differentialEquationSolving;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;

public class JFrameStopCondition extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameStopCondition(int state)
		{
		this.state = state;
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
			this.jpanelstopcondition = new JPanelStopCondition(this);

			boxV = Box.createVerticalBox();
			boxH = Box.createHorizontalBox();

			boxH.add(Box.createHorizontalGlue());
			boxH.add(jpanelstopcondition);
			boxH.add(Box.createHorizontalGlue());

			boxV.add(Box.createVerticalGlue());
			boxV.add(boxH);
			boxV.add(Box.createVerticalGlue());

			setLayout(new BorderLayout());
			add(boxV,BorderLayout.CENTER);
		}

	private void control()
	{

	}

	private void appearance()
		{
			setSize(450, 200);
			setLocationRelativeTo(null); // frame centrer
			setVisible(true); // last!
			setResizable(false);
		}

	public double getTime()
		{
		double t = 0;
		switch(state)
			{
			case JPanelStopCondition.TIME:
				t = jpanelstopcondition.getTime();
				break;
			case JPanelStopCondition.QUANTITY:
				double q = jpanelstopcondition.getQuantity();
				t = SimulationSingleton.getInstance().getMainTank().timeQuantityQ(q);
				break;
			case JPanelStopCondition.OVERFLOW:
				t = SimulationSingleton.getInstance().getMainTank().timeOverflow();
				break;
			case JPanelStopCondition.EMPTY:
				t = SimulationSingleton.getInstance().getMainTank().timeEmpty();
				break;
			default:
				System.out.println("Default");
				t = 0;
				break;
			}
			return t;
		}

	public void setStateCondition(int state)
	{
		this.state = state;
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelStopCondition jpanelstopcondition;

	private Box boxV;
	private Box boxH;

	private int state;

	}
