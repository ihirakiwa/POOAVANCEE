package serie04.motion.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Timer;

import util.Contract;

public class TimerAnimator extends AbstractAnimator  {
	private final String PROP_SPEED =  "speed";
	private int speed;
	private final Timer timer;
	private final PropertyChangeSupport pcs;
	
	private void ChangeValue() {
		int oldV = speed;
		int newV= speed == 0 ? getMaxSpeed() : speed-1;
		pcs.firePropertyChange(PROP_SPEED,oldV,newV);
	}

	public TimerAnimator(int max) {
		super(max);
		speed = getMaxSpeed()/2;
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeValue();
				
			}
		});
		pcs = new PropertyChangeSupport(this);
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public boolean hasStarted() {
		return timer.isRunning();
	}

	@Override
	public boolean hasStopped() {
		return  !timer.isRunning();
	}

	@Override
	public boolean isPaused() {
		return speed == 0 && timer.isRunning();
	}

	@Override
	public boolean isResumed() {
		return speed != 0 && timer.isRunning();
	}

	@Override
	public boolean isRunning() {
		return hasStarted() && !hasStopped();
	}

	@Override
	public void pause() {
		Contract.checkCondition(isRunning(), "isRunning()");
		speed = 0;
		fireStateChanged();
		fireTickOccured();
		
	}

	@Override
	public void resume() {
		Contract.checkCondition(isRunning(), "isRunning()");
		setSpeed( super.getMaxSpeed()/2);
		fireStateChanged();
		fireTickOccured();
	}

	@Override
	public void setSpeed(int d) {
		Contract.checkCondition(0 <= d && d <= getMaxSpeed(), "0 <= d && d <= getMaxSpeed()");
		speed = d;
		fireStateChanged();
		fireTickOccured();
	}

	@Override
	public void start() {
		if(isRunning()) {
			stop();
		}
		timer.start();
		fireStateChanged();
		fireTickOccured();
	}

	@Override
	public void stop() {
		Contract.checkCondition(isRunning(), "isRunning()");
		timer.stop();
		speed = 0;
		fireStateChanged();
		fireTickOccured();
	}
	
}
