package net.barakiroth.batchloop;

public interface IOsUr {
	boolean hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed();
	void run();
}
