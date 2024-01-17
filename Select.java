/*
Programmers: Hamza Khan & Leo Chen
Program Name: Select
Program Date: 2024-01-16
Program Description: character and map select screen
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Select extends JPanel implements ActionListener, KeyListener, ComponentListener {
	// declare variables
	int mode, p1Type, p2Type;
	GameImage background;
	
	JLabel[] titles = new JLabel[2];
	JLabel[] headers = new JLabel[2];
	JLabel[] characters = new JLabel[6];
	JButton[] maps = new JButton[6];
	JButton confirm;
    
    // constructor
    public Select() {
		// initialize screen
		this.setLayout(null);
		this.setFocusable(true);
		
		// initialize variables
		mode = -1;
		p1Type = -1;
		p2Type = -1;
		
		this.background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
		
		// add titles
		titles[0] = new JLabel("Character Select", SwingConstants.CENTER);
        titles[0].setBounds(Main.WIDTH / 2 - 200, 30, 400, 50);
		titles[1] = new JLabel("Map Select", SwingConstants.CENTER);
        titles[1].setBounds(Main.WIDTH / 2 - 200, 260, 400, 50);
		for (int i = 0; i < titles.length; i++) {
			titles[i].setFont(Main.BOLD48);
			titles[i].setForeground(Color.BLACK);
			this.add(titles[i]);
		}
		
		// add headers
		headers[0] = new JLabel("Player 1 Select", SwingConstants.CENTER);
        headers[0].setBounds(150, 100, 200, 30);
		headers[1] = new JLabel("Player 2 Select", SwingConstants.CENTER);
        headers[1].setBounds(600, 100, 200, 30);
		for (int i = 0; i < headers.length; i++) {
			headers[i].setFont(Main.BOLD24);
			headers[i].setForeground(Color.BLACK);
			this.add(headers[i]);
		}
		
		// add character select display
		characters[0] = new JLabel("E - Samurai", SwingConstants.CENTER);
        characters[0].setBounds(150, 140, 200, 30);
		characters[1] = new JLabel("W - Ninja", SwingConstants.CENTER);
        characters[1].setBounds(150, 180, 200, 30);
		characters[2] = new JLabel("Q - Archer", SwingConstants.CENTER);
        characters[2].setBounds(150, 220, 200, 30);
		characters[3] = new JLabel("O - Samurai", SwingConstants.CENTER);
        characters[3].setBounds(600, 140, 200, 30);
		characters[4] = new JLabel("I - Ninja", SwingConstants.CENTER);
        characters[4].setBounds(600, 180, 200, 30);
		characters[5] = new JLabel("U - Archer", SwingConstants.CENTER);
        characters[5].setBounds(600, 220, 200, 30);
		for (int i = 0; i < characters.length; i++) {
			characters[i].setBackground(Color.ORANGE);
			characters[i].setOpaque(true);
			characters[i].setFont(Main.PLAIN24);
			characters[i].setForeground(Color.BLACK);
			this.add(characters[i]);
		}
		
		// add map buttons
		maps[0] = new JButton("Random");
        maps[0].setBounds(120, 350, 200, 40);
		maps[1] = new JButton("Default");
        maps[1].setBounds(Main.WIDTH / 2 - 100, 350, 200, 40);
		maps[2] = new JButton("Sand");
        maps[2].setBounds(Main.WIDTH - 320, 350, 200, 40);
		maps[3] = new JButton("Ice");
        maps[3].setBounds(120, 410, 200, 40);
		maps[4] = new JButton("Space");
        maps[4].setBounds(Main.WIDTH / 2 - 100, 410, 200, 40);
		maps[5] = new JButton("Fire");
        maps[5].setBounds(Main.WIDTH - 320, 410, 200, 40);
		for (int i = 0; i < maps.length; i++) {
			maps[i].setFont(Main.PLAIN18);
			maps[i].setForeground(Color.BLACK);
			maps[i].setBackground(Color.ORANGE);
			maps[i].setOpaque(true);
			maps[i].addActionListener(this);
			maps[i].setFocusable(false);
			this.add(maps[i]);
		}
		
		// add confirm button
		confirm = new JButton("Confirm");
        confirm.setBounds(Main.WIDTH / 2 - 50, 180, 100, 40);
        confirm.setContentAreaFilled(true);
        confirm.setForeground(Color.BLACK);
		confirm.setBackground(Color.ORANGE);
        confirm.addActionListener(this);
		confirm.setFocusable(false);
        this.add(confirm);
		confirm.setVisible(false);

		addKeyListener(this);
		addComponentListener(this);
    }
	
	// check for buttons clicked
	public void actionPerformed(ActionEvent e) {
		// check buttons and change mode
		for (int i = 0; i < maps.length; i++) {
			if (e.getSource() == maps[i]) {
				mode = i;
				
				// display which map is selected
				resetMapStates();
				maps[i].setBackground(Color.MAGENTA);
				
				this.requestFocusInWindow();
				checkIsSelected();
				return;
			} 
		}
		
		// return to main menu when confirmed
		if (e.getSource() == confirm) {
			Main.showCard("Menu");
			return;
		}
		
		this.requestFocusInWindow();
		checkIsSelected();
    }
	
	public void keyTyped(KeyEvent e) {}

	// check for key inputs
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
		
		// update type and display 
		if (key == KeyEvent.VK_E) {
			p1Type = 2;
			
			resetCharacter1States();
			characters[0].setBackground(Color.MAGENTA);
		} 
        else if (key == KeyEvent.VK_W) {
			p1Type = 0;
			
			resetCharacter1States();
			characters[1].setBackground(Color.MAGENTA);
        } 
		else if (key == KeyEvent.VK_Q) {
			p1Type = 1;
			
			resetCharacter1States();
			characters[2].setBackground(Color.MAGENTA);
        } 
		else if (key == KeyEvent.VK_O) {
			p2Type = 2;
			
			resetCharacter2States();
			characters[3].setBackground(Color.MAGENTA);
		} 
        else if (key == KeyEvent.VK_I) {
			p2Type = 0;
			
			resetCharacter2States();
			characters[4].setBackground(Color.MAGENTA);;
        } 
		else if (key == KeyEvent.VK_U) {
			p2Type = 1;
			
			resetCharacter2States();
			characters[5].setBackground(Color.MAGENTA);
        }
		
		checkIsSelected();
    }
			
	public void keyReleased(KeyEvent e) {}
	
	// update Game variables when confirmed
	public void componentHidden(ComponentEvent e) {
		Game.mode = this.mode;
		Game.p1Type = this.p1Type;
		Game.p2Type = this.p2Type;
	}

    public void componentMoved(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {}
	
    public void componentShown(ComponentEvent e) {
        this.requestFocusInWindow();
    }
	
	// method for reseting background of map buttons
	private void resetMapStates() {
		for (int i = 0; i < maps.length; i++)
			maps[i].setBackground(Color.ORANGE);
	}
	
	// method for reseting background of p1 displays
	private void resetCharacter1States() {
		for (int i = 0; i < characters.length / 2; i++)
			characters[i].setBackground(Color.ORANGE);
	}
	
	// method for reseting background of p2 displays
	private void resetCharacter2States() {
		for (int i = characters.length / 2; i < characters.length; i++)
			characters[i].setBackground(Color.ORANGE);
	}
	
	// display confirm button only when map and characters are selected
	private void checkIsSelected() {
		if (mode != -1 && p1Type != -1 && p2Type != -1)
			confirm.setVisible(true);
	}
	
	// paint component
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		background.draw(g);
	}  
}
