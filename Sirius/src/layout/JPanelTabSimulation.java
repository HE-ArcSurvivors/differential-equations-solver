
package layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.junit.Rule;

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
			
//		jpanelcontent.setPreferredSize(new Dimension(10, 10));
			
		//TODO make the scroll work
		
		scrollPaneContent = new JScrollPane();
		
		scrollPaneContent.setViewportView(jpanelcontent);
		
		scrollPaneContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
//		scrollPaneContent.setCorner(JScrollPane.UPPER_LEFT_CORNER, jpanelcontent.);
//		scrollPaneContent.setCorner(JScrollPane.LOWER_LEFT_CORNER, new Corner());
//		scrollPaneContent.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new Corner());

		// JComponent : add
//		add(jpanelcontent, BorderLayout.CENTER);
		add(scrollPaneContent, BorderLayout.CENTER);
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
