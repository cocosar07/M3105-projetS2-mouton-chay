package fr.iutvalence.ardechois.stealthgameproject.model;

/**
 * Enemies vision field.
 * 
 * @author kelemenn
 *
 */
public class VisionField
{
	// Constants
	/**
	 * The default vision field width.
	 */
	public final static int DEFAULT_WIDTH = 5;

	/**
	 * The default vision field height;
	 */
	public final static int DEFAULT_HEIGHT = 2;

	// Attributes
	/**
	 * The vision field width
	 */
	private int width;

	/**
	 * The vision field height
	 */
	private int height;

	/**
	 * The vision field position
	 * 
	 * @see Position
	 */
	private Position position;

	/**
	 * The current direction of the vision field.
	 * 
	 * @see Direction
	 */
	private Direction curDirection;

	/**
	 * Constructor with a position parameter.
	 * 
	 * @param position
	 *            The vision field position.
	 * @param initDir
	 *            The initial vision field direction.
	 */
	public VisionField(Position position, Direction initDir)
	{
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.curDirection = Direction.UP;
		this.rotate(initDir);
		this.position = position;
		this.curDirection = initDir;
	}

	/**
	 * Constructor with width, height and position parameters.
	 * 
	 * @param width
	 *            The vision field width.
	 * @param height
	 *            The vision field height.
	 * @param position
	 *            The vision field position.
	 * @param initDir
	 *            The initial vision field direction.
	 */
	public VisionField(int width, int height, Position position,
			Direction initDir)
	{
		this.width = width;
		this.height = height;
		this.position = position;
		this.curDirection = initDir;
	}

	/**
	 * Allow to rotate the vision field.
	 * 
	 * @param direction
	 */
	private void rotate(Direction direction)
	{
		switch (direction)
		{
		case UP:
				if (this.curDirection == Direction.RIGHT || this.curDirection == Direction.LEFT)
				{
					int widthTemp = this.width;
					this.width = this.height;
					this.height = widthTemp;
				}
				this.curDirection = Direction.UP;
			break;
		case DOWN:
			if (this.curDirection == Direction.RIGHT || this.curDirection == Direction.LEFT)
			{
				int widthTemp = this.width;
				this.width = this.height;
				this.height = widthTemp;
			}
			this.curDirection = Direction.DOWN;
			break;
		case LEFT:
			if (this.curDirection == Direction.UP || this.curDirection == Direction.DOWN)
			{
				int widthTemp = this.width;
				this.width = this.height;
				this.height = widthTemp;
			}
			this.curDirection = Direction.LEFT;
			break;
		case RIGHT:
			if (this.curDirection == Direction.UP || this.curDirection == Direction.DOWN)
			{
				int widthTemp = this.width;
				this.width = this.height;
				this.height = widthTemp;
			}
			this.curDirection = Direction.RIGHT;
			break;
		}

	}
	
	/**
	 * Update the vision field.
	 * @param direction
	 */
	public void update(Direction direction)
	{
		rotate(direction);
	}
	
	/**
	 * Check if the player is in the vision field.
	 * @param player
	 * @return
	 */
	public boolean check(Player player)
	{
		for (int x = 0; x < this.width; x++)
		{
			for (int y = 0; y < this.height; y++)
			{
				Position halfSquarePosition = null;
				Position halfSquarePosition2 = null;
				switch(this.curDirection)
				{
				case UP:
					halfSquarePosition = new Position(this.position.getX() + x/2 , this.position.getY() - y - 1 );
					halfSquarePosition2 = new Position(this.position.getX() - x/2 , this.position.getY() - y - 1 );
				case DOWN:
					halfSquarePosition = new Position(this.position.getX() + x/2 , this.position.getY() + y - 1 );
					halfSquarePosition2 = new Position(this.position.getX() - x/2 , this.position.getY() + y - 1 );
					break;
				case RIGHT:
					halfSquarePosition = new Position(this.position.getX() + x + 1 , this.position.getY() + y/2 );
					halfSquarePosition2 = new Position(this.position.getX() + x + 1 , this.position.getY() - y/2 );
					break;
				case LEFT:
					halfSquarePosition = new Position(this.position.getX() - x - 1 , this.position.getY() + y/2 );
					halfSquarePosition2 = new Position(this.position.getX() - x - 1 , this.position.getY() - y/2 );
					break;
				}

				if (halfSquarePosition.equals(player.getPosition()) || halfSquarePosition2.equals(player.getPosition()))
				{
					return true;
				}
			}
		}
		return false;
	}

}