package multiThreadDictionary2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer {
	ConcurrentHashMap<String, AtomicInteger> concurrentDictionary ;

	public Writer(ConcurrentHashMap<String, AtomicInteger> concurrentDictionary) {
		super();
		this.concurrentDictionary = concurrentDictionary;
	}

	public ConcurrentHashMap<String, AtomicInteger> getConcurrentDictionary() {
		return concurrentDictionary;
	}

	/**
	 * put and get must to be atomic operation because
	 * Answering to a comment that asks for clarification on why this is a race condition.

Imagine there are two threads A, B that are going to put two different values in the map, v1 and v2 respectively, having the same key. The key is initially not present in the map. They interleave in this way:

Thread A calls containsKey and finds out that the key is not present, but is immediately suspended.
Thread B calls containsKey and finds out that the key is not present, and has the time to insert its value v2.
Thread A resumes and inserts v1, "peacefully" overwriting (since put is threadsafe) the value inserted by thread B.
Now thread B "thinks" it has successfully inserted its very own value v2, but the map contains v1. This is really a disaster because thread B may call v2.updateSomething() and will "think" that the consumers of the map (e.g. other threads) have access to that object and will see that maybe important update ("like: this visitor IP address is trying to perform a DOS, refuse all the requests from now on"). Instead, the object will be soon garbage collected and lost.
	 * @param concurrentDictionaryOfFile
	 */
	public synchronized void syncFillMap(ConcurrentHashMap<String, Long> concurrentDictionaryOfFile) {
		for (Map.Entry<String, Long> entry : concurrentDictionaryOfFile.entrySet()) {
			//with synch we haven't the race condition. Only one thread at a time can put value in the map
			if(concurrentDictionary.containsKey(entry.getKey())) {
				AtomicInteger oldValue = concurrentDictionary.get(entry.getKey());
				//AtomicInteger delta = new AtomicInteger();
				concurrentDictionary.put(entry.getKey(),new AtomicInteger(oldValue.addAndGet((int)(long)entry.getValue())));
			}else {
				concurrentDictionary.put(entry.getKey(),new AtomicInteger((int)(long)entry.getValue()));				
			}
		}
	}
}