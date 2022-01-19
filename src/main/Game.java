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
	private Scene scene1,scene2,scene3,currentScene;
	private ArrayList<Scene> scenes;
	private int currentSceneNumber; 
	
	public Game() {
		currentSceneNumber=0;
		scenes = new ArrayList<>();
		
		ArrayList<GameObject> scene1Blocks = new ArrayList<>();
		scene1Blocks.add(new GameObject(-2000,300,4000,400,"block"));
		scene1Blocks.add(new GameObject(50,200,"block"));
		scene1Blocks.add(new GameObject(100,125,"block"));
		scene1Blocks.add(new GameObject(200,100,"block"));
		scene1Blocks.add(new GameObject(500,75,"block"));
		scene1Blocks.add(new GameObject(550,25,"block"));
		scene1Blocks.add(new GameObject(850,50,"block"));
		scene1Blocks.add(new GameObject(950,-25,150,40,"block"));
		scene1Blocks.add(new GameObject(1150,-275,100,40, "block"));
		scene1Blocks.add(new GameObject(1750,-275,100,40, "block"));
		scene1Blocks.add(new GameObject(1810,-275,40,1500, "block"));
		scene1Blocks.add(new GameObject(-1600,-275,400,1500, "block"));
		scene1Blocks.add(new GameObject(2025,-1070,400,1500, "block"));
		
		ArrayList<Enemy> scene1Enemies = new ArrayList<>();
		scene1Enemies.add(new Enemy(0,0,200,200,1,"enemy"));
		scene1 = new Scene(0,279,1850,250-30,2005,280,scene1Blocks, scene1Enemies, "main/images/background/full_background.png");

		ArrayList<GameObject> scene2Blocks = new ArrayList<>();
		scene2Blocks.add(new GameObject(-2000,300,100000,400,"block"));
		scene2Blocks.add(new GameObject(2025,-275,100000,1500, "block"));
		scene2Blocks.add(new GameObject(-1400,-275,40,1500, "block"));
		for(int x=0;x<8;x++)
			scene2Blocks.add(new GameObject(-1000+x*150,200-50*x,"block"));
		for(int x=0;x<6;x++)
			scene2Blocks.add(new GameObject(200+x*200,-200,"block"));
		scene2Blocks.add(new GameObject(200+40,-200,"block"));
		
		scene2Blocks.add(new GameObject(1900,-200,"block"));
	
		ArrayList<Enemy> scene2Enemies = new ArrayList<>();
		
		scene2 = new Scene(0,279,1885,-250-30,2025,-200,scene2Blocks, scene2Enemies, "main/images/background/full_background.png");
		
		scene3 = new Scene(0,279,1885,-250-30,2025,-200,scene2Blocks, scene2Enemies, "main/images/background/full_background.png");
		
		scenes.add(scene1);
		scenes.add(scene2);
		scenes.add(scene3);
		
		currentScene=scenes.get(0);
		player=currentScene.getPlayer();
	}
	
	
	public void nextScene() {
		currentSceneNumber+=1;
		this.currentScene=scenes.get(currentSceneNumber);
		player=currentScene.getPlayer();
		System.out.println("hi");
	}
	public void update(ArrayList<String>listOflastPresses){
		currentScene.updatePositions(listOflastPresses,this,currentSceneNumber);
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
