package com.mycompany.a3;




import java.io.InputStream;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;


public class GameWorld extends Observable{
	GameObjectCollection myObj;
	private int height=1000;
	private int width=1000;
	private int tick=0;
	private int livesRemaining=3;
	private boolean isFlag;
	private Ant ant; 
	private boolean gamePaused= false;
	private boolean sound=false;
	private Vector<GameObjects> antCollision;
	private Vector<GameObjects> foodCollision;
	private Vector<GameObjects> flag1Collision;
	private Vector<GameObjects> spider1Collision;
	
	private BGSound background;
	private Sound spiderCollision;
	private Sound crunch;
	private Sound cheer;

	
	//Constructor to Initialize
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void init() {	
			System.out.println();
			System.out.println("Instiated World");
			antCollision = new Vector();
			foodCollision= new Vector();
			flag1Collision = new Vector();
			spider1Collision = new Vector();
			
			//Initialize GameObjectCollection for the purpose of deleting old data
			//once init() method is called appropriately.
			myObj = new GameObjectCollection();
			ant=ant.getInstance();
			Point p1 = new Point((float) 100.0, (float) 300.0);
			Point p2= new Point ((float) 900.0, (float) 200.0);
			Point p3 = new Point((float) 100.5, (float) 800.4);
			Point p4= new Point ((float) 900.2, (float) 900.3);
			Random random= new Random();
			
			//Flag 1 / Initial Flag (int size, int color, Point location, int sequenceNumber) 
			Flags flag1 = new Flags(100, ColorUtil.BLUE, p1, 1);
		
			//Flag 2
			Flags flag2 = new Flags(100, ColorUtil.BLUE, p2, 2);
		
			//Flag 3
			Flags flag3 = new Flags(100, ColorUtil.BLUE, p3, 3);
				
			//Flag 4
			Flags flag4 = new Flags(100, ColorUtil.BLUE, p4, 4);
			
			//Ant(int size, int color, Point location, int heading, int speed,int maximumSpeed, int foodLevel, int foodConsumption,int healthLevel,int lastFlagReached)
			//ant= new Ant(10,ColorUtil.rgb(255, 0, 0),p1,0, 5, 50, 20, 2, 10, 1);
			//Dimnension dCmpSize= new Dimension()
			
			//Spider1 (size,color,location,heading,speed)
			Point spider1Location= new Point((float) random.nextInt(1000),(float) random.nextInt(1000));
			Spider spider1= new Spider(random.nextInt(40)+60, ColorUtil.BLACK, spider1Location, random.nextInt(360), random.nextInt(5)+2);
		
			//Spider2 (size,color,location,heading,speed)
			Point spider2Location= new Point((float) random.nextInt(1000),(float) random.nextInt(1000));
			Spider spider2= new Spider(random.nextInt(40)+60, ColorUtil.BLACK, spider2Location, random.nextInt(360), random.nextInt(5)+2);
		
			//Food Station 1 (int size, int color, Point location, int capacity)
			int size1=random.nextInt(40)+40;
			Point food1Location= new Point ((float) random.nextInt(1000), (float) random.nextInt(1000));
			FoodStations foodStation1 = new FoodStations(size1,ColorUtil.GREEN,food1Location,size1);
			
			//Food Station 2(int size, int color, Point location, int capacity)
			int size2=random.nextInt(40)+40;
			Point food2Location= new Point ((float) random.nextInt(1000), (float) random.nextInt(1000));
			FoodStations foodStation2 = new FoodStations(size2,ColorUtil.GREEN,food2Location,size2);
			
			//All objects added into ArrayList
			myObj.add(flag1);
			myObj.add(flag2);
			myObj.add(flag3);
			myObj.add(flag4);
			myObj.add(ant);
			myObj.add(spider1);
			myObj.add(spider2);
			myObj.add(foodStation1);
			myObj.add(foodStation2);
			setChanged();
			notifyObservers();
			ant.setGameWorld(this);
		}
	
