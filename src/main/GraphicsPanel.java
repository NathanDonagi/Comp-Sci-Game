package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;

public class GraphicsPanel extends JPanel implements KeyListener{

	private Timer timer;
	private Game game= new Game();

	public GraphicsPanel(){
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
		game.update();
		this.repaint();
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			game.player.move("right");
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			game.player.move("left");
		if(e.getKeyCode() == KeyEvent.VK_UP)
			game.player.move("up");
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			game.player.move("down");
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			game.player.move("up");
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
