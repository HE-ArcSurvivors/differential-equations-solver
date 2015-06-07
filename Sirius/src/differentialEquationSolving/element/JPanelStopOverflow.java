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

public class JPanelStopOverflow extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopOverflow(JPanelStopCondition jpanelstopcondition)
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
		stopOverflow = new JRadioButton("Arrêt débordement");
		stopOverflow.setSelected(false);
		stopOverflow.setPreferredSize(new Dimension(200, 20));

		jlabelPossible = new JLabel("");

		Box boxH = Box.createHorizontalBox();
		boxH.add(stopOverflow);
		boxH.add(Box.createHorizontalGlue());
		boxH.add(jlabelPossible);

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	private void control()
		{
		stopOverflow.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					jpanelstopcondition.setSelectedElement(JPanelStopCondition.OVERFLOW);
					}
			});
		}

	private void appearance()
		{
		}

	public void setSelected(boolean value)
		{
			stopOverflow.setSelected(value);
		}

	public void setPossible(boolean value)
	{
		if(!value)
			{
			stopOverflow.setEnabled(false);
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
	private JRadioButton stopOverflow;
	private JPanelStopCondition jpanelstopcondition;
	private JLabel jlabelPossible;

	private boolean possible;

	}
