package assignment1;

public abstract class Observable {
	
	private boolean observableState;
	
	public abstract void notifyObserver();
	
	public void setObservableState(boolean observable) {
		this.observableState = observableState;
		if (this.observableState)
			this.notifyObserver();
	}
	
	public boolean getObservableState() {
		return this.getObservableState();
	}
}
