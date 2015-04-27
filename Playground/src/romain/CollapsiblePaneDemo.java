package romain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXFrame;

public class CollapsiblePaneDemo
{

  /**
   * @param args
   */
  public static void main( String[] args )
  {
    final JXCollapsiblePane cp = 
        new JXCollapsiblePane( JXCollapsiblePane.Direction.RIGHT );

    // JXCollapsiblePane can be used like any other container
    cp.setLayout( new BorderLayout() );

    // the Controls panel with a textfield to filter the tree
    JPanel controls = new JPanel( new FlowLayout( FlowLayout.LEFT, 4, 0 ) );
    controls.add( new JLabel( "Search:" ) );
    controls.add( new JTextField( 10 ) );
    controls.add( new JButton( "Refresh" ) );
    controls.setBorder( new TitledBorder( "Filters" ) );

    cp.add( "Center", controls );

    JXFrame frame = new JXFrame();
    frame.setLayout( new BorderLayout() );

    // Then the tree - we assume the Controls would somehow filter the tree
    JScrollPane scroll = new JScrollPane( new JTree() );
    // Put the "Controls" first
    frame.add( "Center", scroll );


    // Show/hide the "Controls"
    final JButton toggle = new JButton( cp.getActionMap()
        .get( JXCollapsiblePane.TOGGLE_ACTION ) );
    toggle.setText( "-" );
    toggle.setPreferredSize( new Dimension( 20, toggle.getSize().height ) );

    toggle.addMouseListener( new MouseAdapter()
    {
      @Override
      public void mouseEntered( MouseEvent e )
      {
          toggle.doClick();
      }
    } );

    final JPanel panel = new JPanel();
    panel.setLayout( new BorderLayout() );
    panel.add( "Center", toggle );
    panel.add( "East", cp );

    panel.addMouseListener( new MouseAdapter()
    {
      @Override
      public void mouseExited( MouseEvent e )
      {
        if ( !panel.contains( e.getPoint() ) )
        {
          toggle.doClick();
        }
      }
    } );

    frame.add( "East", panel );

    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.pack();
    cp.setCollapsed( true );
    frame.setVisible( true );

  }
}
