package layout;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import layout.tabresolution.JPanelResolutionData;
import layout.tabresolution.JPanelResolutionSolving;

public class JPanelTabResolution extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelTabResolution()
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

	public void reload()
		{
			jpanelresolutiondata.reload();
			jpanelresolutionsolving.reload();
		}

	private void geometry()
		{
			// JComponent : Instanciation
			//jpanelresolutionschema = new JPanelResolutionSchema();

			jpanelresolutiondata = new JPanelResolutionData();
			jpanelresolutionsolving = new JPanelResolutionSolving();

			JScrollPane scrollerData = new JScrollPane(jpanelresolutiondata);

			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollerData, jpanelresolutionsolving);
			splitPane.setOneTouchExpandable(true);
			splitPane.setDividerLocation(0.5);
			splitPane.setContinuousLayout(true);

			BorderLayout layout = new BorderLayout();
			setLayout(layout);
			add(splitPane,BorderLayout.CENTER);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	//private JPanelResolutionSchema jpanelresolutionschema;
	private JPanelResolutionData jpanelresolutiondata;
	private JPanelResolutionSolving jpanelresolutionsolving;

	private JSplitPane splitPane;

	}
