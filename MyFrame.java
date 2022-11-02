import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;
import java.io.*;

public class MyFrame extends JFrame
                        implements ActionListener
{
//==============================================DATA MEMBERS====================================================================================
Toolkit toolkit;
Dimension screenSize;

JButton exit;
JButton newImage;
JButton playAndPause;

JPanel displayPuzzle;
JPanel displayOriginalImage;
JPanel southPanel;

JLabel labelForTotalMoves;
JLabel labelForElapsedTime;
JLabel labelForIncorrectPieces;

int numberOfMoves = 0;
int incorrectPieces;
double elapsedTime;

JFileChooser theFileChooser;

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

        exit = new JButton("Exit");
        exit.addActionListener(this);

        newImage = new JButton("New_Image");
        newImage.addActionListener(this);

        playAndPause = new JButton("Play");
        playAndPause.addActionListener(this);

        labelForTotalMoves = new JLabel("Number of moves: " + numberOfMoves);

        labelForElapsedTime = new JLabel("Elapsed Time: " + elapsedTime);

        labelForIncorrectPieces = new JLabel("Incorrect Pieces: " + incorrectPieces);

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
        theFileChooser.showOpenDialog(this);
        

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
            playAndPause.setText("Play");
        }
    }

}


