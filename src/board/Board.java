package board;

import java.util.List;

import game.Character;
import game.Game;
import game.Player;

public class Board {

	protected static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

	protected Game game;
	protected int rowSize;
	protected int columnSize;
	protected Position[][] positions;
	protected Position opponentBase;
	protected Position myBase;
	protected BoardSide playerBoardSide;
	protected BoardSide opponentBoardSide;
	protected List<Character> playerListOfCharacters;
	protected List<Character> opponentListOfCharacters;

	public Board(Game game, int rowSize, int columnSize, BoardSide chosenBoardSide) {
		this.game = game;
		this.game.setBoard(this);
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.positions = new Position[rowSize][columnSize];
		this.myBase = null;
		this.opponentBase = null;
		this.playerBoardSide = chosenBoardSide;
		this.opponentBoardSide = null;
		this.setBoardSides();
		this.playerListOfCharacters = this.game.getPlayer().getCharactersList();
		this.opponentListOfCharacters = this.game.getOpponent().getCharactersList();;
		this.boardSetup();
	}

	public void setBoardSides() {
		if (this.playerBoardSide == BoardSide.UP) {
			this.opponentBoardSide = BoardSide.DOWN;
		} else {
			this.opponentBoardSide = BoardSide.UP;
		}
	}

	public void boardSetup() {
		this.setBasesOnBoard(this.playerBoardSide);
		this.setPlayerCharactersOnBoard(this.game.getPlayer());
		// this.game.setOpponentsCharacters(this.opponentListOfCharacters);
		this.setPlayerCharactersOnBoard(this.game.getOpponent());
		this.generateBases();
		this.setCharactersOnBoard(this.playerListOfCharacters);
		this.setCharactersOnBoard(this.opponentListOfCharacters);
		this.generateBoardObjects();
		// this.generateBoardGrass();
	}

	public void setBases() {
		this.opponentBase = this.game.getOpponent().getMainBase();
		this.myBase = this.game.getPlayer().getMainBase();
	}

	private void generateBases() {
		this.positions[myBase.getX()][myBase.getY()] = myBase;
		this.positions[opponentBase.getX()][opponentBase.getY()] = opponentBase;
	}

