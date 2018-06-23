package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Control;
import game.TypeCharacter;

public class GUISelectCharacter extends JFrame {

	private static final long serialVersionUID = 862549634422345844L;
	protected Control control;
	protected JPanel panel_;

	public GUISelectCharacter(Control control) {
		this.control = control;
		this.panel_ = new JPanel();

		this.control.setGuiSelectCharacter(this);
		
		this.setFrame();
		this.setButtons();
	}

	private void setFrame() {
		this.setVisible(true);
		this.setSize(new Dimension(300, 450));
		this.setLocation(300, 500);
		this.pack();
		this.add(panel_);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void setButtons() {
		
		GridBagConstraints gbc = new GridBagConstraints();

		
		JButton swordsman = new JButton("SWORDSMAN");
		gbc.gridx = 0;
		gbc.gridy = 0;
		swordsman.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "SWORDSMAN");
					control.selectCharacter(TypeCharacter.SWORDSMAN);
			}
		});
		this.panel_.add(swordsman, gbc);
		
		
		JButton archer = new JButton("ARCHER");
		gbc.gridy++;
		archer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "ARCHER");
					control.selectCharacter(TypeCharacter.ARCHER);
			}
		});
		this.panel_.add(archer, gbc);
		
		
		JButton clerig = new JButton("CLERIG");
		gbc.gridy++;
		clerig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "CLERIG");
					control.selectCharacter(TypeCharacter.CLERIG);
			}
		});
		this.panel_.add(clerig, gbc);
		
		
		JButton bard= new JButton("BARD");
		gbc.gridy++;
		bard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "BARD");
					control.selectCharacter(TypeCharacter.BARD);
			}
		});
		this.panel_.add(bard, gbc);
		

	}
}
