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

package differentialEquationSolving;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import substance.Substance;
import differentialEquationSolving.element.JPanelStopEmpty;
import differentialEquationSolving.element.JPanelStopOverflow;
import differentialEquationSolving.element.JPanelStopQuantityQ;
import differentialEquationSolving.element.JPanelStopTimeT;

public class JPanelStopCondition extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopCondition(JFrameStopCondition jframestopcondition)
		{

		this.jframestopcondition = jframestopcondition;
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
		jpanelstopquantityq = new JPanelStopQuantityQ(this);
		jpanelstopoverflow = new JPanelStopOverflow(this);
		jpanelstopempty = new JPanelStopEmpty(this);

		if (!SimulationSingleton.getInstance().getMainTank().isOverflowPossible())
			{
			jpanelstopoverflow.setPossible(false);
			}

		if (!SimulationSingleton.getInstance().getMainTank().isEmptyPossible())
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
					jframestopcondition.setStateCondition(CANCEL);
					jframestopcondition.setVisible(false);
					}
			});

		buttonOk.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					if (currentState == QUANTITY)
						{
						if (!SimulationSingleton.getInstance().getMainTank().isQuantityPossible(jpanelstopquantityq.getQuantity(), jpanelstopquantityq.getSubstance()))
							{
							JOptionPane.showMessageDialog(null, "Oups, cette condition n'arrivera jamais!", "Condition inatteignable", JOptionPane.ERROR_MESSAGE);
							}
						else
							{
							System.out.println("SET VISIBLE");
							jframestopcondition.setStateCondition(currentState);
							jframestopcondition.setVisible(false);
							}
						}
					else
						{
						jframestopcondition.setStateCondition(currentState);
						jframestopcondition.setVisible(false);
						}
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
				if (jpanelstopoverflow.isPossible())
					{
					jpanelstoptimet.setSelected(false);
					jpanelstopquantityq.setSelected(false);
					jpanelstopoverflow.setSelected(true);
					jpanelstopempty.setSelected(false);
					}
				break;
			case EMPTY:
				System.out.println("StopEmpty");
				if (jpanelstopempty.isPossible())
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

	private JFrameStopCondition jframestopcondition;

	public static final int TIME = 0;
	public static final int QUANTITY = 1;
	public static final int OVERFLOW = 2;
	public static final int EMPTY = 3;
	public static final int CANCEL = 4;

	}
