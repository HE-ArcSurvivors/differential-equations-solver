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

package tools;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DoubleDocumentFilter extends DocumentFilter
	{

	@Override
	public void insertString(DocumentFilter.FilterBypass fp, int offset, String string, AttributeSet aset) throws BadLocationException
		{
		int len = string.length();
		boolean isValidInteger = true;

		for(int i = 0; i < len; i++)
			{
			if (string.charAt(i) == '.' && isPointHere == 0)
				{
					isPointHere = 1;
				}
			else if(!Character.isDigit(string.charAt(i)))
				{
				isValidInteger = false;
				break;
				}
			}
		if (isValidInteger)
			{
			super.insertString(fp, offset, string, aset);
			}
		else
			{
			Toolkit.getDefaultToolkit().beep();
			}
		}

	@Override
	public void replace(DocumentFilter.FilterBypass fp, int offset, int length, String string, AttributeSet aset) throws BadLocationException
		{
		int len = string.length();
		boolean isValidInteger = true;

		for(int i = 0; i < len; i++)
			{
			if (string.charAt(i) == '.' && isPointHere == 0)
				{
				isPointHere = 1;
				}
			else if (!Character.isDigit(string.charAt(i)))
				{
				isValidInteger = false;
				break;
				}
			}
		if (isValidInteger)
			{
			super.replace(fp, offset, length, string, aset);
			}
		else
			{
			Toolkit.getDefaultToolkit().beep();
			}
		}

		@Override
		public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException
		{
		 	String string = fb.getDocument().getText(offset, length);
		 	for(int i = 0; i < string.length(); i++)
		 		{
		 			if(string.charAt(i) == '.') { isPointHere = 0; }
		 		}
			super.remove(fb, offset, length);
		}

		int isPointHere = 0;
	}
