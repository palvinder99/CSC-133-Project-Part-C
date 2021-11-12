package com.mycompany.a3;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Command;
/**
 * Position Command is used selected position for new location of object
 * @author PalvinderSingh
 *
 */
public class PositionCommand extends Command {
	private static  PositionCommand positionCommand;
	private static GameWorld target;
	
	public PositionCommand(GameWorld target) {
		super("Position");
		this.target=target;
	}

	public static PositionCommand getInstance() {
		if(positionCommand == null) {
			positionCommand= new PositionCommand(target);
		}
		return positionCommand;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(target.isFlag()) {
			
		}
	}

	public void setTarget(GameWorld gw) {
		if (target==null) {
			target=gw;
		}
	}
}
