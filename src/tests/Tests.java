package tests;

import game.Control;
import game.Game;
import game.Player;
import game.TypeCharacter;
import board.Position;
import game.Character;

public class Tests {

	private Control control_;
	private Game game_;
	private Player player_;
	private Player opponent_;

	public Tests(Control control) {
		this.control_ = control;
		this.control_.delegateCreateNewGameToActorPlayer(1);
		this.game_ = this.control_.getGame();
		
		
		
		testCreatePlayers();
		testMove();
		testBases();
		testSelectCharacter();
	}

	private void testSelectCharacter() {
		this.control_.selectCharacter(TypeCharacter.CLERIG);
		
	}

	private void testCreatePlayers() {
		this.player_ = this.control_.getActorPlayer();
		this.player_.setName("Player 1");
		this.opponent_= this.game_.getOpponent();
		this.opponent_.setName("Player 2");
		
	}

	private void testMove() {
		Character cPl = new Character(20, 20, 20, 20, TypeCharacter.SWORDSMAN);
		Character cOp = new Character(20, 20, 20, 20, TypeCharacter.SWORDSMAN);
		
		Position p1 = this.game_.getPosition(10, 10);
		Position p2 = this.game_.getPosition(11, 12);
		
		this.player_.addCharacter(cPl);
		this.opponent_.addCharacter(cOp);
		
		p1.setCharacter(cPl);
		p2.setCharacter(cOp);
		
		this.control_.makeAction(10, 10);
		this.control_.makeAction(11, 13);
		this.control_.makeAction(11, 12);
		
	}
	
	private void testBases() {
		this.player_.getMainBase();
		this.opponent_.getMainBase();
	}

}
