package netgames;

import javax.swing.JOptionPane;

import actors.ActorPlayer;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import game.Action;
import game.Control;
import gui.GUIMainMenu;

public class ActorNetGames implements OuvidorProxy {

	protected int id;
	protected Proxy proxy_;
	protected Control control_;
	public boolean isMyTurn = false;

	public ActorNetGames(Control control) {
		super();
		proxy_ = Proxy.getInstance();
		proxy_.addOuvinte(this);
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		this.control_.delegateCreateNewGameToActorPlayer(posicao);
	}

	public void startGameOnline() {
		try {
			this.proxy_.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			new JOptionPane().setMessage("You're disconnected.");
			e.printStackTrace();
		}
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberJogada(Jogada jogada) {
		LaunchAction launchAction = (LaunchAction) jogada;
		this.control_.receiveLaunchedAction(launchAction.getLaunchAction());
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

	public void sendAction(Action action) {
		LaunchAction launchAction = new LaunchAction(action);
		try {
			proxy_.enviaJogada(launchAction);
		} catch (NaoJogandoException e) {
			new JOptionPane().setMessage("Not playing.");
			e.printStackTrace();
		}
	}

	public void connect(String ip, String name) {
		try {
			if(this.proxy_ == null) {
				new JOptionPane().showMessageDialog(null, "Proxy is null.", "Proxy error", JOptionPane.ERROR_MESSAGE);
			} else {
				this.proxy_.conectar(ip, name);
			}
		} catch (JahConectadoException e) {
			new JOptionPane().setMessage("You're already connected.");
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			new JOptionPane().setMessage("Connection failure.");
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			new JOptionPane().setMessage("Multiplayer Property files error.");
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			this.proxy_.desconectar();
		} catch (NaoConectadoException e) {

			new JOptionPane().setMessage("You're already disconnected.");
			e.printStackTrace();
		}

	}

}
