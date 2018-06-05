package game;

import gui.GUIInitMenu;
import gui.GUISelectCharacter;
import gui.GUITable;
import netgames.ActorNetGames;
import table.Position;
import table.Table;

public class Control {

	protected static final int NUMBER_OF_PLAYERS = 6;

	protected boolean connected_;
	protected ActorNetGames netgames_;
	protected Game game_;
	protected Player player_;
	protected int characterLeft_;

	public void run() {
		this.player_ = new Player();
		this.netgames_ = new ActorNetGames(this);
		this.characterLeft_ = this.NUMBER_OF_PLAYERS;
		new GUIInitMenu(this);
		
	}

	// NOT IMPLEMENTED CORRECTLY
	// ######
	public void connect() {
		this.netgames_.connect("", null);
	}

	public void disconnect() {
		this.netgames_.disconnect();
	}

	public void startGame() {
		this.netgames_.startGame();
		new GUITable(this);
	}

	public void receiveBeginMessage(int i) {
		this.game_ = new Game(32, 32);
		this.game_.setPlayersOnTable(i, this.player_);
		new GUISelectCharacter(this);

		if (i == 1) {
			this.player_.setTurn(true);
		}

	}

	public Action changeTurn() {
		if (this.player_.isTurn()) {
			return this.game_.changeTurn();
		}
		return new Action(null, null, TypeAction.CHANGE_TURN);
	}

	public Action makeAction(int x, int y) {
		Position clickedPosition = null;
		Character character;

		if (this.player_.isTurn()) {
			clickedPosition = this.game_.getPosition(x, y);
			character = clickedPosition.getCharacter();

			if (clickedPosition.getCharacter() != null) {

				if (this.player_.checkPlayerCharacter(character)) {
					return this.game_.selectPosition(clickedPosition);
				}

				return this.game_.attack(clickedPosition);
			}

			return this.game_.move(clickedPosition);
		}
		return null;
	}

	public void selectCharacter(TypeCharacter type) {

		// SELECT CHARACTER LOOP
		if (this.characterLeft_ > 0) {
			this.game_.selectCharacter(this.player_, type);
			this.characterLeft_--;
		}

		if (this.characterLeft_ == 0) {
			System.out.println(this.player_.isTurn());
		}
	}

	public void quit() {

	}

	public Game getGame() {
		return this.game_;
	}

	public Player getPlayer() {
		return this.player_;
	}

	public Table getTable() {
		return this.game_.getTable();
	}

}