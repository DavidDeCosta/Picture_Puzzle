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
    BufferedImage img;
    SmartButton smartButton;

    SmartButton hole;

    
    int holeColumn = MyFrame.ROW-1;
    int holeRow = MyFrame.ROW-1;

    int x;
    int y;

    Board()
    {
        
    }
    Board(BufferedImage bufferedImg, JPanel panelForBoard)
    {
        this.panelForBoard = panelForBoard;
        this.bufferedImg = bufferedImg;
        populateBoard();
    //    hole = new SmartButton();
 //       hole = buttonArray[MyFrame.ROW-1][MyFrame.ROW-1]; //where the hole is

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
        buttonArray[0][0].swapImage(buttonArray[2][2]);
    }

    void shuffle()
    {
        int z;

        Random rand;
        rand = new Random();
        z = rand.nextInt(4);

        if(z == 0 && holeColumn !=0)
        {
            x = holeRow;
            y = holeColumn -1;
            buttonArray[holeRow][holeColumn-1].doClick();
            System.out.println("clicked here 1st!");
            holeColumn = holeColumn -1;
//            hole = buttonArray[holeRow][holeColumn-1];  //update where the hole button is

        }
        else if(z == 1 && holeRow !=0)
        {
            x = holeRow -1;
            y = holeColumn;
            buttonArray[holeRow-1][holeColumn].doClick();
            System.out.println("clicked here 2nd!");
            holeRow = holeRow -1;
//            hole = buttonArray[holeRow-1][holeColumn];
        }
        else if(z == 2 && holeColumn != MyFrame.ROW -1)
        {
            x = holeRow;
            y = holeColumn +1;
            buttonArray[holeRow][holeColumn+1].doClick();
            System.out.println("clicked here 3rd!");
            holeColumn = holeColumn +1;
//            hole = buttonArray[holeRow][holeColumn+1];
        }
        else if(z == 3 && holeRow != MyFrame.ROW-1)
        {
            x = holeRow +1;
            y = holeColumn;
            buttonArray[holeRow+1][holeColumn].doClick();
            System.out.println("clicked here 4th!");
            holeRow = holeRow + 1;
 //           hole = buttonArray[holeRow+1][holeColumn];
        }
        else
        {
            z = rand.nextInt(4);
        }



    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        SmartButton smart;
        smart = (SmartButton)e.getSource();
    buttonArray[holeRow][holeColumn].swapImage(buttonArray[smart.row][smart.col]);
//        hole = buttonArray[x][y];
        System.out.println(x + "   " + y);
        System.out.println("im in action performed! \n");

    }
}
