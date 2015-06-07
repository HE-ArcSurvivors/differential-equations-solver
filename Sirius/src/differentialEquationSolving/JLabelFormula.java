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

package differentialEquationSolving;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class JLabelFormula extends JLabel
	{

	public JLabelFormula(String latex)
		{
		super(new TeXFormula(latex).new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(16).setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_LEFT).setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build());
		}

	public JLabelFormula(TeXIcon iconFormula)
		{
		setIcon(iconFormula);
		setMaximumSize(new Dimension(500,300));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}

	public void setFormula(String latex)
		{
		TeXFormula formula = new TeXFormula(latex);
		TeXIcon iconFormula = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(16).setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_LEFT).setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f).build();
		setIcon(iconFormula);
		}

	}
