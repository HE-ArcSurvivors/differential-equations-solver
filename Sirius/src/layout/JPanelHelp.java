package layout;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JPanelHelp extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelHelp()
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
			buttonValidate = new JButton("OK");

			// Layout : Specification
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			Box box = Box.createVerticalBox();
			box.add(new JLabel("En cas de problème lors de l'utilisation de notre application,"));
			box.add(new JLabel("veuillez contacter le Support Banana par e-mail : "));
			box.add(Box.createVerticalStrut(20));
			box.add(new JLabel("banana.help@gmail.com"));
			box.add(Box.createVerticalStrut(20));
			box.add(new JLabel("Notre équipe tentera de répondre à toutes vos questions"));
			box.add(Box.createVerticalStrut(20));
			box.add(buttonValidate);

			add(box);
		}

	private void control()
		{
		buttonValidate.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					Window w = SwingUtilities.getWindowAncestor(JPanelHelp.this);
					w.setVisible(false);
					}
			});
		}

	private void appearance()
		{
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton buttonValidate;

	}
