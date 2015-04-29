
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
