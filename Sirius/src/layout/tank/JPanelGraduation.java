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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import substance.Substance;

public class JPanelGraduation extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGraduation(double capacite, int type)
		{
		this.capacite = capacite;
		this.arial = new Font("Arial", Font.PLAIN, 10);
		this.type = type;

		if (type == Substance.SOLID)
			{
			pasL = 1;
			}
		else
			{
			pasL = 1;
			}

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

	public void setCapacite(double capacite)
		{
		this.capacite = capacite;
		repaint();
		}

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
		int h = this.getHeight();
		double ratio = h / capacite;
		double pasPixel = (ratio * pasL);

		int nbGrad = (int)(h / pasPixel);
		int maxGrad = 30;

		if (nbGrad > maxGrad)
			{
			pasPixel = h / maxGrad;
			pasL = (int)(pasPixel / ratio);
			}

		int L = -pasL;
		for(double i = h; i > 0; i -= pasPixel)
			{
			g2d.setColor(Color.BLACK);

			if (type == Substance.SOLID)
				{
				int marginL = 30;
				int marginR = 12;

				L += pasL;

				if (L % (2 * pasL) == 0 && i != h)
					{
					g2d.setFont(arial);
					g2d.drawString(L + "", 5, (int)(i + 4));
					marginR = 8;
					}
				g2d.draw(new Line2D.Double(marginL, i, this.getWidth() - marginR, i));

				}
			else
				{

				int margin = 5;
				int marginSup = 3;
				int sizeNumbers = 30;

				L += pasL;
				if (L % (2 * pasL) == 0 && i != h)
					{
					g2d.setFont(arial);
					g2d.drawString(L + "", this.getWidth()/2 - sizeNumbers/2 + 6, (int)(i + 4));
					marginSup = 0;
					}

				g2d.draw(new Line2D.Double(margin+marginSup, i, this.getWidth()/2 - sizeNumbers/2, i));
				g2d.draw(new Line2D.Double(this.getWidth()/2 + sizeNumbers/2, i, this.getWidth()/2 + sizeNumbers/2 + margin-marginSup, i));

				}
			}
		}

	private void geometry()
		{
		setLayout(null);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		setBackground(Color.LIGHT_GRAY);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// In
	private double capacite;
	private int type;

	//tools
	private Font arial;
	private int pasL;

	}
