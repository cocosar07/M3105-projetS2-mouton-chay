package fr.iutvalence.ardechois.stealthgameproject.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;
import fr.iutvalence.ardechois.stealthgameproject.model.Position;

public class DisplayMap extends JPanel
{
	private static final long serialVersionUID = 1L;

	public static final int NUM_COLS = 50;
	public static final int NUM_ROWS = 40;

	public static final String PLAYER_FILENAME = "/player.png";
	public static final String SPAWN_FILENAME = "/spawn.png";
	public static final String ITEM_FILENAME = "/item.png";
	public static final String ENEMY_FILENAME = "/enemy.png";

	private int mapWidth;
	private int mapHeight;
	private int preferredBlockSize;
	private MapGetter mapGetter;
	private PlayerGetter playerGetter;
	private LevelGetter levelGetter;

	private Icon[][] groundGrid;
	private Icon playerIcon;
	private Icon spawnIcon;
	private Icon itemIcon;
	private Icon enemyIcon;

	public DisplayMap(LevelGetter levelGetter, MapGetter mapGetter, PlayerGetter playerGetter, int preferredBlockSize)
	{
		this.mapGetter = mapGetter;
		this.playerGetter = playerGetter;
		this.levelGetter = levelGetter;

		this.mapWidth = mapGetter.getMapWidth();
		this.mapHeight = mapGetter.getMapHeight();
		this.preferredBlockSize = preferredBlockSize;

		if (playerGetter != null)
			playerIcon = new ImageIcon(getClass().getResource(PLAYER_FILENAME));
		
		spawnIcon = new ImageIcon(getClass().getResource(SPAWN_FILENAME));
		itemIcon = new ImageIcon(getClass().getResource(ITEM_FILENAME));
		enemyIcon = new ImageIcon(getClass().getResource(ENEMY_FILENAME));

		updateGroundGrid();

		int preferredWidth = mapWidth * this.preferredBlockSize;
		int preferredHeight = mapWidth * this.preferredBlockSize;
		setPreferredSize(new Dimension(preferredWidth, preferredHeight));

	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		g.clearRect(0, 0, getWidth(), getHeight());

		int rectWidth = this.preferredBlockSize;
		int rectHeight = this.preferredBlockSize;

		updateGroundGrid();

		for (int i = 0; i < mapWidth; i++)
		{
			for (int j = 0; j < mapHeight; j++)
			{
				// Upper left corner of this terrain rect
				int x = i * rectWidth;
				int y = j * rectHeight;
				Icon groundIcon = groundGrid[i][j];
				if (groundIcon != null)
					g.drawImage(((ImageIcon) groundIcon).getImage(), x, y, null);
			}
		}
		
		g.drawImage(((ImageIcon) spawnIcon).getImage(), mapGetter.getSpawnPosition().getX() * rectWidth, mapGetter.getSpawnPosition().getY() * rectHeight, null);
		
		ArrayList<Position> enemiesPositions = levelGetter.getEnemiesPositions();
		
		for(Position currentPos : enemiesPositions)
		{
			g.drawImage(((ImageIcon) enemyIcon).getImage(), currentPos.getX() * rectWidth, currentPos.getY() * rectHeight, null);
		}
		
		g.drawImage(((ImageIcon) itemIcon).getImage(), levelGetter.getItemPosition().getX() *rectWidth,  levelGetter.getItemPosition().getY() *rectHeight, null);

		if (playerGetter != null)
		{
			Position playerPosition = new Position(playerGetter.getPosition().getX() * rectWidth, playerGetter.getPosition().getY()
					* rectHeight);
			g.drawImage(((ImageIcon) playerIcon).getImage(), playerPosition.getX(), playerPosition.getY(), null);
		}
	}

	private void updateGroundGrid()
	{
		this.groundGrid = new Icon[mapWidth][mapHeight];
		for (int i = 0; i < mapWidth; i++)
		{
			for (int j = 0; j < mapHeight; j++)
			{
				try
				{
					if (mapGetter.getBlock(new Position(i, j)) != null)
						this.groundGrid[i][j] = mapGetter.getBlock(new Position(i, j)).getIcon();
				}
				catch (InvalidPositionException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
