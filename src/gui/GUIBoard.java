package gui;

import game.Control;
import board.Position;
import board.Board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actors.ActorPlayer;
 
public class GUIBoard extends JFrame {

	private Control control;
	private JPanel panel;

	public GUIBoard(Control control) {
		this.control = control;
		this.panel = new JPanel();
		this.control.setGuiBoard(this);

		this.setFrame();
		this.setButtons();
	}

	private void setFrame() {
		this.setLayout(null);
		this.setSize(new Dimension(800, 600));
		this.panel.setSize(new Dimension(this.getSize().height - 30, this.getSize().width - 30));
		this.panel.setBackground(new Color(111));
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public Board setBoard(List<Position> positions) {
		ImageIcon icon = new ImageIcon("resources/grassTile.png");
		icon.setImage(icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

		JButton firstTile = new JButton();
		firstTile.setPreferredSize(new Dimension(15, 15));
		firstTile.setIcon(icon);
		firstTile.setContentAreaFilled(false);
		firstTile.setLayout(null);
		firstTile.setActionCommand("grass");
		firstTile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "grass") {
					System.out.println("clicked grass");
				}
			}
		});
		this.panel.add(firstTile);
		firstTile.setBounds(0, 0, 15, 15);
		firstTile.setVisible(true);


		JButton secondTile = new JButton();
		secondTile.setPreferredSize(new Dimension(15, 15));
		secondTile.setIcon(icon);
		secondTile.setContentAreaFilled(false);
		secondTile.setLayout(null);
		secondTile.setActionCommand("grass");
		secondTile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "grass") {
					System.out.println("clicked grass");
				}
			}
		});
		this.panel.add(secondTile);
		secondTile.setBounds(0, 0, 15, 15);
		secondTile.setVisible(true);

		// Board board = new Board(50, 50);
		return null;
	}


	private void setButtons() {
		JButton changeTurnButton = setChangeTurnButton();
	}

	private JButton setChangeTurnButton() {
		return new JButton();
	}

	public void tellTurn(boolean turn) { 
		if (turn) {
			JOptionPane.showMessageDialog(this, "It's your turn.");		
		} else {
			JOptionPane.showMessageDialog(this, "Wait for opponent to move.");
		}
	}

	public void tellItsTurn() {
	}

}
