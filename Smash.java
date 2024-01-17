/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Platformer game where 2 characters can move around and jump onto platforms. There are multiple different maps with effects.
*/

// "write one comment"

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Smash extends JPanel implements KeyListener, ComponentListener{
    //Character
    Character p1, p2;
	Music backgroundMusic;
	Platform[] platforms;
	Timer timer;
	GameImage background;
	
	public boolean[] p1KeysPressed, p2KeysPressed;
	public static int mode, p1Type, p2Type;
	static boolean randomizer;
    
    //Base Constructor
    public Smash() {
		mode = 0;
		p1Type = 0;
		p2Type = 0;
		
		initialize();

		addKeyListener(this);
		addComponentListener(this);
    }
	
	public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        handleKeyPress(e);
    }
			
	public void keyReleased(KeyEvent e) {
        handleKeyRelease(e);
    }
    
    public void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
            p1KeysPressed[0] = true;
			
			if (p1.isOnPlatform(platforms))
				p1.jump();
        }
        else if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = true;
            p1.state = "left";
        } 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = true;
            p1.state = "right";
        }
		else if (key == KeyEvent.VK_E) {
			p1KeysPressed[4] = true;
			
			if (p1.canAttack) {
				p1.spriteCounter = 0;
				p2.takeDamage(p1.attack(), p1.atkDmg);
			}
		} 
        else if (key == KeyEvent.VK_Q) {
			p1KeysPressed[5] = true;
			
			if (p1.canShoot) {
				p1.shoot(); 
				p1.spriteCounter = 0;
				if (p1.horizontalFacing == 0){                 
					p1.state = "shootL";
				}
				if (p1.horizontalFacing == 1){          
					p1.state = "shootR";
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
            p2.state = "left";
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = true;
            p2.state = "right";
        }
		else if (key == KeyEvent.VK_O) {
			p2KeysPressed[4] = true;
			
			if (p2KeysPressed[4] && p2.canAttack) {
				p2.spriteCounter = 0;
				p1.takeDamage(p2.attack(), p2.atkDmg);
			}
		}
		else if (key == KeyEvent.VK_U) {
			p2KeysPressed[5] = true;
			
			if (p2KeysPressed[5] && p2.canShoot) {
				p2.shoot(); 
				p2.spriteCounter = 0;
				if (p2.horizontalFacing == 0){                 
					p2.state = "shootL";
				}
				if (p2.horizontalFacing == 1){                 
					p2.state = "shootR";
				}
			}
		}		
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			p1KeysPressed[0] = false;
		} 
        else if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = false;
			
            if (p1.state == "left")
                p1.state = "idleL";
        } 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = false;
			
            if (p1.state == "right")
                p1.state = "idleR";
        } 
		else if (key == KeyEvent.VK_E) {
			p1KeysPressed[4] = false;
			
			p1.canAttack = true;
		}
		else if (key == KeyEvent.VK_Q) {
			p1KeysPressed[5] = false;
			
			p1.canShoot = true;
		}
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = false;
		}
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = false;
			
            if (p2.state == "left")
                p2.state = "idleL";
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = false;
			
            if (p2.state == "right")
                p2.state = "idleR";
        }
		else if (key == KeyEvent.VK_O) {
			p2KeysPressed[4] = false;
			
			p2.canAttack = true;
		}
		else if (key == KeyEvent.VK_U) {
			p2KeysPressed[5] = false;
			
			p2.canShoot = true;
		}
    }
	
	public void componentHidden(ComponentEvent e) {
		backgroundMusic.stop();
		timer.stop();
	}

    public void componentMoved(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {
        this.requestFocusInWindow();
		
		initialize();
		backgroundMusic.start();
		timer.start();
    }
	
	public void initialize() {
		p1KeysPressed = new boolean[6];
		p2KeysPressed = new boolean[6];

		p1 = setCharacter(50, 0, 1, p1Type);
		p2 = setCharacter(800, 0, 0, p2Type);
		
		backgroundMusic = new Music("backgroundmusic.wav", -1);
		
		platforms = generatePlatforms();
		
		setMap();

        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
	}

    public void update() {
		// Movement control
        p1.updateCharacter(p1KeysPressed, platforms, p2.bullets);
		p2.updateCharacter(p2KeysPressed, platforms, p1.bullets);

		if (p1.getHealth() <= 0) {
			Main.showCard("P2Win");
		}
		if (p2.getHealth() <= 0) {
			Main.showCard("P1Win");
		}
    }
	
	public Platform[] generatePlatforms() {
		Platform[] platforms = {
			new Platform(0, 400, 960, 20, 1, "newplatform.png"),
			new Platform(90, 300, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(380, 300, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(670, 300, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(186, 200, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(572,  200, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(90, 100, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(380, 100, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
			new Platform(670, 100, 200, 10, (int)Math.round(Math.random()), "newplatform.png")
		};
		
		return platforms;
	}
	
	public Character setCharacter(int startX, int startY, int startFace, int type) {
		if (type == 1)
			return new Archer(startX, startY, startFace);
		else if (type == 2)
			return new Samurai(startX, startY, startFace);
		else
			return new Ninja(startX, startY, startFace);
	}
	
	public void setMap() {
		randomizer = mode == 0;
		
		switch (mode) {
			case 0:
				mode = (int)(Math.random() * 5);
			case 1: 
				background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
				break;
				
            case 2:
				background = new GameImage("SandBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
				
                p1.speed /= 2;
                p2.speed /= 2;
                break;
            case 3:
				background = new GameImage("IceBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
			
                p1.speed *= 2;
                p2.speed *= 2;
                break;
            case 4:
				background = new GameImage("SpaceBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
			
                p1.jumpHeight = 150;
                p2.jumpHeight = 150;
                break;
			case 5:
				background = new GameImage("FireBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
				break;
        }
		
		if (randomizer) {
			mode = 0;
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2d = (Graphics2D) g;

        background.draw(g);

        for (Platform p : platforms) {
            if (p.active == 1){
                p.draw(graphics2d);
			}
        }

            
		p1.draw(g);
		p2.draw(g);
            
            
		// Draw health bars
		if (p1.health >= 0) {
			int hpBarWidth = 2 * p1.health;
			g.setColor(Color.RED);
			g.fillRect(30, 20, 200, 30);
			g.setColor(Color.GREEN);
			g.fillRect(30, 20, hpBarWidth, 30);
		}
		if (p2.health >= 0) {
			int hpBarWidth = 2 * p2.health;
			g.setColor(Color.RED);
			g.fillRect(Main.WIDTH - 200 - 30, 20, 200, 30);
			g.setColor(Color.GREEN);
			g.fillRect(Main.WIDTH - hpBarWidth - 30, 20, hpBarWidth, 30);
			
		}
			
		g.setColor(Color.RED);
		for (Bullet bullet: p2.bullets) {
			g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
		}
		for (Bullet bullet: p1.bullets) {
			g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
		}
    }
}