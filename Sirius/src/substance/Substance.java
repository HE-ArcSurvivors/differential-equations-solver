package substance;



public class Substance
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Substance(String name, double hue_color, int state)
		{
		this.name = name;
		this.hue_color = hue_color;
		this.state = state;
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

	public void setState(int state)
		{
		this.state = state;
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

	public int getState()
		{
		return this.state;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private String name;
	private double hue_color;
	private int state;

	public static final int SOLID = 0;
	public static final int LIQUID = 1;
	}

