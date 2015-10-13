package fr.iutvalence.ardechois.stealthgameproject.view;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;
import fr.iutvalence.ardechois.stealthgameproject.model.Blocks;
import fr.iutvalence.ardechois.stealthgameproject.model.Position;

/**
 * Interface for the map.
 * 
 * @author chayc
 *
 */
public interface MapGetter
{
	/**
	 * Getter for the block at the asked position.
	 * 
	 * @param position
	 * @return
	 * @throws InvalidPositionException
	 */
	public Blocks getBlock(Position position) throws InvalidPositionException;
	
	/**
	 * Getter for the map width.
	 * 
	 * @return
	 */
	public int getMapWidth();

	/**
	 * Getter for the map height.
	 * 
	 * @return
	 */
	public int getMapHeight();

	/**
	 * Getter for the spawn position.
	 * 
	 * @return
	 */
	public Position getSpawnPosition();

	/**
	 * Setter for the spawn position at the asked spawnPosition.
	 * 
	 * @param spawnPosition
	 */
	public void setSpawnPosition(Position spawnPosition);
}
