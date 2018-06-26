package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import game.Control;
import game.TypeCharacter;
import netgames.Message;
import netgames.MessageType;

public class GUISelectCharacter extends JFrame {

	private static final long serialVersionUID = 862549634422345844L;
	protected Control control;
	protected JPanel panel;
	protected JFrame parent;
	protected JButton readyButton;
	protected JButton startGameButton;
	protected JTextArea selectedCharactersText;
	protected JTextArea isReadyToStart;
	protected GridBagConstraints buttonsGbc;

	public GUISelectCharacter(Control control, JFrame parent) {
		this.control = control;
		this.parent = parent;
		this.panel = new JPanel();
		this.buttonsGbc = new GridBagConstraints();
		this.setFrame();
		this.setButtons();
		this.setStartButton();
	}


	private void setReadyButton() {
		this.readyButton = new JButton("Ready");
		this.readyButton.setVisible(false);
		this.add(this.readyButton);
		buttonsGbc.gridy++;
		this.readyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Ready") {
					control.toggleIsReadyToStart();
					updateReadyText();
					if (!control.boardSidesSet()) {
						control.getGame().getPlayer().setBoardSide(control.askForBoardSide());
					}
					Message message = new Message(MessageType.PLAYER_READY, control.getGame().getPlayer().getBoardSide());
					control.getActorNetGames().sendMessage(message);			
				}
			}
		});
	}


	private void setStartButton() {
		this.startGameButton = new JButton("Begin Game");
		this.startGameButton.setVisible(false);
		this.add(this.startGameButton);
		buttonsGbc.gridy++;
		this.startGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Begin Game") {
					control.sendStartGameMessage();
				}
			}
		});
	}

	private void setFrame() {
		this.setLayout(new GridBagLayout());
		// this.add(this.parent, gbc);
		this.setSize(new Dimension(1100, 300));
		this.setLocation(500, 500);	
		this.add(this.panel);
		// this.pack();
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void setButtons() {
		JButton swordsman = new JButton("SWORDSMAN");
		buttonsGbc.gridx = 0;
		buttonsGbc.gridy = 1;
		swordsman.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "SWORDSMAN") {
					control.selectCharacter(TypeCharacter.SWORDSMAN);
				}
			}
		});
		this.panel.add(swordsman, buttonsGbc);
		
		
		JButton archer = new JButton("ARCHER");
		buttonsGbc.gridx++;
		archer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "ARCHER") {
					control.selectCharacter(TypeCharacter.ARCHER);
				}
			}
		});
		this.panel.add(archer, buttonsGbc);
		
		
		JButton clerig = new JButton("CLERIG");
		buttonsGbc.gridy++;
		clerig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "CLERIG") {
					control.selectCharacter(TypeCharacter.CLERIG);
				}
			}
		});
		this.panel.add(clerig, buttonsGbc);
		
		
		JButton bard = new JButton("BARD");
		buttonsGbc.gridy++;
		bard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "BARD") {
					control.selectCharacter(TypeCharacter.BARD);
				}
			}
		});
		this.panel.add(bard, buttonsGbc);
		

		JButton removeLast = new JButton("Remove last");
		buttonsGbc.gridx++;
		removeLast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "Remove last") {
					control.removeLastCharacter();
				}
			}
		});
		this.panel.add(removeLast, buttonsGbc);

		this.selectedCharactersText = new JTextArea("0 characters selected. (Max.: 6)");
		buttonsGbc.gridx++;
		this.panel.add(selectedCharactersText);

		this.setReadyButton();	  

		this.isReadyToStart = new JTextArea("  -> Ready");
		buttonsGbc.gridx++;
		this.isReadyToStart.setVisible(false);
		this.panel.add(isReadyToStart); 
		
	}

	private void updateReadyText() {
		boolean shouldBeVisible = this.control.getActorNetGames().getIsReadyToStart();
		this.isReadyToStart.setVisible(shouldBeVisible);
		this.startGameButton.setVisible(shouldBeVisible);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void updateCharactersCount(int size) {
		this.selectedCharactersText.setText(size + " characters selected. (Max.: 6)");
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void showStartButton(boolean show) {
		startGameButton.setVisible(show);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void tellSelectionListIsEmpty() {
		JOptionPane.showMessageDialog(this, "Selection list already empty, can't remove.", "List empty", JOptionPane.ERROR_MESSAGE);
	}

	public void showReadyButton(boolean show) {
		readyButton.setVisible(show);
		SwingUtilities.updateComponentTreeUI(this);
	}
}
