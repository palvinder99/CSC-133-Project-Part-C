package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command {
	private static PauseCommand pauseCommand;
	private Game target;
	public PauseCommand() {
		super("Pause");
	}

	public static PauseCommand getInstance() {
		if(pauseCommand == null) {
			pauseCommand= new PauseCommand();
		}
		return pauseCommand;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(target.paused()) {
			target.resuming();
		} else {
			target.pauseGame();
		}
	}

	public void setTarget(Game gw) {
		if (target==null) {
			target=gw;
		}
	}

}
