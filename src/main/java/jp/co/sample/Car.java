package jp.co.sample;

public class Car {
	private int speed = 2;

	public void run() {
		speed += 10;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
