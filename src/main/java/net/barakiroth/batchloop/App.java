package net.barakiroth.batchloop;

import java.util.ArrayList;
import java.util.Collection;

public class App {
	public static void main(String[] args) {
		
		System.out.println("===== STARTING =====");
		
		final Collection<IOsUr> services = new ArrayList<>();
		services.add(new Os());
		services.add(new Ur());
		
		final int sleepTimeBetweenRunsInMs = 1000;
		final int maxNumberOfRuns = 100;
		int actualNumberOfRuns = 0;
		
		do {
			System.out.println(System.lineSeparator() + "Run number " + String.valueOf(actualNumberOfRuns + 1));
			services
				.stream()
				.forEach(
						(final IOsUr service) -> {
							if (service.hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed()) {
								service.run();
							}
						}
				);
			actualNumberOfRuns++;
			if (
				actualNumberOfRuns < maxNumberOfRuns
				&&
				services
					.stream()
					.map((final IOsUr service) -> service.hasNotYetBeenRunOrLastRunWasUnsuccessfulAndANewRunMaySucceed())
					.reduce(false, ((b1, b2) -> b1 || b2))
			) {
				try {
					Thread.sleep(sleepTimeBetweenRunsInMs);
				} catch (Throwable e) {
				}
			} else {
				break;
			}
		} while (true);
		System.out.println(System.lineSeparator() + "===== Left retry loop, EXITING =====");
	}
}
