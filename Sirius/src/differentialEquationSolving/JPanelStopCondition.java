
package differentialEquationSolving;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.element.JPanelStopEmpty;
import differentialEquationSolving.element.JPanelStopOverflow;
import differentialEquationSolving.element.JPanelStopQuantityQ;
import differentialEquationSolving.element.JPanelStopTimeT;

public class JPanelStopCondition extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopCondition(JFrameStopCondition jframestopcondition, Tank mainTank, List<Substance> listSubstance)
		{
		jpanelstopquantityq = new JPanelStopQuantityQ(this, listSubstance);

		this.jframestopcondition = jframestopcondition;
		this.mainTank = mainTank;
		this.currentState = 0;

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
		buttonOk = new JButton("OK");
		buttonCancel = new JButton("Annuler");

		jpanelstoptimet = new JPanelStopTimeT(this);
		jpanelstopoverflow = new JPanelStopOverflow(this);
		jpanelstopempty = new JPanelStopEmpty(this);

		if(!this.mainTank.isOverflowPossible())
		{
			jpanelstopoverflow.setPossible(false);
		}

		if(!this.mainTank.isEmptyPossible())
		{
			jpanelstopempty.setPossible(false);
		}

		Box boxV = Box.createVerticalBox();
		boxV.add(jpanelstoptimet);
		boxV.add(jpanelstopquantityq);
		boxV.add(jpanelstopoverflow);
		boxV.add(jpanelstopempty);

		Box boxH = Box.createHorizontalBox();
		boxH.add(buttonOk);
		boxH.add(buttonCancel);

		boxV.add(boxH);

		setLayout(new FlowLayout());
		add(boxV);
		}

	private void control()
		{
		buttonCancel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					jframestopcondition.setVisible(false);
					}
			});

		buttonOk.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					jframestopcondition.setStateCondition(currentState);
					jframestopcondition.setVisible(false);
					}
			});
		}

	private void appearance()
		{
		// rien
		}

	public void setSelectedElement(int element)
		{
		currentState = element;
		switch(element)
			{
			case TIME:
				System.out.println("StopTimeT");
				jpanelstoptimet.setSelected(true);
				jpanelstopquantityq.setSelected(false);
				jpanelstopoverflow.setSelected(false);
				jpanelstopempty.setSelected(false);
				break;
			case QUANTITY:
				System.out.println("StopQuantityQ");
				jpanelstoptimet.setSelected(false);
				jpanelstopquantityq.setSelected(true);
				jpanelstopoverflow.setSelected(false);
				jpanelstopempty.setSelected(false);
				break;
			case OVERFLOW:
				System.out.println("StopOverflow");
				if(jpanelstopoverflow.isPossible())
				{
					jpanelstoptimet.setSelected(false);
					jpanelstopquantityq.setSelected(false);
					jpanelstopoverflow.setSelected(true);
					jpanelstopempty.setSelected(false);
				}
				break;
			case EMPTY:
				System.out.println("StopEmpty");
				if(jpanelstopempty.isPossible())
				{
					jpanelstoptimet.setSelected(false);
					jpanelstopquantityq.setSelected(false);
					jpanelstopoverflow.setSelected(false);
					jpanelstopempty.setSelected(true);
				}
				break;
			default:
				currentState = 0;
				break;
			}
		}

		public double getTime()
		{
			return jpanelstoptimet.getTime();
		}

		public double getQuantity()
		{
			return jpanelstopquantityq.getQuantity();
		}

		public Substance getSubstance()
		{
			return jpanelstopquantityq.getSubstance();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelStopTimeT jpanelstoptimet;
	private JPanelStopQuantityQ jpanelstopquantityq;
	private JPanelStopOverflow jpanelstopoverflow;
	private JPanelStopEmpty jpanelstopempty;

	private JButton buttonOk;
	private JButton buttonCancel;

	private int currentState;
	private Tank mainTank;

	private JFrameStopCondition jframestopcondition;

	public static final int TIME = 0;
	public static final int QUANTITY = 1;
	public static final int OVERFLOW = 2;
	public static final int EMPTY = 3;

	}
