package multiThreadSum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class MakeSum implements Callable<Integer>{
	AtomicInteger sum = null;
	ArrayList<Integer> threadSequence = null;
	Random random = null;

	public MakeSum( ) {
		this.sum = new AtomicInteger(0);
		this.threadSequence = new ArrayList<>();
		this.random = new Random();		
	}

	@Override
	public Integer call() throws Exception {
		
		int valueToSum = 0;
		for(int i=0; i<10; i++) {
			valueToSum = random.nextInt(10);
			//printValuesBeforeSUm(sum,valueToSum);
			//sum.getAndAdd(valueToSum);
			//printValuesAfterSum(sum,valueToSum);
			
			syncPrintValues(sum, valueToSum);
			
		}
		
		return null;
	}

	private synchronized void printValuesBeforeSUm(AtomicInteger sum, int valueToSum) {
		System.out.println("Thread : "+ Thread.currentThread().getId());	
		System.out.println("Valore di sum prima della somma : "+ sum);	
		System.out.println("Valore da sommare : "+ valueToSum);	
	}

	private synchronized void printValuesAfterSum(AtomicInteger sum, int valueToSum) {
		System.out.println("Thread : "+ Thread.currentThread().getId());	
		System.out.println("Valore finale : "+ sum);
		System.out.println("Inserisco thread nella sequence");
		threadSequence.add((int) Thread.currentThread().getId());
		System.out.println("---------------");
	}
	
	private synchronized void syncPrintValues(AtomicInteger sum, int valueToSum) {
		System.out.println("Thread : "+ Thread.currentThread().getId());	
		System.out.println("Valore di sum prima della somma : "+ sum);	
		System.out.println("Valore da sommare : "+ valueToSum);
		sum.getAndAdd(valueToSum);
		System.out.println("Valore finale : "+ sum);
		System.out.println("Inserisco thread nella sequence");
		threadSequence.add((int) Thread.currentThread().getId());
		System.out.println("---------------");
	}

	public AtomicInteger getSum() {
		return sum;
	}


	public List<Integer> getThreadSequence() {
		return threadSequence;
	}

}
