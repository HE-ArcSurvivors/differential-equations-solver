package layout.toolsbar;

import java.awt.Color;
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
import javax.swing.JTextField;

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

			this.stateEdit = false;

			// Layout
			Box boxH = Box.createHorizontalBox();
			boxH.add(substance_name);
			boxH.add(substance_field);
			boxH.add(Box.createHorizontalStrut(10));
			boxH.add(buttonEdit);
			boxH.add(buttonValidate);

			setLayout(new FlowLayout(FlowLayout.LEFT));

			// JComponent : add
			add(boxH);
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
	private boolean stateEdit;

	}
