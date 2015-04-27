package romain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
/** Test mouse-dragged */
@SuppressWarnings("serial")
public class UseDragBox extends JFrame {
   private int startX, startY, endX, endY;  // of a rectangle
   private JLabel statusBar;  // display the status
 
   /** Constructor to setup the GUI */
   public UseDragBox() {
      // Define an anonymous inner class extends JPanel for custom drawing
      // and allocate an instance
      JPanel drawPanel = new JPanel() {
         @Override
         public void paintComponent(Graphics g) {
            super.paintComponent(g);  // paint parent's background
            g.setColor(Color.RED);
            // drawRect() uses x, y, width and height instead of (x1,y1) and (x2,y2)
            int x = (startX < endX) ? startX : endX;
            int y = (startY < endY) ? startY : endY;
            int width = endX - startX + 1;
            if (width < 0) width = -width;
            int height = endY - startY + 1;
            if (height < 0) height = -height;
            g.drawRect(x, y, width, height);
         }
      };
 
      statusBar = new JLabel();
      drawPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      drawPanel.add(statusBar);
 
      // Allocate an instance of MyMouseDraggedListener
      // and used it as MouseListener and MouseMotionListener
      MyMouseDraggedListener listener = new MyMouseDraggedListener();
      drawPanel.addMouseListener(listener);
      drawPanel.addMouseMotionListener(listener);
 
      setContentPane(drawPanel);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Mouse-Drag Demo");
      setSize(400, 250);
      setVisible(true);
   }
 
   private class MyMouseDraggedListener extends MouseInputAdapter {
      @Override
      public void mousePressed(MouseEvent evt) {
         startX = evt.getX();
         startY = evt.getY();
         statusBar.setText("(" + startX + "," + startY + ")");
      }
      @Override
      public void mouseDragged(MouseEvent evt) {
         endX = evt.getX();
         endY = evt.getY();
         statusBar.setText("(" + endX + "," + endY + ")");
         repaint();  // Called back paintComponent()
      }
      @Override
         public void mouseReleased(MouseEvent evt) {
         endX = evt.getX();
         endY = evt.getY();
         statusBar.setText("(" + endX + "," + endY + ")");
         repaint();  // Called back paintComponent()
      }
   }
 
   /** The entry main method */
   public static void main(String[] args) {
      // Run GUI codes on the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new UseDragBox();  // Let the constructor do the job
         }
      });
   }
}