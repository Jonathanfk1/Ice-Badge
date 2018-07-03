package netgames;

import br.ufsc.inf.leobr.cliente.Jogada;

public enum MessageType implements Jogada {
    BEGIN_GAME,
    LIST_OF_CHARACTERS,
    PLAYER_READY,
    CHANGED_TURN,
    TEXT,
    OPENED_BOARD,
    GAME_OVER;
}
