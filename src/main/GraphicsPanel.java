package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;


import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphicsPanel extends JPanel implements KeyListener{

	private Timer timer;
	private Game game= new Game();
	private Set<String> keysPressed; 
	private ArrayList<String> listOflastPresses;


	public GraphicsPanel(){
		this.keysPressed = new HashSet<String>();
		this.listOflastPresses = new ArrayList<>();
		timer = new Timer(5, new ClockListener(this)); 
		timer.start();
		this.setFocusable(true);
		this.addKeyListener(this);
		game= new Game();
		setPreferredSize(new Dimension(game.getScene().getBackground().getImage().getIconWidth(),
				game.getScene().getBackground().getImage().getIconHeight()));  
	}
	
	
	public void paintComponent(Graphics g){
		game.getScene().draw(this,g);
	}

	
	public void clock(){
		if(keysPressed.size()>0) {
			for(String s: keysPressed) {
				game.player.move(s);
			}
		}
		game.update(this.listOflastPresses);
		this.repaint();
	}
	
	
	@Override
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

	@Override
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


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
