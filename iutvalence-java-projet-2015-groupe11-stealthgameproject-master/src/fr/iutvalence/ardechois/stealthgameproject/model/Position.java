package fr.iutvalence.ardechois.stealthgameproject.model;


/**
 * Position of an element.
 * 
 * @author vanbossm
 *
 */
public class Position
{

	/** X coordinate position. */
	private int x;

	/** Y coordinate position. */
	private int y;

	/**
	 * Constructor of Position.
	 * 
	 * @param x
	 *            : position x
	 * @param y
	 *            : position y
	 */
	public Position(int x, int y)
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
	 * Move with x and y deltas.
	 * @param x
	 * @param y
	 */
	public void move(int x, int y)
	{
		setPosition(this.x + x, this.y + y);
	}
	
	/**
	 * Move the position with given direction.
	 * @param direction
	 */
	public void move(Direction direction)
	{
		setPosition(this.x + direction.getX(), this.y + direction.getY());
	}

	/**
	 * Setter for the Position.
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object position)
	{
		if (this.x == ((Position) position).getX() && this.y == ((Position) position).getY())
			return true;
		return false;
	}
}
