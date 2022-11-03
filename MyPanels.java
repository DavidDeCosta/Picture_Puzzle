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
        g.drawImage(originalImage, ALLBITS, ABORT, getFocusCycleRootAncestor());
    }
}
