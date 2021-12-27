package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

public class Game{
	private Timer timer;
	public PlayerCharacter player;
	private Scene currentScene;
	
	public Game() {
		ArrayList<GameObject> entities = new ArrayList<>();
		currentScene = new Scene(entities, "main/images/background/raceTrack.png");
		player=currentScene.getPlayer();
	}
	
	public void update(){
		currentScene.updatePositions();
		currentScene.checkCollisions();
	}

	

	public static void playSound(String fileName) {
		try {
			File url = new File(fileName);
			Clip clip = AudioSystem.getClip();

			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip.open(ais);
			clip.start();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Scene getScene() {
		return this.currentScene;
	}
	public PlayerCharacter getPlayer() {
		return this.player;
	}
	
}
