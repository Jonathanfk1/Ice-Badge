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

import board.BoardSide;
import game.Control;

public class GUIMainMenu extends JFrame {

	private static final long serialVersionUID = 668941998137019373L;
	protected JPanel panel;
	protected Control control;
	protected JTextArea connectedText;
	protected JTextArea roomStartedText;
	protected String playerName;

	public GUIMainMenu(Control control) {
		this.control = control;
		this.panel = new JPanel();

		this.setFrame();
		this.setButtons();
		this.setLabels();
	}

	private void setLabels() {
		this.connectedText = new JTextArea("Connected");
		// buttonsGbc.gridx++;
		this.connectedText.setVisible(false);
		this.panel.add(connectedText);

		this.roomStartedText = new JTextArea("Room Started");
		// buttonsGbc.gridx++;
		this.roomStartedText.setVisible(false);
		this.panel.add(roomStartedText); 
	}

	public void setFrame() {
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.add(panel, gbc);
		this.setVisible(true);
		this.setSize(new Dimension(700, 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public String getConnectionIp() {
		String ip = (String)JOptionPane.showInputDialog(
                this,
                "Enter the IP address",
                "Enter info",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "localhost");
		return ip;
	}
	
	public String getConnectionName() {
		this.playerName = (String)JOptionPane.showInputDialog(
                this,
                "Enter your name",
                "Enter info",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		return this.playerName;
	}

	public void informNotConnected() {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "You're not connected, can't start game.", "Not connected", JOptionPane.ERROR_MESSAGE);
	}

	public void setButtons() {

		GridBagConstraints gbc = new GridBagConstraints();

		// ################## CONNECT ##################
		JButton connect = new JButton("CONNECT");
		gbc.gridx = 0;
		gbc.gridy = 0;
		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "CONNECT") {
					control.connectToNetGames();
				}
			}
		});
		this.panel.add(connect, gbc);

		// ################## DISCONNECT ##################
		JButton disconnect = new JButton("DISCONNECT");
		gbc.gridy++;
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "DISCONNECT") {
					control.disconnect();;
				}
			}
		});
		this.panel.add(disconnect, gbc);

		// ################## START GAME ##################

		JButton startGame = new JButton("START GAME");
		gbc.gridy++;
		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "START GAME") {
					control.askToStartGame();
				}

			}
		});
		this.panel.add(startGame, gbc);

		// ################## EXIT ##################

		JButton exit = new JButton("EXIT");
		gbc.gridy++;
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "EXIT") {
					System.exit(0);
				}

			}
		});
		this.panel.add(exit, gbc);

	}

	public void warnConnectionTrial() {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "One of the players is not ready to start.", "Start Game received", JOptionPane.PLAIN_MESSAGE);
	}

	public void listOfCharactersReceived() {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "List of characters received.", "Chars received", JOptionPane.PLAIN_MESSAGE);
	}

	public void setConnectedText(boolean isConnected) {
		this.connectedText.setVisible(isConnected);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void setRoomStartedText(boolean isRoomStarted) {
		this.roomStartedText.setVisible(isRoomStarted);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void informRoomAlreadyStarted() {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "Room is already started.", "Room already started", JOptionPane.PLAIN_MESSAGE);
	}

	public BoardSide askForBoardSide() {
			Object[] options = {"UP",
			"DOWN"};
			int n = JOptionPane.showOptionDialog(this,
			"Chose your side of the board",
			"Choose side",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[1]);
			if (n == 0) {
				return BoardSide.UP;
			} else {
				return BoardSide.DOWN;
			}
	}

	public String getPlayerName() {
		return this.playerName;
	}

}
