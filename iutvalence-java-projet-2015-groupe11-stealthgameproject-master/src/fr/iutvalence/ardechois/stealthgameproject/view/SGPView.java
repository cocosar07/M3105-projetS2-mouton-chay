package fr.iutvalence.ardechois.stealthgameproject.view;

import java.awt.event.KeyListener;

/**
 * The view.
 * 
 * @author kelemenn
 *
 */
public class SGPView
{
	/**
	 * The game window.
	 * 
	 * @see GameWindow
	 */
	private GameWindow gameWindow;
	
	/**
	 * Constructor with levelGetter, mapGetter, playerGetter and keyListener parameters.
	 * 
	 * @param levelGetter
	 * @param mapGetter
	 * @param playerGetter
	 * @param keyListener
	 */
	public SGPView(LevelGetter levelGetter, MapGetter mapGetter, PlayerGetter playerGetter, KeyListener keyListener)
	{
		gameWindow = new GameWindow(levelGetter, mapGetter, playerGetter, keyListener);
	}

	/**
	 * Allow to update the window.
	 */
	public void updateWindow()
	{
		gameWindow.repaint();
	}

	/**
	 * Close the window AND the application.
	 */
	public void closeWindowAndApp()
	{
		this.closeWindow();
		// Application don't stop if we don't exit it.
		System.exit(0);
	}

	/**
	 * Close the window WITHOUT closing the application.
	 */
	public void closeWindow()
	{
		gameWindow.dispose();
	}
}
