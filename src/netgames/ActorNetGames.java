package netgames;

import javax.swing.JOptionPane;

import board.BoardSide;
import board.Position;
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
import gui.GUISelectCharacter;


public class ActorNetGames implements OuvidorProxy {

	private static final long serialVersionUID = 6879226942687723339L;
	// protected int id;
	protected Proxy proxy;
	protected Control control;
	protected boolean isMyTurn;
	protected boolean isReadyToStart;
	protected boolean isOpponentReadyToStart;

	public ActorNetGames(Control control) {
		super();
		this.proxy = Proxy.getInstance();
		this.proxy.addOuvinte(this);
		this.control = control;
		this.isMyTurn = false;
		this.isReadyToStart = false;
		this.isOpponentReadyToStart = false;
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		this.control.setNetGamesPosition(posicao);
		if (posicao == 1) {
			this.isMyTurn = true;
			// this.control.getGame().getPlayer().setId(1);
		} else {
			this.isMyTurn = false;
			// this.control.getGame().getPlayer().setId(2);
		}
		this.control.setIsRoomStarted(true);
	}

	public void sendMessage(Message message) {
		try {
			proxy.enviaJogada(message);
		} catch (NaoJogandoException e) {
			new JOptionPane().setMessage("Not playing.");
			e.printStackTrace();
		}
	}

	public void sendAction(Action action) {
		LaunchAction launchAction = new LaunchAction(action);
		try {
			proxy.enviaJogada(launchAction);
			isMyTurn = false;
		} catch (NaoJogandoException e) {
			new JOptionPane().setMessage("Not playing.");
			e.printStackTrace();
		}
	}

	public void sendStart() {
		try {
			this.proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "You're disconnected.");
			e.printStackTrace();
		}
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		LaunchAction launchAction = null;
		Message message = null;
		if (jogada instanceof LaunchAction) {
			launchAction = (LaunchAction) jogada;

			switch (launchAction.getLaunchAction().getType()) {
				default:
					if (launchAction.getLaunchAction().getMovedCharacter() != null && launchAction.getLaunchAction().getFinalPosition() != null) {
						this.control.updatePositions(null, launchAction.getLaunchAction().getFinalPosition());	
					}
					if (launchAction.getLaunchAction().getAttackedCharacter() != null) {
						this.control.updateCharacter(launchAction.getLaunchAction().getAttackedCharacter());
					}
				break;
			}

		} else if (jogada instanceof Message) {
			message = (Message) jogada;

				switch (message.getMessageType()) {
				case PLAYER_READY:
					if (!(this.control.getCurrentMenu() instanceof GUISelectCharacter)) {
						this.control.openSelectCharacterMenu();
					}
					BoardSide opponentBoardSide = message.getPlayerBoardSide();
					if (opponentBoardSide == BoardSide.DOWN) {
						this.control.getGame().getOpponent().setBoardSide(opponentBoardSide);
						this.control.getGame().getPlayer().setBoardSide(BoardSide.UP);
					} if (opponentBoardSide == BoardSide.UP) 	{
						this.control.getGame().getOpponent().setBoardSide(opponentBoardSide);
						this.control.getGame().getPlayer().setBoardSide(BoardSide.DOWN);
					}
				break;
				case BEGIN_GAME:

					if (this.isReadyToStart) {
						this.control.gameAboutToStart(message.getListOfCharacters());
						this.control.createGame(this.isMyTurn);
						this.control.openNewBoard();
						Message openedBoardMessage = new Message(MessageType.OPENED_BOARD, this.control.getSelectedCharacters(), 
						this.control.getGame().getBoard().getPositions());
						this.control.getActorNetGames().sendMessage(openedBoardMessage);
					} else {
						this.control.warnPlayerNotReady();
					}
				break;
				case OPENED_BOARD:
					if (this.control.getSelectCharacterGui() != null) {
						if (this.control.getGuiBoard() == null) {
							this.control.gameAboutToStart(message.getListOfCharacters());
							this.control.createGameWithSetPositions(this.isMyTurn, message.getPositions());
							this.control.openNewBoard();
						}
					}
				break;
				case CHANGED_TURN: 
					Position[][] newPositions = message.getPositions();
					this.isMyTurn = !this.isMyTurn;
					this.control.setNewPositions(newPositions);
				break;
				case GAME_OVER:
					this.control.warnGameIsOver(false);
				break;
				default:
				break;
			}

		}
	}

	public boolean connect(String ip, String name) {
		try {
			if(this.proxy == null) {
				JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "Proxy is null.", "Proxy error", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				this.proxy.conectar(ip, name);
				JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "Sucessfully connected", "Connected", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "You're already connected.", "Already connected", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			this.control.setIsRoomStarted(false);
			this.control.setIsConnected(false);
			return false;
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "Connection failure.", "Can't connect", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			this.control.setIsRoomStarted(false);
			this.control.setIsConnected(false);
			return false;
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "Multiplayer Property files error.", "Property eror", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			this.control.setIsRoomStarted(false);
			this.control.setIsConnected(false);
			return false;
		}
	}

	public void disconnect() {
		try {
			this.proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "You're already disconnected.", "Already disconnected", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(this.control.getCurrentMenu(), message);
		this.control.setIsRoomStarted(false);
	}

	public String askForOpponentName() {
		String name = "DefaultOpponentName";

		if(isMyTurn) { 
			name = proxy.obterNomeAdversario(2);
		} else {
			name = proxy.obterNomeAdversario(1);
		}

		return name;
	}

	@Override
	public void tratarConexaoPerdida() {
		JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "A conexão com o servidor foi perdida!");
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		JOptionPane.showMessageDialog(this.control.getCurrentMenu(), "Não foi possível iniciar a partida");
	}

	public boolean getIsTurn() {
		return isMyTurn;
	}

	public boolean getIsReadyToStart() {
		return this.isReadyToStart;
	}

	public void setIsReadyToStart(boolean b) {
		this.isReadyToStart = b;
	}

}
