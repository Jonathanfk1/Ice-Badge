package board;

import java.util.List;
import java.util.Random;

import game.Character;
import game.Game;
import game.Player;

public class Board {

	protected static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

	protected Position[][] positions;
	protected int rowSize;
	protected int columnSize;
	protected Game game;
	protected Position opponentBase;
	protected Position myBase;

	public Board(Game game, int rowSize, int columnSize) {
		this.game = game;
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.positions = new Position[rowSize][columnSize];
		this.generateBoard();
		this.setMainBases(this.game.getOpponent().isTurn());
	}

	public void generateBoard() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
				System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
			}
		}
	}

	public void generateBoardObjects() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				int random = (int )(Math.random() * 31);
				if (i == 7 || i == 8) {
					if (j < 3 || i > 11) {
						this.positions[i][j] = new Position(i, j, TypeTile.WATER);
					}
				} else {
					if (this.positions[i][j] == null) {
						if (random >= 12 || random <= 18) {
							this.positions[i][j] = new Position(i, j, TypeTile.ROCK);
						} else if (random >= 7 && random <= 11) {
							this.positions[i][j] = new Position(i, j, TypeTile.MOUNTAIN);
						} else if (random >= 19 && random <= 26) {
							this.positions[i][j] = new Position(i, j, TypeTile.TREE);
						} else {
							this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
						}
					}
				}
				System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
			}
		}
	}

	private void setMainBases(boolean isOpponentTurn) {
		if (isOpponentTurn) {
			this.opponentBase = new Position(0, 7, TypeTile.MAIN_BASE_OPPONENT);
			this.myBase = new Position(16, 8, TypeTile.MAIN_BASE_SELF);
		} else {
			this.opponentBase = new Position(16, 8, TypeTile.MAIN_BASE_OPPONENT);
			this.myBase = new Position(0, 7, TypeTile.MAIN_BASE_SELF);
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
				typeTile[i][j] = positions[i][j].getTile();
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

	public void setCharactersOnBoard(Player player, List<Character> characters) {
		int charactersIndex = characters.size()-1;
		for (int i = 3; i < characters.size(); i = i+5) {
			positions[0][i].setCharacter(characters.get(charactersIndex));
			charactersIndex++;
		}
		charactersIndex = 0;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	public int getRowSize() {
		return this.rowSize;
	}

	public int getColumnSize() {
		return this.columnSize;
	}

}
