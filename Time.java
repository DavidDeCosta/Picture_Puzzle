
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class Time extends Timer
{
    TimerTask task;
    int timePassed =0;
    int elapsedTime =0;
    boolean isPaused = false;
    JLabel labelForElapsedTime;

    Time(int elapsedTime,JLabel labelForElapsedTime)
    {
        this.elapsedTime = elapsedTime;
        this.labelForElapsedTime = labelForElapsedTime;
    }

    void handleTime()
    {
        new Timer(false);
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
        scheduleAtFixedRate(task, 1000, 1000);
    }
    public void pause()
    {
        isPaused = true;
        timePassed = elapsedTime;
        cancel();

    }
    public void resume() 
    {
        new Timer();
        scheduleAtFixedRate( task, 1000, 1000 );

        
    }
    
    void stopTimer()
    {
        cancel();
    }
}
