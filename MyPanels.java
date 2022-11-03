import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class MyPanels extends JPanel
{

    BufferedImage originalImage;

    MyPanels()
    {

    }

    MyPanels(BufferedImage originalImage)
    {
        this.originalImage = originalImage;
    }



    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(originalImage, 0,0, null);
    }
}
