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

package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import substance.Substance;
import tank.Tank;
import tools.MathTools;

public class JUnitMathTest
{
/*------------------------------------------------------------------*\
	 |*							Constructeurs							*|
	 \*------------------------------------------------------------------*/

	private static Substance eau;
	private static Substance sel;

@Before public void before()
	{
	eau = new Substance("Eau", (float)0.6, Substance.LIQUID);
	sel = new Substance("Sel", (float)1.0, Substance.SOLID);
	}

@After public void after()
	{
	// rien
	}

/*------------------------------------------------------------------*\
	 |*							Methodes Public							*|
	 \*-----------------------------------------------------------------*/

@Test public void testDidierMuller()
	{
	// http://www.apprendre-en-ligne.net/MADIMU2/ANALY/ANALY9.PDF

	Tank r1 = new Tank(false, 500, 5);
	r1.setName("ParentTank");
	Tank r2 = new Tank(false, 200, 5);
	r2.setName("MainTank");

	r1.addSubstance(sel, 2);
	r1.addSubstance(eau, 500);

	r2.addSubstance(sel, 4);
	r2.addSubstance(eau, 100);
	r2.addTankParent(r1);

	double valeurDuProgramme = r2.equaDiff(10,sel);
	double valeurAttendue = 81.1;

	Assert.assertTrue(MathTools.isEquals(valeurDuProgramme, valeurAttendue, 0.01));
	}

@Test public void testSerie4Ex1()
	{
	Tank r1 = new Tank(false, 5000000, 25);
	r1.addSubstance(sel, 20);
	r1.addSubstance(eau, 5000);

	Tank r2 = new Tank(false, 200, 25);
	r2.addSubstance(sel, 0.03);
	r2.addSubstance(eau, 100);

	r1.addTankParent(r2);

	double valeurDuProgramme = r1.equaDiff(30,sel);
	double valeurAttendue = 38.1;

	Assert.assertTrue(MathTools.isEquals(valeurDuProgramme, valeurAttendue, 0.01));
	}

@Test public void testSerie5Ex2()
	{
	Tank r0 = new Tank(false, 5000, 20);
	r0.addSubstance(sel,0);
	r0.addSubstance(eau, 5000);

	Tank r1 = new Tank(false, 500, 20);
	r1.addSubstance(sel, 1);
	r1.addSubstance(eau, 200);

	r1.addTankParent(r0);

	double t;

	t = Math.random();

	double valeurDuProgramme = r1.equaDiff(t,sel);
	double valeurAttendue = Math.exp(-0.1*t);

	//Point a de l'exercice
	Assert.assertTrue(MathTools.isEquals(valeurDuProgramme, valeurAttendue, 0.0001));
	}

/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

}