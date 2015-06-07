
package layout.tank;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tools.MagasinImage;

public class JPannelTap extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPannelTap(boolean isLeftMode)
		{
		this.isLeftMode = isLeftMode;
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


	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		dessiner(g2d);
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void dessiner(Graphics2D g2d)
		{
		if(isLeftMode)
			{
			g2d.drawImage(MagasinImage.tapLeft.getImage(), 0, this.getHeight()-this.getWidth(), this.getWidth(),this.getWidth(), null);
			}
		else
			{
			g2d.drawImage(MagasinImage.tapRight.getImage(), 0, this.getHeight()-this.getWidth(), this.getWidth(),this.getWidth(), null);
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
		//setBackground(Color.black);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// in
	private boolean isLeftMode;

	}
