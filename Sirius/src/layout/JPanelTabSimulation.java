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

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import layout.toolsbar.JPanelOutils;

public class JPanelTabSimulation extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTabSimulation()
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

	public JPanelContent getJPanelContent()
		{
		return jpanelcontent;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		jpaneloutil = new JPanelOutils();
		jpanelcontent = new JPanelContent();
		jpanelbottom = new JPanelBottom(jpanelcontent);

		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// JComponent : add
		add(jpanelcontent, BorderLayout.CENTER);
		add(jpaneloutil, BorderLayout.WEST);
		add(jpanelbottom, BorderLayout.SOUTH);

		}

	private void control()
		{
		addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent e)
					{
					jpanelbottom.resizeEvent();
					}
			});
		}

	private void appearance()
		{
		// rien
		}

	public void changeTabEvent()
		{
		jpanelbottom.resizeEvent();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelOutils jpaneloutil;
	private JPanelContent jpanelcontent;
	private JPanelBottom jpanelbottom;

	}
