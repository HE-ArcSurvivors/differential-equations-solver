
package layout.bottom;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.JPanel;

public class JPanelBottom extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelBottom()
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

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		}

	private void geometry()
		{

		jpanelbottomequationline = new JPanelBottomEquationLine();
		jpanelbottomsimulationline = new JPanelBottomSimulationLine();

		Box boxV = Box.createVerticalBox();

		boxV.add(jpanelbottomsimulationline);
		boxV.add(Box.createVerticalGlue());
		boxV.add(jpanelbottomequationline);

		setLayout(new BorderLayout());
		add(boxV,BorderLayout.WEST);

		}

	private void control()
		{

		}

	private void appearance()
		{

		}

	public void setEquationVisible(boolean visible)
		{
			jpanelbottomequationline.setVisible(visible);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelBottomEquationLine jpanelbottomequationline;
	private JPanelBottomSimulationLine jpanelbottomsimulationline;

	}
