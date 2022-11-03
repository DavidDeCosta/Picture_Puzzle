import java.awt.*;

import javax.imageio.*;  //for imageIO
import javax.swing.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MyFrame extends JFrame
                        implements ActionListener
{
//==============================================DATA MEMBERS====================================================================================
Toolkit toolkit;
Dimension screenSize;

JButton exit;
JButton newImage;
JButton playAndPause;

JPanel displayPuzzle[][];
JPanel displayOriginalImage;
JPanel southPanel;

JLabel labelForTotalMoves;
JLabel labelForElapsedTime;
JLabel labelForIncorrectPieces;

int numberOfMoves = 0;
int incorrectPieces;
double elapsedTime;

JFileChooser theFileChooser;
BufferedImage bufferedImg;
ImageIcon imageIcon;
ImageIcon originalImage;

JLabel labelForPauseImage;

JPanel panelForGrid;

Buttons buttons;


public static final int ROW = 10;


//==============================================CONSTRUCTOR================================================================================    
    MyFrame()
    {
        theFileChooser = new JFileChooser(".");


        addComponents();
        buildMainFrame();
    }

//===============================================METHODS=======================================================================================
    void addComponents()
    {
        southPanel = new JPanel();
        southPanel.setBackground(Color.GRAY);
        southPanel.setPreferredSize(new Dimension(100,30));
        add(southPanel, BorderLayout.SOUTH);

        displayOriginalImage = new JPanel();

        exit = new JButton("Exit");
        exit.addActionListener(this);

        newImage = new JButton("New_Image");
        newImage.addActionListener(this);

        playAndPause = new JButton("Play");
        playAndPause.addActionListener(this);

        labelForTotalMoves = new JLabel("Number of moves: " + numberOfMoves);

        labelForElapsedTime = new JLabel("Elapsed Time: " + elapsedTime);

        labelForIncorrectPieces = new JLabel("Incorrect Pieces: " + incorrectPieces);




        panelForGrid = new JPanel();
        displayPuzzle = new JPanel[ROW][ROW];
        panelForGrid.setLayout(new GridLayout(ROW,ROW));

        for(int i = 0;i < ROW; i++)
        {
            for(int j = 0; j < ROW;j++)
            {
                displayPuzzle[i][j] = new JPanel();
                panelForGrid.add(displayPuzzle[i][j]);
            }
        }


        int numnum = 0;
        for(int i = 0;i < ROW; i++)
        {
            for(int j = 0; j < ROW;j++)
            {
                displayPuzzle[i][j].add(new JLabel(Integer.toString(numnum)));
                numnum++;
            }
        }


        add(panelForGrid,BorderLayout.CENTER);

        southPanel.add(labelForElapsedTime);
        southPanel.add(labelForIncorrectPieces);
        southPanel.add(labelForTotalMoves);
        southPanel.add(playAndPause);
        southPanel.add(newImage);
        southPanel.add(exit);

    }

    void buildMainFrame()
    {
        toolkit = Toolkit.getDefaultToolkit();                                      // used to help get the users screen size
        screenSize = toolkit.getScreenSize();                                       //get the users screen size
        setSize((screenSize.width/2 + 70), (screenSize.height/2 + 70));           
        setLocationRelativeTo(null);                                              // window is placed in the center of screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             //when close frame the program stops
        setTitle("Picture Puzzle");
        setVisible(true);
    }

    void handleNewImage()
    {
        int fileChooser;
        
        fileChooser = theFileChooser.showOpenDialog(this);
        if(fileChooser == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                bufferedImg = ImageIO.read(theFileChooser.getSelectedFile());

                int width;
                int height;
                int heightOfRegion;
                
                width = bufferedImg.getWidth();
                height = bufferedImg.getHeight();
                if(width > height)
                {
                    heightOfRegion = (width-height)/2;
                    bufferedImg = bufferedImg.getSubimage((width-height)/2, 0, heightOfRegion, heightOfRegion);
                     
                }
                else if(width < height)
                {
                    heightOfRegion = (height-width)/2;
                    bufferedImg = bufferedImg.getSubimage((height-width)/2, 0, heightOfRegion, heightOfRegion);
                }


                imageIcon = new ImageIcon(bufferedImg);         //going to shuffle around
                buttons = new Buttons(imageIcon);

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Error, could not load");
            }
        }
        else
        {
            System.out.println("Do nothing.");
        }
        

    }

//=============================================IMPLEMENTED METHODS==================================================================================
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Exit"))
        {
            this.dispose();
        }
        else if(e.getActionCommand().equals("New_Image"))
        {
            handleNewImage();
        }
        else if(e.getActionCommand().equals("Play"))
        {
            playAndPause.setText(("Pause"));
        }
        else if(e.getActionCommand().equals("Pause"))
        {
//            labelForPauseImage = new JLabel();
//            labelForPauseImage.setIcon(originalImage);
//            displayOriginalImage.add(labelForPauseImage);
//            add(displayOriginalImage,BorderLayout.CENTER);
            displayOriginalImage = new MyPanels(bufferedImg);
            add(displayOriginalImage,BorderLayout.CENTER);

            playAndPause.setText("Play");
        }
    }

}