	//This method keeps track of user input of flag numbers and makes sure flag reached in order
		public void flagReaching(int input) {
			if(soundOn()) {
				cheer.play();
			}
			//Parsing user key command for flags into integer value
			int inum=input;
			//Locating instance of ant in the arraylist
			//Checking to make sure flags reached are in order
			if(inum > ant.getLastFlagReached() && inum< ant.getLastFlagReached()+2) {
				ant.setLastFlagReached();
				setChanged();
				notifyObservers();
			}
			if(ant.getLastFlagReached()==4) {
				winner();
				setChanged();
				notifyObservers();
			}
			//setChanged();
			//l notifyObservers();
		}
		
		//Winner method displays when the user has reached all flags in order
		private void winner() {
			
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println("Congraturlation You have Won the Game");
			System.out.println("------------------------------------");
			System.exit(0);
		}
		
		//This method is called when user either quits the game, or gameover when user fails to complete the game
		public void exitGame() {
			System.exit(0);
		} 		
	
		//Updates Heading of Spider, all moveable Objects depending on speed and heading
		//reduced ant food and elapsed clock time
		/*public void tickClock() {
			IIterator iterator = myObj.getIterator();
			Object currObj= new Object();
			Random range= new Random();
			int pathRandom= 0;
			
			System.out.println();
			setTick();
			while(iterator.hasNext()) {
				currObj= iterator.next();
				double spiderHeading= 0;
				if (currObj instanceof Moveable) {
					Moveable mobj= (Moveable) currObj;
					if(currObj instanceof Spider) {
						//Random value between 0 or 1 to randomly add or subract from spider heading
						pathRandom= range.nextInt(1);
						switch(pathRandom) {
							//Adds five degrees to the heading of spider
						case 0: 
							spiderHeading=((Moveable) currObj).getHeading()+ 10;
							mobj.move(20,spiderHeading);
							
							break;
						case 1:
							spiderHeading=((Moveable) currObj).getHeading()-10;
							mobj.move(20,spiderHeading);
							break;
							
						}
						if(((GameObjects) currObj).getLocation().getY() >=height || ((GameObjects) currObj).getLocation().getY()<=0.0 || ( ((GameObjects) currObj).getLocation().getX() >=width || (((GameObjects) currObj).getLocation().getX() <=0.0 ))) {
							//Subtract 90 degrees from the current outofbounds heading
							((Moveable) currObj).setHeading(((Moveable) currObj).getHeading()-90);
						
						}
					}		
					//Checks for spider being out of bound, then turns the spider around
					 if (currObj instanceof Ant) {
						mobj= (Moveable) currObj;
						mobj.move(20);
						if(getLivesRemaining() !=0) {
							if(((Ant) currObj).getFoodLevel()>0) {
								subtractFoodLevel();
								
							}else {
								setLivesRemaining();
								playerDied();
							}

						} else {
							gameOver();
						}		
					} 
				}
			}
			setChanged();
			notifyObservers();
		}*/
		
		public void tickClock() {
			IIterator iterator = myObj.getIterator();
			Object currObj= new Object();
			Random range= new Random();
			int pathRandom= 0;
			
			System.out.println();
			setTick();
			while(iterator.hasNext()) {
				currObj= iterator.next();
				double spiderHeading= 0;
				if (currObj instanceof Moveable) {
					Moveable mobj= (Moveable) currObj;
					if(currObj instanceof Spider) {
						//Random value between 0 or 1 to randomly add or subract from spider heading
						pathRandom= range.nextInt(1);
						switch(pathRandom) {
							//Adds five degrees to the heading of spider
						case 0: 
							spiderHeading=((Moveable) currObj).getHeading()+ 10;
						//	mobj.move(20,spiderHeading);
							
							break;
						case 1:
							spiderHeading=((Moveable) currObj).getHeading()-10;
						//	mobj.move(20,spiderHeading);
							break;
							
						}
						mobj.move(20, spiderHeading);
						
						if(((Moveable) currObj).getLocation().getX()+((GameObjects) currObj).getSize() >=1000.0 ||((Moveable) currObj).getLocation().getX() < 0.0) {
							((Moveable) currObj).setHeading(((Moveable) currObj).getHeading()+90);
						}
						if(((Moveable) currObj).getLocation().getY()+((GameObjects) currObj).getSize() >=1000.0 || ((Moveable) currObj).getLocation().getY() < 0.0) {
							((Moveable) currObj).setHeading(((Moveable) currObj).getHeading()+90);
						}
						/*if(((GameObjects) currObj).getLocation().getY() >=height || ((GameObjects) currObj).getLocation().getY()<=0.0 || ( ((GameObjects) currObj).getLocation().getX() >=width || (((GameObjects) currObj).getLocation().getX() <=0.0 ))) {
							//Subtract 90 degrees from the current outofbounds heading
							((Moveable) currObj).setHeading(((Moveable) currObj).getHeading()-90);
						
						} 
						
						if(((Moveable) currObj).getLocation().getX()+((GameObjects) currObj).getSize() >= 1000 || (((GameObjects) currObj).getLocation().getX() < 0)) {
							spiderHeading=180-((Moveable) currObj).getHeading();
						}
						if(((Moveable) currObj).getLocation().getY()+((GameObjects) currObj).getSize() >= 1000 || (((GameObjects) currObj).getLocation().getY() < 0)) {
							spiderHeading=180-((Moveable) currObj).getHeading();
						} */
					}		
					//Checks for spider being out of bound, then turns the spider around
					 if (currObj instanceof Ant) {
						mobj= (Moveable) currObj;
						mobj.move(20);
						if(getLivesRemaining() !=0) {
							if(((Ant) currObj).getFoodLevel()>0) {
								subtractFoodLevel();
								
							}else {
								setLivesRemaining();
								playerDied();
							}

						} else {
							gameOver();
						}		
					} 
				}
			}
				
			iterator = myObj.getIterator();
			while(iterator.hasNext()) {
				//Collidable Obj
				ICollider colObj= (ICollider) iterator.next();
				IIterator iter2= myObj.getIterator();
				while(iter2.hasNext()) {
					ICollider otherObj= (ICollider) iter2.next();
					if(otherObj !=colObj ||colObj !=otherObj) {
						if(colObj.collidesWith(otherObj) && colObj instanceof Ant) {
							//colObj.handleCollision(otherObj); 
							//antCollision.add((GameObjects) otherObj);
							if((colObj instanceof Ant && otherObj instanceof FoodStations && (!antCollision.contains(otherObj)))) {
								
								colObj.handleCollision(otherObj);
								antCollision.add((GameObjects) otherObj);
								foodCollision.add((GameObjects) (colObj));
							} 
							if((colObj instanceof Ant && otherObj instanceof Spider && (!antCollision.contains(colObj)))){
								
								colObj.handleCollision(otherObj);
								antCollision.add((GameObjects) otherObj);
								spider1Collision.add((GameObjects) colObj);
								
								
							}
							if((colObj instanceof Ant && otherObj instanceof Flags && (!antCollision.contains(otherObj)))){
							
								colObj.handleCollision(otherObj);
								antCollision.add((GameObjects) otherObj);
								flag1Collision.add((GameObjects) colObj);
							}
							
						}
						
					}
					//antCollision.remove(otherObj);
					spider1Collision.remove(otherObj);
					
				}
				
				
				
				
			} 

			
			setChanged();
			notifyObservers();
		}
		
		//method subtract foodLevel from ant level on each tick
		private void subtractFoodLevel() {
			this.ant.settotalLevel(ant.getFoodLevel()- (ant.getFoodConsumption()));
			
			
			
		}
		
		//method reinitializes the world
		private void playerDied() {
			init();
		}
		
		//This method displays gameover screen, then quits the game
		private void gameOver() {
			
			System.out.println("----------------------------");
			System.out.println("           Game Over        ");
			System.out.println("----------------------------");
			exitGame();
		}
		
