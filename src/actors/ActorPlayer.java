package actors;

import game.Control;
import game.Player;

public class ActorPlayer extends Player {
    int id;
    Control control;

    public ActorPlayer(Control control) {
        this.control = control;
    }

    public void askToConnect() {
        this.control.connectToNetGames();
    }

    public void askToDisconnect() {
        this.control.disconnectFromNetGames();
    }

    public void askToStartRoom() {
        this.control.askToStartRoom();
    }

    public void sendStartGameMessage() {
        this.control.sendStartGameMessage();
    }

	public void startPlayOverNet(boolean iStartPlaying) {
        this.control.createGame(iStartPlaying); 
    }

}
