
package margauxV2.substance;



public class Substance
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Substance(String name, double hue_color)
		{
		super();
		this.name = name;
		this.hue_color = hue_color;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setName(String name)
		{
		this.name = name;
		}

	public void setHue_color(double hue_color)
		{
		this.hue_color = hue_color;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getName()
		{
		return this.name;
		}

	public double getHue_color()
		{
		return this.hue_color;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String name;
	private double hue_color;
	}

