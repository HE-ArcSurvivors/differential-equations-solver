
package layout;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.JFrameStopCondition;
import differentialEquationSolving.JPanelStopCondition;

public class JPanelBottom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBottom(JPanelContent panelContent)
		{

		this.listSubstance = new ArrayList<Substance>();
		eau = new Substance("Eau", (float)5.0, Substance.LIQUID);
		sel = new Substance("Sel", (float)1.0, Substance.SOLID);
		listSubstance.add(eau);
		listSubstance.add(sel);

		this.panelContent = panelContent;

		this.r1 = new Tank(false, 500, 5);
		this.r2 = new Tank(false, 200, 5);
		this.r1.addSubstance(sel, 2);
		this.r1.addSubstance(eau, 500);

		this.r2.addSubstance(sel, 4);
		this.r2.addSubstance(eau, 100);
		r2.addTankParent(r1);
		mainReservoir = r2;		geometry();
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

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		}

	private void geometry()
		{
		startSimulation = new JButton("Start");
		stopCondition = new JButton("Condition");

		slider = new JSlider(0,0);
		formule = new JLabel("");

		jframestopcondition = new JFrameStopCondition(mainReservoir, listSubstance, JPanelStopCondition.TIME);
		jframestopcondition.setVisible(false);

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.LEFT);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		add(startSimulation);
		add(stopCondition);
		add(slider);
		add(formule);

		}

	private void control()
		{
		startSimulation.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					slider.setVisible(true);
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
					formule.setText(" = "+df.format(mainReservoir.equaDiff(t,sel))+" pour t = "+t);
					System.out.println("T = "+t);
//					mainReservoir.printTank(t,sel);

					//panelContent.affTime(t);
					}
			});

		jframestopcondition.addComponentListener(new ComponentAdapter()
			{
			@Override
			public void componentHidden(ComponentEvent e)
				{
				    slider.setMaximum((int)jframestopcondition.getTime());
				}
			});
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton startSimulation;
	private JButton stopCondition;
	private JSlider slider;

	private JLabel formule;

	private Tank mainReservoir;

	private JPanelContent panelContent;
	private JFrameStopCondition jframestopcondition;

	//	TEST
	private Tank r1, r2;
	private Substance eau, sel;
	private List<Substance> listSubstance;
	}
