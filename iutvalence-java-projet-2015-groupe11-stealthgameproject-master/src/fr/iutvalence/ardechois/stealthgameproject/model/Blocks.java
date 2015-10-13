package fr.iutvalence.ardechois.stealthgameproject.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Block that compose the map.
 * 
 * @author chayc
 *
 */
/**
 * @author antoine
 *
 */
public enum Blocks
{
	/**
	 * Floor block.
	 * 
	 * @value "0"
	 */
	FLOOR('0', "/floor.png"),
	
	/**
	 * Wall block.
	 * 
	 * @value "1"
	 */
	WALL('1', "/wall.png"),
	
	/**
	 * Grass block.
	 * 
	 * @value "2"
	 */
	GRASS('2', "/grass.png"),
	
	/**
	 * Slab block.
	 * 
	 * @value "3"
	 */
	SLAB('3', "/slab.png"),
	
	/**
	 * Water block.
	 * 
	 * @value "4"
	 */
	WATER('4', "/water.png"),
	
	/**
	 * Sand block.
	 * 
	 * @value "5"
	 */
	SAND('5', "/sand.png");
	
	/**
	 * Block id, only one character.
	 */
	private char id;
	
	/**
	 * Graphic representation of the block.
	 */
	private Icon icon;

	/**
	 * Give an id to a block.
	 * 
	 * @param id
	 */
	private Blocks(char id, String filename)
	{
		this.id = id;
		this.icon = new ImageIcon(getClass().getResource(filename));
	}

	/**
	 * Get the block id.
	 * 
	 * @return id
	 */
	public char getId()
	{
		return this.id;
	}

	/**
	 * Get the block icon.
	 * 
	 * @return icon
	 */
	public Icon getIcon()
	{
		return this.icon;
	}
	
	/**
	 * Get the next block.
	 * @return values()[(ordinal()+1) % values().length]
	 */
	public Blocks getNext()
	{
		return values()[(ordinal()+1) % values().length];
	}
}
