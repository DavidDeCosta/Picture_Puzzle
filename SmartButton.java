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
    BufferedImage img;
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

    SmartButton(BufferedImage img, int row, int col)
    {
        this.img = img;
        this.row = row;
        this.col = col;
        setIcon(new ImageIcon(img));
    }

    void swapImage(SmartButton button)
    {
        BufferedImage temp;

        temp = button.img;
        button.img = this.img;  //makes the button that was clicked the blank image
        this.img = temp;        //make the blank spot the image of the button that was passed in
    }


}
