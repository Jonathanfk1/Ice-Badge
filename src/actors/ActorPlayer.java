package actors;

import game.Control;
import game.Player;

public class ActorPlayer extends Player {
    int id;
    Control control;

    public ActorPlayer(Control control) {
        this.control = control;
        this.control.setActorPlayer(this);
    }

    public void askToConnect() {
        this.control.connectToNetGames();
    }

    public void askNetGamesToStartGameOnline() {
        this.control.delegateStartGameOnlineToNetGames();
    }

	public void startPlayOverNet(boolean iStartPlaying) {
        this.control.createGame(iStartPlaying); 
    }

}
