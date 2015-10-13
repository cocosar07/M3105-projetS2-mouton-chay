package fr.iutvalence.ardechois.stealthgameproject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;
import fr.iutvalence.ardechois.stealthgameproject.model.Blocks;
import fr.iutvalence.ardechois.stealthgameproject.model.Level;
import fr.iutvalence.ardechois.stealthgameproject.model.Position;
import fr.iutvalence.ardechois.stealthgameproject.view.EditorWindow;

public class Editor implements MouseListener
{
	public static final String DEFAULT_MAP_NAME = "tempMap.txt";

	private Level level;

	private EditorWindow editorWindow;

	private File file;

	public Editor()
	{
		this.file = new File(DEFAULT_MAP_NAME);
		this.level = new Level(this.file);
		this.editorWindow = new EditorWindow(level, level.getCurrentMap(), null, this);
	}

	public Editor(String filename)
	{
		this(new File(filename));
	}

	public Editor(File file)
	{
		this.file = file;
		this.level = new Level(this.file);
		this.editorWindow = new EditorWindow(level, level.getCurrentMap(), null, this);
	}

	public void setBlock(Position position, Blocks block)
	{
		try
		{
			level.getCurrentMap().setBlock(position, block);
		} catch (InvalidPositionException e)
		{
			e.printStackTrace();
		}
	}

	public void saveMap(String filename)
	{
		saveMap(new File(filename));
	}

	public void saveMap(File file)
	{
		level.getCurrentMap().saveMapInFile(file, this.level.getCurrentItem().getPosition(), this.level.getEnemiesPositions());
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// Do nothing

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// / Do nothing

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// / Do nothing

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		Position mousePositionBlock = new Position(e.getX() / EditorWindow.PREFERRED_BLOCK_SIZE, e.getY()
				/ EditorWindow.PREFERRED_BLOCK_SIZE);
		switch (e.getButton())
		{
		case MouseEvent.BUTTON1:
			if (!e.isAltDown() && !e.isControlDown() && !e.isShiftDown())
			{
				try
				{
					level.getCurrentMap().setBlock(mousePositionBlock, level.getCurrentMap().getBlock(mousePositionBlock).getNext());
				} catch (InvalidPositionException e1)
				{
					System.out.println("Mouse button pressed out of map.");
				}
			} else if (e.isAltDown() && !e.isControlDown() && !e.isShiftDown())
			{
				level.getCurrentMap().setSpawnPosition(mousePositionBlock);
			} else if (!e.isAltDown() && e.isControlDown() && !e.isShiftDown())
			{
				level.getCurrentItem().setPosition(mousePositionBlock);
			} else if (!e.isAltDown() && !e.isControlDown() && e.isShiftDown())
			{
				level.addEnemy(mousePositionBlock);
			}
			break;

		case MouseEvent.BUTTON3:
			saveMap(file);
			System.out.println("Map saved in " + file.getName());
			break;

		case MouseEvent.BUTTON2:
			level.resetLevel();
			break;
		}

		updateEditorWindow();
	}

	private void updateEditorWindow()
	{
		editorWindow.invalidate();
		editorWindow.repaint();
		editorWindow.validate();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// / Do nothing

	}
}
