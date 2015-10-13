package fr.iutvalence.ardechois.stealthgameproject.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JOptionPane;

import fr.iutvalence.ardechois.stealthgameproject.model.Direction;
import fr.iutvalence.ardechois.stealthgameproject.model.SGPModel;
import fr.iutvalence.ardechois.stealthgameproject.view.SGPView;

/**
 * Game controller.
 * 
 * @author antoine
 *
 */
public class SGPController implements KeyListener
{
	/**
	 * Model.
	 */
	private SGPModel model;

	/**
	 * View.
	 */
	private SGPView view;

	/**
	 * Map to convert string into direction.
	 */
	public HashMap<String, Direction> directionHashMap;

	/**
	 * Current map filename.
	 */
	private String filename;

	/**
	 * Default constructor.
	 */
	public SGPController()
	{
		setHashMap();
		this.model = new SGPModel();
		this.view = new SGPView(model.getLevel(), model.getLevel().getCurrentMap(), model.getPlayer(), this);
	}

	/**
	 * Constructor with a given filename.
	 * 
	 * @param filename
	 */
	public SGPController(String filename)
	{
		this.filename = filename;
		setHashMap();
		this.model = new SGPModel(filename);
		this.view = new SGPView(model.getLevel(), model.getLevel().getCurrentMap(), model.getPlayer(), this);
	}

	/**
	 * Set the hash map.
	 */
	private void setHashMap()
	{
		this.directionHashMap = new HashMap<String, Direction>();
		this.directionHashMap.put("UP", Direction.UP);
		this.directionHashMap.put("DOWN", Direction.DOWN);
		this.directionHashMap.put("LEFT", Direction.LEFT);
		this.directionHashMap.put("RIGHT", Direction.RIGHT);
	}

	/**
	 * Move the player and all the game.
	 * 
	 * @param direction
	 */
	public void move(Direction direction)
	{
		model.move(direction);
		if (model.hasWon())
		{
			JOptionPane.showMessageDialog(null, "You won the game!");
			this.view.closeWindowAndApp();
		}
		if (model.hasLose())
		{
			JOptionPane.showMessageDialog(null, "You loose :(");
			this.view.closeWindow();
			this.model = new SGPModel(filename);
			this.view = new SGPView(model.getLevel(), model.getLevel().getCurrentMap(), model.getPlayer(), this);
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// / Do nothing

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		switch (key)
		{
		case KeyEvent.VK_UP:
			this.move(Direction.UP);
			break;

		case KeyEvent.VK_DOWN:
			this.move(Direction.DOWN);
			break;

		case KeyEvent.VK_LEFT:
			this.move(Direction.LEFT);
			break;

		case KeyEvent.VK_RIGHT:
			this.move(Direction.RIGHT);
			break;
		}

		this.view.updateWindow();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// / Do nothing
	}
}
