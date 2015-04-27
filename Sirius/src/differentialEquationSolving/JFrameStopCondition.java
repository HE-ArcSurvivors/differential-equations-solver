package differentialEquationSolving;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;

public class JFrameStopCondition extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameStopCondition()
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
			jpanelstopcondition = new JPanelStopCondition();

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
			setSize(700, 400);
			setLocationRelativeTo(null); // frame centrer
			setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelStopCondition jpanelstopcondition;

	private Box boxV;
	private Box boxH;

	}
