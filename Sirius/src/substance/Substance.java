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

package substance;

import java.io.Serializable;

public class Substance extends Object implements Serializable
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Substance(String name, float hue_color, int state)
		{
		this.name = name;
		this.hue_color = hue_color;
		this.state = state;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setName(String name)
		{
		this.name = name;
		}

	public void setHue_color(float hue_color)
		{
		this.hue_color = hue_color;
		}

	public void setState(int state)
		{
		this.state = state;
		}

	@Override
	public String toString()
		{
			return name;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getName()
		{
		return this.name;
		}

	public float getHue_color()
		{
		return this.hue_color;
		}

	public int getState()
		{
		return this.state;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String name;
	private float hue_color;
	private int state;

	public static final int SOLID = 0;
	public static final int LIQUID = 1;
	}
