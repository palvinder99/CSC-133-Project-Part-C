package com.mycompany.a3;


import java.util.Vector;

import com.codename1.charts.util.ColorUtil;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

/**
 * Game Class makes creates obj fields of GameWorld,MapView,ScoreView, and responsible for all Commands
 * and GUI Component that users can interact with such as buttons, sidemenu
 * @author PalvinderSingh
 *
 */
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private boolean paused;
	//Command field
	private UITimer gameTimer;
	private AccelerateCommand accelerateCommand = new AccelerateCommand();
	private BrakeCommand brakeCommand = new BrakeCommand();
	private LeftCommand leftCommand = new LeftCommand();
	private	RightCommand rightCommand = new RightCommand();
	private ExitCommand exitCommand= new ExitCommand();
	private HelpCommand helpCommand= new HelpCommand();
	private AboutUsCommand aboutUsCommand= new AboutUsCommand();
	private PauseCommand pauseCommand= new PauseCommand();
	private PositionCommand positionCommand= new PositionCommand(gw);
//	private TimerCommand timerCommand= new TimerCommand();
	
	//Button field
	private Button accelerate,brake,left,right,pause,position;
	
	private Toolbar myToolbar= new Toolbar();
	private Label checkStatusVal = new Label("OFF");
	@SuppressWarnings("deprecation")
	public Game() {
		gw= new GameWorld();
		mv= new MapView(gw);
		sv= new ScoreView();
		gw.addObserver(sv);
		gw.addObserver(mv);
		paused=false;
		Vector<GameObjectCollection> gameObj= new Vector<GameObjectCollection>();
		gameObj.add(new GameObjectCollection());
		
		gameTimer= new UITimer(this);
		gameTimer.schedule(500,true,this);

		accelerate= new Button("Accelerate");
		accelerate.getUnselectedStyle().setBgTransparency(255);
		accelerate.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		accelerate.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 0));
		
		brake= new Button("Brake");
		brake.getUnselectedStyle().setBgTransparency(255);
		brake.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		brake.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 0));
		
		left= new Button("Left");
		left.getUnselectedStyle().setBgTransparency(255);
		left.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		left.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 0));
		
		right= new Button("Right");
		right.getUnselectedStyle().setBgTransparency(255);
		right.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		right.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 0));
		
		pause= new Button ("Pause");
		pause.getUnselectedStyle().setBgTransparency(255);
		pause.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		pause.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 0));
		
		position= new Button ("Position");
		position.getUnselectedStyle().setBgTransparency(255);
		position.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		position.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 0));
	
		position.setEnabled(false);
		positionCommand.setEnabled(false);
		//Set Color 
		//Command object for each command in the game
		//Command accelerateCommand = new AccelerateCommand();
		//Command brakeCommand = new BrakeCommand();
	//	Command leftCommand = new LeftCommand();
	//	Command rightCommand = new RightCommand();
	//	Command exitCommand= new ExitCommand();
	//	Command helpCommand= new HelpCommand();
	//	Command aboutUsCommand= new AboutUsCommand();
	//	Command pauseCommand= new PauseCommand();
		
		//Adding function to pause button
		//Set Target for each command
		((AccelerateCommand) accelerateCommand).setTarget(gw);
		((BrakeCommand) brakeCommand).setTarget(gw);
		((LeftCommand) leftCommand).setTarget(gw);
		((RightCommand) rightCommand).setTarget(gw);
		//((SpiderCollideCommand) spiderCollideCommand).setTarget(gw);
		//((FlagCollideCommand) flagCollideCommand).setTarget(gw);
		//((FoodCollideCommand) foodCollideCommand).setTarget(gw);
	//	((TimerCommand) timerCommand).setTarget(gw);
		((ExitCommand) exitCommand).setTarget(gw);
		((AboutUsCommand) aboutUsCommand).setTarget(gw);
		((PauseCommand) pauseCommand).setTarget(this);
		positionCommand.setTarget(gw);
		
		
		//Setting command to buttons
		accelerate.setCommand(accelerateCommand);
		brake.setCommand(brakeCommand);
		left.setCommand(leftCommand);
		right.setCommand(rightCommand);
		pause.setCommand(pauseCommand);
		setLayout(new BorderLayout(BASELINE));
		setToolbar(myToolbar);
		myToolbar.addCommandToRightBar(helpCommand);
		myToolbar.setTitle("The Path Game");
		
		CheckBox checkSideMenuComponent = new CheckBox("Sound");

		checkSideMenuComponent.getAllStyles().setBgTransparency(255);
		checkSideMenuComponent.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		//create a command object and set it as the command of check box
		Command mySound= new SoundCommand(this);
		((SoundCommand) mySound).setTarget(gw);
		//add the CheckBox component as a side menu item
		checkSideMenuComponent.setCommand(mySound);

		checkSideMenuComponent.setComponentState(true);
		myToolbar.addComponentToSideMenu(checkSideMenuComponent);
		//Label checkStatusCheck= new Label ("Check Box Status: ");
		myToolbar.addCommandToSideMenu(accelerateCommand);
		myToolbar.addCommandToSideMenu(exitCommand);
		myToolbar.addCommandToSideMenu(aboutUsCommand);
		//topContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.YELLOW));
		
		Container statContain= new Container (new FlowLayout(CENTER));
		statContain.getAllStyles().setPadding(Component.BOTTOM, 10);
		statContain.getAllStyles().setBgTransparency(255);
		statContain.getAllStyles().setBgColor(ColorUtil.rgb(153, 153, 153));
		statContain.add(sv);
		//Top panel for scores and status;
		add(BorderLayout.NORTH,statContain);
		
	
		statContain.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.CYAN));
		
		
		
		//left container with boxlayout positioned on the west
		Container leftContainer= new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Start adding components at a location 50 pixels below the upper border of the container
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		leftContainer.getAllStyles().setBgTransparency(255);
		leftContainer.getAllStyles().setBgColor(ColorUtil.rgb(153, 153, 153));
		leftContainer.add(accelerate);
		leftContainer.add(left);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.YELLOW));
		add(BorderLayout.WEST,leftContainer);
		//Right container with the GridLayout position on the east
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.getAllStyles().setBgTransparency(255);
		
		rightContainer.getAllStyles().setBgColor(ColorUtil.rgb(153, 153, 153));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.add(brake);
		rightContainer.add(right);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		//add similar components that exists on the left container
		add(BorderLayout.EAST,rightContainer);
		
		Dimension d= new Dimension(1000,1000);
		//Add empty container to the center
		//Container centerContainer = new Container();
		
		add(BorderLayout.CENTER,mv);
		mv.getAllStyles().setBgColor(ColorUtil.MAGENTA);
		
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		// Setting color of container
		bottomContainer.getAllStyles().setBgTransparency(255);	
		bottomContainer.getAllStyles().setPadding(Component.BOTTOM, 20);
		bottomContainer.getAllStyles().setBgColor(ColorUtil.rgb(153, 153, 153));
		
		//Buttons for container	
		bottomContainer.add(pause);
		bottomContainer.add(position);
		//Setting border Color 
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.YELLOW));
		add(BorderLayout.SOUTH,bottomContainer);
		
		addKeyListener('a',accelerateCommand);
		addKeyListener('b',brakeCommand);
		addKeyListener('l',leftCommand);
		addKeyListener('r',rightCommand);
	//	addKeyListener('f',foodCollideCommand);
	//	addKeyListener('g',spiderCollideCommand);
	//	addKeyListener('t',timerCommand);
		addKeyListener('x',exitCommand);
		
		
		// code here to query MapView’s width and height and set them as world’s    
		// width and height
		
		//Register the views so they update
		this.show();
		//1674 1254
		//mv.setHeight(1000);
		//mv.setWidth(1000);
		mv.setSize(d);
		System.out.println("Center container width/height (printed AFTER show()): " + mv.getWidth() + " " + mv.getHeight());
		//Initialization of GameWorld
		gw.init();
		
		//Sounds
		gw.createSounds();
	//	gw.createBackground("background.wav");
		revalidate();
		//gw.playBackground();
		
	}
	public void resuming() {
		paused=false;
		
		//Enable key Listener
		addKeyListener('a',accelerateCommand);
		addKeyListener('b',brakeCommand);
		addKeyListener('l',leftCommand);
		addKeyListener('r',rightCommand);
		addKeyListener('x',exitCommand);
		
		
		gameTimer.schedule(500,true,this);
		pause.setText("Pause");
		pause.setEnabled(true);
		accelerate.setEnabled(true);
		accelerateCommand.setEnabled(true);
		brake.setEnabled(true);
		brakeCommand.setEnabled(true);
		left.setEnabled(true);
		leftCommand.setEnabled(true);
		right.setEnabled(true);
		rightCommand.setEnabled(true);
		helpCommand.setEnabled(true);
		aboutUsCommand.setEnabled(true);
		pauseCommand.setEnabled(true);
		myToolbar.setEnabled(true);
		//timerCommand.setEnabled(true);
		position.setEnabled(false);
		positionCommand.setEnabled(false);
		gw.playBackground();
	}
