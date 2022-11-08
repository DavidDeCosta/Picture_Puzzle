import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.event.*;
import javax.lang.model.util.ElementScanner14;
import javax.swing.Icon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.*;

public class Board
                    implements ActionListener
{

    SmartButton buttonArray[][];
    JPanel panelForBoard;
    BufferedImage bufferedImg;
    Image img;
    SmartButton smartButton;



    Board()
    {
        
    }
    Board(BufferedImage bufferedImg, JPanel panelForBoard)
    {
        this.panelForBoard = panelForBoard;
        this.bufferedImg = bufferedImg;
        populateBoard();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
        shuffle();
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
                buttonArray[row][col].addActionListener(this);
                panelForBoard.add(buttonArray[row][col]);
            }
        }
        buttonArray[MyFrame.ROW-1][MyFrame.ROW-1].setIcon(null);
    }

    void shuffle()
    {
        int z;
        int holeColumn = MyFrame.ROW-1;
        int holeRow = MyFrame.ROW-1;
        Random rand;
        rand = new Random();
        z = rand.nextInt(4);

        if(z == 0 && holeColumn !=0)
        {
            buttonArray[holeRow][holeColumn-1].doClick();
            System.out.println("clicked here 1st!");
            holeColumn = holeColumn -1;
        }
        else if(z == 1 && holeRow !=0)
        {
            buttonArray[holeRow-1][holeColumn].doClick();
            System.out.println("clicked here 2nst!");
            holeRow = holeRow -1;
        }
        else if(z == 2 && holeColumn != MyFrame.ROW -1)
        {
            buttonArray[holeRow][holeColumn+1].doClick();
            System.out.println("clicked here 3trdst!");
            holeColumn = holeColumn +1;
        }
        else if(z == 3 && holeRow != MyFrame.ROW-1)
        {
            buttonArray[holeRow+1][holeColumn].doClick();
            System.out.println("clicked here 4thst!");
            holeRow = holeRow + 1;
        }
        else
        {
            z = rand.nextInt(4);
        }



    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        smartButton = new SmartButton();
        smartButton.swapImage(img, img);
        System.out.println("im hereeerereeee! \n");

    }
}
