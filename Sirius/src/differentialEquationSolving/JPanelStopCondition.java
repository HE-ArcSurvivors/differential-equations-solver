
package differentialEquationSolving;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import differentialEquationSolving.element.JPanelStopTimeT;

public class JPanelStopCondition extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStopCondition()
		{
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

		jpanelstoptimet = new JPanelStopTimeT();

		stopQuantityQ = new JRadioButton("Arrêt quantité t");
		stopOverflow = new JRadioButton("Arrêt débordement");
		stopEmpty = new JRadioButton("Arrêt vide");

		paramQuantity = new JTextField();
		paramQuantity.setEnabled(false);

		String comboBoxItems[] = { "Eau", "Sel" };
		paramSubstance = new JComboBox<String>(comboBoxItems);
		paramSubstance.setEnabled(false);

		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		setLayout(layout);

//		ButtonGroup group = new ButtonGroup();
//		group.add(stopTimeT);
//		group.add(stopQuantityQ);
//		group.add(stopOverflow);
//		group.add(stopEmpty);

//		FormLayout layout = new FormLayout("100dlu, 110dlu, 10dlu, 110dlu", // columns
//				"pref, pref, pref, pref, pref, pref"); // rows
//
//		setLayout(layout);

		// JComponent : add

//		add(stopQuantityQ, CC.xy(1, 2));
//		add(paramSubstance, CC.xy(2, 2));
//		add(paramQuantity, CC.xy(4, 2));
//
//		add(stopOverflow, CC.xy(1, 3));
//		add(stopEmpty, CC.xy(1, 4));
//
//		add(buttonOk, CC.xy(2, 5));
//		add(buttonCancel, CC.xy(4, 5));
		}

	private void control()
		{
		jpanelstoptimet.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
					{
					System.out.println("StopTimeT");
					jpanelstoptimet.setEnabled(true);
					jpanelstoptimet.setSelected(true);

					paramQuantity.setEnabled(false);
					paramSubstance.setEnabled(false);
					}
			});

		stopQuantityQ.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					System.out.println("StopQuantityQ");
					jpanelstoptimet.setEnabled(false);
					paramQuantity.setEnabled(true);
					paramSubstance.setEnabled(true);
					}
			});

		stopOverflow.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					System.out.println("StopOverflow");
					jpanelstoptimet.setEnabled(false);
					paramQuantity.setEnabled(false);
					paramSubstance.setEnabled(false);
					}
			});

		stopEmpty.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					System.out.println("StopEmpty");
					jpanelstoptimet.setEnabled(false);
					paramQuantity.setEnabled(false);
					paramSubstance.setEnabled(false);
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
	private JPanelStopTimeT jpanelstoptimet;


	private JRadioButton stopQuantityQ;
	private JRadioButton stopOverflow;
	private JRadioButton stopEmpty;

	private JTextField paramQuantity;
	private JComboBox<String> paramSubstance;

	private JButton buttonOk;
	private JButton buttonCancel;

	}
