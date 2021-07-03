package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.BonBon003.calliope.framework.GameObject;
import com.BonBon003.calliope.framework.ID;
import com.BonBon003.calliope.objects.Block;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for (int i= 0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			
				
			tempObject.tick(object);
		}
			
	}
	
	public void render(Graphics g) {
		for (int i= 0; i< object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		for (int i = 0; i< Game.WIDTH+32; i+=32)
			addObject(new Block(i, Game.HEIGHT-64, ID.Block));
		for (int i = 0; i< Game.HEIGHT+32; i+=32) {
			//addObject(new Block(Game.WIDTH-48, i, ID.Block));
			//aaaaaddObject(new Block(0, i, ID.Block));
		}
		for (int i = 0; i < 8; i++)
			addObject(new Block((32*i)+100, 200, ID.Block));
			
	}
	
}
