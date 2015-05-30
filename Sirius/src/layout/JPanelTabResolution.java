package layout;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;

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

			Box boxH = Box.createHorizontalBox();
			boxH.add(jpanelresolutiondata);
			boxH.add(jpanelresolutionsolving);

			boxH.setAlignmentX(LEFT_ALIGNMENT);
			boxH.setAlignmentY(TOP_ALIGNMENT);

			FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
			setAlignmentY(TOP_ALIGNMENT);
			setLayout(layout);
			add(boxH);
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

	}
