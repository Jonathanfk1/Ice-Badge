package netgames;

import javax.swing.JOptionPane;

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

public class ActorNetGames implements OuvidorProxy {

	private static final long serialVersionUID = 6879226942687723339L;
	protected int id;
	protected Proxy proxy_;
	protected Control control_;
	public boolean isMyTurn = false;

	public ActorNetGames(Control control) {
		super();
		proxy_ = Proxy.getInstance();
		proxy_.addOuvinte(this);
		this.control_ = control;
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1) {
			this.isMyTurn = true;
		} else {
			this.isMyTurn = false;
		}
		control_.startPlayOverNet(this.isMyTurn);
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
		JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), message);
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		LaunchAction launchAction = (LaunchAction) jogada;
		this.control_.receiveLaunchedAction(launchAction.getLaunchAction());
		isMyTurn = true;
	}

	@Override
	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "A conexão com o servidor foi perdida!");
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "Não foi possível iniciar a partida");
	}

	public String getOpponentName() {
		String name = "";

		if(isMyTurn) { 
			name = proxy_.obterNomeAdversario(2);
		} else {
			name = proxy_.obterNomeAdversario(1);
		}

		return name;
	}

	public void sendAction(Action action) {
		LaunchAction launchAction = new LaunchAction(action);
		try {
			proxy_.enviaJogada(launchAction);
			isMyTurn = false;
		} catch (NaoJogandoException e) {
			new JOptionPane().setMessage("Not playing.");
			e.printStackTrace();
		}
	}

	public boolean connect(String ip, String name) {
		boolean connectionSuccess = false;
		try {
			if(this.proxy_ == null) {
				JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "Proxy is null.", "Proxy error", JOptionPane.ERROR_MESSAGE);
			} else {
				this.proxy_.conectar(ip, name);
				return connectionSuccess = true;
			}
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "You're already connected.", "Already connected", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "Connection failure.", "Can't connect", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "Multiplayer Property files error.", "Property eror", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return connectionSuccess;
	}

	public void disconnect() {
		try {
			this.proxy_.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(this.control_.getCurrentFrame(), "You're already disconnected.", "Already disconnected", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

}
