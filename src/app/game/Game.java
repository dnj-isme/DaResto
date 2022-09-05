package app.game;

import app.model.Restaurant;
import app.model.entity.customer.CustomerGenerator;
import app.model.entity.customer.GeneratorFetcher;
import app.utility.Timer;
import app.utility.Utility;

public class Game extends Timer{

	private static Game singleton;
	
	public static Game getInstance() {
		if(singleton == null) singleton = new Game();
		return singleton;
	}
	
	private boolean clean = true;
	private GameTimerManager timerManager = GameTimerManager.getInstance();
	private Restaurant restaurant = Restaurant.getInstance();
	private GeneratorFetcher fetcher = GeneratorFetcher.getInstance();
	
	// for simplyfing Pause Menu
	private PauseMenu pauseMenu = PauseMenu.getInstance();

	public boolean isClean() {
		return clean;
	}

	private Game() {
		reInit();
	}
	
	// Prelaunch game...
	public void reInit() {
		timerManager.clear();
		timerManager.register(this);
		fetcher.reset();
		fetcher.subscribe(new CustomerGenerator(restaurant));
		this.clean = true;
	}

	// Game starting and running
	private void registerName() {
		Utility.cls();
		System.out.println("Let's set your restaurant name before starting!");
		System.out.println("");
		
		String input = "";
		do {
			System.out.print("Input restaurant name : ");
			input = Utility.nextLine();			
		} while (input.length() < 3 || input.length() > 15);
		
		restaurant.generate(input);
	}
	
	@Override
	public void start() {
		// if the class is not clean yet, we reinit the game
		if(!clean) reInit();
		
		// starting the game...
		// because the game is starting, the class is now dirty
		this.clean = false;
		registerName();
		super.start();
		super.pause();
		
		while(this.isRunning()) {
			if(!isPaused()) {
				// wait until user press Pause
				refreshPage();
				Utility.nextLine();
				
				// if game is still running
				if(isRunning()) {
					timerManager.switchState(GameState.PAUSE);
				}
			}
			// if the game is paused, the condition will go here...
			else pauseMenu.pause();
		}
	}

	public synchronized void refreshPage() {
		Utility.cls();
		restaurant.printStatus();
		restaurant.printPeople();
	}

	@Override
	protected void timerOnTick() {
		// refresh page for every seconds
		if(getTick() % getTicksPerSecond() == 0) {
			refreshPage();
			
			if(getSeconds() % 3 == 0) {
				fetcher.doAction();
			}
		}
	}
}
