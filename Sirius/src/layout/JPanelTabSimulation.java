
package layout;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		//TODO make the scroll work
//		scrollPaneContent = new JScrollPane(jpanelcontent);

		// JComponent : add
		 add(jpanelcontent, BorderLayout.CENTER);
//		add(scrollPaneContent, BorderLayout.CENTER);
		add(jpaneloutil, BorderLayout.WEST);
		add(jpanelbottom, BorderLayout.SOUTH);
		}

	private void control()
		{
		addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent e)
					{
					jpanelbottom.resizeEvent();
					}
			});
		}

	private void appearance()
		{
		// rien
		}

	public void changeTabEvent()
		{
		jpanelbottom.resizeEvent();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelOutils jpaneloutil;
	private JPanelContent jpanelcontent;
	private JPanelBottom jpanelbottom;

	public JScrollPane scrollPaneContent;

	}
