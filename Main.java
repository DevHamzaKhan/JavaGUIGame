import java.awt.*;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements Runnable,KeyListener{

	private JPanel contentPane;
    int x=300,y=50,width=50,height=80;
    int velx=0;
    Rectangle body=new Rectangle(x,y,width,height);
	Thread thread;Graphics2D g2;
	boolean running=false;
	int frameRate=50;
	int key;boolean jump=false;
	int lastY=y;
	Random r=new Random();
	int bWidth=100,bHieght=20;
	int [][]block=new int[1000][2];
	Rectangle boundary=new Rectangle(-200,0,1000,600);
	Rectangle rect=new Rectangle();
	Rectangle lava=new Rectangle(0,450,800,150);
	Rectangle out=new Rectangle(-200,0,200,600);
	Rectangle up=new Rectangle(0,0,800,10);
	int m=0;int health=150;
	int c=0;int score=0;
	Font sf=new Font("Monospaced",Font.BOLD,30);
	Font f=new Font("Monospaced",Font.BOLD,50);
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.addKeyListener(this);
		contentPane.setFocusable(true);
		setContentPane(contentPane);
		
		block[m][0]=300;
		block[m][1]=200;
		m++;
	}

	public void start() {
		running=true;
		thread=new Thread(this);
		thread.start();
	}
	public int random(int a,int b)
	{	
		return ((r.nextInt((b-a)/10)*10)+a);
	}
	public void generate() {
		block[m][0]=block[m-1][0]+150;
		block[m][1]=random(250,300);
		m++;
		block[m][0]=block[m-1][0]+150;
		block[m][1]=random(150,200);
		m++;
		block[m][0]=block[m-1][0]+150;
		block[m][1]=random(250,300);
		m++;
		block[m][0]=block[m-1][0]+150;
		block[m][1]=random(350,400);
		m++;
		block[m][0]=block[m-1][0]+150;
		block[m][1]=random(250,300);
		m++;
		
		if(m>=1000) {
			m=1;
		}
				
	}
	
	boolean isBounded(int a,int b,int c,int d) {
		rect.setBounds(a,b,c,d);
		if(boundary.contains(rect))
			return true;
		return false;
	}
	
	boolean hasNotCollided(int a,int b,int c,int d) {
		rect.setBounds(a,b,c,d);
		if(m>20)
		{
			c=m-20;
		}
		else {
			c=0;
		}
		for(;c<m;c++)
		{	
			if(rect.intersects(block[c][0], block[c][1], bWidth,bHieght)) {			
				return false;
			}
				
		}
		return true;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		generate();
		   while(running) {
			   
			//if(!hasNotCollided(body.x,body.y+20,body.width,body.height))
				//downCollided=false;
				//else
				//downCollided=true;
			update();
			if(score==50)
				{
				frameRate=60;
				}
			
			healthReducer();
			repaint();
		
			if(block[m-1][0]<800)
				{generate();score++;}
			try {
				thread.sleep(1000/frameRate);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void paint(Graphics g) {
		g2=(Graphics2D)g;
		g2.fillRect(0, 0, 800, 600);
		g2.setColor(Color.RED);
		g2.fill(body);
		g2.setColor(Color.ORANGE);
		g2.fill(lava);
		g2.setColor(Color.GRAY);
		if(m>20)
		{
			c=m-20;
		}
		else {
			c=0;
		}
		for(;c<m;c++)
		{	if(body.intersects(block[c][0]-10, block[c][1], bWidth,bHieght)) {
			 x-=10;health--;
			 }else	    
			block[c][0]-=10;
			g2.fillRect(block[c][0], block[c][1], bWidth,bHieght);
		}
		g2.setColor(Color.GREEN);
		g2.fill(up);
		g2.setColor(Color.WHITE);
		g2.drawRect(600,50,150,20);
		g2.fillRect(600,50,health,20);
		g2.setFont(sf);
		g2.drawString("Score : "+score, 60, 60);
		if(health<=0) {
			g2.setFont(f);
			g2.drawString("You Dead !",110,120);
			g2.setFont(sf);
			g2.drawString("Press 'R' to retry .",210,170);
			g2.drawString("Press 'ESCAPE' to exit .",210,210);
			running=false;
		}
	}
	public void update() {
		//jump
		if(jump) {
			if(lastY-y<=150) {
				if(isBounded(body.x,body.y-10,body.width,body.height)&&hasNotCollided(body.x,body.y-10,body.width,body.height))
				y-=20;
				else {
					jump=false;
				}
			}else {
				jump=false;
			}
		}
		//gravity
		if(isBounded(body.x,body.y+10,body.width,body.height)&&hasNotCollided(body.x,body.y+10,body.width,body.height))
		y+=10;
		//else
			//downCollided=true;
		//update
		body.setLocation(x,y);
	}
	public void healthReducer() {
		if(lava.contains(body))
			health-=50;
		if(out.contains(body))
			health-=50;
		if(up.intersects(body))
			health-=2;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		key=e.getKeyCode();
        if(key==KeyEvent.VK_UP&&!jump) {
        	jump=true;
        	lastY=y;
        }
        if(key==KeyEvent.VK_R) {
        	main(null);
        }
        if(key==KeyEvent.VK_ESCAPE) {
        	running=false;
        	System.exit(0);
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}