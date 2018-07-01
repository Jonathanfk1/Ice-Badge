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
import board.BoardSide;
import board.Position;

public class Control {

	protected static final int NUMBER_OF_CHARACTERS = 6;
	protected static final int DEFAULT_BOARD_SQUARE_SIZE = 16;

	protected ActorNetGames actorNetGames;
	protected ActorPlayer actorPlayer;
	protected Game game;
	protected GUIMainMenu guiMainMenu;
	protected JFrame currentMenu;
	protected GUISelectCharacter guiSelectCharacter;
	protected GUIBoard guiBoard;
	protected List<Character> opponentsCharacters;
	protected List<Character> selectedCharacters;
	protected Integer netGamesPosition;
	protected boolean isFirstPlayer;
	protected boolean isConnected;
	protected boolean isRoomStarted;
	protected boolean isReadyToStart;

	public Control() {
		this.actorNetGames = new ActorNetGames(this);
		this.actorPlayer = new ActorPlayer(this);
		this.game = new Game(this);
		this.guiMainMenu = new GUIMainMenu(this);
		this.currentMenu = this.guiMainMenu;
		this.guiSelectCharacter = null;
		this.guiBoard = null;
		this.opponentsCharacters = new ArrayList<>();
		this.selectedCharacters = new ArrayList<>();
		this.netGamesPosition = null;
		this.isFirstPlayer = false;
		this.isConnected = false;
		this.isRoomStarted = false;
		this.isReadyToStart = false;
	}

	public void disconnect() {
		this.actorNetGames.disconnect();
		this.setIsRoomStarted(false);
		this.setIsConnected(false);
		this.setIsReadyToStart(false);
		this.selectedCharacters.clear();
		this.guiSelectCharacter.setVisible(false);
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
				if (!this.isRoomStarted) {
					if (this.guiSelectCharacter == null) {
						this.openSelectCharacterMenu();
					} else {
						this.guiSelectCharacter.setVisible(true);
						this.updateFrame(this.guiSelectCharacter);
					}
				}
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
		} else {
			this.game.setOpponentName("Offline Player");
		}
		this.game.createBoard(this.game, DEFAULT_BOARD_SQUARE_SIZE, DEFAULT_BOARD_SQUARE_SIZE);
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
		if (this.selectedCharacters.size() < NUMBER_OF_CHARACTERS) {
			this.selectedCharacters.add(this.game.selectCharacter(type));
			this.guiSelectCharacter.updateCharactersCount(selectedCharacters.size());
			if (this.selectedCharacters.size() == NUMBER_OF_CHARACTERS) {
				System.out.println("Can't add more characters.");
				this.guiSelectCharacter.showReadyButton(true);	
			}
		} 
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


	public void openSelectCharacterMenu() {
		this.guiSelectCharacter = new GUISelectCharacter(this, this.guiMainMenu);
		this.currentMenu = this.guiSelectCharacter;
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

	public void warnPlayerNotReady() {
		this.guiMainMenu.warnConnectionTrial();
	}

	public void toggleIsReadyToStart() {
		this.actorNetGames.setIsReadyToStart(!this.actorNetGames.getIsReadyToStart());
	}

	public void listOfCharactersReceived() {
		this.guiMainMenu.listOfCharactersReceived();
	}

	public void sendStartGameMessage() {
		Message message = new Message(MessageType.START_GAME, this.getSelectedCharacters());
		this.actorNetGames.sendMessage(message);
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

	public void sendReadyToServer() {
		Message message = new Message(MessageType.PLAYER_READY, this.getGame().getPlayer().getBoardSide());
		this.actorNetGames.sendMessage(message);		
	}

	public BoardSide askForBoardSide() {
		int option = this.guiMainMenu.askForBoardSide();
		if (option == 0) {
			return BoardSide.UP;
		} else {
			return BoardSide.DOWN;
		}
	}


	// > GETTERS AND SETTERS

	// > > EXTERNAL

	public void setOpponentsCharacters(List<Character> listOfCharacters) {
		this.getGame().getOpponent().setCharactersList(listOfCharacters);
	}

	public boolean areBothBoardSidesSet() {
		return ((this.getGame().getPlayer().getBoardSide() != null)
		&& (this.getGame().getPlayer().getBoardSide() != null));
	}

	// > > INTERNAL

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return this.game;
	}

	public ActorPlayer getActorPlayer() {
		return this.actorPlayer;
	}

	public List<Character> getSelectedCharacters() {
		return this.selectedCharacters;
	}

	public JFrame getCurrentMenu() {
		return this.currentMenu;
	}

	public void setCurrentMenu(JFrame menu) {
		this.currentMenu = menu;
	}

	public void setNetGamesPosition(Integer posicao) {
		this.netGamesPosition = posicao;
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

	public void setIsReadyToStart(boolean isReadyToStart) {
		this.isReadyToStart = isReadyToStart;
	}

}