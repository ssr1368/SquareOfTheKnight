


import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Basics {
    
    public static void delay(){
        try {Thread.sleep(30);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void delay(int x)
    {
        try {Thread.sleep(x);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void saveScreen(Rectangle rect)
    {
        java.awt.Robot a;
        try {
            a = new java.awt.Robot();
            Image image = a.createScreenCapture(rect);
            try {
                //ImageIO.write((RenderedImage) image, "png", new File("../fileName.png"));
                ImageIO.write((RenderedImage) image, "png", new File("src/Resources/img/temp/fileName.png"));
            } catch (IOException ex) {
                //Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (AWTException ex) {
            //Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Image img(String name)
    {
        return new ImageIcon(SquareOfTheKnight.class.getResource("resources/img/"+name)).getImage();
    }
}