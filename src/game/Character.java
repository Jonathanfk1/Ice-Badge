package game;

import board.Position;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.servidor.Jogo;

public class Character implements Jogada {

	protected int life_;
	protected int powerAttack_;
	protected int rangeAttack_;
	protected int rangeMove_;
	protected boolean moved_;
	protected boolean attacked_;
	protected TypeCharacter type_;
	private Position position;

	public Character(int life, int powerAttack, int rangeAttack, int rangeMove, TypeCharacter type) {
		super();
		this.life_ = life;
		this.powerAttack_ = powerAttack;
		this.rangeAttack_ = rangeAttack;
		this.rangeMove_ = rangeMove;
		this.type_ = type;
		this.moved_ = false;
		this.attacked_ = false;
		this.position = new Position(0, 0, this);
	}
	
	public int getLife() {
		return this.life_;
	}

	public TypeCharacter getType() {
		return this.type_;
	}

	public boolean get_moved() {
		return this.moved_;
	}

	public boolean getAttacked() {
		return this.attacked_;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
