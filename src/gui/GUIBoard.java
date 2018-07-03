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
	private JButton[][] buttonsMap;
	private ImageIcon tombstoneIcon;
	private JButton sendPlay;


	public GUIBoard(Control control) {
		super();
		this.control = control;
		this.board = control.getGame().getBoard();
		this.sendPlay = null;
		this.buttonsMap = new JButton[this.control.getGame().getBoard().getRowSize()][this.control.getGame().getBoard().getColumnSize()];

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
		this.lowerPanel.setBackground(Color.GRAY);
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

		ImageIcon tombstoneIcon = new ImageIcon("resources/tombstoneTile1.png");
		tombstoneIcon.setImage(tombstoneIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.tombstoneIcon = tombstoneIcon;

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

	public void removeButtons() {
		this.upperPanel.removeAll();
		for (int i = 0; i < board.getRowSize(); i++) {
			for (int j = 0; j < board.getColumnSize(); j++) {
				buttonsMap[i][j] = null;
			}			
		}
	}

	public void revalidateGUI() {
		this.upperPanel.revalidate();
		this.upperPanel.repaint();
	}

	public Board setBoard() {

		// this.updateMap();
		for (int i = 0; i < board.getRowSize(); i++) {
			for (int j = 0; j < board.getColumnSize(); j++) {
				JButton tile = new JButton();
				tile.setPreferredSize(new Dimension(15, 15));
				tile.setContentAreaFilled(false);
				this.buttonsMap[i][j] = tile;
				switch(control.getGame().getBoard().getPositions()[i][j].getTile()) {
					case GRASS:
						tile.setIcon(this.grassIcon);
						tile.setActionCommand("grass");
						final int innerIg = i;
						final int innerJg = j;
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "grass") {
									System.out.println("clicked grass");
									cleanSelection();
									control.clickedGrass(control.getGame().getPosition(innerIg, innerJg));
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
									cleanSelection();
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
									cleanSelection();
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
									cleanSelection();
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
									cleanSelection();
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case TOMBSTONE:
						tile.setIcon(this.tombstoneIcon);
						tile.setActionCommand("tombstone");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "tombstone") {
									System.out.println("clicked tombstone");
									cleanSelection();
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case MAIN_BASE:
							tile.setIcon(this.baseIcon);
							tile.setActionCommand("main_base");
							final int innerIb = i;
							final int innerJb = j;
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (e.getActionCommand() == "main_base") {
										System.out.println("clicked main base");
										control.clickedBase(control.getGame().getPosition(innerIb, innerJb));
										cleanSelection();
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
						final int innerI1 = i;
						final int innerJ1 = j;
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "swordsman") {
									System.out.println("clicked swordsman");
									// System.out.println("Clicked " + tile.getAlignmentX() + tile.getAlignmentY() + tile.getX() + tile.getY());
									control.clickedCharacter(control.getGame().getPosition(innerI1, innerJ1));
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
						final int innerI2 = i;
						final int innerJ2 = j;
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "cleric") {
									System.out.println("clicked cleric");
									control.clickedCharacter(control.getGame().getPosition(innerI2, innerJ2));
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
						final int innerI3 = i;
						final int innerJ3 = j;
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "archer") {
									System.out.println("clicked archer");
									control.clickedCharacter(control.getGame().getPosition(innerI3, innerJ3));
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
						final int innerI4 = i;
						final int innerJ4 = j;
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "bard") {
									System.out.println("clicked bard");
									control.clickedCharacter(control.getGame().getPosition(innerI4, innerJ4));
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

	public void surroundAction(int x, int y, int range) {
		cleanSelection();

		int matrixInitX = x-range;
		int matrixInitY = y-range;
		if (matrixInitX < 0) {
			matrixInitX = 0;
		} else if (matrixInitX > this.control.getGame().getBoard().getRowSize()) {
			matrixInitX = this.control.getGame().getBoard().getRowSize();
		}
		if (matrixInitY < 0) {
			matrixInitY = 0;
		} else if (matrixInitY > this.control.getGame().getBoard().getColumnSize()) {
			matrixInitY = this.control.getGame().getBoard().getRowSize();
		}

		int matrixBoundsX = x+(range)+1;
		int matrixBoundsY = y+(range)+1;
		if (matrixBoundsX < 0) {
			matrixBoundsX = 0;
		} else if (matrixBoundsX > this.control.getGame().getBoard().getRowSize()) {
			matrixBoundsX = this.control.getGame().getBoard().getRowSize();
		}
		if (matrixBoundsY < 0) {
			matrixBoundsY = 0;
		} else if (matrixBoundsY > this.control.getGame().getBoard().getRowSize()) {
			matrixBoundsY = this.control.getGame().getBoard().getRowSize();
		}


		for (int i = matrixInitX; i < matrixBoundsX; i++) {
			for (int j = matrixInitY; j < matrixBoundsY; j++) {
				if (this.buttonsMap[i][j] != null) {
					this.buttonsMap[i][j].setContentAreaFilled(true);
					if (this.control.getGame().getBoard().getPositions()[i][j].isObstacle()) {
						this.buttonsMap[i][j].setContentAreaFilled(false);
					}
					game.Character selectedCharacter = this.control.getGame().getBoard().getPosition(i, j).getCharacter();
					if (this.control.getGame().getBoard().getPosition(i, j).getCharacter() != null 
					|| this.control.getGame().getBoard().getPosition(i, j).isOccupied()) {
						if (this.control.getGame().getBoard().getPosition(i, j).getCharacter().getOwner() == this.control.getGame().getPlayer()) {
							this.buttonsMap[i][j].setContentAreaFilled(false);
						} else {
							this.buttonsMap[i][j].setContentAreaFilled(true);
						}
					}
					if (this.control.getGame().getBoard().getPositions()[i][j].isObjective()) {
						this.buttonsMap[i][j].setContentAreaFilled(true);
					}
				}
			}
		}
	}

	public void cleanSelection() {
		for (int i = 0; i < this.control.getGame().getBoard().getRowSize(); i++) {
			for (int j = 0; j < this.control.getGame().getBoard().getColumnSize(); j++) {
				this.buttonsMap[i][j].setContentAreaFilled(false);
			}
		}
	}

	private void updatePosition(int x, int y) {
		buttonsMap[x][y].setIcon(this.grassIcon);
		buttonsMap[x][y].setActionCommand("grass");
		final int innerIg = x;
		final int innerJg = y;
		buttonsMap[x][y].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "grass") {
					System.out.println("clicked grass");
					cleanSelection();
					control.clickedGrass(control.getGame().getPosition(innerIg, innerJg));
				}
			}
		});
		this.buttonsMap[x][y].repaint();
		this.upperPanel.repaint();
	}

	private void setButtons() {
		this.sendPlay = new JButton("Send play");
		this.sendPlay.setPreferredSize(new Dimension(40, 30));
		this.sendPlay.setContentAreaFilled(true);
		this.sendPlay.setBackground(Color.GRAY);
		this.sendPlay.setActionCommand("LaunchAction");
		this.sendPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "LaunchAction") {
					System.out.println("LaunchAction button clicked");
					control.launchPlay();
				}
			}
		});
		this.sendPlay.setVisible(true);
		this.lowerPanel.add(this.sendPlay, BorderLayout.EAST);
	}
	
	public void tellTurn(boolean turn) { 
		if (turn) {
			JOptionPane.showMessageDialog(this, "It's your turn.");		
		} else {
			JOptionPane.showMessageDialog(this, "Wait for opponent to move.");
		}
	}


	public int askForAction() {
		Object[] actions = {"MOVE",
			"ATTACK"};
			int chosenAction = JOptionPane.showOptionDialog(this,
			"Chose an action",
			"Choose action",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			actions,
			actions[1]);
			return chosenAction;
	}

	public void warnAlreadyMoved() {
		JOptionPane.showMessageDialog(this, "Already moved this turn.", "Move not available", JOptionPane.WARNING_MESSAGE);
	}

	public void playDone() {
		this.sendPlay.setVisible(true);
	}

	public void warnAlreadyAttacked() {
		JOptionPane.showMessageDialog(this, "Already attacked this turn.", "Attack not available", JOptionPane.WARNING_MESSAGE);
	}

	public void warnGameIsOver(boolean isWinner) {
		if (isWinner) {
			JOptionPane.showMessageDialog(this, "You win the game.", "Game won!", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Sorry, you lost the game. :(", "Game lost...", JOptionPane.PLAIN_MESSAGE);
		}
		this.removeButtons();
	}

	public void warnOutOfActions() {
		JOptionPane.showMessageDialog(this, "Already moved and attacked this turn.", "Out of actions", JOptionPane.WARNING_MESSAGE);
	}

}
