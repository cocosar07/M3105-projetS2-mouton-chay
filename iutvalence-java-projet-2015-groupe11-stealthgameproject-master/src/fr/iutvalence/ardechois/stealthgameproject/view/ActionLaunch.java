package fr.iutvalence.ardechois.stealthgameproject.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.iutvalence.ardechois.stealthgameproject.Editor;
import fr.iutvalence.ardechois.stealthgameproject.controller.SGPController;

/**
 * Action when you click on a button.
 * 
 * @author kelemenn
 *
 */
public class ActionLaunch extends AbstractAction {
	
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The action type.
	 */
	private ActionType actionType;
	/**
	 * The window.
	 */
	private JFrame frame;
	
	/**
	 * Constructor with ActionType and JFrame parameter. 
	 * 
	 * @param actionType
	 * @param frame
	 */
	public ActionLaunch(ActionType actionType, JFrame frame) {
		this.actionType = actionType;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		
		String choice;
		
		switch(this.actionType)
		{
		case PLAY:
			choice = JOptionPane.showInputDialog(frame, "Which map to open? (without extension)", null);
			SGPController game = new SGPController(choice + ".txt");
			break;
		case EDITOR:
			choice = JOptionPane.showInputDialog(frame, "Which map to open? (without extension)", null);
			Editor editor = new Editor(choice + ".txt");
			break;
		default:
			
			break;
		}
	}

}
