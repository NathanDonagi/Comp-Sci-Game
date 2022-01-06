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
	private Scene scene1,scene2,currentScene;
	
	public Game() {
		ArrayList<GameObject> scene1Entities = new ArrayList<>();
		scene1Entities.add(new GameObject(-2000,300,100000,40,"block"));
		scene1Entities.add(new GameObject(50,200,"block"));
		scene1Entities.add(new GameObject(100,125,"block"));
		scene1Entities.add(new GameObject(200,100,"block"));
		scene1Entities.add(new GameObject(500,75,"block"));
		scene1Entities.add(new GameObject(550,25,"block"));
		scene1Entities.add(new GameObject(850,50,"block"));
		scene1Entities.add(new GameObject(950,-25,150,40,"block"));
		scene1Entities.add(new GameObject(1150,-275,100,40, "block"));
		scene1Entities.add(new GameObject(1750,-275,100,40, "block"));
		scene1Entities.add(new GameObject(1810,-275,40,700, "block"));
		scene1Entities.add(new GameObject(-1400,-275,40,700, "block"));
		scene1Entities.add(new GameObject(2025,-11070,40,11500, "block"));
		scene1 = new Scene(scene1Entities, "main/images/background/raceTrack.png");

		ArrayList<GameObject> scene2Entities = new ArrayList<>();
		scene2Entities.add(new GameObject(-2000,300,100000,40,"block"));
		for(int x=0;x<22;x++)
			scene2Entities.add(new GameObject(-500+x*150,200,"block"));
		
		scene2 = new Scene(scene2Entities, "main/images/background/raceTrack.png");
		
		
		currentScene=scene1;
		player=currentScene.getPlayer();
	}
	
	public void update(ArrayList<String>listOflastPresses){
		currentScene.updatePositions(listOflastPresses);
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
