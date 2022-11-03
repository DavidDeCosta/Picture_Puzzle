import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.*;

public class Board 
{

    SmartButton buttonArray[][];
    JPanel panelForBoard;
    BufferedImage bufferedImg;
    Image img;


    Board()
    {
        
    }
    Board(BufferedImage bufferedImg, JPanel panelForBoard)
    {
        this.panelForBoard = panelForBoard;
        this.bufferedImg = bufferedImg;
        populateBoard();
    }


    void populateBoard()
    {

//        panelForBoard.setLayout(new GridLayout[MyFrame.ROW][MyFrame.ROW]);
        buttonArray = new SmartButton[MyFrame.ROW][MyFrame.ROW];
        int b;
        b = bufferedImg.getWidth() / MyFrame.ROW;

        for(int row = 0;row < MyFrame.ROW; row++)
        {
            for(int col = 0; col < MyFrame.ROW;col++)
            {
                
                img = bufferedImg.getSubimage(col*(b), row*(b), b, b);
                buttonArray[row][col] = new SmartButton(img,row,col);
                panelForBoard.add(buttonArray[row][col]);
                System.out.println("Number: " + row);
            }
        }
        buttonArray[MyFrame.ROW-1][MyFrame.ROW-1].setIcon(null);
    }

    void shuffle()
    {

    }
}
