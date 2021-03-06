/*********************************************************************************
# Copyright © Haute-Ecole ARC - All Rights Reserved
# Copyright © Banana Rocket - All Rights Reserved
#
# This file is part of <P2 Java Project: GlouGlou - Problèmes de mélanges>.
#
# Unauthorized copying of this file, via any medium is strictly prohibited
# Proprietary and confidential
# Written by Claret-Yakovenko Roman <romain.claret@rocla.ch>
# Written by Divernois Margaux <margaux.divernois@gmail.com>
# Written by Visinand Steve <visinandst@gmail.com>
#
# Date : June 2015
**********************************************************************************/

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

import tools.MagasinImage;

public class JPanelAbout extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelAbout()
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
			box.add(new JLabel("Un programme proposé par Banana Rocket"));
			box.add(Box.createVerticalStrut(40));
			box.add(new JLabel(MagasinImage.bananaRocket));
			box.add(Box.createVerticalStrut(40));
			box.add(new JLabel("Banana Rocket est une équipe composée de :"));
			box.add(new JLabel("* Claret-Yakovenko Romain"));
			box.add(new JLabel("* Divernois Margaux"));
			box.add(new JLabel("* Visinand Steve"));
			box.add(Box.createVerticalStrut(30));
			box.add(new JLabel("Version Release 1.0"));
			box.add(new JLabel("Projet P2 - Printemps 2015"));
			box.add(Box.createVerticalStrut(20));
			box.add(new JLabel("Icons : Copyright © 2014 Drifty (http://drifty.com/)"));
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
					Window w = SwingUtilities.getWindowAncestor(JPanelAbout.this);
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
