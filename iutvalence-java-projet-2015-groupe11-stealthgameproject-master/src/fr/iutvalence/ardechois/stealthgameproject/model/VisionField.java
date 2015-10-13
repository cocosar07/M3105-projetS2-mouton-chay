package fr.iutvalence.ardechois.stealthgameproject.model;

public class VisionField
{
	public final static int DEFAULT_WIDTH = 5;
	public final static int DEFAULT_HEIGHT = 2;

	private int width;
	private int height;
	private Position position;

	private Direction curDirection;

	public VisionField(Position position, Direction initDir)
	{
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.curDirection = Direction.UP;
		this.rotate(initDir);
		this.position = position;
		this.curDirection = initDir;
	}

	public VisionField(int width, int height, Position position,
			Direction initDir)
	{
		this.width = width;
		this.height = height;
		this.position = position;
		this.curDirection = initDir;
	}

	private void rotate(Direction direction)
	{
		switch (direction)
		{
		case UP:
			if (isHorizontallyOriented())
			{
				shiftWidthAndHeight();
			}
			break;
		case DOWN:
			if (isHorizontallyOriented())
			{
				shiftWidthAndHeight();
			}
			break;
		case LEFT:
			if (isVerticallyOriented())
			{
				shiftWidthAndHeight();
			}
			break;
		case RIGHT:
			if (isVerticallyOriented())
			{
				shiftWidthAndHeight();
			}
			break;
		}
		this.curDirection = direction;
	}

	private boolean isHorizontallyOriented()
	{
		return this.curDirection == Direction.RIGHT || this.curDirection == Direction.LEFT;
	}

	private boolean isVerticallyOriented()
	{
		return this.curDirection == Direction.UP || this.curDirection == Direction.DOWN;
	}

	private void shiftWidthAndHeight()
	{
		int widthTemp = this.width;
		this.width = this.height;
		this.height = widthTemp;
	}
	
	public void updateDirection(Direction direction)
	{
		rotate(direction);
	}
	
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