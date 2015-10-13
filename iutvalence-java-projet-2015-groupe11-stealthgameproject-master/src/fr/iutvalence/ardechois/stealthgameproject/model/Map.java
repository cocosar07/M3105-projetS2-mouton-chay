package fr.iutvalence.ardechois.stealthgameproject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidMapSizeException;
import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;
import fr.iutvalence.ardechois.stealthgameproject.view.MapGetter;

public class Map implements MapGetter
{
	public static final int MAX_MAP_WIDTH = 50;

	public static final int MAX_MAP_HEIGHT = 40;

	private static final int DEFAULT_MAP_WIDTH = 10;

	private static final int DEFAULT_MAP_HEIGHT = 10;

	private Blocks[][] grid;

	private HashMap<Character, Blocks> hashMap;

	private Position spawnPosition;

	public Map() throws InvalidMapSizeException
	{
		this(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
	}

	public Map(int width, int height) throws InvalidMapSizeException
	{
		setHashMap();
		
		spawnPosition = new Position(0, 0);

		if (width < 0 || height < 0 || width > MAX_MAP_WIDTH || height > MAX_MAP_HEIGHT)
			throw new InvalidMapSizeException();

		this.grid = new Blocks[width][height];

		for (int x = 0; x < getMapWidth(); x++)
		{
			for (int y = 0; y < getMapHeight(); y++)
			{
				this.grid[x][y] = Blocks.FLOOR;
			}
		}
	}

	public Map(String filename, Item currentItem, Level level) throws InvalidMapSizeException
	{
		this(new File(filename), currentItem, level);
	}

	public Map(File file, Item currentItem, Level level)
	{
		setHashMap();
		
		spawnPosition = new Position(0, 0);
		
		try
		{
			loadMapFromFile(file, currentItem, level);
		} catch (InvalidMapSizeException e)
		{
			e.printStackTrace();
		}
	}

	private void setHashMap()
	{
		this.hashMap = new HashMap<Character, Blocks>();
		for (Blocks block : Blocks.values())
		{
			this.hashMap.put(Character.valueOf(block.getId()), block);
		}
	}

	public int getMapWidth()
	{
		if(grid !=null)
			return grid.length;
		return 0;
	}

	public int getMapHeight()
	{
		if(grid != null)
			return grid[0].length;
		return 0;
	}

	public Blocks getBlock(Position position) throws InvalidPositionException
	{
		if(isPositionValid(position))
		{
			throw new InvalidPositionException();
		}

		return grid[position.getX()][position.getY()];
	}

	public void setBlock(Position position, Blocks block) throws InvalidPositionException
	{
		if(isPositionValid(position))
		{
			throw new InvalidPositionException();
		}

		grid[position.getX()][position.getY()] = block;
	}

	private boolean isPositionValid(Position position) throws InvalidPositionException
	{
		if (position.getX() < 0 || position.getY() < 0 || position.getX() >= getMapWidth() || position.getY() >= getMapHeight())
			return false;
		
		return true;
	}

	public void loadMapFromFile(String filename, Item currentItem, Level level) throws InvalidMapSizeException
	{
		loadMapFromFile(new File(filename), currentItem, level);
	}

	public void loadMapFromFile(File file, Item currentItem, Level level) throws InvalidMapSizeException
	{
		try
		{
			FileReader fileReader = new FileReader(file);

			int width = fileReader.read();
			int height = fileReader.read();

			if (width < 0 || width > MAX_MAP_WIDTH || height < 0 || height > MAX_MAP_HEIGHT)
			{
				fileReader.close();
				throw new InvalidMapSizeException();
			}

			this.grid = new Blocks[width][height];

			for (int lineNumber = 0; lineNumber < height; lineNumber++)
			{
				for (int columnNumber = 0; columnNumber < width; columnNumber++)
				{
					grid[columnNumber][lineNumber] = hashMap.get((char) fileReader.read());
				}
			}
			
			spawnPosition.setPosition((int) fileReader.read(), (int) fileReader.read());
			
			if(currentItem != null)
				currentItem.setPosition((int) fileReader.read(), (int) fileReader.read());
			
			while(fileReader.ready())
			{
				level.addEnemy(new Position((int) fileReader.read(), (int) fileReader.read()));
			}

			fileReader.close();

		} catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
			System.exit(1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveMapInFile(String filename, Position itemPosition, ArrayList<Position> enemiesPositions)
	{
		saveMapInFile(new File(filename), itemPosition, enemiesPositions);
	}

	public void saveMapInFile(File file, Position itemPosition, ArrayList<Position> enemiesPositions)
	{
		try
		{
			FileWriter fileWriter = new FileWriter(file);

			fileWriter.write(getMapWidth());
			fileWriter.write(getMapHeight());

			for (int lineNumber = 0; lineNumber < getMapHeight(); lineNumber++)
			{
				for (int columnNumber = 0; columnNumber < getMapWidth(); columnNumber++)
				{
					if (grid[columnNumber][lineNumber] != null)
						fileWriter.write(grid[columnNumber][lineNumber].getId());
				}
			}
			
			fileWriter.write(spawnPosition.getX());
			fileWriter.write(spawnPosition.getY());
			
			fileWriter.write(itemPosition.getX());
			fileWriter.write(itemPosition.getY());
			
			for(Position pos : enemiesPositions)
			{
				fileWriter.write(pos.getX());
				fileWriter.write(pos.getY());
			}

			fileWriter.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void resetAllMapBlocksAsGrass()
	{
		for (int lineNumber = 0; lineNumber < getMapHeight(); lineNumber++)
		{
			for (int columnNumber = 0; columnNumber < getMapWidth(); columnNumber++)
			{
				grid[columnNumber][lineNumber] = Blocks.GRASS;
			}
		}

	}

	public Position getSpawnPosition()
	{
		return this.spawnPosition;
	}

	public void setSpawnPosition(Position spawnPosition)
	{
		this.spawnPosition = spawnPosition;
	}
}
