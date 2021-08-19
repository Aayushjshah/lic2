package finalmaven;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.Border;

    public class RoundedBorder implements Border {
        
        private int radius;
        private  Color fillRectCol;
        

        RoundedBorder(Color fillRect,int radius) {
            
            this.radius=radius;
            this.fillRectCol=fillRect;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            // insets.set(2, 2, 2, 2);
            // return insets;
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
        FontPicker fp = new FontPicker();
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(fillRectCol);
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
            g.fillRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }