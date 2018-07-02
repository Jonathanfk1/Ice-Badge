package game;

import java.awt.Component;

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
	protected Player owner;

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
		this.owner = null;
	}
	
	public int attack(Character opponentPositionCharacter) {
		return opponentPositionCharacter.getAttacked(this);
	}

	public int getAttacked(Character attackant) {
		this.lifeValue = this.lifeValue - attackant.getAttackDamage();

		if (this.lifeValue <= 0) {
			System.out.println("Char killed");
			// this.owner.getCharactersList().remove(this);
		} else {
			return this.lifeValue;
		}
		return 0;
	}

	public int getLife() {
		return this.lifeValue;
	}

	public TypeCharacter getType() {
		return this.type;
	}

	public boolean getMoved() {
		return this.movedThisTurn;
	}

	public boolean getAttackedThisTurn() {
		return this.attackedThisTurn;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void setOwner(Player player) {
		this.owner = player;
	}

	public int getMoveRange() {
		return this.moveRange;
	}

	public int getAttackRange() {
		return this.attackRange;
	}

	public int getAttackDamage() {
		return this.attackDamage;
	}

}
