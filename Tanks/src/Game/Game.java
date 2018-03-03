package Game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import DisplayGame.Display;
import Game.Level.Level;
import Graphics.TextureAtlas;
import IO.Input;
import utils.Time;

public class Game implements Runnable{
	
	public static final int WIDTH               	 = 800;
	public static final int HEIGHT        	 	     = 600;
	public static final String TITLE 			     = "Tanks";
	public static final int CLEAR_COLOR              = 0x70000000;
	public static final int NUM_BUFFERS              = 3;
	
	public static final float UPDATE_RATE            = 60.0f;
	public static final float UPDATE_INTERVAL        = Time.SECOND / UPDATE_RATE;
	public static final long IDLE_TIME 	             = 1;
	
	public static final String ATLAS_FILE_NAME = "texture_atlas.png"; 
	
	private boolean running;
	private Thread gameThread;
	private Graphics2D graphics;
	private Input input;
	private TextureAtlas atlas;
	private Player player;
	public ArrayList<Shell> shells;
	private Level level;
	

	public Game(){
		running = false;
		Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
		graphics = Display.getGraphics();
		input= new Input();
		Display.addInputListener(input);
		atlas = new TextureAtlas(ATLAS_FILE_NAME);
		player = new Player(300, 300, 2, 2, atlas);
		level = new Level(atlas);
	}
	
	public synchronized void start(){
		
		if(running)
			return;
		
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop(){
		
		if(!running)
			return;
		
		running = false;
		
		try{
		gameThread.join();
		
	}catch(InterruptedException e){
		e.printStackTrace();
	}
		cleanUp();
	}
	
	private void update(){
		level.update();
		player.update(input);
		shells = player.getShells();
		for (int i = 0; i < shells.size(); i++) {
			shells.get(i).update(input);
		}
	}
	
	private void render(){		
		Display.clear();
		level.render(graphics);
		player.render(graphics);
		shells = player.getShells();
		for (int i = 0; i < shells.size(); i++) {
			if(shells.get(i).active) {
			shells.get(i).render(graphics);
			}else{
				shells.remove(i);
			}
		}
		System.out.println(shells.size());
		level.renderGrass(graphics);		
		Display.swapBuffer();
	}
	
	public void run(){
		
		
		int fps = 0;
		int upd = 0;
		int updl = 0;
		
		long count = 0;

		float delta = 0;
		
		long lastTime = Time.get();
		while (running){
			long now = Time.get();
			long elapsedTime = now - lastTime;
			lastTime = now;
			
			count += elapsedTime;
			boolean render = false;
			delta += (elapsedTime / UPDATE_INTERVAL);
			while(delta>1){
				update();
				upd++;
				delta--;
				if(render){
					updl++;
				}else{
				render = true;
			}
			}
			if(render){
				render();
				fps++;
			}else{
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(count>=Time.SECOND){
				Display.setTitle(TITLE + "||  Fps: " + fps + " | Upd " + upd + " | Updl: " + updl);
				upd=0;
				fps=0;
				updl=0;
				count=0;
			}
		}
	}
	
	private void cleanUp(){
		Display.destroy();
	}

}
