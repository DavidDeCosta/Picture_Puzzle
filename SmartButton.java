import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;

public class SmartButton extends JButton
{
//===========================================DATA MEMBERS===============================================================
    Icon icon;
    //Board board;
    Image img;
    int row;
    int col;


//===========================================CONSTRUCTORS==============================================================
    SmartButton()
    {

    }
    SmartButton(Icon icon)
    {
        this.icon = icon;

    }
    SmartButton(BufferedImage bufferedImg)
    {

    }

    SmartButton(Image img, int row, int col)
    {
        this.img = img;
        this.row = row;
        this.col = col;
        setIcon(new ImageIcon(img));
    }




}
