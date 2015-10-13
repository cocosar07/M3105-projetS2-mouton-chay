package fr.iutvalence.ardechois.stealthgameproject.model;

import java.io.File;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;

/**
 * Model of the game.
 * @author chayc
 *
 */
public class SGPModel
{
	/**
	 * Player of the game.
	 */
	private Player player;

	/**
	 * Current level.
	 */
	private Level currentLevel;

	/**
	 * Default constructor.
	 */
	public SGPModel()
	{
		currentLevel = new Level();
		player = new Player(currentLevel.getCurrentMap().getSpawnPosition());
	}
	
	/**
	 * Create the model with the given filename.
	 * @param filename
	 */
	public SGPModel(String filename)
	{
		currentLevel = new Level(new File(filename));
		player = new Player(currentLevel.getCurrentMap().getSpawnPosition());
	}

	/**
	 * Move in the given direction.
	 * @param direction
	 */
	public void move(Direction direction)
	{
		try
		{
			player.move(direction, currentLevel.getCurrentMap());
			
			currentLevel.moveEnemies();
			
			currentLevel.updateItem(player);

		} catch (InvalidPositionException e)
		{
			// Do nothing
		}
	}
	
	/**
	 * Return true if the player has won.
	 * @return
	 */
	public boolean hasWon()
	{
		return this.currentLevel.hasWon(player);
	}

	/**
	 * Return true if the player has lose.
	 * @return
	 */
	public boolean hasLose()
	{
		return (currentLevel.checkAllVisionFields(player));
	}

	/**
	 * Get the current player.
	 * @return
	 */
	public Player getPlayer()
	{
		return this.player;
	}
	
	/**
	 * Get the current level.
	 * @return
	 */
	public Level getLevel()
	{
		return currentLevel;
	}
}
