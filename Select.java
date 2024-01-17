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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Select extends JPanel implements ActionListener, KeyListener, ComponentListener{
	int mode, p1Type, p2Type;
	GameImage background;
	
	JLabel characterTitle, mapTitle;
	JLabel p1Head, p1_0, p1_1, p1_2;
	JLabel p2Head, p2_0, p2_1, p2_2;
	JButton map0, map1, map2, map3, map4, map5;
	JButton confirm;
    
    //Base Constructor
    public Select() {
		this.setLayout(null);
		this.setFocusable(true);
		
		mode = -1;
		p1Type = -1;
		p2Type = -1;
		
		this.background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
		
		characterTitle = new JLabel("Character Select", SwingConstants.CENTER);
        characterTitle.setBounds(Main.WIDTH / 2 - 200, 30, 400, 50);
		characterTitle.setFont(new Font("SansSerif", Font.BOLD, 48));
        this.add(characterTitle);
		
		p1Head = new JLabel("Player 1 Select", SwingConstants.CENTER);
        p1Head.setBounds(150, 100, 200, 30);
		p1Head.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(p1Head);
		p1_0 = new JLabel("E - Samurai", SwingConstants.CENTER);
        p1_0.setBounds(150, 140, 200, 30);
		p1_0.setBackground(Color.WHITE);
		p1_0.setOpaque(true);
		p1_0.setFont(new Font("SansSerif", Font.PLAIN, 24));
        this.add(p1_0);
		p1_1 = new JLabel("W - Ninja", SwingConstants.CENTER);
        p1_1.setBounds(150, 180, 200, 30);
		p1_1.setBackground(Color.WHITE);
		p1_1.setOpaque(true);
		p1_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
        this.add(p1_1);
		p1_2 = new JLabel("Q - Archer", SwingConstants.CENTER);
        p1_2.setBounds(150, 220, 200, 30);
		p1_2.setBackground(Color.WHITE);
		p1_2.setOpaque(true);
		p1_2.setFont(new Font("SansSerif", Font.PLAIN, 24));
        this.add(p1_2);
		
		p2Head = new JLabel("Player 2 Select", SwingConstants.CENTER);
        p2Head.setBounds(600, 100, 200, 30);
		p2Head.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(p2Head);
		p2_0 = new JLabel("O - Samurai", SwingConstants.CENTER);
        p2_0.setBounds(600, 140, 200, 30);
		p2_0.setBackground(Color.WHITE);
		p2_0.setOpaque(true);
		p2_0.setFont(new Font("SansSerif", Font.PLAIN, 24));
        this.add(p2_0);
		p2_1 = new JLabel("I - Ninja", SwingConstants.CENTER);
        p2_1.setBounds(600, 180, 200, 30);
		p2_1.setBackground(Color.WHITE);
		p2_1.setOpaque(true);
		p2_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
        this.add(p2_1);
		p2_2 = new JLabel("U - Archer", SwingConstants.CENTER);
        p2_2.setBounds(600, 220, 200, 30);
		p2_2.setBackground(Color.WHITE);
		p2_2.setOpaque(true);
		p2_2.setFont(new Font("SansSerif", Font.PLAIN, 24));
        this.add(p2_2);
		
		mapTitle = new JLabel("Map Select", SwingConstants.CENTER);
        mapTitle.setBounds(Main.WIDTH / 2 - 200, 260, 400, 50);
		mapTitle.setFont(new Font("SansSerif", Font.BOLD, 48));
        this.add(mapTitle);
		
		map0 = new JButton("Random");
        map0.setBounds(120, 350, 200, 40);
        map0.setForeground(Color.BLACK);
		map0.setBackground(Color.WHITE);
		map0.setOpaque(true);
        map0.addActionListener(this);
        this.add(map0);
		map1 = new JButton("Default");
        map1.setBounds(Main.WIDTH / 2 - 100, 350, 200, 40);
        map1.setForeground(Color.BLACK);
		map1.setBackground(Color.WHITE);
		map1.setOpaque(true);
        map1.addActionListener(this);
        this.add(map1);
		map2 = new JButton("Sand");
        map2.setBounds(Main.WIDTH - 320, 350, 200, 40);
        map2.setForeground(Color.BLACK);
		map2.setBackground(Color.WHITE);
		map2.setOpaque(true);
        map2.addActionListener(this);
        this.add(map2);
		map3 = new JButton("Ice");
        map3.setBounds(120, 410, 200, 40);
        map3.setForeground(Color.BLACK);
		map3.setBackground(Color.WHITE);
		map3.setOpaque(true);
        map3.addActionListener(this);
        this.add(map3);
		map4 = new JButton("Space");
        map4.setBounds(Main.WIDTH / 2 - 100, 410, 200, 40);
        map4.setForeground(Color.BLACK);
		map4.setBackground(Color.WHITE);
		map4.setOpaque(true);
        map4.addActionListener(this);
        this.add(map4);
		map5 = new JButton("Fire");
        map5.setBounds(Main.WIDTH - 320, 410, 200, 40);
        map5.setForeground(Color.BLACK);
		map5.setBackground(Color.WHITE);
		map5.setOpaque(true);
        map5.addActionListener(this);
        this.add(map5);

		confirm = new JButton("Confirm");
        confirm.setBounds(Main.WIDTH / 2 - 50, 180, 100, 40);
        confirm.setContentAreaFilled(true);
        confirm.setForeground(Color.BLACK);
        confirm.addActionListener(this);
        this.add(confirm);
		confirm.setVisible(false);

		addKeyListener(this);
		addComponentListener(this);
    }
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == map0) {
			mode = 0;

			map0.setBackground(Color.BLUE);
			map1.setBackground(Color.WHITE);
			map2.setBackground(Color.WHITE);
			map3.setBackground(Color.WHITE);
			map4.setBackground(Color.WHITE);
			map5.setBackground(Color.WHITE);
		} 
        else if (e.getSource() == map1) {
			mode = 1;
			
			map0.setBackground(Color.WHITE);
			map1.setBackground(Color.BLUE);
			map2.setBackground(Color.WHITE);
			map3.setBackground(Color.WHITE);
			map4.setBackground(Color.WHITE);
			map5.setBackground(Color.WHITE);
        } 
		else if (e.getSource() == map2) {
			mode = 2;
			
			map0.setBackground(Color.WHITE);
			map1.setBackground(Color.WHITE);
			map2.setBackground(Color.BLUE);
			map3.setBackground(Color.WHITE);
			map4.setBackground(Color.WHITE);
			map5.setBackground(Color.WHITE);
        } 
		else if (e.getSource() == map3) {
			mode = 3;
			
			map0.setBackground(Color.WHITE);
			map1.setBackground(Color.WHITE);
			map2.setBackground(Color.WHITE);
			map3.setBackground(Color.BLUE);
			map4.setBackground(Color.WHITE);
			map5.setBackground(Color.WHITE);
		} 
        else if (e.getSource() == map4) {
			mode = 4;
			
			map0.setBackground(Color.WHITE);
			map1.setBackground(Color.WHITE);
			map2.setBackground(Color.WHITE);
			map3.setBackground(Color.WHITE);
			map4.setBackground(Color.BLUE);
			map5.setBackground(Color.WHITE);
        } 
		else if (e.getSource() == map5) {
			mode = 5;
			
			map0.setBackground(Color.WHITE);
			map1.setBackground(Color.WHITE);
			map2.setBackground(Color.WHITE);
			map3.setBackground(Color.WHITE);
			map4.setBackground(Color.WHITE);
			map5.setBackground(Color.BLUE);
        }
		else if (e.getSource() == confirm) {
			Main.showCard("Menu");
			return;
		}
		
		this.requestFocusInWindow();
		checkIsSelected();
    }
	
	public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_E) {
			p1Type = 2;
			
			p1_0.setBackground(Color.BLUE);
			p1_1.setBackground(Color.WHITE);
			p1_2.setBackground(Color.WHITE);
		} 
        else if (key == KeyEvent.VK_W) {
			p1Type = 0;
			
			p1_0.setBackground(Color.WHITE);
			p1_1.setBackground(Color.BLUE);
			p1_2.setBackground(Color.WHITE);
        } 
		else if (key == KeyEvent.VK_Q) {
			p1Type = 1;
			
			p1_0.setBackground(Color.WHITE);
			p1_1.setBackground(Color.WHITE);
			p1_2.setBackground(Color.BLUE);
        } 
		else if (key == KeyEvent.VK_O) {
			p2Type = 2;
			
			p2_0.setBackground(Color.BLUE);
			p2_1.setBackground(Color.WHITE);
			p2_2.setBackground(Color.WHITE);
		} 
        else if (key == KeyEvent.VK_I) {
			p2Type = 0;
			
			p2_0.setBackground(Color.WHITE);
			p2_1.setBackground(Color.BLUE);
			p2_2.setBackground(Color.WHITE);
        } 
		else if (key == KeyEvent.VK_U) {
			p2Type = 1;
			
			p2_0.setBackground(Color.WHITE);
			p2_1.setBackground(Color.WHITE);
			p2_2.setBackground(Color.BLUE);
        }
		
		checkIsSelected();
    }
			
	public void keyReleased(KeyEvent e) {}
	
	public void componentHidden(ComponentEvent e) {
		Smash.mode = this.mode;
		Smash.p1Type = this.p1Type;
		Smash.p2Type = this.p2Type;
	}

    public void componentMoved(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {
        this.requestFocusInWindow();
    }
	
	public void checkIsSelected() {
		if (mode != -1 && p1Type != -1 && p2Type != -1) {
			confirm.setVisible(true);
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		background.draw(g);
	}  
}