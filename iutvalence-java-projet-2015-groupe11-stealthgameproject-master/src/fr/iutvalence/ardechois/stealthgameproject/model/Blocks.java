package fr.iutvalence.ardechois.stealthgameproject.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public enum Blocks
{
	FLOOR('0', "/floor.png"),
	
	WALL('1', "/wall.png"),
	
	GRASS('2', "/grass.png"),
	
	SLAB('3', "/slab.png"),
	
	WATER('4', "/water.png"),
	
	SAND('5', "/sand.png");
	
	private char id;
	
	private Icon icon;

	private Blocks(char id, String filename)
	{
		this.id = id;
		this.icon = new ImageIcon(getClass().getResource(filename));
	}

	public char getId()
	{
		return this.id;
	}

	public Icon getIcon()
	{
		return this.icon;
	}
	
	public Blocks getNext()
	{
		return values()[(ordinal()+1) % values().length];
	}
}
