package netgames;

import game.Character;

import java.util.List;

import board.BoardSide;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Message implements Jogada {

    private static final long serialVersionUID = -4815593823256674486L;
	protected String message;
    protected MessageType messageType;
	protected List<Character> listOfCharacters;
	protected BoardSide playerBoardSide;
    
    public Message(MessageType messageType) {
        super();
        this.messageType = messageType;
    }

    public Message(List<Character> listOfCharacters) {
        super();
        this.listOfCharacters = listOfCharacters;
        this.messageType = MessageType.LIST_OF_CHARACTERS;
    }

    public Message(MessageType messageType, List<Character> listOfCharacters) {
        super();
        this.messageType = messageType;
        this.listOfCharacters = listOfCharacters;
    }

    public Message(MessageType messageType, BoardSide playerBoardSide) {
        super();
        this.messageType = messageType;
        this.playerBoardSide = playerBoardSide;
    }

    public Message(String message) {
        super();
        this.message = message;
        this.messageType = MessageType.TEXT;
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


}