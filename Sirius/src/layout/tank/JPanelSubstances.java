
package layout.tank;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;

import substance.Substance;
import tank.Tank;
import differentialEquationSolving.SimulationSingleton;

public class JPanelSubstances extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSubstances(Tank tank, JPannelContentTank panelContentTank, int typeSubstance, boolean pourcentageMode)
		{
		this.pourcentageMode = pourcentageMode;
		this.typeSubstance = typeSubstance;
		this.panelContentTank = panelContentTank;
		this.changingSubstance = null;
		this.tank = tank;

		map_Substances = tank.getTypeSubstances(0, typeSubstance);

		map_Rectangles = new HashMap<Substance, Rectangle2D>();

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateSubstancesInTank()
		{
		//update the substances in Tank with the content of map_Substances
		Set<Entry<Substance, Double>> set = map_Substances.entrySet();
		Iterator<Entry<Substance, Double>> it = set.iterator();
		while(it.hasNext())
			{
			Entry<Substance, Double> ligne = it.next();
			Substance substance = ligne.getKey();
			Double quantity = ligne.getValue();

			tank.editSubstance(substance, quantity);
			}
		}

	public void updateFromTank(double t)
		{
		map_Substances.clear();
		map_Substances = tank.getTypeSubstances(t, typeSubstance);

		if (panelContentTank != null)
			{
			panelContentTank.setContent(tank.getContent(t));
			}

		repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		dessiner(g2d);
		}

	private void dessiner(Graphics2D g2d)
		{
		buildRects();

		Set<Entry<Substance, Rectangle2D>> set = map_Rectangles.entrySet();
		Iterator<Entry<Substance, Rectangle2D>> it = set.iterator();
		while(it.hasNext())
			{
			Entry<Substance, Rectangle2D> ligne = it.next();
			Substance substance = ligne.getKey();
			Rectangle2D rect = ligne.getValue();

			int rgb = Color.HSBtoRGB(substance.getHue_color(), 1, 1);
			Color color = new Color(rgb);

			g2d.setColor(color);
			g2d.fill(rect);

			g2d.setColor(Color.BLACK);
			String name = substance.getName();

			String content;
			if (pourcentageMode)
				{
				content = (int)(map_Substances.get(substance) / calculateContent() * 100) + "%";
				}
			else
				{
				String type = "l";
				if (substance.getState() == Substance.SOLID)
					{
					type = "g";
					}
				content = String.format("%.2f"+type, map_Substances.get(substance));
				}

			int x = 5; //(int)(rect.getX() + rect.getWidth() / 2)-12;
			int y = (int)(rect.getY() + rect.getHeight() / 2);
			g2d.drawString(name, x, y + 20);
			g2d.drawString(content, x, y);
			}
		}

	private void buildRects()
		{
		map_Rectangles.clear();

		int totalHeight = this.getHeight();
		int createdHeight = 0;

		double totalCapacite = tank.getCapacite();

		Set<Entry<Substance, Double>> set = map_Substances.entrySet();
		Iterator<Entry<Substance, Double>> it = set.iterator();
		while(it.hasNext())
			{
			Entry<Substance, Double> ligne = it.next();
			Substance substance = ligne.getKey();
			Double qte = ligne.getValue();

			int h = (int)((qte / totalCapacite) * totalHeight);

			Rectangle2D rect = new Rectangle2D.Double(0, totalHeight - h - createdHeight, this.getWidth(), h);

			createdHeight += h;

			map_Rectangles.put(substance, rect);
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

	public Substance overSubstance(double y)
		{
		int precision = 10;
		Substance substanceOver = null;

		Set<Entry<Substance, Rectangle2D>> set = map_Rectangles.entrySet();
		Iterator<Entry<Substance, Rectangle2D>> it = set.iterator();

		while(it.hasNext())
			{
			Entry<Substance, Rectangle2D> ligne = it.next();
			Substance substance = ligne.getKey();
			Rectangle2D rectangle2d = ligne.getValue();

			if (rectangle2d.getY() > y - precision && rectangle2d.getY() < y + precision)
				{
				substanceOver = substance;
				}
			}

		return substanceOver;
		}

	private void control()
		{

		addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseReleased(MouseEvent e)
					{
					changingSubstance = null;
					//getParent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}

				@Override
				public void mousePressed(MouseEvent e)
					{
					Substance tmp = overSubstance(e.getY());
					if (tmp != null && !pourcentageMode && !SimulationSingleton.getInstance().isStarted()) //TODO: change for many Liquids in infinite mode
						{
						changingSubstance = tmp;
						}
					}
			});

		addMouseMotionListener(new MouseMotionListener()
			{

				@Override
				public void mouseDragged(MouseEvent e)
					{
					if (changingSubstance != null && !SimulationSingleton.getInstance().isStarted())
						{
						//getParent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						changeSize(changingSubstance, map_Rectangles.get(changingSubstance), e.getY());
						}
					}

				@Override
				public void mouseMoved(MouseEvent e)
					{
					Substance tmp = overSubstance(e.getY());

					if (tmp != null && !SimulationSingleton.getInstance().isStarted())
						{
						getParent().setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						}
					else
						{
						getParent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					}

			});

		}

	private void changeSize(Substance substance, Rectangle2D rectangle2d, double y)
		{

		double origineY = rectangle2d.getY();

		double ratio = tank.getCapacite() / this.getHeight();

		double variation = origineY - y;

		Double valueSubstance = map_Substances.get(substance) + variation * ratio;

		Double maxSize = tank.getCapacite()*100 - (calculateContent() - map_Substances.get(substance)); //t
		if (valueSubstance < 0)
			{
			valueSubstance = 0.0;
			}
		else if (valueSubstance > maxSize)
			{
			valueSubstance = maxSize;
			}

		map_Substances.put(substance, (double)(Math.round(valueSubstance * 100)) / 100); //2 chiffres après la vigule

		if (panelContentTank != null)
			{
			panelContentTank.setContent(calculateContent());
			}

		updateSubstancesInTank();
		repaint();
		}

	private Double calculateContent()
		{
		Set<Entry<Substance, Double>> set = map_Substances.entrySet();
		Iterator<Entry<Substance, Double>> it = set.iterator();

		Double total = 0.0;
		while(it.hasNext())
			{
			Entry<Substance, Double> ligne = it.next();
			Double qte = ligne.getValue();
			total += qte;
			}

		return total;
		}

	private void appearance()
		{
		// setBackground(Color.green);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Map<Substance, Double> map_Substances;
	private Map<Substance, Rectangle2D> map_Rectangles;

	private Substance changingSubstance;

	// in
	private Tank tank;
	private JPannelContentTank panelContentTank;
	private int typeSubstance;

	private boolean pourcentageMode;
	private static final int RATIO_SOLID_LIQUID = 1000;

	}
