package multiThreadDictionary2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadDictionary {


	public static void main(String[] args) {
		Map<String, Integer> dictionary = new HashMap<>();
		ConcurrentHashMap<String, AtomicInteger> concurrentDictionary = new ConcurrentHashMap<>();
		List<Future> results = new ArrayList<>();
		//creare un pool di 10 thread
		final int nThread = 10;
		ExecutorService service = Executors.newFixedThreadPool(nThread);
		Print printer = new Print();
		Writer writer = new Writer (concurrentDictionary);
		
		try {
			Future result;
			for(int i=0; i<nThread ; i++) {
				result = service.submit(new CalculateOccurencies(i,printer,writer));
				//results.add(result);
			}
			service.shutdown();
			service.awaitTermination(100, TimeUnit.SECONDS);
			
			
			
			
			Print p = new Print();
			p.printNewMap(concurrentDictionary);
			
			//service.awaitTermination(1, TimeUnit.MINUTES);

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		service.shutdown();
		
		

	}

}
