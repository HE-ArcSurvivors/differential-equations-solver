
package layout;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

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
		startSimulation = new JButton(MagasinImage.iconPlay);
		stopSimulation = new JButton(MagasinImage.iconStop);
		stopSimulation.setVisible(false);

		stopCondition = new JButton(MagasinImage.iconSettings);

		slider = new JSliderSimulation(0, 0);
		slider.setVisible(false);

		sliderAnimation = new SimulationAnimation(slider);

		formule = new JLabelFormula("");
		result = new JLabelFormula("");

		jframestopcondition = new JFrameStopCondition(JPanelStopCondition.TIME);
		jframestopcondition.setVisible(false);

		FlowLayout flowlayout = new FlowLayout(FlowLayout.LEFT);
		setLayout(flowlayout);

		add(startSimulation);
		add(stopSimulation);
		add(stopCondition);
		add(slider);
		add(formule);
		add(result);
		add(sliderAnimation);

		}

	private void control()
		{
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

		stopCondition.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					stopAnimation();
					jframestopcondition.setVisible(true);
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
		formule.setFormula("");
		result.setFormula("");
		}

	private void appearance()
		{

		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		startSimulation.setEnabled(SimulationSingleton.getInstance().isActive());
		stopCondition.setEnabled(SimulationSingleton.getInstance().isActive());
		}

	private void startAnimation()
		{
		SimulationSingleton.getInstance().setStarted(true);
		startSimulation.setVisible(false);
		stopSimulation.setVisible(true);
		slider.setVisible(true);
		sliderAnimation.startAnimation();
		}

	private void stopAnimation()
		{
		SimulationSingleton.getInstance().setStarted(false);
		sliderAnimation.stopAnimation();
		startSimulation.setVisible(true);
		stopSimulation.setVisible(false);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton startSimulation;
	private JButton stopCondition;
	private JButton stopSimulation;
	private JSliderSimulation slider;

	private JLabelFormula formule;
	private JLabelFormula result;

	private JFrameStopCondition jframestopcondition;
	private JPanelContent jpanelcontent;

	private SimulationAnimation sliderAnimation;

	}
