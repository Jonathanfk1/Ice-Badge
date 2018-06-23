package netgames;

import br.ufsc.inf.leobr.cliente.Jogada;
import game.Action;

public class LaunchAction implements Jogada {
    
    private static final long serialVersionUID = 3631376366041293471L;
	private Action action;

    public LaunchAction(Action action) {
        super();
        this.action = action;
    }

    public Action getLaunchAction() {
        return action;
    }


}