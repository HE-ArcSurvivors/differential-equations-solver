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

package layout.tank;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import tools.MagasinImage;

public class JPannelContentTank extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPannelContentTank(double capacite, double content)
		{
		this.capacite = capacite;
		this.content = content;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setContent(double content)
		{
		this.content = content;
		repaint();
		}

	public void setCapacite(double capacite)
		{
		this.capacite = capacite;
		repaint();
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
	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		dessiner(g2d);
		}

	private void dessiner(Graphics2D g2d)
		{
		int totalh = this.getHeight();


		if (content > capacite)
			{
			rectContent = new Rectangle2D.Double(0, 0, this.getWidth(), totalh);
			g2d.setColor(Color.RED);
			}
		else
			{
			double RatioRempli = content / capacite;

			int h = (int)(RatioRempli * totalh);

			rectContent = new Rectangle2D.Double(0, totalh - h, this.getWidth(), totalh);
			g2d.setColor(Color.BLUE);
			}

		String contentL = (double)(Math.round(content * 100)) / 100 + " l";

		g2d.fill(rectContent);

		g2d.setColor(Color.BLACK);

		int x = (int)(rectContent.getWidth() / 2) - 12;
		int y = (int)((rectContent.getY() + rectContent.getHeight()) / 2);
		g2d.drawString(contentL, x, y);

		if (content > capacite)
			{
			g2d.drawImage(MagasinImage.iconWarning.getImage(), 15, 15, null);
			}
		}

	private void geometry()
		{
			// JComponent : Instanciation

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add

		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		//setBackground(Color.blue);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private double capacite;
	private double content;

	// Tools
	private Rectangle2D rectContent;

	}
