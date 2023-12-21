import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.event.ActionEvent;

public class GamePanel extends javax.swing.JPanel implements ActionListener{
    Player player1;

    Timer gameTimer;

    public GamePanel(){
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){

            @Override
            public void run(){   
            
            }
        }, 0, 17);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    } 
}