		//This method display the map when the mapview is invoked 
		public void displayMap() {
			IIterator printIterator= myObj.getIterator();
			Object printObj= new Object();
			System.out.println();
			System.out.println("Map of the game:");
			while(printIterator.hasNext()) {
				printObj=printIterator.next();
				System.out.println(printObj.toString());
			}
			System.out.println();

		}
		
		//This method detects collision of ant with spider
		public void antCollision() {
			//Checks for if ant health is not 0
			if(soundOn()) {
				spiderCollision.play();
			}
			if (ant.getHealthLevel() !=0) {
				//Subtract 1 from health of ant
				ant.setHealthLevel();
				if(ant.getHealthLevel()==9) {

					ant.setColor(ColorUtil.rgb(201, 13, 13));
				} else if(ant.getHealthLevel()==8) {
					ant.setColor(ColorUtil.rgb(225, 164, 164));
				} else if(ant.getHealthLevel()==7) {
					ant.setColor(ColorUtil.rgb(209, 27, 27));
				} else if(ant.getHealthLevel()==6) {
					ant.setColor(ColorUtil.rgb(225, 46, 46));
				} else if(ant.getHealthLevel()==5) {
					ant.setColor(ColorUtil.rgb(238, 74, 74));
				} else if(ant.getHealthLevel()==4) {
					ant.setColor(ColorUtil.rgb(255, 90, 90));
				} else if(ant.getHealthLevel()==3) {
					ant.setColor(ColorUtil.rgb(253, 103, 103));
				} else if(ant.getHealthLevel()==2) {
					ant.setColor(ColorUtil.rgb(250, 129, 129));
				} else if(ant.getHealthLevel()==1) {
					ant.setColor(ColorUtil.rgb(252, 157, 157));
				} else if(ant.getHealthLevel()==0) {
					ant.setColor(ColorUtil.rgb(247, 184, 184));
				}
				
				//Decrease speed by 5
				brake();
				
				//Change color of ant to light red
				
			//If ant's health is 0 , re-initializes the game and subtacts 1 lives remaining
			} else if (ant.getHealthLevel() ==0){
				setLivesRemaining();
				playerDied();
			}
			//If lives remaining is 0, call gameover
			if(getLivesRemaining() == 0) {
				gameOver();
			}
			setChanged();
			notifyObservers();
		}	
		
		//Ant collides with food station
		public boolean foodCollision(int num) {
			if(soundOn()) {
				crunch.play();
			}
			Random random= new Random();
			//IIterator iterator= myObj.getIterator();
			//Object currentObj= new Object();
			//Counts the total food to add to ant
			//while( iterator.hasNext() ){
			//	currentObj = iterator.next();
				// found the first food Station
			//	if( currentObj instanceof FoodStations){
					// add food to ant remainingfood
					ant.settotalLevel(getFoodLevel()+ num);
					
					// remove the food station
				//	myObj.remove(currentObj);
					
					// create new FoodStations 
					int cap= random.nextInt(40)+10;
					Point food3Location= new Point ((float) random.nextInt(1000), (float) random.nextInt(1000));
					FoodStations foodStation3 = new FoodStations(cap,ColorUtil.GREEN,food3Location,cap);
					myObj.add(foodStation3);
					// remove successful, return true
					notifyObservers();
					return true;
			//	}
			//}
			//return false;
		}	
	
		//This method changes the direction of ant 5 degrees to the right
		public void directRight() {
			//Changes direction of ant to right on command
			ant.directionRight();
		}

		//This method changes the direction of ant 5 degrees to the left
		public void directLeft() {
			//Changes direction of ant to left on command
			ant.directionLeft();
		}

		//This method decreases acceleration of the ant by 5
		public void brake() {
			if (ant.getSpeed() > 0 &&  ant.getFoodLevel() !=0) {
				ant.setSpeedDec();	
			}
		}

		//This method increases acceleration of the any by 5
		public void accelerate(){
			//Checks if the health is lower, to account to maximum speed being limited to health of ant
			if (ant.getSpeed() < ant.getMaximumSpeed()) {
				int acceleration= (ant.getMaximumSpeed()-ant.getHealthLevel());
				ant.getSpeed(acceleration);
			}
		}

