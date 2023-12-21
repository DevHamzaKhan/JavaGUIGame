import java.awt.Color;

public class MainFrame extends javax.swing.JFrame {
    public MainFrame(){
        GamePanel gp = new GamePanel();
        gp.setLocation(0, 0);
        gp.setSize(this.getSize());
        gp.setBackground(Color.LIGHT_GRAY);
        gp.setVisible(true);
        this.add(gp);
    }
}
