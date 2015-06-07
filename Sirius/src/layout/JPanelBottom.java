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

package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import layout.atom.JSliderSimulation;
import tools.MagasinImage;
import differentialEquationSolving.JFrameStopCondition;
import differentialEquationSolving.JLabelFormula;
import differentialEquationSolving.JPanelStopCondition;
import differentialEquationSolving.SimulationAnimation;
import differentialEquationSolving.SimulationSingleton;

public class JPanelBottom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBottom(JPanelContent jpanelcontent)
		{
		this.jpanelcontent = jpanelcontent;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{

		addMainTankToSimulation = new JButton("Ajouter un tank principal");
		addMainTankToSimulation.setVisible(false);
		centerSimulation = new JButton("Centrer la simulation");

		startSimulation = new JButton(MagasinImage.iconPlay);

		stopSimulation = new JButton(MagasinImage.iconStop);
		stopSimulation.setVisible(false);

		pauseSimulation = new JButton(MagasinImage.iconPause);
		pauseSimulation.setVisible(false);

		replaySimulation = new JButton(MagasinImage.iconBackward);
		replaySimulation.setVisible(false);

		stopCondition = new JButton(MagasinImage.iconSettings);

		slider = new JSliderSimulation(0, 0);
		slider.setVisible(false);

		sliderAnimation = new SimulationAnimation(slider);

		formule = new JLabelFormula("");
		result = new JLabelFormula("");

		jframestopcondition = new JFrameStopCondition(JPanelStopCondition.TIME);
		jframestopcondition.setVisible(false);

		setLayout(new BorderLayout());

		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalStrut(2));
		boxH.add(startSimulation);
		boxH.add(pauseSimulation);
		boxH.add(replaySimulation);
		boxH.add(Box.createHorizontalStrut(10));
		boxH.add(stopSimulation);
		boxH.add(stopCondition);
		boxH.add(Box.createHorizontalStrut(20));
		boxH.add(slider);
		boxH.add(Box.createHorizontalStrut(20));
		boxH.add(formule);
		boxH.add(result);

		boxH.add(sliderAnimation);
		boxH.add(Box.createHorizontalGlue());
		boxH.add(centerSimulation);
		boxH.add(addMainTankToSimulation);

		add(boxH);
		}

	private void control()
		{

//		actionPerformed(ActionEvent e) {
//			if (SimulationSingleton.getInstance().getMainTank()==null)
//			{
//				System.out.println("no main tank !");
//			}
//	    }



		centerSimulation.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
				{
				jpanelcontent.centerSimulation();
				}
		});

		addMainTankToSimulation.addActionListener(new ActionListener()
		{
		@Override
		public void actionPerformed(ActionEvent e)
			{
			jpanelcontent.centerSimulation();
			}
	});

		startSimulation.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					if (slider.getMaximum() == 0)
						{
						jframestopcondition.setVisible(true);
						}
					else
						{
						startAnimation();
						}
					}
			});

		stopSimulation.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					stopAnimation();
					}
			});

		pauseSimulation.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					pauseAnimation();
					}
			});

		stopCondition.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					stopAnimation();
					jframestopcondition.setVisible(true);
					}
			});

		replaySimulation.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					restartAnimation();
					}
			});

		slider.addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent e)
					{
					double t = slider.getValue();
					formule.setFormula(SimulationSingleton.getInstance().getMainTank().getFormula(t / 10));

					DecimalFormat df = new DecimalFormat("0.00");
					result.setFormula("\\text{=" + df.format(SimulationSingleton.getInstance().getMainTank().equaDiff(t / 10, SimulationSingleton.getInstance().getSubstanceAt(1))) + "}");

					jpanelcontent.affTime(t / 10);

					if (slider.getValue() == slider.getMaximum())
						{
						replaySimulation.setVisible(true);
						pauseSimulation.setVisible(false);
						}
					}
			});

		jframestopcondition.addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentHidden(ComponentEvent e)
					{
					resetSimulation();
					slider.setMaximum((int)(jframestopcondition.getTime() * 10));
					if (slider.getMaximum() != 0)
						{
						startAnimation();
						}
					}
			});
		}

	private void resetSimulation()
		{
		slider.setValue(0);
		formule.setVisible(false);
		result.setVisible(false);
		jpanelcontent.resetTime();
		}

	private void appearance()
		{
		setMinimumSize(new Dimension(getWidth(),50));
		setPreferredSize(new Dimension(getWidth(),50));
		setMaximumSize(new Dimension(getWidth(),50));
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		startSimulation.setEnabled(SimulationSingleton.getInstance().isActive());
		stopCondition.setEnabled(SimulationSingleton.getInstance().isActive());
		}

	public void resizeEvent()
		{
		if (SimulationSingleton.getInstance().isStarted())
			{
			pauseAnimation();
			}
		}

	private void startAnimation()
		{
		formule.setVisible(true);
		result.setVisible(true);

		SimulationSingleton.getInstance().setStarted(true);

		startSimulation.setVisible(false);
		stopCondition.setVisible(false);
		stopSimulation.setVisible(true);
		pauseSimulation.setVisible(true);
		replaySimulation.setVisible(false);

		slider.setVisible(true);
		sliderAnimation.startAnimation();
		}

	private void pauseAnimation()
		{
		sliderAnimation.stopAnimation();

		startSimulation.setVisible(true);
		pauseSimulation.setVisible(false);
		replaySimulation.setVisible(false);
		stopCondition.setVisible(false);
		stopSimulation.setVisible(true);
		}

	private void stopAnimation()
		{
		SimulationSingleton.getInstance().setStarted(false);
		sliderAnimation.stopAnimation();

		startSimulation.setVisible(true);
		stopCondition.setVisible(true);
		replaySimulation.setVisible(false);
		stopSimulation.setVisible(false);
		pauseSimulation.setVisible(false);

		slider.setVisible(false);
		resetSimulation();
		}

	private void restartAnimation()
		{
		stopAnimation();
		startAnimation();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton startSimulation;
	private JButton stopCondition;
	private JButton stopSimulation;
	private JButton pauseSimulation;
	private JButton replaySimulation;

	private JButton centerSimulation;
	private JButton addMainTankToSimulation;

	private JSliderSimulation slider;

	private JLabelFormula formule;
	private JLabelFormula result;

	private JFrameStopCondition jframestopcondition;
	private JPanelContent jpanelcontent;

	private SimulationAnimation sliderAnimation;

	}
