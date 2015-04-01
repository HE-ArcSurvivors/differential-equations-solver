package margaux.conteneur;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

	public Container getConteneur()
	{
		boolean infiniBoolean = infini.getState();

		double capaciteDouble = 0;
		double contenanceDouble = 0;
		if(!infiniBoolean)
		{
			capaciteDouble = Double.parseDouble(capacite.getText());
			contenanceDouble = Double.parseDouble(contenance.getText());
		}

		double liquidePourcentDouble = Double.parseDouble(liquidePourcent.getText());
		double substancePourcentDouble = Double.parseDouble(substancePourcent.getText());
		double debitDouble = Double.parseDouble(debit.getText());

		return new Container(infiniBoolean, capaciteDouble, contenanceDouble, liquidePourcentDouble, substancePourcentDouble, debitDouble);
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
			liquidePourcent = new JTextField(20);
			substancePourcent = new JTextField(20);
			debit = new JTextField(20);

			//TEMPORARY BECAUSE WE CAN'T CHECK BOTH EDIT
			substancePourcent.setEnabled(false);

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

			add(new JLabel("Quantité d'eau :"),CC.xy(1,5));
			add(liquidePourcent,CC.xy(2,5));

			add(new JLabel("Quantité de substance :"),CC.xy(1,6));
			add(substancePourcent,CC.xy(2,6));

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

			liquidePourcent.getDocument().addDocumentListener(new DocumentListener()
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
						substancePourcent.setText(String.valueOf(100));
						}

					public void correctData()
					{
						Double liquideValue = Double.parseDouble(liquidePourcent.getText());
						if(liquideValue <= 100 && liquideValue >= 0)
							{
							    Double newValue = 100 - liquideValue;
								substancePourcent.setText(String.valueOf(newValue));
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
	private JTextField liquidePourcent;
	private JTextField substancePourcent;
	private JTextField debit;

	}
