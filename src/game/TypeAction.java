package game;

import br.ufsc.inf.leobr.cliente.Jogada;

public enum TypeAction implements Jogada  {
	ATTACK,
	MOVE,
	CHANGE_TURN,
	SELECT_CHARACTER;
}
