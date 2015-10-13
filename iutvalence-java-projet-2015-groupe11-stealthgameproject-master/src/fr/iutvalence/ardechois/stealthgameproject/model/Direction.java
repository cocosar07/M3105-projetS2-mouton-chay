package fr.iutvalence.ardechois.stealthgameproject.model;

/**
 * Choice of direction.
 * 
 * @author kelemenn
 * 
 * @version 0.1.0
 */
public enum Direction

{
	/**
	 * Up direction.
	 * 
	 * @value "0,-1"
	 */
	UP(0, -1),

	/**
	 * Down direction.
	 * 
	 * @value "0,+1"
	 */
	DOWN(0, 1),

	/**
	 * Left direction.
	 * 
	 * @value "-1,0"
	 */
	LEFT(-1, 0),

	/**
	 * right direction.
	 * 
	 * @value "1,0"
	 */
	RIGHT(1, 0);

	/**
	 * Delta X move value.
	 */
	private int x;
	/**
	 * Delta X move value.
	 */
	private int y;

	/**
	 * Player direction, only one character.
	 */
	private Direction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter Position x.
	 * 
	 * @return positionX
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Getter Position y.
	 * 
	 * @return positionY
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Get the direction of the given id.
	 * @param id
	 * @return Direction.
	 */
	public static Direction getDirection(int id)
	{
		return values()[id];
	}
}