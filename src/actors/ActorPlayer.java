package actors;

import game.Control;
import game.Player;

public class ActorPlayer extends Player {
    Control control;

    public ActorPlayer(Control control) {
        this.control = control;
        this.control.setActorPlayer(this);
    }

    public void askToConnect() {
        control.connectToNetGames();
    }
}
