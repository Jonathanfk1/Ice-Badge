package game;

import board.Position;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Character implements Jogada {

	private static final long serialVersionUID = 6209382746370126888L;
	protected int lifeValue;
	protected int attackDamage;
	protected int attackRange;
	protected int moveRange;
	protected boolean movedThisTurn;
	protected boolean attackedThisTurn;
	protected TypeCharacter type;
	protected boolean willDoubleAttack;
	protected boolean willCounterAttack;
	protected Position position;

	public Character(int lifeValue, int attackDamage, int attackRange, int moveRange, TypeCharacter type) {
		super();
		this.lifeValue = lifeValue;
		this.attackDamage = attackDamage;
		this.attackRange = attackRange;
		this.moveRange = moveRange;
		this.movedThisTurn = false;
		this.attackedThisTurn = false;
		this.type = type;
		this.willDoubleAttack = false;
		this.willCounterAttack = false;
		this.position = new Position(0, 0, this);
	}
	
	public int getLife() {
		return this.lifeValue;
	}

	public TypeCharacter getType() {
		return this.type;
	}

	public boolean get_moved() {
		return this.movedThisTurn;
	}

	public boolean getAttacked() {
		return this.attackedThisTurn;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
