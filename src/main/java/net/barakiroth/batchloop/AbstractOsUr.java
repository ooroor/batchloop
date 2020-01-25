package net.barakiroth.batchloop;

import java.util.Random;

abstract class AbstractOsUr implements IOsUr {
	
	private boolean hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed = true;
	
	private final Random random = new Random(System.currentTimeMillis());
	
	protected AbstractOsUr() {
		try {
			Thread.currentThread().sleep(System.currentTimeMillis() % 500);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public boolean hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed() {
		return this.hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed;
	}
	
	@Override
	public void run() {
		System.out.println(this + "executing run() of " + this);
		this.hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed =
				this.random.nextInt(1000) > 50;
	}
}