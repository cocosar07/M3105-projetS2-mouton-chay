package fr.iutvalence.ardechois.stealthgameproject.view;

import java.util.ArrayList;

import fr.iutvalence.ardechois.stealthgameproject.model.Position;

/**
 * The interface for the level.
 * 
 * @author chayc
 *
 */
public interface LevelGetter
{
	/**
	 * Getter for the level. 
	 * 
	 * @return
	 */
	Position getItemPosition();
	
	ArrayList<Position> getEnemiesPositions();
}
