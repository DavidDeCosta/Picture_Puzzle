import java.awt.image.BufferedImage;

import javax.swing.*;

public class SmartButton extends JButton
{
//===========================================DATA MEMBERS===============================================================
    Icon icon;
    BufferedImage img;
    
    int rowforButton;
    int colForButton;

    int rowForImage;
    int colForImage;

//===========================================CONSTRUCTORS==============================================================
    SmartButton()
    {

    }

    SmartButton(BufferedImage img, int row, int col)
    {
        this.img = img;
        this.rowforButton = row;
        this.colForButton = col;
        
        this.rowForImage = row;
        this.colForImage = col;
        setIcon(new ImageIcon(img));
    }

}
