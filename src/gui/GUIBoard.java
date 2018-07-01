package gui;

import game.Control;
import board.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class GUIBoard extends JFrame {

	private static final long serialVersionUID = -4942685444367223974L;

	private Control control;
	private JPanel lowerPanel;
	private JPanel upperPanel;
	private Board board;
	private ImageIcon grassIcon;
	private ImageIcon baseIcon;
	private ImageIcon treeIcon;
	private ImageIcon waterIcon;
	private ImageIcon rockIcon;
	private ImageIcon mountainIcon;
	private ImageIcon archerWarriorIcon;
	private ImageIcon swordsman1Icon;
	private ImageIcon cleric1Icon;
	private ImageIcon bardIcon;

	public GUIBoard(Control control) {
		super();
		this.control = control;
		this.board = control.getGame().getBoard();

		this.setFrame();
		this.setPanel();
		this.setBoard();
		this.setButtons();
		this.add(this.upperPanel, BorderLayout.NORTH);
		this.add(this.lowerPanel, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
		// this.setButtons();
	}

	private void setFrame() {
		// this.setLayout(new GridBagLayout());
		// // this.add(this.parent, gbc);
		// this.setSize(new Dimension(1000, 300));
		// this.setLocation(300, 500);	
		// this.add(this.upperPanel);
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
		this.upperPanel = new JPanel();
		// this.upperPanel.setSize(new Dimension(500, 500));
		this.upperPanel.setBackground(new Color(45, 175, 49));
        this.upperPanel.setLayout(new GridLayout(this.board.getRowSize(), this.board.getColumnSize()));
		this.upperPanel.setPreferredSize(new Dimension(500, 500));

		this.lowerPanel = new JPanel();
		// this.upperPanel.setSize(new Dimension(500, 500));
		this.lowerPanel.setBackground(Color.PINK);
        this.lowerPanel.setLayout(new GridLayout(1, 3));
		this.lowerPanel.setPreferredSize(new Dimension(200, 60));
		
		this.setIconImages();
	}
	
	public void setIconImages() {
		ImageIcon grassIcon = new ImageIcon("resources/grassTile.png");
		grassIcon.setImage(grassIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.grassIcon = grassIcon;

		ImageIcon baseIcon = new ImageIcon("resources/baseTile.png");
		baseIcon.setImage(baseIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.baseIcon = baseIcon;

		ImageIcon treeIcon = new ImageIcon("resources/treeTile.png");
		treeIcon.setImage(treeIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.treeIcon = treeIcon;

		ImageIcon mountainIcon = new ImageIcon("resources/mountainTile.png");
		mountainIcon.setImage(mountainIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.mountainIcon = mountainIcon;

		ImageIcon waterIcon = new ImageIcon("resources/waterTile.png");
		waterIcon.setImage(waterIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.waterIcon = waterIcon;
		
		ImageIcon rockIcon = new ImageIcon("resources/rockTile.png");
		rockIcon.setImage(rockIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.rockIcon = rockIcon;

		ImageIcon archerWarriorIcon = new ImageIcon("resources/warriorTile.png");
		archerWarriorIcon.setImage(archerWarriorIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.archerWarriorIcon = archerWarriorIcon;

		ImageIcon swordsman1Icon = new ImageIcon("resources/swordsmanTile1.png");
		swordsman1Icon.setImage(swordsman1Icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.swordsman1Icon = swordsman1Icon;

		ImageIcon cleric1Icon = new ImageIcon("resources/clericTile1.png");
		cleric1Icon.setImage(cleric1Icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.cleric1Icon = cleric1Icon;

		ImageIcon bardIcon = new ImageIcon("resources/bardTile1.png");
		bardIcon.setImage(bardIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.bardIcon = bardIcon;

	}

	public Board setBoard() {

		// this.updateMap();

		for (int i = 0; i < board.getRowSize(); i++) {
			for (int j = 0; j < board.getColumnSize(); j++) {
				JButton tile = new JButton();
				tile.setPreferredSize(new Dimension(15, 15));
				tile.setContentAreaFilled(false);
				switch(control.getGame().getBoard().getPositions()[i][j].getTile()) {
					case GRASS:
						tile.setIcon(this.grassIcon);
						tile.setActionCommand("grass");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "grass") {
									System.out.println("clicked grass");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
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
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
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
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
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
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
						break;
					case TREE:
						tile.setIcon(this.treeIcon);
						tile.setActionCommand("tree");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "tree") {
									System.out.println("clicked tree");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case MAIN_BASE_OPPONENT:
							tile.setIcon(this.baseIcon);
							tile.setActionCommand("main_base_opponent");
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (e.getActionCommand() == "main_base_opponent") {
										System.out.println("clicked opponent's main base");
									}
								}
							});
							this.upperPanel.add(tile);
							System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
							+ " to position " + i + " , " + j);
					break;
					case MAIN_BASE_SELF:
							tile.setIcon(this.baseIcon);
							tile.setActionCommand("main_base_self");
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (e.getActionCommand() == "main_base_self") {
										System.out.println("clicked own main base");
									}
								}
							});
							this.upperPanel.add(tile);
							System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
							+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_SWORDSMAN:
						tile.setIcon(this.swordsman1Icon);
						tile.setActionCommand("swordsman");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "swordsman") {
									System.out.println("clicked swordsman");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_CLERIC:
						tile.setIcon(this.cleric1Icon);
						tile.setActionCommand("cleric");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "cleric") {
									System.out.println("clicked cleric");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_ARCHER:
						tile.setIcon(this.archerWarriorIcon);
						tile.setActionCommand("archer");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "archer") {
									System.out.println("clicked archer");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_BARD:
						tile.setIcon(this.bardIcon);
						tile.setActionCommand("bard");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "bard") {
									System.out.println("clicked bard");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					default:
							System.out.println("Default Case");
							System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
							+ " to position " + i + " , " + j);
					break;
				}
			}
		}
		
		return null;
	}

	private void updateBoard() {
		
	}

	private void setButtons() {
		JButton sendPlay = new JButton("Send play");
		sendPlay.setPreferredSize(new Dimension(40, 30));
		sendPlay.setContentAreaFilled(true);
		sendPlay.setBackground(Color.GRAY);
		sendPlay.setActionCommand("LaunchAction");
		sendPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "LaunchAction") {
					System.out.println("LaunchAction button clicked");
				}
			}
		});
		this.lowerPanel.add(sendPlay, BorderLayout.EAST);
	}

	public void tellTurn(boolean turn) { 
		if (turn) {
			JOptionPane.showMessageDialog(this, "It's your turn.");		
		} else {
			JOptionPane.showMessageDialog(this, "Wait for opponent to move.");
		}
	}

}
