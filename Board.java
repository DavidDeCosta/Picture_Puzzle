import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.event.*;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board
                    implements ActionListener
{

    SmartButton originalConfiguration[][];

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

    boolean youWin = false;

    int numberToDisplayMoves = -1;

    JLabel labelForTotalMoves;

    Board()
    {
        
    }
    Board(BufferedImage bufferedImg, JPanel panelForBoard, JLabel labelForTotalMoves)
    {
        this.panelForBoard = panelForBoard;
        this.bufferedImg = bufferedImg;
        this.labelForTotalMoves = labelForTotalMoves;
        populateBoard();

        for(int i = 0; i < 5 ; i++)
        {
            shuffle();
        }

    }


    void populateBoard()
    {
        buttonArray = new SmartButton[MyFrame.ROW][MyFrame.ROW];
        originalConfiguration = new SmartButton[MyFrame.ROW][MyFrame.ROW];
        int b;
        b = bufferedImg.getWidth() / MyFrame.ROW;

        for(int row = 0;row < MyFrame.ROW; row++)
        {
            for(int col = 0; col < MyFrame.ROW;col++)
            {
                
                img = bufferedImg.getSubimage(col*(b), row*(b), b, b);
                buttonArray[row][col] = new SmartButton(img,row,col);
                originalConfiguration[row][col] = buttonArray[row][col];   //original cofig has the same smartbutton as buttonarray
                buttonArray[row][col].addActionListener(this);
                panelForBoard.add(buttonArray[row][col]);
            }
        }
        buttonArray[MyFrame.ROW-1][MyFrame.ROW-1].setIcon(null);                 //makes the bottom right corner the blank image
        originalConfiguration[MyFrame.ROW-1][MyFrame.ROW-1].setIcon(null);

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

        }
        else if(z == 1 && holeRow !=0)
        {
            x = holeRow -1;
            y = holeColumn;
            buttonArray[holeRow-1][holeColumn].doClick();
        }
        else if(z == 2 && holeColumn != (MyFrame.ROW -1))
        {
            x = holeRow;
            y = holeColumn +1;
            buttonArray[holeRow][holeColumn+1].doClick();
        }
        else if(z == 3 && holeRow != (MyFrame.ROW-1))
        {
            x = holeRow +1;
            y = holeColumn;
            buttonArray[holeRow+1][holeColumn].doClick();
        }
        else
        {
           shuffle();
        }

        numberToDisplayMoves = -1;                //so the shuffling isnt counted as moves for the player
    }

    void checkForWin()
    {
        for(int i = 0; i < MyFrame.ROW;i++)
        {
            for(int j = 0; j < MyFrame.ROW;j++)
            {
                if(originalConfiguration[i][j].img.equals(buttonArray[i][j].img))
                {
                    youWin =true;
                }
                else
                {
                    youWin = false;
                }
               
            }
        }

        if(youWin == true)
        {
            System.out.println("YOU WIN! ");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        SmartButton smart;
        smart = (SmartButton)e.getSource();



        Icon temp;
        temp = buttonArray[smart.row][smart.col].getIcon();                               //these 3 lines are reponsible for swapping the images.

        if((smart.row == holeRow && smart.col == (holeColumn -1)) || (smart.col == holeColumn && smart.row == (holeRow -1)) || smart.row == holeRow && smart.col == (holeColumn + 1) 
            || smart.col == holeColumn && smart.row == (holeRow +1))
        {
            checkForWin();
        buttonArray[smart.row][smart.col].setIcon(buttonArray[holeRow][holeColumn].icon);
        buttonArray[holeRow][holeColumn].setIcon(temp);
        
        System.out.println("Smart row: " + smart.row + " Hole Row: " + holeRow + " Smart Col: " + smart.col + "  hole Col: " + holeColumn +"   ");


        holeRow = smart.row;                                                                //updates where the hole is
        holeColumn = smart.col;                                                             //updates where the hole is


        }
        else
        {
            System.out.println("Do not perform swap!!!! \n");
        }

        numberToDisplayMoves++;
        labelForTotalMoves.setText("Number of moves: " + numberToDisplayMoves);  //everytime the user clicks an image it increments the moves by 1.

        x = smart.col;
        y=smart.row;
     //   System.out.println("x value: " + x + " |  y value: " + y);

    }
}
