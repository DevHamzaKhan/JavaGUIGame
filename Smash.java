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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Smash extends JPanel implements KeyListener{
    public boolean[] p1KeysPressed = new boolean[6]; // [W, A, S, D, E, Q]
	public boolean[] p2KeysPressed = new boolean[6]; // [I, J, K, L, O, U]
    public int mode = (int)(Math.random() * 5);

    //Character
    Character p1 = new Character(50, 0, 1, 5, 5, 5, 50, 80, 20, 20);
	Character p2 = new Character(800, 0, 0, 5, 5, 5, 50, 75, 40, 60);
    Music backgroundMusic = new Music("backgroundmusic.wav", -1);
    GameImage [] backgrounds = {
        new GameImage("FireBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false),
        new GameImage("SandBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false),
        new GameImage("IceBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false),
        new GameImage("SpaceBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false),
        new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false)
    };
    SpriteImage [] character_runR = {
        new SpriteImage(p1, "RunAnimation/tile000.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "RunAnimation/tile001.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "RunAnimation/tile002.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "RunAnimation/tile003.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "RunAnimation/tile004.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "RunAnimation/tile005.png", p1.x, p1.y, 96, 96, false)
    };
    SpriteImage [] character_attackR = {
        new SpriteImage(p1, "AttackAnimation/tile000.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "AttackAnimation/tile001.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "AttackAnimation/tile002.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "AttackAnimation/tile003.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "AttackAnimation/tile004.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "AttackAnimation/tile005.png", p1.x, p1.y, 96, 96, false)
    };
    SpriteImage [] character_shootR = {
        new SpriteImage(p1, "ShootAnimation/tile000.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "ShootAnimation/tile001.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "ShootAnimation/tile002.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "ShootAnimation/tile003.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "ShootAnimation/tile004.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "ShootAnimation/tile005.png", p1.x, p1.y, 96, 96, false)
    };
    SpriteImage [] character_idleR = {
        new SpriteImage(p1, "IdleAnimation/tile000.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "IdleAnimation/tile001.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "IdleAnimation/tile002.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "IdleAnimation/tile003.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "IdleAnimation/tile004.png", p1.x, p1.y, 96, 96, false),
        new SpriteImage(p1, "IdleAnimation/tile005.png", p1.x, p1.y, 96, 96, false)
    };
    SpriteImage [] character_runL = {
        new SpriteImage(p1, "RunAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "RunAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "RunAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "RunAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "RunAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "RunAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
    };
    SpriteImage [] character_attackL = {
        new SpriteImage(p1, "AttackAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "AttackAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "AttackAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "AttackAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "AttackAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "AttackAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
    };
    SpriteImage [] character_shootL = {
        new SpriteImage(p1, "ShootAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "ShootAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "ShootAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "ShootAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "ShootAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "ShootAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
    };
    SpriteImage [] character_idleL = {
        new SpriteImage(p1, "IdleAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "IdleAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "IdleAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "IdleAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "IdleAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
        new SpriteImage(p1, "IdleAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
    };


    SpriteImage [] Gcharacter_runR = {
        new SpriteImage(p2, "RunAnimationG/tile001.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "RunAnimationG/tile002.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "RunAnimationG/tile003.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "RunAnimationG/tile005.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "RunAnimationG/tile006.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "RunAnimationG/tile007.png", p2.x, p2.y, 128, 128, false)
    };
    SpriteImage [] Gcharacter_attackR = {
        new SpriteImage(p2, "AttackAnimationG/tile002.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "AttackAnimationG/tile003.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "AttackAnimationG/tile004.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "AttackAnimationG/tile005.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "AttackAnimationG/tile006.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "AttackAnimationG/tile007.png", p2.x, p2.y, 128, 128, false)
    };
    SpriteImage [] Gcharacter_shootR = {
        new SpriteImage(p2, "ShootAnimationG/tile000.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "ShootAnimationG/tile001.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "ShootAnimationG/tile002.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "ShootAnimationG/tile003.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "ShootAnimationG/tile004.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "ShootAnimationG/tile005.png", p2.x, p2.y, 128, 128, false)
    };
    SpriteImage [] Gcharacter_idleR = {
        new SpriteImage(p2, "IdleAnimationG/tile000.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "IdleAnimationG/tile001.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "IdleAnimationG/tile002.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "IdleAnimationG/tile003.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "IdleAnimationG/tile006.png", p2.x, p2.y, 128, 128, false),
        new SpriteImage(p2, "IdleAnimationG/tile007.png", p2.x, p2.y, 128, 128, false)
    };
    SpriteImage [] Gcharacter_runL = {
        new SpriteImage(p2, "RunAnimationG/tile001.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "RunAnimationG/tile002.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "RunAnimationG/tile003.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "RunAnimationG/tile005.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "RunAnimationG/tile006.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "RunAnimationG/tile007.png", p2.x, p2.y, 128, 128, true)
    };
    SpriteImage [] Gcharacter_attackL = {
        new SpriteImage(p2, "AttackAnimationG/tile002.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "AttackAnimationG/tile003.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "AttackAnimationG/tile004.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "AttackAnimationG/tile005.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "AttackAnimationG/tile006.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "AttackAnimationG/tile007.png", p2.x, p2.y, 128, 128, true)
    };
    SpriteImage [] Gcharacter_shootL = {
        new SpriteImage(p2, "ShootAnimationG/tile000.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "ShootAnimationG/tile001.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "ShootAnimationG/tile002.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "ShootAnimationG/tile003.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "ShootAnimationG/tile004.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "ShootAnimationG/tile005.png", p2.x, p2.y, 128, 128, true)
    };
    SpriteImage [] Gcharacter_idleL = {
        new SpriteImage(p2, "IdleAnimationG/tile000.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "IdleAnimationG/tile001.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "IdleAnimationG/tile002.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "IdleAnimationG/tile003.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "IdleAnimationG/tile006.png", p2.x, p2.y, 128, 128, true),
        new SpriteImage(p2, "IdleAnimationG/tile007.png", p2.x, p2.y, 128, 128, true)
    };

    Platform [] platforms = {
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
    //Base Constructor
    public Smash() {
        //backgroundMusic.start();
		setVisible(true);

        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });

        timer.start();
        GameMode();

        //Key listener and Inputs
        addKeyListener(this);
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
		} 
        else if (key == KeyEvent.VK_Q) {
			p1KeysPressed[5] = true;
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
		}
		else if (key == KeyEvent.VK_U) {
			p2KeysPressed[5] = true;
		}		
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			p1KeysPressed[0] = false;
		} 
        else if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = false;
            if (p1.state == "left"){
                p1.state = "idleL";
            }
        } 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = false;
            if (p1.state == "right"){
                p1.state = "idleR";
            }
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
            if (p2.state == "left"){
                p2.state = "idleL";
            }
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = false;
            if (p2.state == "right"){
                p2.state = "idleR";
            }
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

    

    public void update() {
		// Movement control
        p1.updateCharacter(p1KeysPressed, platforms, p2.bullets);
		p2.updateCharacter(p2KeysPressed, platforms, p1.bullets);
		
		if (p1KeysPressed[4] && p1.canAttack) {
			p1.spriteCounter = 0;
			p2.takeDamage(p1.attack());
		}
		if (p1KeysPressed[5] && p1.canShoot) {
			p1.shoot(); 
			p1.spriteCounter = 0;
			if (p1.horizontalFacing == 0){                 
				p1.state = "shootL";
			}
			if (p1.horizontalFacing == 1){          
				p1.state = "shootR";
			}
		}
		if (p2KeysPressed[4] && p2.canAttack) {
			p2.spriteCounter = 0;
			p1.takeDamage(p2.attack());
		}
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
		
		if (p1.health <= 0) {
			GameOver endScreen = new GameOver("Player 2", backgrounds[mode]);
			Main.addCard(endScreen, "GameOver");
			Main.showCard("GameOver");
			Main.removeCard(this);
		}
		if (p2.health <= 0) {
			GameOver endScreen = new GameOver("Player 1", backgrounds[mode]);
			Main.addCard(endScreen, "GameOver");
			Main.showCard("GameOver");
			Main.removeCard(this);
		}
    }

    public void GameMode(){
            switch (mode) {
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
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //backgroundImage.draw(g);
        Graphics2D graphics2d = (Graphics2D) g;
        // Draw platform
        switch (mode) {
            case 0:
                p1.burn();
                    p2.burn();
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
                case 4:
                    backgrounds[4].draw(g);;
                    break;
            }

            for (Platform p : platforms) {
                if (p.active == 1){
                    p.draw(graphics2d);
				}
            }

            
            g.setColor(Color.GREEN);
            for (SpriteImage i : character_runL){i.update();}
            for (SpriteImage i : character_runR){i.update();}
            for (SpriteImage i : character_attackL){i.update();}
            for (SpriteImage i : character_attackR){i.update();}
            for (SpriteImage i : character_idleL){i.update();}
            for (SpriteImage i : character_idleR){i.update();}
            for (SpriteImage i : character_shootL){i.update();}
            for (SpriteImage i : character_shootR){i.update();}
            for (SpriteImage i : Gcharacter_runL){i.update();}
            for (SpriteImage i : Gcharacter_runR){i.update();}
            for (SpriteImage i : Gcharacter_attackL){i.update();}
            for (SpriteImage i : Gcharacter_attackR){i.update();}
            for (SpriteImage i : Gcharacter_idleL){i.update();}
            for (SpriteImage i : Gcharacter_idleR){i.update();}
            for (SpriteImage i : Gcharacter_shootL){i.update();}
            for (SpriteImage i : Gcharacter_shootR){i.update();}
            p1.draw(g, character_runL, character_runR, character_attackL, character_attackR, character_idleL, character_idleR, character_shootL, character_shootR);
            p2.draw(g, Gcharacter_runL, Gcharacter_runR, Gcharacter_attackL, Gcharacter_attackR, Gcharacter_idleL, Gcharacter_idleR, Gcharacter_shootL, Gcharacter_shootR);
            
            
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