		//Getter method for lives remaining
		public int getLivesRemaining() {
			return livesRemaining;
		}

		//Subract the lives remaining value by 1
		public void setLivesRemaining() {
			this.livesRemaining--;
		}

		//Getter method for tick that calculates seconds
		public int getTick() {
			return tick;
		}

		//Adds tick each time the commands is entered
		public void setTick() {
			this.tick = tick+1;
			
		}
		
		//Gets the highest flag the ant has reached
		public int getHighestFlag() {
			return ant.getLastFlagReached();
		}
		
		//Gets food level for ant
		public int getFoodLevel() {
			return ant.getFoodLevel();
			
		}

		//Gets healthlevel of ant
		public int getHealth() {
			return ant.getHealthLevel();
		}
		
		//sets sound to turn on 
		public boolean soundOn() {
			return sound;
		}
		
		//Changes the value for sound to indicate it is turned on
		public void turnSoundOn() {
			sound=true;
			setChanged();
			notifyObservers();	
		}
		
		//Changes the value for sound to indicate it is turned off
		public void turnSoundOff() {
			sound=false;
			setChanged();
			notifyObservers();
		}
		
		//Toggles sound on or off
		public void toggleSound() {
			if(sound) {
				sound=false;
			} else {
				sound=true;
				
			}
			setChanged();
			notifyObservers();
		}

		public IIterator getIterator() {
			
			return myObj.getIterator();
		}
		
		public boolean pointerSelected() {
			IIterator iterator = myObj.getIterator();
			boolean flag=false;
			Object currentObj= new Object();
			while(iterator.hasNext()) {
				currentObj= iterator.next();
				if(((Fixed) currentObj).contains(((GameObjects) currentObj).getLocation(),((GameObjects) currentObj).getLocation())) {
					flag=false;
				} else {
					flag=true;
				}	
			}
			return flag;
		}
		
		public void setFlag() {
			isFlag=true;
		}
		
		public boolean isFlag() {
			return isFlag;
		}
		
		//Creating sounds
		public void createSounds() {
				try {
					background= new BGSound("background.wav");
					spiderCollision= new Sound("collision.wav");
					crunch= new Sound("cruch.wav");
					cheer= new Sound("cheering.wav");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		} 
		
		//checking if game is paused
		public boolean getgamePaused() {
			return  gamePaused;
		}
		
		//setting pause
		public void setPaused(boolean paused) {
			gamePaused=paused;
		}
		
		//playing background music 
		public void playBackground() {
			if(soundOn()) {
				background.play();
			}
		}
		
		//stopping background music
		public void stopBackground() {
				background.pause();
		}
	
		public void setPosition() {
			if(isFlag ==true) {
				isFlag=false;
			} else {
				isFlag=true;
			}
		}
		
		public void positionDisabled() {
			isFlag=false;
		}
		
		public void pressed(Point pPtrRelPrnt, Point pCmpRelPrnt) {
			IIterator obj= myObj.getIterator();
			Object tempObj= new Object();
			while(obj.hasNext()) {
				tempObj=obj.next();
				if(tempObj instanceof Fixed) {
					Fixed temporary= ((Fixed) tempObj);
					if(isFlag && temporary.isSelected()) {
						int xLoc= (int) (pPtrRelPrnt.getX()-pCmpRelPrnt.getX());
						int yLoc= (int) (pPtrRelPrnt.getY()-pCmpRelPrnt.getY());
						Point newPoint= new Point(xLoc,yLoc);
						temporary.setLocation(newPoint);
						isFlag=false;
						temporary.setSelected(false);
					} else {
						if(temporary.contains(pPtrRelPrnt, pCmpRelPrnt)) {
							temporary.setSelected(true);
						} else {
							temporary.setSelected(false);
						}
					}
				}
			}
			setChanged();
			notifyObservers(this);
		}
	
}

