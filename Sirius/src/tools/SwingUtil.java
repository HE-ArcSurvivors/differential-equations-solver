
package tools;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * This class contains a collection of static utility methods for Swing.
 *
 */
public class SwingUtil
	{

	// Public static methods.

	/**
	 * Repaints the parent of the given component. If the parent is null, the component itself is repainted.
	 *
	 * @param   component    The component whose parent will be repainted.
	 * @author Heidi Rakels
	 *
	 * From http://www.java2s.com/
	 */
	public static void repaintParent(JComponent component)
		{

		// Get the parent of the component.
		JComponent parentComponent = (JComponent)SwingUtilities.getAncestorOfClass(JComponent.class, component);

		// Could we find a parent?
		if (parentComponent != null)
			{
			// Repaint the parent.
			parentComponent.revalidate();
			parentComponent.repaint();
			}
		else
			{
			// Repaint the component itself.
			component.revalidate();
			component.repaint();
			}

		}

	public static void repaintAllParent(JComponent component)
		{
		JComponent parentComponent = (JComponent)SwingUtilities.getAncestorOfClass(JComponent.class, component);

		do
			{
			repaintParent(parentComponent);
			parentComponent = (JComponent)SwingUtilities.getAncestorOfClass(JComponent.class, parentComponent);

			} while(parentComponent != null);

		}
	}
