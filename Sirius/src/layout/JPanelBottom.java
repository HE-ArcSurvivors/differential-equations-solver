package layout;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import substance.Substance;
import tank.Tank;


public class JPanelBottom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBottom(JPanelContent panelContent)
		{
		this.panelContent = panelContent;

		Substance eau = new Substance("Eau",(float)5.0,Substance.LIQUID);
		sel = new Substance("Sel",(float)1.0,Substance.SOLID);

		this.r1 = new Tank(false, 1000, 2);
		this.r1.addSubstance(eau, 100);
		this.r1.addSubstance(sel,1);

		this.r2 = new Tank(false,500,2);
		this.r2.addSubstance(eau, 50);
		this.r2.addSubstance(sel,2);

		this.r3 = new Tank(false,500,1);
		this.r3.addSubstance(eau, 50);
		this.r3.addSubstance(sel,0);

		r2.addTankParent(r1);
//		r3.addTankParent(r2);

//		this.r1 = new Tank(false, 10000, 5);
//		this.r3 = new Tank(false, 1000, 5);
//
//		r1.addSubstance(eau,10000);
//		r3.addSubstance(eau, 100);
//		r1.addSubstance(sel,2);
//		r3.addSubstance(sel,4);
//
//		r3.addTankParent(r1);

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

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		}

	private void geometry()
		{
			// JComponent : Instanciation
			startSimulation = new JButton("OK");
			formule = new JLabel("");
			slider = new JSlider(SwingConstants.HORIZONTAL,0,10,0);
			slider.setVisible(false);

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.LEFT);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

			// JComponent : add
			add(startSimulation);
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
					mainReservoir = r2; //panelContent.getMainContainer();
					slider.setVisible(true);
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
					mainReservoir.printTank(t,sel);
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
	private JSlider slider;
	private JLabel	formule;

	private Tank mainReservoir;

	private JPanelContent panelContent;

//	TEST
	private Tank r1;
	private Tank r2;
	private Tank r3;
	private Substance sel;
	}
