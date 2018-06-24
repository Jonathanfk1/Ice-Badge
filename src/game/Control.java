package game;

import gui.GUIMainMenu;
import gui.GUISelectCharacter;
import gui.GUIBoard;
import netgames.ActorNetGames;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;

import actors.ActorPlayer;
import board.Position;

public class Control {

	protected static final int NUMBER_OF_CHARACTERS = 6;

	protected ActorNetGames actorNetGames;
	protected ActorPlayer actorPlayer;
	protected Game game;
	protected boolean connected;
	protected boolean isFirstPlayer;
	protected List<Character> selectedCharacters;
	protected int charactersLeft;
	protected GUIMainMenu guiMainMenu;
	protected GUIBoard guiBoard;
	protected GUISelectCharacter guiSelectCharacter;
	protected JFrame currentMenu;

	public Control() {
		this.actorPlayer = new ActorPlayer(this);
		this.actorNetGames = new ActorNetGames(this);
		this.connected = false;
		this.charactersLeft = this.NUMBER_OF_CHARACTERS;
		this.selectedCharacters = new ArrayList<Character>();
		this.game = new Game(this);
	}

	public void runInitialMenu() {
		this.guiMainMenu = new GUIMainMenu(this);
		currentMenu = guiMainMenu;
	}

	public void disconnect() {
		this.actorNetGames.disconnect();
	}
	

	public void delegateStartGameOnlineToNetGames() {
		this.actorNetGames.startGameOnline();
	}

	public void startPlayOverNet(boolean startsPlaying) {
		this.actorPlayer.startPlayOverNet(startsPlaying);
	}

	public String getOpponentName() {
		return this.actorNetGames.getOpponentName();
	}
	
	public void createGame(boolean iStartPlaying) {
		this.game.setOpponentName(getOpponentName());
		// this.game.openSelectCharacterMenu();
		this.game.createBoard(this.game, 32, 32);
		this.game.setPlayersOnBoard(iStartPlaying, this.actorPlayer);
	}

	public void startGame() {
		if (actorNetGames.isMyTurn) {
			this.actorPlayer.setTurn(true);
		}
	}

	public Action changeTurn() {
		if (this.actorPlayer.isTurn()) {
			return this.game.changeTurn();
		}
		return new Action(null, null, TypeAction.CHANGE_TURN);
	}

	public Action makeAction(int x, int y) {
		Position clickedPosition = null;
		Character character;

		if (this.actorPlayer.isTurn()) {
			clickedPosition = this.game.getPosition(x, y);
			character = clickedPosition.getCharacter();

			if (clickedPosition.getCharacter() != null) {

				if (this.actorPlayer.checkPlayerCharacter(character)) {
					return this.game.selectPosition(clickedPosition);
				}

				return this.game.attack(clickedPosition);
			}

			return this.game.move(clickedPosition);
		}
		return null;
	}

	public void selectCharacter(TypeCharacter type) {

		// SELECT CHARACTER LOOP
		if (this.selectedCharacters.size() < 6) {
			this.selectedCharacters.add(this.game.selectCharacter(type));
			this.guiSelectCharacter.updateCharactersCount(selectedCharacters.size());
			if (this.selectedCharacters.size() == 6) {
				System.out.println("Can't add more characters.");
				this.guiSelectCharacter.showStartButton(true);	
			}
		} else {

		}
	}

	public Game getGame() {
		return this.game;
	}

	public ActorPlayer getActorPlayer() {
		return this.actorPlayer;
	}

	public void receiveLaunchedAction(Action launchAction) {

	}

	public void setActorPlayer(ActorPlayer actorPlayer) {
		this.actorPlayer = actorPlayer; 
	}

	public void connectToNetGames() {
		this.connected = this.actorNetGames.connect(this.guiMainMenu.getConnectionIp(), this.guiMainMenu.getConnectionName());
	}

	public ActorNetGames getActorNetGames() {
		return actorNetGames;
	}

	public void sendStart() {
		if(connected) {
			this.actorNetGames.startGameOnline();
		} else {
			this.guiMainMenu.informNotConnected();
		}
	}

	public void tellTurn(boolean turn) {
		if(this.guiBoard != null) {
			this.guiBoard.tellTurn(turn);
		}
	}

	public void setGuiBoard(GUIBoard guiBoard) {
		this.guiBoard = guiBoard;
	}

	public void setGuiMainMenu(GUIMainMenu guiMainMenu) {
		this.guiMainMenu = guiMainMenu;
	}

	public void setGuiSelectCharacter(GUISelectCharacter guiSelectCharacter) {
		this.guiSelectCharacter = guiSelectCharacter;
	}

	public JFrame getCurrentFrame() {
		return this.currentMenu;
	}

	public void openSelectCharacterMenu() {
		this.guiSelectCharacter = new GUISelectCharacter(this, this.guiMainMenu);
		this.currentMenu = this.guiSelectCharacter;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void removeLastCharacter() {
		if (selectedCharacters.size() > 0) {
			selectedCharacters.remove(selectedCharacters.size()-1);
			this.guiSelectCharacter.updateCharactersCount(selectedCharacters.size());
			this.guiSelectCharacter.showStartButton(selectedCharacters.size() == 6);
		} else {
			this.guiSelectCharacter.tellSelectionListIsEmpty();
			System.out.println("List of selected characters is empty.");
		}
	}

	public Object getSelectedCharacters() {
		return selectedCharacters;
	}

	// public void notPossibleToConnectError(NaoPossivelConectarException e) {

	// }

	// public void alreadyConnectedError(JahConectadoException e) {

	// }

	
}