	public void generateBoardGrass() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				if (this.positions[i][j] != null) {
					System.out.println("Position "+ i + " , " + j +" not null.");
					if (this.positions[i][j].isOccupied || this.positions[i][j].isObstacle) {
						System.out.println("Position "+ i + " , " + j +" already occupied by " + this.positions[i][j].getTile().name());
					}
				} else {
					this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
					System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
				}
			}
		}
	}

	public void generateBoardObjects() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				int random = (int )(Math.random() * 256);
				if (this.positions[i][j] != null) {
					System.out.println("Position "+ i + " , " + j +" not null.");
					if (this.positions[i][j].isOccupied || this.positions[i][j].isObstacle) {
						System.out.println("Position "+ i + " , " + j +" already occupied by " + this.positions[i][j].getTile().name());
					}
				} else {
					if (this.positions[i][j] == null) {
						if (i == 7) {
							if (j < 4 || j > 11) {
								this.positions[i][j] = new Position(i, j, TypeTile.WATER);
							} else {
								this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
							}
						} else if (i == 8) {
							if (j < 4 || j > 11) {
								this.positions[i][j] = new Position(i, j, TypeTile.WATER);
							} else {
								this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
							}
						} else {
							if (random >= 0 && random <= 8) {
								this.positions[i][j] = new Position(i, j, TypeTile.ROCK);
							} else if (random >= 9 && random <= 13) {
								this.positions[i][j] = new Position(i, j, TypeTile.MOUNTAIN);
							} else if (random >= 14 && random <= 25) {
								this.positions[i][j] = new Position(i, j, TypeTile.TREE);
							} else {
								this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
							}
						}
						System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
					}
				}
			}
		}
	}



	public void setBasesOnBoard(BoardSide chosenBoardSide) {
		switch (chosenBoardSide) {
			case UP:
				this.opponentBase = new Position(0, 7, TypeTile.MAIN_BASE_OPPONENT);
				this.myBase = new Position(15, 8, TypeTile.MAIN_BASE_SELF);
				this.opponentBoardSide = BoardSide.DOWN;
			break;
			case DOWN:
				this.opponentBase = new Position(15, 8, TypeTile.MAIN_BASE_OPPONENT);
				this.myBase = new Position(0, 7, TypeTile.MAIN_BASE_SELF);
				this.opponentBoardSide = BoardSide.UP;
			break;
			default:
			break;
		}
		this.game.setBasesForPlayers();
	}

	public void setPlayerCharactersOnBoard(Player player) {
		switch (player.getBoardSide()) {
			case UP:
				Character char1 = player.getCharactersList().get(0);
				char1.setPosition(new Position(15, 1, char1));
				positions[char1.getPosition().getX()][char1.getPosition().getY()] = char1.getPosition();

				Character char2 = player.getCharactersList().get(1);
				char2.setPosition(new Position(15, 3, char2));
				positions[char1.getPosition().getX()][char1.getPosition().getY()] = char2.getPosition();

				Character char3 = player.getCharactersList().get(2);
				char3.setPosition(new Position(15, 6, char3));
				positions[char1.getPosition().getX()][char1.getPosition().getY()] = char3.getPosition();

				Character char4 = player.getCharactersList().get(3);
				char4.setPosition(new Position(15, 10, char4));
				positions[char1.getPosition().getX()][char1.getPosition().getY()] = char4.getPosition();

				Character char5 = player.getCharactersList().get(4);
				char5.setPosition(new Position(15, 12, char5));
				positions[char1.getPosition().getX()][char1.getPosition().getY()] = char5.getPosition();
				
				Character char6 = player.getCharactersList().get(5);
				char6.setPosition(new Position(15, 14, char6));
				positions[char1.getPosition().getX()][char1.getPosition().getY()] = char6.getPosition();

			// this.
			// 	int j = 3;
			// 	for (int i = 0; i < this.game.getPlayer().getCharactersList().size(); i++) {
			// 		if (this.positions[15][j].isObstacle || this.positions[15][j].isOccupied) {
			// 		}
			// 	}
				
			break;
			case DOWN:
				Character charA = player.getCharactersList().get(0);
				charA.setPosition(new Position(0, 1, charA));
				positions[charA.getPosition().getX()][charA.getPosition().getY()] = charA.getPosition();

				Character charB = player.getCharactersList().get(1);
				charB.setPosition(new Position(0, 3, charA));
				positions[charB.getPosition().getX()][charB.getPosition().getY()] = charB.getPosition();

				Character charC = player.getCharactersList().get(2);
				charC.setPosition(new Position(0, 5, charC));
				positions[charC.getPosition().getX()][charC.getPosition().getY()] = charC.getPosition();

				Character charD = player.getCharactersList().get(3);
				charD.setPosition(new Position(0, 9, charD));
				positions[charD.getPosition().getX()][charD.getPosition().getY()] = charD.getPosition();

				Character charE = player.getCharactersList().get(4);
				charE.setPosition(new Position(0, 11, charE));
				positions[charE.getPosition().getX()][charE.getPosition().getY()] = charE.getPosition();

				Character charF = player.getCharactersList().get(5);
				charF.setPosition(new Position(0, 13, charF));
				positions[charF.getPosition().getX()][charF.getPosition().getY()] = charF.getPosition();
			break;
			default:
			break;
		}

	}

	public Position getPosition(int x, int y) {
		if (x <= rowSize && y <= columnSize) {
			return this.positions[x][y];
		} else if (x >= rowSize) {
			System.out.println("Row position out of bounds.");
			return null;
		} else if (y >= columnSize) {
			System.out.println("Column position out of bounds.");
			return null;
		} else {
			return null;
		}
	}

	public TypeTile[][] mapPositions() {
		TypeTile[][] typeTile = new TypeTile[this.rowSize][this.columnSize];
		for (int i = 0; i < this.rowSize; i++) {
			for (int j = 0; j < this.columnSize; j++) {
				if (positions[i][j] != null) {
					// typeTile[i][j] = this.getPosition(i, j).getTile();
					typeTile[i][j] = positions[i][j].getTile();
					System.out.println("Position "+ i + " , " + j +" OF TYPE " + typeTile[i][j].toString());
				} else {
					System.out.println("Position "+ i + " , " + j +" is null.");
				}
			}
		}
		return typeTile;
	}

	public Position[][] getPositions() {
		return this.positions;
	}

	public Position getSelfMainBase() {
		return this.myBase;
	}
	
	public Position getOpponentMainBase() {
		return this.opponentBase;
	}

	public void setOpponentMainBase(Position base) {
		this.opponentBase = base;
	}

	public void setSelfMainBase(Position base) {
		this.myBase = base;
	}


	public int getRowSize() {
		return this.rowSize;
	}

	public int getColumnSize() {
		return this.columnSize;
	}

	public void setCharactersOnBoard(List<Character> listOfCharacters) {
		for (Character character : listOfCharacters) {
			this.positions[character.getPosition().getX()][character.getPosition().getY()] = character.getPosition();
			this.positions[character.getPosition().getX()][character.getPosition().getY()] = character.getPosition();
		}
	}

}
