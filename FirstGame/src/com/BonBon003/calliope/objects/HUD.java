package com.BonBon003.calliope.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.BonBon003.calliope.framework.GameObject;
import com.tutorial.main.Game;

public class HUD {
	
	public static int HEALTH = 100;
	
	public void tick(LinkedList<GameObject> object) {
		HEALTH--;
		HEALTH = Game.clamp(HEALTH, 0, 100);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	}

}

