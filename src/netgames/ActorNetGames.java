package netgames;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.rmi.MarshalledObject;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.swing.JOptionPane;

import board.Board;
import board.Position;
import board.TypeTile;
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
import game.TypeAction;

public class ActorNetGames implements OuvidorProxy {

	private static final long serialVersionUID = 6879226942687723339L;
	protected int id;
	protected Proxy proxy_;
	protected Control control;
	public boolean isMyTurn = false;

	public ActorNetGames(Control control) {
		super();
		proxy_ = Proxy.getInstance();
		proxy_.addOuvinte(this);
		this.control = control;
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if (posicao == 1) {
			this.isMyTurn = true;
		} else {
			this.isMyTurn = false;
		}
		this.control.startPlayOverNet(this.isMyTurn);
	}

	public void startGameOnline() {
		try {
			this.proxy_.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "You're disconnected.");
			e.printStackTrace();
		}
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), message);
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		LaunchAction launchAction = (LaunchAction) jogada;
		this.control.receiveLaunchedAction(launchAction.getLaunchAction());
		isMyTurn = true;
	}

	@Override
	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "A conexão com o servidor foi perdida!");
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "Não foi possível iniciar a partida");
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
		try {
			if(this.proxy_ == null) {
				JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "Proxy is null.", "Proxy error", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				this.proxy_.conectar(ip, name);
				JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "Sucessfully connected", "Connected", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "You're already connected.", "Already connected", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "Connection failure.", "Can't connect", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "Multiplayer Property files error.", "Property eror", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
	}

	public void disconnect() {
		try {
			this.proxy_.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentFrame(), "You're already disconnected.", "Already disconnected", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

}
