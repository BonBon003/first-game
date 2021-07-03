package com.BonBon003.calliope.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.BonBon003.calliope.framework.GameObject;
import com.BonBon003.calliope.framework.ID;

public class Block extends GameObject{

	public Block(float x, float y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);
		
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	

}
