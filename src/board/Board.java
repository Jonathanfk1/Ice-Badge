package board;

import game.Character;
import game.Player;

public class Board {

	protected static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

	protected Position[][] positions;
	protected Position mainBases[];
	protected int rowSize;
	protected int columnSize;

	public Board(int rowSize, int columnSize, int numberOfPlayers) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.positions = new Position[rowSize][columnSize];
		this.generateBoard();
		this.setMainBases(numberOfPlayers);
	}


	public Board(int rowSize, int columnSize) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.positions = new Position[rowSize][columnSize];
		this.generateBoard();
		this.setMainBases(DEFAULT_NUMBER_OF_PLAYERS);
	}

	public void generateBoard() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
				System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
			}
		}
	}

	private void setMainBases(int numberOfPlayers) {
		this.mainBases = new Position[numberOfPlayers];
		Position mainBase1 = this.getPosition(0, 15);
		mainBase1.setTile(TypeTile.MAIN_BASE_1);
		this.mainBases[0] = mainBase1;

		Position mainBase2 = this.getPosition(31, 15);
		mainBase2.setTile(TypeTile.MAIN_BASE_2);
		this.mainBases[1] = mainBase2;
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

	public Position[] getMainBases() {
		return this.mainBases;
	}

	public void setCharacterOnBoard(Player player, Character character) {

	}

}
