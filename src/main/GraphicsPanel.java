package main;
// Class: GraphicsPanel
// Written by: Woodland Wanderer Dev Team
// Date: 1/20/2022
// Description: This class consists of the implementation of the graphics panel.
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GraphicsPanel extends JPanel implements KeyListener {

	// instance variables
	private Timer timer;
	private Game game;
	private Set<String> keysPressed; 
	private ArrayList<String> listOflastPresses;

	// default constructor
	public GraphicsPanel() throws IOException {
		this.keysPressed = new HashSet<String>();
		this.listOflastPresses = new ArrayList<>();
		timer = new Timer(5, new ClockListener(this)); 
		this.setFocusable(true);
		this.addKeyListener(this);
		setPreferredSize(new Dimension(1280,720));  
		start();
	}

	public void start() throws IOException {
		game = new Game();
		timer.start();
	}
	// draws image
	public void paint(Graphics g){
		if(game!=null) {
			game.getScene().draw(this, g);
		}
	}

	// clock listener
	public void clock() throws IOException {
		if(keysPressed.size() > 0) {
			for(String s : keysPressed) {
				game.player.move(s);
			}
		}

		game.update(this.listOflastPresses);
		this.repaint();
	}

	// movement (up down left right)
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.player.move("right");
			this.keysPressed.add("right");
			listOflastPresses.add("right");
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.player.move("left");
			this.keysPressed.add("left");
			listOflastPresses.add("left");
		}

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			game.player.move("up");
			listOflastPresses.add("up");
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			game.player.move("down");
			this.keysPressed.add("down");
			listOflastPresses.add("down");
		}
	}

	// movement (up down left right)
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.player.friction("right");
			this.keysPressed.remove("right");
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.player.friction("left");
			this.keysPressed.remove("left");
		}

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			game.player.friction("up");
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			game.player.friction("down");
			this.keysPressed.remove("down");
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}