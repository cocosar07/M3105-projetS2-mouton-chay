package fr.iutvalence.ardechois.stealthgameproject.model;

/**
 * Represent an item.
 * 
 * @author vanbossm
 * 
 * */
public class Item
{
	/**
	 * The Item Position.
	 * 
	 * @see Position
	 * 
	 * */
	private Position position;

	/**
	 * True if the Item is already taken by the Character.
	 * 
	 * @see Character
	 * 
	 */
	private boolean taken;

	/**
	 * Constructor of Item Position
	 * 
	 * @param position
	 * 
	 */
	public Item(Position position)
	{
		this.position = position;
	}

	/**
	 * Getter for the Item Position.
	 * 
	 * @return position
	 * 
	 */
	public Position getPosition()
	{
		return position;
	}
	
	/**
	 * Set the item position with the given position.
	 * @param position
	 */
	public void setPosition(Position position)
	{
		this.position = new Position(position.getX(), position.getY());
	}

	/**
	 * Set the item position with the given x and y coordinates.
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y)
	{
		this.position = new Position(x, y);
		
	}

	/**
	 * Getter for the taken boolean.
	 * 
	 * @return taken
	 * 
	 */
	public boolean isTaken()
	{
		return taken;
	}
	
	/**
	 * Set the item "state" to taken.
	 * @param playerPosition
	 */
	public void setTaken(Position playerPosition)
	{
		this.taken = true;
		this.position = playerPosition;
	}

}
