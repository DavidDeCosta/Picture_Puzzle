import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.event.*;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.transform.Source;

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

    boolean youWin;

    int numberToDisplayMoves = -1;

    int imagesWrongPos = 0;

    JLabel labelForTotalMoves;
    JLabel labelForIncorrectPieces;
    JLabel labelForElapsedTime;
    int elapsedTime;

    Icon temp;
    SmartButton smart;

    Board()
    {
        
    }
    Board(BufferedImage bufferedImg, JPanel panelForBoard, JLabel labelForTotalMoves, JLabel labelForIncorrectPieces,JLabel labelForElapsedTime, int elapsedTime)
    {
        this.panelForBoard = panelForBoard;
        this.bufferedImg = bufferedImg;
        this.labelForTotalMoves = labelForTotalMoves;
        this.labelForIncorrectPieces = labelForIncorrectPieces;
        this.labelForElapsedTime = labelForElapsedTime;
        this.elapsedTime = elapsedTime;
        populateBoard();

        for(int i = 0; i < 2 ; i++)
        {
            shuffle();
        }

    }


    void populateBoard()
    {
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
        buttonArray[MyFrame.ROW-1][MyFrame.ROW-1].setIcon(null);                 //makes the bottom right corner the blank image

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

        youWin = false;
        numberToDisplayMoves = -1;                //so the shuffling isnt counted as moves for the player
    }

    boolean checkForWin()
    {

        for(int i = 0; i < MyFrame.ROW;i++)
        {
            for(int j = 0; j < MyFrame.ROW;j++)
            {
               if(!((buttonArray[i][j].rowForImage == buttonArray[i][j].rowforButton) && (buttonArray[i][j].colForImage == buttonArray[i][j].colForButton)))
               {
                    return false;
               }
            }
        }
        
        return true;
    }

    void checkPositions()
    {
        imagesWrongPos = 25;
        for(int i = 0; i < MyFrame.ROW;i++)
        {
            for(int j = 0; j < MyFrame.ROW;j++)
            {
               if(((buttonArray[i][j].rowForImage == buttonArray[i][j].rowforButton) && (buttonArray[i][j].colForImage == buttonArray[i][j].colForButton)))
               {
                imagesWrongPos--;   //the image is in the right spot , so its 1 less image in the wrong spot
  //              System.out.println("Images wrong Pos subtracted: " + imagesWrongPos + "  "); 
               }
               else
               {
                imagesWrongPos++;  //the image was in the wrong spot so its 1 more in the wrong spot
  //              System.out.println("Images wrong Pos added: " + imagesWrongPos + "  ");
               }
            }
        }
    }

    void swapImage()
    {
        temp = buttonArray[smart.rowforButton][smart.colForButton].getIcon();
        buttonArray[smart.rowforButton][smart.colForButton].setIcon(buttonArray[holeRow][holeColumn].icon);
        buttonArray[holeRow][holeColumn].setIcon(temp);

        //=======================STUDY THIS
        int tmp;
        tmp = smart.rowForImage;
        smart.rowForImage = buttonArray[holeRow][holeColumn].rowForImage; 
        buttonArray[holeRow][holeColumn].rowForImage = tmp;

        tmp = smart.colForImage;
        smart.colForImage = buttonArray[holeRow][holeColumn].colForImage; 
        buttonArray[holeRow][holeColumn].colForImage = tmp;
        //========================STUDY THIS


        holeRow = smart.rowforButton;                                 //Hole row is where the button that was clicked on
        holeColumn = smart.colForButton;                              //Hole column is where the button that was clicked on



    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        smart = (SmartButton)e.getSource();
        System.out.println("Smart row: " + smart.rowforButton + " Smart Col: " + smart.colForButton +" Hole Row: " + holeRow +  "  hole Col: " + holeColumn +"   ");

        //=====================================if the button clicked is adjacent to the whole swap the images =======================================================================
        if((smart.rowforButton == holeRow && smart.colForButton == (holeColumn -1)) || (smart.colForButton == holeColumn && smart.rowforButton == (holeRow -1)) || smart.rowforButton == holeRow && smart.colForButton == (holeColumn + 1) 
            || smart.colForButton == holeColumn && smart.rowforButton == (holeRow +1))
        {
            swapImage();
            checkPositions();
            youWin = checkForWin();
            System.out.println("Images wrong pos: " + (imagesWrongPos / 2));
            labelForIncorrectPieces.setText("Incorrect Pieces: " + (imagesWrongPos/2));
            if(youWin == true)
            {
                int response;
                response = JOptionPane.showConfirmDialog(null, "Play Again?", " YOU WON! ", JOptionPane.YES_NO_OPTION);
                if(JOptionPane.NO_OPTION == response)
                {
                System.out.println("Do nothing");
                }
                else
                {
                    for(int i = 0; i < 3; i++)
                    {
                        shuffle();
                        elapsedTime = 0;
                        labelForElapsedTime.setText("Elapsed Time: " + elapsedTime);
                    }
                }
            }
            numberToDisplayMoves++;
            
        }
        else
        {
            System.out.println("Do not perform swap!!!! \n");
        }

        labelForTotalMoves.setText("Number of moves: " + numberToDisplayMoves);  //everytime the user clicks an image it increments the moves by 1.


    }
}
