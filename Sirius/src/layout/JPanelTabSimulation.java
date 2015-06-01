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
	public JPanelContent getJpanelContent()
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
//			jpanelbottom.setMinimumSize(new Dimension(this.getWidth(),100));
//			jpanelbottom.setSize(new Dimension(this.getWidth(),100));
//			jpanelbottom.setMaximumSize(new Dimension(this.getWidth(),100));
//			jpanelbottom.setPreferredSize(new Dimension(this.getWidth(),100));

			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			// borderLayout.setHgap(20);
			// borderLayout.setVgap(20);
			}

			// JComponent : add
			add(jpanelcontent,BorderLayout.CENTER);
			add(jpaneloutil,BorderLayout.WEST);
			add(jpanelbottom,BorderLayout.SOUTH);
		}

	private void control()
		{
			addComponentListener(new ComponentAdapter()
				{
				        @Override
						public void componentResized(ComponentEvent e) {
							//NEED TO CHECK FOR RESIZE EVENT
							jpanelbottom.resizeEvent();
				        }
				});
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelOutils 	jpaneloutil;
	private JPanelContent 	jpanelcontent;
	private JPanelBottom	jpanelbottom;

	}
