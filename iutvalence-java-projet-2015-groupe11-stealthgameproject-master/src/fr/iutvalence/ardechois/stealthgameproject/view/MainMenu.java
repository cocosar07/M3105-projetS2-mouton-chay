package fr.iutvalence.ardechois.stealthgameproject.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The application main menu.
 * 
 * @author vanbossm
 *
 */
public class MainMenu extends JFrame {
	/**
	 * The height space between buttons.
	 */
	private static final int HEIGHT_SPACE_BETWEEN_BUTTONS = 70;
	/**
	 * The window height.
	 */
	public static final int WINDOW_HEIGHT = 670;
	/**
	 * The window width.
	 */
	public static final int WINDOW_WIDTH = 808;
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * Default constructor.
	 */
	public MainMenu() {
		this.setTitle("Stealth Game Project");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    
	    this.setLayout(new FlowLayout(FlowLayout.CENTER,WINDOW_WIDTH,HEIGHT_SPACE_BETWEEN_BUTTONS));
	    
	    JLabel title=new JLabel("Stealth Game Project");
	    title.setHorizontalAlignment(SwingConstants.CENTER);
	    title.setFont(new Font("Linux", Font.PLAIN, 50));
	    this.getContentPane().add(title, BorderLayout.NORTH);
	    
	    final JButton play=new JButton(new ActionLaunch(ActionType.PLAY, this));
	    play.setText("Play");
	    play.setPreferredSize(new Dimension(200,50));
	    play.setHorizontalAlignment(SwingConstants.CENTER);
	    play.setFont(new Font("Linux", Font.PLAIN, 20));
	    this.getContentPane().add(play,BorderLayout.CENTER);
	    
	    JButton editor=new JButton(new ActionLaunch(ActionType.EDITOR, this));
	    editor.setText("Editor");
	    editor.setPreferredSize(new Dimension(200,50));
	    editor.setHorizontalAlignment(SwingConstants.CENTER);
	    editor.setFont(new Font("Linux", Font.PLAIN, 20));
	    this.getContentPane().add(editor,BorderLayout.CENTER);
	    
	    JButton quit=new JButton(new ActionLaunch(ActionType.QUIT, this));
	    quit.setText("Quit");
	    quit.setPreferredSize(new Dimension(200,50));
	    quit.setHorizontalAlignment(SwingConstants.CENTER);
	    quit.setFont(new Font("Linux", Font.PLAIN, 20));
	    this.getContentPane().add(quit,BorderLayout.CENTER);
	    }
}	
