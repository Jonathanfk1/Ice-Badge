package game;

import gui.GUIMainMenu;
import gui.GUISelectCharacter;
import gui.GUIBoard;
import netgames.ActorNetGames;
import netgames.MessageType;
import netgames.Message;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import actors.ActorPlayer;
import board.Board;
import board.BoardSide;
import board.Position;

public class Control {

	protected static final int NUMBER_OF_CHARACTERS = 6;
	protected static final int DEFAULT_BOARD_SQUARE_SIZE = 16;

	protected ActorNetGames actorNetGames;
	protected ActorPlayer actorPlayer;
	protected Game game;
	protected boolean isConnected;
	protected boolean isFirstPlayer;
	protected List<Character> selectedCharacters;
	// protected int charactersLeft;
	protected GUIMainMenu guiMainMenu;
	protected GUIBoard guiBoard;
	protected GUISelectCharacter guiSelectCharacter;
	protected JFrame currentMenu;
	protected List<Character> opponentsCharacters;
	protected boolean isRoomStarted;
	protected boolean isReadyToStart;
	protected Integer position;

	public Control() {
		this.actorPlayer = new ActorPlayer(this);
		this.actorNetGames = new ActorNetGames(this);
		this.isConnected = false;
		this.selectedCharacters = new ArrayList<>();
		this.isReadyToStart = false;
		this.game = new Game(this);
	}

	public void runMainMenu() {
		this.guiMainMenu = new GUIMainMenu(this);
		currentMenu = guiMainMenu;
	}

	public void disconnect() {
		this.actorNetGames.disconnect();
		this.setIsRoomStarted(false);
		this.setIsConnected(false);
		this.selectedCharacters.clear();
		this.guiSelectCharacter.show(false);
	}

	public void updateFrame(JFrame frame) {
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	
	public void askToStartGame() {
		if (!this.isRoomStarted) {	
			if(this.isConnected) {
				this.actorNetGames.sendStart();
				// if (!this.guiSelectCharacter.isEnabled()) {
				if (!this.isRoomStarted) {
					if (this.guiSelectCharacter == null) {
						this.openSelectCharacterMenu();
					} else {
						this.guiSelectCharacter.show(true);
						this.updateFrame(this.guiSelectCharacter);
					}
				}
				// }
			} else {
				this.guiMainMenu.informNotConnected();
			}
		} else if (this.isRoomStarted && this.selectedCharacters.isEmpty()) {
			this.openSelectCharacterMenu();
		} else { 
			this.guiMainMenu.informRoomAlreadyStarted();
		}
	}

	public void startPlayOverNet(boolean startsPlaying) {
		this.actorPlayer.startPlayOverNet(startsPlaying);
	}

	public String findOpponentName() {
		return this.actorNetGames.askForOpponentName();
	}
	
	public void createGame(boolean iStartPlaying) {
		this.game.getPlayer().setName(this.guiMainMenu.getPlayerName());
		if(isConnected) {
			this.game.setOpponentName(findOpponentName());
			// this.game.setOpponentsCharacters();
		} else {
			this.game.setOpponentName("Offline Player");
		}
		// this.game.openSelectCharacterMenu();
		// this.game.getOpponent().setCharactersList(this.opponentsCharacters);
		this.game.createBoard(this.game, DEFAULT_BOARD_SQUARE_SIZE, DEFAULT_BOARD_SQUARE_SIZE);
		// this.game.getBoard().setMainBases(iStartPlaying);
		// this.game.setBasesForPlayers(iStartPlaying);
		// this.game.board.generateBoardObjects();
		// this.guiSelectCharacter.dispose();
		this.guiBoard = new GUIBoard(this);
	}

	public void startGame() {
		if (actorNetGames.getIsTurn()) {
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
		if (this.selectedCharacters.size() < NUMBER_OF_CHARACTERS) {
			this.selectedCharacters.add(this.game.selectCharacter(type));
			this.guiSelectCharacter.updateCharactersCount(selectedCharacters.size());
			if (this.selectedCharacters.size() == NUMBER_OF_CHARACTERS) {
				System.out.println("Can't add more characters.");
				this.guiSelectCharacter.showReadyButton(true);	
			}
		} 
	}

	public Game getGame() {
		return this.game;
	}

	public ActorPlayer getActorPlayer() {
		return this.actorPlayer;
	}

	public void receiveLaunchedAction(Action launchAction) {
		switch(launchAction.getType()) {
			case ATTACK:

			break;
			case MOVE:

			break;
			case CHANGE_TURN:

			break;
			case SELECT_CHARACTER:

			break;
			default:

			break;
		}
	}

	public void setActorPlayer(ActorPlayer actorPlayer) {
		this.actorPlayer = actorPlayer; 
	}

	public void connectToNetGames() {
		this.setIsConnected(this.actorNetGames.connect(this.guiMainMenu.getConnectionIp(), this.guiMainMenu.getConnectionName()));
	}

	public ActorNetGames getActorNetGames() {
		return this.actorNetGames;
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
			this.guiSelectCharacter.showReadyButton(selectedCharacters.size() == NUMBER_OF_CHARACTERS);
			this.guiSelectCharacter.showStartButton(selectedCharacters.size() == NUMBER_OF_CHARACTERS);
		} else {
			this.guiSelectCharacter.tellSelectionListIsEmpty();
			System.out.println("List of selected characters is empty.");
		}
	}

	public List<Character> getSelectedCharacters() {
		return selectedCharacters;
	}

	public void setOpponentsCharacters(List<Character> listOfCharacters) {
		this.opponentsCharacters = listOfCharacters;
	}

	public void warnPlayerNotReady() {
		this.guiMainMenu.warnConnectionTrial();
	}

	public void toggleIsReadyToStart() {
		this.actorNetGames.setIsReadyToStart(!this.actorNetGames.getIsReadyToStart());
	}

	public void listOfCharactersReceived() {
		this.guiMainMenu.listOfCharactersReceived();
	}

	public void setIsConnected(boolean connected) {
		this.isConnected = connected;
		this.guiMainMenu.setConnectedText(this.isConnected);
		this.guiMainMenu.update(this.guiMainMenu.getGraphics());
	}

	public void setIsRoomStarted(boolean roomStarted) {
		this.isRoomStarted = roomStarted;
		this.guiMainMenu.setRoomStartedText(this.isRoomStarted);
		this.guiMainMenu.update(this.guiMainMenu.getGraphics());
	}

	public void sendStartGameMessage() {
		Message message = new Message(MessageType.START_GAME, this.getSelectedCharacters());
		this.actorNetGames.sendMessage(message);
	}

	public BoardSide askForBoardSide() {
		return this.guiMainMenu.askForBoardSide();
	}

	public void setPosition(Integer posicao) {
		this.position = posicao;
	}

	public boolean boardSidesSet() {
		return (this.getGame().getPlayer().getBoardSide() != null);
	}

	// public void notPossibleToConnectError(NaoPossivelConectarException e) {

	// }

	// public void alreadyConnectedError(JahConectadoException e) {

	// }

	
}