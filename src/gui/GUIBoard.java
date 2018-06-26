package gui;

import game.Control;
import board.TypeTile;
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
	private ImageIcon charIcon1;
	private ImageIcon charIcon2;
	private ImageIcon charIcon3;
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

		ImageIcon charIcon1 = new ImageIcon("resources/swordsmanTile1.png");
		charIcon1.setImage(charIcon1.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.charIcon1 = charIcon1;

		ImageIcon charIcon2 = new ImageIcon("resources/clericTile1.png");
		charIcon2.setImage(charIcon2.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.charIcon2 = charIcon2;

		ImageIcon charIcon3 = new ImageIcon("resources/warriorTile.png");
		charIcon3.setImage(charIcon3.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.charIcon3 = charIcon3;

		ImageIcon rockIcon = new ImageIcon("resources/rockTile.png");
		rockIcon.setImage(rockIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
		this.rockIcon = rockIcon;

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
							tile.setActionCommand("base");
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (e.getActionCommand() == "base") {
										System.out.println("clicked base");
									}
								}
							});
							this.upperPanel.add(tile);
							System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
							+ " to position " + i + " , " + j);
					break;
					case MAIN_BASE_SELF:
							tile.setIcon(this.baseIcon);
							tile.setActionCommand("base");
							tile.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (e.getActionCommand() == "base") {
										System.out.println("clicked base");
									}
								}
							});
							this.upperPanel.add(tile);
							System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
							+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_1:
						tile.setIcon(this.charIcon1);
						tile.setActionCommand("base");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "base") {
									System.out.println("clicked base");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_2:
						tile.setIcon(this.charIcon2);
						tile.setActionCommand("base");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "base") {
									System.out.println("clicked base");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					case CHARACTER_TYPE_3:
						tile.setIcon(this.charIcon3);
						tile.setActionCommand("base");
						tile.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand() == "base") {
									System.out.println("clicked base");
								}
							}
						});
						this.upperPanel.add(tile);
						System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
						+ " to position " + i + " , " + j);
					break;
					default:
							// Position[][] positions = this.board.getPositions();
							// if(positions[i][j].getCharacter() != null) {
								// tile.setIcon(this.char1Icon);
								// tile.setActionCommand("char 1");
								// tile.addActionListener(new ActionListener() {
									// @Override
									// public void actionPerformed(ActionEvent e) {
										// if (e.getActionCommand() == "char 1") {
											// System.out.println("clicked char 1");
										// }
									// }
								// });
							// }
							System.out.println("Default Case");
							System.out.println("Added tile of type: " + control.getGame().getBoard().getPositions()[i][j].getTile().toString()
							+ " to position " + i + " , " + j);
					break;
				}
			}
		}
		
		return null;
	}

	private void updatePosition() {

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
