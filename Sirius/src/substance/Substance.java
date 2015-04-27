package substance;



public class Substance
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Substance(String name, float hue_color, int state)
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

	public void setHue_color(float hue_color)
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

	public float getHue_color()
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
	private float hue_color;
	private int state;

	public static final int SOLID = 0;
	public static final int LIQUID = 1;
	}

