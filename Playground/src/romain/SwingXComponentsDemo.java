package romain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXCollapsiblePane.Direction;

import org.jdesktop.swingx.JXFrame;


public class SwingXComponentsDemo extends JXFrame {
    private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SwingXComponentsDemo().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public SwingXComponentsDemo() {
        super("SwingX Components Demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());
        demo();
        this.pack();
    }

    public void demo() {
        //JXCollapsiblePane
        JXCollapsiblePane collapsibelPane = new JXCollapsiblePane(Direction.TRAILING);
        collapsibelPane.setLayout(new FlowLayout());

        //JXMonthView
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(new JXButton("Bouton 2"));
  
        collapsibelPane.add(box);

        JButton toggle = new JXButton(collapsibelPane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
        toggle.setText("Bouton 1");
        this.add(toggle);
        this.add(collapsibelPane);
    }

}