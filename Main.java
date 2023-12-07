import java.awt.*; 
import javax.swing.*;
class MyFrame extends JFrame{
	int x=70, y=70; 
	int paintCnt=0;  
	Color myC[]={Color.BLUE,Color.RED, Color.YELLOW, Color.GRAY, Color.MAGENTA};   

	public MyFrame(){
		System.out.println("constructor.....");
	}
	public void paint ( Graphics g )  {
		super.paint(g);
		x=70;
		y=70;
		int c=(int)(256*Math.random());
		Color randomGray = new Color(c,c,c);
		g.setColor(randomGray);
		g.fillRect( 0, 0, getWidth(), getHeight() );
		g.setColor(myC[1]);
		g.fillRect(x+31,y,67,31);
		g.setColor(Color.BLACK);
		g.drawRect(x+31,y,67,31);
		System.out.println("paint:  " + paintCnt++);
	}
}
public class Main {
	public static void main ( String[] args )  {
		MyFrame frame = new MyFrame();
		frame.setSize( 600, 400 );
		frame.setVisible( true );
		frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}
}