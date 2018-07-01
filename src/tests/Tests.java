package tests;

import game.Control;
import game.Player;
import game.TypeCharacter;
import board.Position;
import game.Character;

public class Tests {

	private Control control;
	private Player player;
	private Player opponent;

	public Tests(Control control) {
		this.control = control;		
	}

	public void testMockGameStart() {
		
	}

	public void testMove() {
		Character cPl = new Character(20, 20, 20, 20, TypeCharacter.SWORDSMAN);
		Character cOp = new Character(20, 20, 20, 20, TypeCharacter.SWORDSMAN);
		
		Position p1 = this.control.getGame().getPosition(10, 10);
		Position p2 = this.control.getGame().getPosition(11, 12);
		
		this.player.addCharacter(cPl);
		this.opponent.addCharacter(cOp);
		
		p1.setCharacter(cPl);
		p2.setCharacter(cOp);
		
		this.control.makeAction(10, 10);
		this.control.makeAction(11, 13);
		this.control.makeAction(11, 12);
	}

}
