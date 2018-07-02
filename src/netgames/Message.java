package netgames;

import game.Character;

import java.util.List;

import board.BoardSide;
import board.Position;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Message implements Jogada {

    private static final long serialVersionUID = -4815593823256674486L;
	protected String message;
    protected MessageType messageType;
	protected List<Character> listOfCharacters;
    protected BoardSide playerBoardSide;
    protected Position[][] positions;
    
    public Message(MessageType messageType) {
        super();
        this.message = null;
        this.messageType = messageType;
        this.listOfCharacters = null;
        this.playerBoardSide = null;
        this.positions = null;
    }

    public Message(List<Character> listOfCharacters) {
        super();
        this.message = null;
        this.messageType = MessageType.LIST_OF_CHARACTERS;
        this.listOfCharacters = listOfCharacters;
        this.playerBoardSide = null;
        this.positions = null;
    }

    public Message(MessageType messageType, List<Character> listOfCharacters) {
        super();
        this.message = null;
        this.messageType = messageType;
        this.listOfCharacters = listOfCharacters;
        this.playerBoardSide = null;
        this.positions = null;
    }

    public Message(MessageType messageType, BoardSide playerBoardSide) {
        super();
        this.message = null;
        this.messageType = messageType;
        this.listOfCharacters = null;
        this.playerBoardSide = playerBoardSide;
        this.positions = null;
    }
    

    public Message(MessageType messageType, Position[][] positions) {
        super();
        this.message = null;
        this.messageType = messageType;
        this.listOfCharacters = null;
        this.playerBoardSide = null;
        this.positions = positions;
    }
    
    public Message(MessageType messageType, List<Character> selectedCharacters, Position[][] positions) {
        super();
        this.message = null;
        this.messageType = messageType;
        this.listOfCharacters = selectedCharacters;
        this.playerBoardSide = null;
        this.positions = positions;
	}

	public String getMessage() {
        return this.message;
    }
    
    public MessageType getMessageType() {
        return this.messageType;
    }

	public List<Character> getListOfCharacters() {
		return this.listOfCharacters;
	}

	public BoardSide getPlayerBoardSide() {
		return this.playerBoardSide;
	}

	public Position[][] getPositions() {
		return this.positions;
	}


}