
package layout.bottom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import layout.JPanelContent;
import tools.SwingUtil;
import differentialEquationSolving.JFrameStopCondition;
import differentialEquationSolving.JPanelStopCondition;
import differentialEquationSolving.SimulationSingleton;

public class JPanelBottomSimulationLine extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBottomSimulationLine(JPanelContent jpanelcontent)
		{
		this.jpanelcontent = jpanelcontent;
		isStarted = false;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		startSimulation = new JButton("Start");
		stopCondition = new JButton("Condition");

		slider = new JSlider(0, 0);
		slider.setVisible(false);
//		slider.setPaintTicks(true);
//        slider.setPaintLabels(true);

		formule = new JLabel("");

		jframestopcondition = new JFrameStopCondition(JPanelStopCondition.TIME);
		jframestopcondition.setVisible(false);

		add(startSimulation);
		add(stopCondition);
		add(slider);

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
						slider.setVisible(true);
						//start annimation
						startanimation();
						}
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
					DecimalFormat df = new DecimalFormat("0.00");
					formule.setText(" = " + df.format(SimulationSingleton.getInstance().getMainTank().equaDiff(t / 10, SimulationSingleton.getInstance().getSubstanceAt(0))) + " pour t = " + df.format(t / 10));
					System.out.println("T = " + t / 10);

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
					formule.setText("");
					}
			});
		}

	private void appearance()
		{
		// rien
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

			System.out.println("set value : "+value + ", get value :"+slider.getValue());
			return true;
			}
		}


	public void startanimation()
		{
		value = 0;
		isStarted = true;

		Thread thread = new Thread(new Runnable()
			{
				@Override
				public void run()
					{
					while(setValueSlider(value*10) && isStarted)
						{
						value ++;

						try
							{
							Thread.sleep(10);
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						}
					}
			});
		thread.run();



		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton startSimulation;
	private JButton stopCondition;
	private JSlider slider;

	private JLabel formule;

	private JFrameStopCondition jframestopcondition;
	private JPanelContent jpanelcontent;

	private boolean isStarted;
	private int value;

	}
