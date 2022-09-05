package app.utility;

public abstract class Timer implements Runnable{
	
	// interval between ticks (in ms)
	private final int interval = 250;

	private Thread thread;
	private int tick;
	
	private boolean isRunning = false;
	private boolean isPaused = false;
	
	public int getTick() {
		return tick;
	}
	
	public double getTicksPerSecond() {
		return 1000 / interval;
	}
	
	public int getSeconds() {
		return tick / (1000 / interval);
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public boolean isPaused() {
		return isPaused;
	}
	
	public void pause() {
		isPaused = true;
	}
	
	public void resume() {
		isPaused = false;
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public void start() {
		thread.start();
		isRunning = true;
	}

	public Timer() {
		thread = new Thread(this);
	}

	@Override
	public final void run() {
		while(isRunning) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) { }
			
			if(!isPaused) {
				tick++;
				timerOnTick();
			}
		}
	}
	
	protected abstract void timerOnTick();
}
