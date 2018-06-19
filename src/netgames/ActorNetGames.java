package netgames;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import game.Control;
import gui.GUIInitMenu;

public class ActorNetGames implements OuvidorProxy {

	protected Proxy proxy_;
	protected Control control_;

	public ActorNetGames(Control control) {
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		this.control_.receiveBeginMessage(posicao);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		try {
			this.proxy_.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			new JOptionPane().setMessage("You're disconnected.");
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
