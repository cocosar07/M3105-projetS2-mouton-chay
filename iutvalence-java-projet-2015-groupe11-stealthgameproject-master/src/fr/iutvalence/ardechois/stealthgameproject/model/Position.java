package fr.iutvalence.ardechois.stealthgameproject.model;

public class Position
{

	private int x;
	private int y;

	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void add(int x, int y)
	{
		setPosition(this.x + x, this.y + y);
	}
	
	public void add(Direction direction)
	{
		setPosition(this.x + direction.getX(), this.y + direction.getY());
	}

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
