package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Control;

public class GUIMainMenu extends JFrame {

	private static final long serialVersionUID = 668941998137019373L;
	protected JFrame frame_;
	protected JPanel panel_;
	protected Control control_;

	public GUIMainMenu(Control control) {
		this.control_ = control;
		this.panel_ = new JPanel();

		this.setFrame();
		this.setButtons();
	}

	public void setFrame() {
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.add(panel_, gbc);
		this.setVisible(true);
		this.setSize(new Dimension(450, 400));
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

	public void informNotConnected() {
		JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "You're not connected, can't start game.", "Not connected", JOptionPane.ERROR_MESSAGE);
	}
	
	public String getConnectionName() {
		String name = (String)JOptionPane.showInputDialog(
                this,
                "Enter your name",
                "Enter info",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		return name;
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
					control_.connectToNetGames();
				}
			}
		});
		this.panel_.add(connect, gbc);

		// ################## DISCONNECT ##################
		JButton disconnect = new JButton("DISCONNECT");
		gbc.gridy++;
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "DISCONNECT") {
					control_.disconnect();;
				}
			}
		});
		this.panel_.add(disconnect, gbc);

		// ################## START GAME ##################

		JButton startGame = new JButton("START GAME");
		gbc.gridy++;
		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "START GAME") {
					
					// control_.startGame();
					
					control_.openSelectCharacterMenu();

					// new GUIBoard(control_);
					
					// control_.delegateCreateNewGameToActorPlayer(1);
				}

			}
		});
		this.panel_.add(startGame, gbc);

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
		this.panel_.add(exit, gbc);

	}

}
