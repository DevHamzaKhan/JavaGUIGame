/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Platformer game where 2 characters can move around and jump onto platforms. There are multiple different maps with effects.
*/

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Smash extends JFrame {
    public SmashPanel smashPanel;
    public boolean[] p1KeysPressed = new boolean[4]; // [W, A, S, D, E]
	public boolean[] p2KeysPressed = new boolean[4]; // [I, J, K, L, O]
    public int screen_width = 960;
    public int screen_height = 520;
    public int mode = (int)(Math.random() * 5);
    

    //Character
    Character p1 = new Character(50, 0, 1, 5, 5, 5);
	Character p2 = new Character(250, 0, 0, 5, 5, 5);
    GameImage [] backgrounds = {
        new GameImage("FireBackground.png", 0, 0, screen_width, screen_height, false),
        new GameImage("SandBackground.png", 0, 0, screen_width, screen_height, false),
        new GameImage("IceBackground.png", 0, 0, screen_width, screen_height, false),
        new GameImage("SpaceBackground.png", 0, 0, screen_width, screen_height, false)
    };
    SpriteImage [] character_runR = {
        new SpriteImage("RunAnimation/tile000.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("RunAnimation/tile001.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("RunAnimation/tile002.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("RunAnimation/tile003.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("RunAnimation/tile004.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("RunAnimation/tile005.png", p1.x, p1.y, 96, 96, false, p1)
    };
    SpriteImage [] character_jumpR = {
        new SpriteImage("JumpAnimation/tile000.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile001.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile002.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile003.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile004.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile005.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile006.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("JumpAnimation/tile007.png", p1.x, p1.y, 96, 96, false, p1)
    };
    SpriteImage [] character_attackR = {
        new SpriteImage("AttackAnimation/tile000.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile001.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile002.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile003.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile004.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile005.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile006.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile007.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile008.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile009.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile0010.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile0011.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile0012.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("AttackAnimation/tile0013.png", p1.x, p1.y, 96, 96, false, p1)
    };
    SpriteImage [] character_idleR = {
        new SpriteImage("IdleAnimation/tile000.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("IdleAnimation/tile001.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("IdleAnimation/tile002.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("IdleAnimation/tile003.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("IdleAnimation/tile004.png", p1.x, p1.y, 96, 96, false, p1),
        new SpriteImage("IdleAnimation/tile005.png", p1.x, p1.y, 96, 96, false, p1)
    };
    SpriteImage [] character_runL = {
        new SpriteImage("RunAnimation/tile000.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("RunAnimation/tile001.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("RunAnimation/tile002.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("RunAnimation/tile003.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("RunAnimation/tile004.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("RunAnimation/tile005.png", p1.x, p1.y, 96, 96, true, p1)
    };
    SpriteImage [] character_jumpL = {
        new SpriteImage("JumpAnimation/tile000.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile001.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile002.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile003.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile004.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile005.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile006.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("JumpAnimation/tile007.png", p1.x, p1.y, 96, 96, true, p1)
    };
    SpriteImage [] character_attackL = {
        new SpriteImage("AttackAnimation/tile000.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile001.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile002.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile003.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile004.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile005.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile006.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile007.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile008.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile009.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile0010.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile0011.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile0012.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("AttackAnimation/tile0013.png", p1.x, p1.y, 96, 96, true, p1)
    };
    SpriteImage [] character_idleL = {
        new SpriteImage("IdleAnimation/tile000.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("IdleAnimation/tile001.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("IdleAnimation/tile002.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("IdleAnimation/tile003.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("IdleAnimation/tile004.png", p1.x, p1.y, 96, 96, true, p1),
        new SpriteImage("IdleAnimation/tile005.png", p1.x, p1.y, 96, 96, true, p1)
    };
    Platform [] platforms = {
        new Platform(0, 460, 960, 20, 1, "newplatform.png"),
        new Platform(90, 360, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
        new Platform(380, 360, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
        new Platform(670, 360, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
        new Platform(186, 260, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
        new Platform(572,  260, 200, 10, (int)Math.round(Math.random()), "newplatform.png"),
        new Platform(380, 160, 200, 10, (int)Math.round(Math.random()), "newplatform.png")
	};

	ArrayList<Bullet> bullets1 = new ArrayList<Bullet>();
	ArrayList<Bullet> bullets2 = new ArrayList<Bullet>();

    //Base Constructor
    public Smash() {
        setTitle("Smash Game");
        setSize(screen_width, screen_height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SmashPanel smashPanel = new SmashPanel();
        add(smashPanel);


        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                smashPanel.repaint();

                
                // Use iterators to avoid ConcurrentModificationExcepdddddddddtion
                Iterator<Bullet> iterator1 = bullets1.iterator();
                while (iterator1.hasNext()) {
                    Bullet b = iterator1.next();
                    b.CheckHit(p2);
                    iterator1.remove();
                }
        
                Iterator<Bullet> iterator2 = bullets2.iterator();
                while (iterator2.hasNext()) {
                    Bullet b = iterator2.next();
                    b.CheckHit(p1);
                    iterator2.remove();
                }
            }
        });

        Timer timer2 = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Bullet b : bullets1){
                    b.Shoot();
                }
                for (Bullet b : bullets2){
                    b.Shoot();
                }
                smashPanel.repaint();

            }
        });

        timer.start();
        GameMode();

        //Key listener and Inputs
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
			
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });

        setFocusable(true);
    }
    
    public void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = true;
            p1.state = "left";
        } 
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = true;
            p1.state = "right";
        } 
		else if (key == KeyEvent.VK_W) {
            p1KeysPressed[0] = true;
			if (p1.isOnPlatform(platforms))
				p1.jump();
                if (p1.horizontalFacing == 0){
                    p1.state = "jumpL";
                } else {
                    p1.state = "jumpR";
                }
        }
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_E) {
			p2.takeDamage(p1.attack());
		} 
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = true;
        } 
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = true;
        } 
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = true;
			if (p2.isOnPlatform(platforms))
				p2.jump();
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_O) {
			p1.takeDamage(p2.attack());
		}
		else if (key == KeyEvent.VK_U) {
			p2.shoot();
		} 		
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = false;
            if(p1.state.equals("left")){
                p1.state = "idleL";
            }
        } 
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = false;
            if(p1.state.equals("right")){
                p1.state = "idleR";
            }
        } 
		else if (key == KeyEvent.VK_W) {
			p1KeysPressed[0] = false;
		} 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = false;
        } 
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = false;
        }
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = false;
		}
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = false;
		}
    }

    

    public void update() {
		// Movement control
        p1.updateCharacter(p1KeysPressed, platforms, p2.bullets);
		p2.updateCharacter(p2KeysPressed, platforms, p1.bullets);
    }

    public void GameMode(){
            switch (mode) {
                case 0:
                    break;
                case 1:
                    p1.speed /= 2;
                    p2.speed /= 2;
                    break;
                case 2:
                    p1.speed *= 2;
                    p2.speed *= 2;
                    break;
                case 3:
                    p1.jumpHeight = 150;
                    p2.jumpHeight = 150;
                    break;
            }
    }
    // Gameplay Panel
    
    public class SmashPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //backgroundImage.draw(g);
            Graphics2D graphics2d = (Graphics2D) g;
            // Draw platform
            switch (mode) {
                case 0:
                    backgrounds[0].draw(g);;
                    break;
                case 1:
                    backgrounds[1].draw(g);;
                    break;
                case 2:
                    backgrounds[2].draw(g);;
                    break;
                case 3:
                    backgrounds[3].draw(g);;
                    break;
            }

            for (Platform p : platforms) {
                if (p.active == 1){
                    p.draw(graphics2d);
				}
            }

            
            g.setColor(Color.GREEN);
                for (SpriteImage i : character_attackR) {i.update();}
                for (SpriteImage i : character_idleR) {i.update();}
                for (SpriteImage i : character_runR) {i.update();}
                for (SpriteImage i : character_jumpR) {i.update();}
                for (SpriteImage i : character_attackL) {i.update();}
                for (SpriteImage i : character_idleL) {i.update();}
                for (SpriteImage i : character_runL) {i.update();}
                for (SpriteImage i : character_jumpL) {i.update();}
                //for (SpriteImage i : character_) {i.update();}
            p1.draw(g, character_runL, character_runR, character_attackL, character_attackR, character_idleL, character_idleR, character_jumpL, character_jumpR);
            g.fillRect(p2.x, p2.y, Character.width, Character.height);

                
            // Draw bullets
            g.setColor(Color.BLUE);  // Set a distinct color for bullet visibility
            for (Bullet b : bullets1) {
                System.out.println("Bullet 1 Drawn");
                b.DrawBullet(graphics2d);
            }
            for (Bullet b : bullets2) {
                System.out.println("Bullet 2 Drawn");
                b.DrawBullet(graphics2d);
            }
     
			// Draw health bars
            g.setColor(Color.GREEN);
			if (p1.health >= 0) {
				g.fillRect(30, 20, 2 * p1.health, 30);
			}
			if (p2.health >= 0) {
				int hpBarWidth = 2 * p2.health;
				g.fillRect(screen_width - hpBarWidth - 30, 20, hpBarWidth, 30);
			}
			
			for (Bullet bullet: p2.bullets) {
				g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
			}
        }
    }

    // Main game loop
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Smash().setVisible(true);
            }
        });
    }
}