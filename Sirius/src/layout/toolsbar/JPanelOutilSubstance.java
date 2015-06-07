package layout.toolsbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import substance.Substance;
import tools.MagasinImage;
import tools.SwingUtil;

public class JPanelOutilSubstance extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelOutilSubstance(Substance substance)
		{
		this.substance = substance;

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
			int rgb = Color.HSBtoRGB(substance.getHue_color(), 1, 1);
			Color color = new Color(rgb);

			substance_name = new JLabel(substance.getName());
			substance_name.setForeground(color);

			buttonEdit = new JButton(MagasinImage.iconEdit);
			buttonEdit.setContentAreaFilled(false);
			buttonEdit.setBorder(BorderFactory.createEmptyBorder());

			buttonValidate = new JButton(MagasinImage.iconValidate);
			buttonValidate.setContentAreaFilled(false);
			buttonValidate.setBorder(BorderFactory.createEmptyBorder());
			buttonValidate.setVisible(false);

			substance_field = new JTextField(substance.getName());
			substance_field.setVisible(false);

			substance_color = new JLabel("Couleur");
			substance_color.setVisible(false);

			sliderColor = new JSlider(0,100);
			sliderColor.setPreferredSize(new Dimension(90, 30));
			sliderColor.setValue((int)(substance.getHue_color()*100));
			sliderColor.setVisible(false);

			this.stateEdit = false;

			// Layout
			Box boxH_line1 = Box.createHorizontalBox();
			boxH_line1.setAlignmentX(Component.LEFT_ALIGNMENT);
			boxH_line1.add(substance_name);
			boxH_line1.add(substance_field);
			boxH_line1.add(Box.createHorizontalStrut(10));
			boxH_line1.add(buttonEdit);
			boxH_line1.add(buttonValidate);
			boxH_line1.add(Box.createHorizontalGlue());

			Box boxH_line2 = Box.createHorizontalBox();
			boxH_line2.setAlignmentX(Component.LEFT_ALIGNMENT);
			boxH_line2.add(substance_color);
			boxH_line2.add(sliderColor);

			Box boxV = Box.createVerticalBox();
			boxV.add(boxH_line1);
			boxV.add(boxH_line2);

			setLayout(new FlowLayout(FlowLayout.LEFT));

			// JComponent : add
			add(boxV);
		}

	private void control()
		{
		substance_field.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
			        	if(stateEdit)
			        		{
			        		validateEdit();
			        		}
					}
				}
			});

		buttonEdit.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					editState();
					}
			});

		buttonValidate.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					validateEdit();
					}
			});

		final JPanelOutilSubstance me = this;
		sliderColor.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				substance.setHue_color((float)sliderColor.getValue()/100);
				SwingUtil.repaintAllParent(me);

				int rgb = Color.HSBtoRGB(substance.getHue_color(), 1, 1);
				Color color = new Color(rgb);
				substance_name.setForeground(color);
				}
		});
		}

	private void validateEdit()
	{
		editState();
		substance.setName(substance_field.getText());
		substance_name.setText(substance_field.getText());
		SwingUtil.repaintAllParent(this);
	}

	private void editState()
	{
		substance_field.setVisible(!stateEdit);
		substance_name.setVisible(stateEdit);

		buttonValidate.setVisible(!stateEdit);
		buttonEdit.setVisible(stateEdit);

		substance_color.setVisible(!stateEdit);
		sliderColor.setVisible(!stateEdit);

		stateEdit = !stateEdit;
	}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Substance substance;

	private JLabel substance_name;
	private JTextField substance_field;

	private JButton buttonEdit;
	private JButton buttonValidate;

	private JLabel substance_color;
	private JSlider sliderColor;

	private boolean stateEdit;

	}
