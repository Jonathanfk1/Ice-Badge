package game;

public enum TypeCharacter {
	
	SWORDSMAN(40, 20, 5, 5),
	CLERIG(10, 25, 3, 5),
	ARCHER(30, 20, 8, 2),
	BARD(20, 20, 2, 6);

	protected int life;
	protected int attackDamage;
	protected int attackRange;
	protected int moveRange;

	TypeCharacter(int life, int attackDamage, int attackRange, int moveRange) {
		this.life = life;
		this.attackDamage = attackDamage;
		this.attackRange = attackRange;
		this.moveRange = moveRange;
	}

	public int getLife() {
		return this.life;
	}

	public int getpowerAttack() {
		return this.attackDamage;
	}

	public int getrangeAttack() {
		return this.attackRange;
	}

	public int getrangeMove() {
		return this.moveRange;
	}

}