/*
	public void setCheckStatusVal(boolean b) {
		if (b) {
			checkStatusVal.setText("ON");
		} else {
			checkStatusVal.setText("OFF");
			revalidate();
		}
		
	}
*/	
	public boolean paused() {
		return paused;
	}
	
	public boolean playing() {
		return !paused;
	}
	
	public void pauseGame() {
		//Stop Timer
		gameTimer.cancel();
		paused=true;
		pause.setText("Play");
		accelerate.setEnabled(false);
		accelerateCommand.setEnabled(false);
		brake.setEnabled(false);
		brakeCommand.setEnabled(false);
		left.setEnabled(false);
		leftCommand.setEnabled(false);
		right.setEnabled(false);
		rightCommand.setEnabled(false);
		pause.setEnabled(true);
		pauseCommand.setEnabled(true);
		myToolbar.setEnabled(false);
		
		//timerCommand.setEnabled(true);
		position.setEnabled(true);
		positionCommand.setEnabled(true);
		gw.stopBackground();
		
		//Disable keyListener
		removeKeyListener('a',accelerateCommand);
		removeKeyListener('b',brakeCommand);
		removeKeyListener('l',leftCommand);
		removeKeyListener('r',rightCommand);
		removeKeyListener('x',exitCommand);
	}
	
	@Override
	public void run() {
		gw.tickClock();
		
	}
}	
