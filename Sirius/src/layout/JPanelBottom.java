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

import tank.Tank;


public class JPanelBottom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBottom(JPanelContent panelContent)
		{
		this.panelContent = panelContent;

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
					mainReservoir = panelContent.getMainContainer();
					slider.setVisible(true);
					}
			});

		slider.addChangeListener(new ChangeListener()
			{
				@Override
				public void stateChanged(ChangeEvent e)
					{
					DecimalFormat df = new DecimalFormat("0.00");
					formule.setText(" = "+df.format(mainReservoir.equaDiff(slider.getValue()))+" pour t = "+slider.getValue());
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
	}
