package differentialEquationSolving;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;

import substance.Substance;
import tank.Tank;

public class JFrameStopCondition extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameStopCondition(Tank mainTank, List<Substance> listSubstance, int state)
		{
		this.jpanelstopcondition = new JPanelStopCondition(this, mainTank, listSubstance);
		this.mainTank = mainTank;
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
				t = Solve.timeQuantityQ(mainTank, q);
				break;
			case JPanelStopCondition.OVERFLOW:
				t = Solve.timeOverflow(mainTank);
				break;
			case JPanelStopCondition.EMPTY:
				t = Solve.timeEmpty(mainTank);
				break;
			default:
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

	private Tank mainTank;

	}
