package fr.iutvalence.ardechois.stealthgameproject.model;

import java.io.File;
import java.util.ArrayList;
import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidMapSizeException;
import fr.iutvalence.ardechois.stealthgameproject.view.LevelGetter;

public class Level implements LevelGetter
{
	private Map currentMap;

	private ArrayList<Enemy> enemyList;

	private Item currentItem;

	public Level()
	{
		currentItem = new Item(new Position(0, 0));
		enemyList = new ArrayList<Enemy>();

		try
		{
			currentMap = new Map("tempMap.txt", currentItem, this);
		} catch (InvalidMapSizeException e)
		{
			e.printStackTrace();
		}
	}

	public Level(File file)
	{
		currentItem = new Item(new Position(0, 0));
		enemyList = new ArrayList<Enemy>();
		currentMap = new Map(file, currentItem, this);
	}

	public Map getCurrentMap()
	{
		return currentMap;
	}

	public Item getCurrentItem()
	{
		return currentItem;
	}

	public void setCurrentItem(Item currentItem)
	{
		this.currentItem = currentItem;
	}

	@Override
	public Position getItemPosition()
	{
		return currentItem.getPosition();
	}

	public boolean checkAllVisionFields(Player player)
	{
		for (Enemy enemy : enemyList)
		{
			if (enemy.checkVisionField(player))
				return true;
		}
		return false;
	}

	public void updateItem(Player player)
	{
		if (player.getPosition().equals(currentItem.getPosition()))
		{
			currentItem.setTaken(player.getPosition());
		}
	}

	public boolean hasWon(Player player)
	{
		return (currentItem.isTaken() && currentMap.getSpawnPosition().equals(player.getPosition()));
	}

	@Override
	public ArrayList<Position> getEnemiesPositions()
	{
		ArrayList<Position> positions = new ArrayList<Position>();
		for (Enemy enemy : enemyList)
		{
			positions.add(enemy.getPosition());
		}

		return positions;
	}

	public void moveEnemiesRandomly()
	{
		for (Enemy enemy : enemyList)
		{
			enemy.randomMove(currentMap);
		}

	}

	public void addEnemy(Position position)
	{
		boolean positionAlreadyUsed = false;
		for (Enemy enemy : enemyList)
		{
			if(enemy.getPosition().equals(position))
			{
				positionAlreadyUsed = true;
				enemyList.remove(enemy);
				break;
			}
		}

		if(!positionAlreadyUsed)
			enemyList.add(new Enemy(new Position(position.getX(), position.getY()), Direction.UP));
	}

	public void resetLevel()
	{
		enemyList.clear();
		currentMap.reset();
	}
}
