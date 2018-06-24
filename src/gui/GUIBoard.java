package gui;

import game.Control;
import board.Position;
import board.TypeTile;
import board.Board;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	private TypeTile[][] map;
	private Board board;
	private ImageIcon grassIcon;
	private ImageIcon baseIcon;
	private ImageIcon treeIcon;
	private ImageIcon waterIcon;
	private ImageIcon char1Icon;
	private ImageIcon char2Icon;
	private ImageIcon char3Icon;
	private ImageIcon mountainIcon;
	private ImageIcon rockIcon;

	public GUIBoard(Control control) {
		super();
		this.control = control;
		this.board = control.getGame().getBoard();
		this.control.setGuiBoard(this);

		this.setFrame();
		this.setPanel();
		this.setBoard();
		this.add(this.panel);
		this.pack();
		this.setVisible(true);
		// this.setButtons();
	}

	private void setFrame() {
		// this.setLayout(new GridBagLayout());
		// // this.add(this.parent, gbc);
		// this.setSize(new Dimension(1000, 300));
		// this.setLocation(300, 500);	
		// this.add(this.panel);
		// // this.pack();
		// // this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// this.setVisible(true);
		// this.setLayout(null);
		this.setResizable(false);
		this.setSize(new Dimension(700, 700));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setVisible(true);
	}

	public void setPanel() {
		this.panel = new JPanel();
		// this.panel.setSize(new Dimension(500, 500));
		this.panel.setBackground(new Color(111));
        this.panel.setLayout(new GridLayout(this.board.getRowSize()-1, this.board.getColumnSize()-1));
		this.panel.setPreferredSize(new Dimension(500, 500));
	}
	
	public void updateMap() {
		this.map = this.board.mapPositions();
	}

	public void setMap(TypeTile[][] tileTypes) {
		this.map = tileTypes;
	}

	public TypeTile[][] getMap() {
		return this.map;
	}

	public void setIconImages() {
		ImageIcon grassIcon = new ImageIcon("resources/grassTile.png");
		grassIcon.setImage(grassIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.grassIcon = grassIcon;

		ImageIcon baseIcon = new ImageIcon("resources/baseIcon.png");
		baseIcon.setImage(baseIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.baseIcon = baseIcon;

		ImageIcon treeIcon = new ImageIcon("resources/treeIcon.png");
		treeIcon.setImage(treeIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.treeIcon = treeIcon;

		ImageIcon mountainIcon = new ImageIcon("resources/mountainIcon.png");
		mountainIcon.setImage(mountainIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.mountainIcon = mountainIcon;

		ImageIcon waterIcon = new ImageIcon("resources/waterIcon.png");
		waterIcon.setImage(waterIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.waterIcon = waterIcon;

		ImageIcon char1Icon = new ImageIcon("resources/char1Icon.png");
		char1Icon.setImage(char1Icon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.char1Icon = char1Icon;

		ImageIcon char2Icon = new ImageIcon("resources/char2Icon.png");
		char2Icon.setImage(char2Icon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.char2Icon = char2Icon;

		ImageIcon rockIcon = new ImageIcon("resources/rockIcon.png");
		rockIcon.setImage(rockIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		this.rockIcon = rockIcon;

	}


	public Board setBoard() {
		Position[][] listOfPositions = this.board.getPositions();
		
		// ImageIcon icon = new ImageIcon("resources/grassTile.png");
		// icon.setImage(icon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		// Board board = new Board(50, 50);
		
		ImageIcon icon = new ImageIcon("resources/grassTile.png");
		icon.setImage(icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

		
		setIconImages();
		updateMap();

		for (int i = 0; i < board.getRowSize()-1 ; i++) {
			for (int j = 0; j < board.getColumnSize()-1; j++) {
				JButton tile = new JButton();
				tile.setPreferredSize(new Dimension(20, 20));
				tile.setContentAreaFilled(false);
				// secondTile.setLayout(null);
				TypeTile typeOfTile = this.map[i][j];
				switch(typeOfTile) {
					case GRASS:
						tile.setIcon(icon);
						tile.setActionCommand("grass");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "grass") {
									System.out.println("clicked grass");
								}
							}
						});
						this.panel.add(tile);
						break;
					case WATER:
						tile.setIcon(this.waterIcon);
						tile.setActionCommand("water");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "water") {
									System.out.println("clicked water");
								}
							}
						});
						this.panel.add(tile);
						break;
					case MOUNTAIN:
						tile.setIcon(this.mountainIcon);
						tile.setActionCommand("mountain");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "mountain") {
									System.out.println("clicked mountain");
								}
							}
						});
						this.panel.add(tile);
						break;
					case ROCK:
						tile.setIcon(this.rockIcon);
						tile.setActionCommand("rock");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "rock") {
									System.out.println("clicked rock");
								}
							}
						});
						this.panel.add(tile);
						break;
					case TREE:
						tile.setIcon(treeIcon);
						tile.setActionCommand("tree");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "tree") {
									System.out.println("clicked tree");
								}
							}
						});
						this.panel.add(tile);
						break;
					default:
						break;
					// tile.setIcon(baseIcon);
					// tile.setActionCommand("base");
					// tile.addActionListener(new ActionListener() {
					// 	@Override
					// 	public void actionPerformed(ActionEvent e) {
					// 		if (e.getActionCommand() == "base") {
					// 			System.out.println("clicked base");
					// 		}
					// 	}
					// });
				}
			}
		}

		return null;
	}

	private void testGenerateTiles() {		
		JButton firstTile = new JButton();
		firstTile.setPreferredSize(new Dimension(15, 15));
		// firstTile.setIcon(icon);
		firstTile.setContentAreaFilled(false);
		// firstTile.setLayout(null);
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

		// this.panel.add(firstTile).setLocation(0, 0);
		// firstTile.setBounds(0, 0, 15, 15);
		// firstTile.setVisible(true);


		
		JButton secondTile = new JButton();
		secondTile.setPreferredSize(new Dimension(15, 15));
		// secondTile.setIcon(icon);
		secondTile.setContentAreaFilled(false);
		// secondTile.setLayout(null);
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
		// secondTile.setBounds(0, 0, 15, 15);
		// secondTile.setVisible(true);

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
