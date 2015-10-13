package fr.iutvalence.ardechois.stealthgameproject.model;

import java.io.File;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;

public class SGPModel
{
	private Player player;
	private Level currentLevel;

	public SGPModel()
	{
		currentLevel = new Level();
		player = new Player(currentLevel.getCurrentMap().getSpawnPosition());
	}
	
	public SGPModel(String filename)
	{
		currentLevel = new Level(new File(filename));
		player = new Player(currentLevel.getCurrentMap().getSpawnPosition());
	}

	public void updateGameAfterMovingThePlayer(Direction direction)
	{
		try
		{
			player.move(direction, currentLevel.getCurrentMap());
			
			currentLevel.moveEnemiesRandomly();
			
			currentLevel.updateItem(player);

		} catch (InvalidPositionException e)
		{
			// Do nothing
		}
	}
	
	public boolean hasWon()
	{
		return this.currentLevel.hasWon(player);
	}

	public boolean hasLose()
	{
		return (currentLevel.checkAllVisionFields(player));
	}

	public Player getPlayer()
	{
		return this.player;
	}
	
	public Level getLevel()
	{
		return currentLevel;
	}
}
