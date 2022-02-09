package yices;

import java.io.IOException;

public class LppCegarRunner {
	public static final boolean DEBUG = true;
	@SuppressWarnings("unused")
	private static int count = 0;

	public static void main(String[] args) throws IOException, InterruptedException {
		// NOTE: Specify full path of the bin directory of yices if you are running on Unix/Linux machine
		String yicesCmd = "yices";
		
		// Note: Specify full path if not run from the project folder
		String codeFile = "yices-module/FrenchWar.ys";
		
		// NOTE: Here y would be the variable that you are trying to maximize
		LppCegarProcedure cegar = new LppCegarProcedure(yicesCmd, codeFile);
		cegar.optimize("y", LppCegarProcedure.OptimizationType.Maximize, 0);
		cegar.execute();
		
		System.out.println("Total Iterations: " + cegar.getTotalIterations());
		System.out.println("SAT: " + cegar.isSatisfiable());
		System.out.println("Optimum: " + cegar.isOptimum());
		System.out.println("Best Result: " + cegar.getBestResult());

//		System.out.println("\nAll Results: ");
//		cegar.getAllResults().forEach(m -> {
//			System.out.println("Intermediate Result " + ++count + ": " + m);
//		});
	}
}
