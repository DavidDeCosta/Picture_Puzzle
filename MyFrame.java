import java.awt.*;
import javax.imageio.*;  //for imageIO
import javax.swing.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;
//import javax.swing.Timer;
import java.util.Timer;
import java.util.TimerTask;

public class MyFrame extends JFrame
                        implements ActionListener
{
//==============================================DATA MEMBERS====================================================================================
Toolkit toolkit;
Dimension screenSize;

JButton exit;
JButton newImage;
JButton playAndPause;


MyPanels panelForOrigImg;         // the main picture to cover up when pause is clicked
JPanel southPanel;              //for buttons on bottom
JPanel panelForGrid;            //for the smart buttons

ImageIcon imageIcon;            //used to create the SmartButtons

JLabel labelForTotalMoves;
JLabel labelForElapsedTime;
JLabel labelForIncorrectPieces;

int numberOfMoves = 0;
int incorrectPieces;
int elapsedTime =0;

JFileChooser theFileChooser;
BufferedImage bufferedImg;

JLabel labelForPauseImage;

Board board;

Timer timer;
TimerTask task;
int timePassed =0;

boolean isPaused = false;


public static final int ROW = 5;


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





        panelForGrid = new MyPanels();
        
        panelForGrid.setLayout(new GridLayout(ROW,ROW));


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
            
                    bufferedImg = bufferedImg.getSubimage(0, (height-width)/2, width,width );
                }



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

    void handleTime()
    {
        timer = new Timer(false);
        elapsedTime = timePassed;
        task = new TimerTask() 
        {
            public void run()
            {
                isPaused =false;
                elapsedTime++;
                labelForElapsedTime.setText("Elapsed Time: " + elapsedTime);
            }
        };

    }
    public void start()
    {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
    public void pause()
    {
        isPaused = true;
        timePassed = elapsedTime;
        timer.cancel();

    }
    public void resume() 
    {
        timer = new Timer();
        timer.scheduleAtFixedRate( task, 1000, 1000 );
    }

//=============================================IMPLEMENTED METHODS==================================================================================
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Exit"))
        {
        //    this.dispose();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("New_Image"))
        {
            handleNewImage();
            panelForOrigImg = new MyPanels(bufferedImg);
            board = new Board(bufferedImg,panelForGrid,labelForTotalMoves,labelForIncorrectPieces,labelForElapsedTime,elapsedTime);
            add(panelForOrigImg,BorderLayout.CENTER);
            panelForOrigImg.setVisible(true);
            panelForGrid.setVisible(false);
        }
        else if(e.getActionCommand().equals("Play"))
        {
            panelForGrid.setVisible(true);
            panelForOrigImg.setVisible(false);
            playAndPause.setText(("Pause"));
            
            handleTime();
            start();

        }
        else if(e.getActionCommand().equals("Pause"))
        {

            add(panelForOrigImg,BorderLayout.CENTER);
            panelForOrigImg.setVisible(true);
            panelForGrid.setVisible(false);
            playAndPause.setText("Play");

            pause();
        }

    }

}


