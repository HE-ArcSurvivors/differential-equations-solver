
package layout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tools.MagasinImage;
import tools.SwingUtil;
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

		slider = new JSlider(0, 0);
		slider.setVisible(false);
		//		slider.setPaintTicks(true);
		//      slider.setPaintLabels(true);

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
						startSimulation.setVisible(false);
						stopSimulation.setVisible(true);

						slider.setVisible(true);
						sliderAnimation.startAnimation();
						}
					}
			});

		stopSimulation.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					SimulationSingleton.getInstance().setStarted(false);
					startSimulation.setVisible(true);
					stopSimulation.setVisible(false);
					sliderAnimation.stopAnimation();
					}
			});

		stopCondition.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
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
					slider.setMaximum((int)(jframestopcondition.getTime() * 10));
					slider.setVisible(slider.getMaximum() != 0);
					formule.setFormula("");
					result.setFormula("");
					if (slider.getMaximum() != 0)
						{
						sliderAnimation.startAnimation();
						startSimulation.setVisible(false);
						stopSimulation.setVisible(true);
						}
					}
			});
		}

	private void appearance()
		{

		}

	public boolean setValueSlider(int value)
		{
		if (value > slider.getMaximum())
			{
			return false;
			}
		else
			{
			slider.setValue(value);

			updateUI();
			repaint();
			validate();
			revalidate();
			SwingUtil.repaintAllParent(this);

			return true;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton startSimulation;
	private JButton stopCondition;
	private JButton stopSimulation;
	private JSlider slider;

	private JLabelFormula formule;
	private JLabelFormula result;

	private JFrameStopCondition jframestopcondition;
	private JPanelContent jpanelcontent;

	private SimulationAnimation sliderAnimation;

	}
