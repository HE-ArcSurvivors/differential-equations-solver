package tank;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import substance.Substance;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.FormLayout;

public class JPanelParametres extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelParametres()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public Tank getConteneur()
	{
		boolean infiniBoolean = infini.getState();
		double debitDouble = Double.parseDouble(debit.getText());
		double capaciteDouble = 0;
		double contenanceDouble = 0;
		if(!infiniBoolean)
		{
			capaciteDouble = Double.parseDouble(capacite.getText());
			contenanceDouble = Double.parseDouble(contenance.getText());
		}
		double substancePourcentDouble = Double.parseDouble(substance.getText());

		Tank newReservoir = new Tank(infiniBoolean, capaciteDouble, debitDouble);
		newReservoir.addLiquid(new Substance("Eau",0.15), contenanceDouble);
		newReservoir.addSubstance(new Substance("Sel",0.20), substancePourcentDouble);

		return newReservoir;
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

	private void geometry()
		{
			// JComponent : Instanciation
			infini = new Checkbox("",false);
			capacite = new JTextField(20);
			contenance = new JTextField(20);
			liquide = new JTextField(20);
			substance = new JTextField(20);
			debit = new JTextField(20);

			//TEMPORARY BECAUSE WE CAN'T CHECK BOTH EDIT
			substance.setEnabled(false);

			// Layout : Specification
			{
			FormLayout layout = new FormLayout(
				      "150dlu, 100dlu",  // columns
				      "pref, pref, pref, pref, pref, pref, pref, pref, pref");           // rows
			setLayout(layout);
			}

			// JComponent : add
			add(new JLabel("Infini : "),CC.xy(1, 1));
			add(infini,CC.xy(2, 1));

			add(new JLabel("Capacité du réservoir (en litre) :"),CC.xy(1, 2));
			add(capacite,CC.xy(2, 2));

			add(createSeparator("Configuration"),CC.xyw(1, 3,2));

			add(new JLabel("Contenance du réservoir (en litre) :"),CC.xy(1, 4));
			add(contenance,CC.xy(2, 4));

			add(new JLabel("Pourcentage d'eau :"),CC.xy(1,5));
			add(liquide,CC.xy(2,5));

			add(new JLabel("Pourcentage de substance :"),CC.xy(1,6));
			add(substance,CC.xy(2,6));

			add(new JLabel("Débit (en l/s)"),CC.xy(1,7));
			add(debit,CC.xy(2,7));
		}

	private Component createSeparator(String string)
		{
        	return DefaultComponentFactory.getInstance().createSeparator(string);
		}

	private void control()
		{
			infini.addItemListener(new ItemListener()
				{
					@Override
					public void itemStateChanged(ItemEvent e)
						{
						capacite.setEnabled(!capacite.isEnabled());
						capacite.setText(null);

						contenance.setEnabled(!contenance.isEnabled());
						contenance.setText(null);
						}
				});

			liquide.getDocument().addDocumentListener(new DocumentListener()
				{
					@Override
				    public void changedUpdate(DocumentEvent e)
					{
				    	correctData();
				    }

					@Override
					public void insertUpdate(DocumentEvent e)
						{
						correctData();
						}

					@Override
					public void removeUpdate(DocumentEvent e)
						{
						substance.setText(String.valueOf(100));
						}

					public void correctData()
					{
						Double liquideValue = Double.parseDouble(liquide.getText());
						if(liquideValue <= 100 && liquideValue >= 0)
							{
							    Double newValue = 100 - liquideValue;
								substance.setText(String.valueOf(newValue));
							}
						else
							{
								//ERROR!
							}
					}
				});

//			substancePourcent.getDocument().addDocumentListener(new DocumentListener()
//				{
//					@Override
//				    public void changedUpdate(DocumentEvent e)
//					{
//				    	correctData();
//				    }
//
//					@Override
//					public void insertUpdate(DocumentEvent e)
//						{
//						correctData();
//						}
//
//					@Override
//					public void removeUpdate(DocumentEvent e)
//						{
//						correctData();
//
//						}
//
//					public void correctData()
//					{
//						int newValue = 100 - Integer.parseInt(substancePourcent.getText());
//					    liquidePourcent.setText(String.valueOf(newValue));
//					}
//				});
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Checkbox infini;
	private JTextField capacite;
	private JTextField contenance;
	private JTextField liquide;
	private JTextField substance;
	private JTextField debit;

	}
