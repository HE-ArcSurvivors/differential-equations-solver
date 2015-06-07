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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;



public class SimulationAnimation extends JPanel implements ActionListener
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public SimulationAnimation(JSlider slider)
	{
	this.slider = slider;

	timer = new Timer(50, this);
	timer.setInitialDelay(1000);
	timer.setDelay(500);
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void actionPerformed(ActionEvent e)
		{
		this.step();
		}

	public void startAnimation()
		{
		int delay = slider.getMaximum() - slider.getMinimum();
		timer.setDelay((int)1.5 * delay);
		timer.start();
		}

	public void stopAnimation()
	{
		timer.stop();
	}

	public void step()
		{
		if (slider.getValue() == slider.getMaximum())
			{
			timer.stop();
			}
		else
			{
			slider.setValue(slider.getValue() + 1);
			}
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

	private Timer timer;
	private JSlider slider;
	}

