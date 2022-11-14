import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class Time
{
    Timer timer;
    TimerTask task;
    int timePassed =0;
    int elapsedTime =0;
    JLabel labelForElapsedTime;


    Time(JLabel labelForElapsedTime)
    {
        this.labelForElapsedTime = labelForElapsedTime;
    }

    void handleTime()
    {
        timer = new Timer(false);
        elapsedTime = timePassed;
        task = new TimerTask() 
        {
            public void run()
            {
                elapsedTime++;
                labelForElapsedTime.setText("Elapsed Time: " + elapsedTime);
            }
        };
    }

    void start()
    {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
    void pause()
    {
        timePassed = elapsedTime;
        timer.cancel();
    }
    void cancel()
    {
        timer.cancel();
    }

    void restart()
    {
        
    }

}
