package multiThreadSum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadSum {
	/**
	 * The purpose of this program is to add 20 random numbers (from 0 to 10) using
	 * multithread concurrency and store the result in a shared variable callled sumObj.
	 * In particular, the program creates two threads and runs them,
	 * Each thread generates a randoom number and adds it to a global sum variable.
	 * Before and after the sum, they print the sum variable status.
	 * At the end, the main prints the final results and the scheduled order of the thread. 
	 * @param args
	 */

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(2);
		
		try {
			
			MakeSum sumObj = new MakeSum();
			service.submit(sumObj);
			service.submit(sumObj);
			
			service.shutdown();
			service.awaitTermination(10,TimeUnit.SECONDS);
			
			if(sumObj.getSum() != null) {
				System.out.println("*************** VALORE FINALE ****************");
				System.out.println("Valore finale di sum = "+ sumObj.getSum());
				System.out.println("La sequenza di accesso alla variabile è = "+ sumObj.getThreadSequence());
			} else {
				System.out.println("sum è NULL");
			}

		} catch(SecurityException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
			System.exit(-2);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-3);
		}
		

		
		
		
		
		

	}

}
