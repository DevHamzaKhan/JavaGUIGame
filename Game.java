/*
Programmers: Hamza Khan & Leo Chen
Program Name: Game
Program Date: 2024-01-16
Program Description: class for the game screen
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Game extends JPanel implements KeyListener, ComponentListener {
	// declare variables
    private Character p1, p2;
	private Music backgroundMusic;
	private Platform[] platforms;
	private Timer timer;
	private GameImage background;
	private boolean[] p1KeysPressed, p2KeysPressed;
	
	public static int mode, p1Type, p2Type;
	private static boolean randomizer;
    
    // constructor
    public Game() {
		// initialize default map and characters to Default map and Ninja character
		mode = 0;
		p1Type = 0;
		p2Type = 0;
		
		initialize();
		addKeyListener(this);
		addComponentListener(this);
    }
	
	public void keyTyped(KeyEvent e) {}

	// handle key inputs
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
            p1KeysPressed[0] = true;
			
			if (p1.isOnPlatform(platforms))
				p1.jump();
        }
        else if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = true;
            p1.setState(4);
        } 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = true;
            p1.setState(0);
        }
		else if (key == KeyEvent.VK_E) {
			p1KeysPressed[4] = true;
			
			if (p1.getCanAttack()) {
				p1.setSpriteCounter(0);
				p2.takeDamage(p1.attack(), p1.getAtkDmg());
			}
		} 
        else if (key == KeyEvent.VK_Q) {
			p1KeysPressed[5] = true;
			
			if (p1.getCanShoot()) {
				p1.shoot(); 
				p1.setSpriteCounter(0);
				if (p1.getHorizontalFacing() == 0){                 
					p1.setState(6);
				}
				if (p1.getHorizontalFacing() == 1){          
					p1.setState(2);
				}
			}
		}
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = true;
			if (p2.isOnPlatform(platforms))
				p2.jump();
        } 
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = true;
            p2.setState(4);
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = true;
            p2.setState(0);
        }
		else if (key == KeyEvent.VK_O) {
			p2KeysPressed[4] = true;
			
			if (p2KeysPressed[4] && p2.getCanAttack()) {
				p2.setSpriteCounter(0);
				p1.takeDamage(p2.attack(), p2.getAtkDmg());
			}
		}
		else if (key == KeyEvent.VK_U) {
			p2KeysPressed[5] = true;
			
			if (p2KeysPressed[5] && p2.getCanShoot()) {
				p2.shoot(); 
				p2.setSpriteCounter(0);
				if (p2.getHorizontalFacing() == 0){                 
					p2.setState(6);
				}
				if (p2.getHorizontalFacing() == 1){                 
					p2.setState(2);
				}
			}
		}		
    }
	
	// handle key release
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			p1KeysPressed[0] = false;
		} 
        else if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = false;
			
            if (p1.getState() == 4)
                p1.setState(7);
        } 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = false;
			
            if (p1.getState() == 0)
                p1.setState(3);
        } 
		else if (key == KeyEvent.VK_E) {
			p1KeysPressed[4] = false;
			
			p1.setCanAttack(true);
		}
		else if (key == KeyEvent.VK_Q) {
			p1KeysPressed[5] = false;
			
			p1.setCanShoot(true);
		}
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = false;
		}
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = false;
			
            if (p2.getState() == 4)
                p2.setState(7);
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = false;
			
            if (p2.getState() == 0)
                p2.setState(3);
        }
		else if (key == KeyEvent.VK_O) {
			p2KeysPressed[4] = false;
			
			p2.setCanAttack(true);
		}
		else if (key == KeyEvent.VK_U) {
			p2KeysPressed[5] = false;
			
			p2.setCanShoot(true);
		}
    }
	
	// stop game when screen is hidden
	public void componentHidden(ComponentEvent e) {
		backgroundMusic.stop();
		timer.stop();
	}

    public void componentMoved(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {}
	
	// start game when screen is visible
    public void componentShown(ComponentEvent e) {
        this.requestFocusInWindow();
		
		initialize();
		backgroundMusic.start();
		timer.start();
    }
	
	// method for initializing game variables
	private void initialize() {
		p1KeysPressed = new boolean[6];
		p2KeysPressed = new boolean[6];
		
		// create indicated character type
		p1 = setCharacter(50, 0, 1, p1Type);
		p2 = setCharacter(800, 0, 0, p2Type);
		
		backgroundMusic = new Music("backgroundmusic.wav", -1);
		
		platforms = generatePlatforms();
		
		setMap();
		
		// initialize timer for screen updates
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
	}
	
	// method for updating 
    private void update() {
		// update characters
        p1.updateCharacter(p1KeysPressed, platforms, p2.getBullets());
		p2.updateCharacter(p2KeysPressed, platforms, p1.getBullets());
		
		// burn if fire map is active
		if (mode == 5) {
            p1.burn();
            p2.burn();
        }
		
		// check if game is over
		if (p1.getHealth() <= 0)
			Main.showCard("P2Win");
		if (p2.getHealth() <= 0)
			Main.showCard("P1Win");
    }
	
	// method for generating random map
	private Platform[] generatePlatforms() {
		Platform[] platforms = {
			// each platform in the set positions are random in whether or not they spawn
			new Platform(0, 400, 960, 20, 1, "newplatform.png"), // bottom platfoorm always active
			new Platform(90, 300, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(380, 300, 200, 10, 1, "newplatform.png"), // platform necessary for eliminating platforms that are impossible to access; always active
			new Platform(670, 300, 200, 10, 1, "newplatform.png"), // platform necessary for eliminating platforms that are impossible to access; always active
			new Platform(186, 200, 200, 10, 1, "newplatform.png"), // platform necessary for eliminating platforms that are impossible to access; always active
			new Platform(572,  200, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(90, 100, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(380, 100, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(670, 100, 200, 10, (int)Math.round(Math.random()), "newplatform.png")
		};
		
		return platforms;
	}
	
	// method for setting character to indicated type
	private Character setCharacter(int startX, int startY, int startFace, int type) {
		if (type == 1)
			return new Archer(startX, startY, startFace);
		else if (type == 2)
			return new Samurai(startX, startY, startFace);
		else
			return new Ninja(startX, startY, startFace);
	}
	
	// method for setting map
	private void setMap() {
		// boolean for keeping random map
		randomizer = mode == 0;
		
		// load corresponding backgrounds and apply effects
		switch (mode) {
			case 0:
				mode = (int)(Math.random() * 5);
			case 1: 
				background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
				break;
				
            case 2:
				background = new GameImage("SandBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
				
                p1.setSpeed(p1.getSpeed() / 2);
                p2.setSpeed(p2.getSpeed() / 2);
                break;
            case 3:
				background = new GameImage("IceBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
			
                p1.setSpeed(p1.getSpeed() * 2);
                p2.setSpeed(p2.getSpeed() * 2);
                break;
            case 4:
				background = new GameImage("SpaceBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
			
                p1.setJumpHeight(150);
                p2.setJumpHeight(150);
                break;
			case 5:
				background = new GameImage("FireBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
				break;
        }
		
		//  preserve random map
		if (randomizer)
			mode = 0;
	}
	
	// paint method
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2d = (Graphics2D) g;

        background.draw(g);

        for (Platform p : platforms) {
            if (p.getActive() == 1)
                p.draw(graphics2d);
        }
            
		p1.draw(g);
		p2.draw(g);
            
		// draw health bars
		int p1HpBar, p2HpBar;
		if (p1.getHealth() >= 0) {
			p1HpBar = 2 * p1.getHealth();
			g.setColor(Color.RED);
			g.fillRect(30, 20, 200, 30);
			g.setColor(Color.GREEN);
			g.fillRect(30, 20, p1HpBar, 30);
		}
		
		if (p2.getHealth() >= 0) {
			p2HpBar = 2 * p2.getHealth();
			g.setColor(Color.RED);
			g.fillRect(Main.WIDTH - 230, 20, 200, 30);
			g.setColor(Color.GREEN);
			g.fillRect(Main.WIDTH - p2HpBar - 30, 20, p2HpBar, 30);
		}
		
		// draw bullets
		g.setColor(Color.RED);
		for (Bullet bullet: p2.getBullets()) {
			g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
		}
		for (Bullet bullet: p1.getBullets()) {
			g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
		}
    }
}