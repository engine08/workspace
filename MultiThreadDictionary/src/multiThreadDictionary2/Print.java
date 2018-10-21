package multiThreadDictionary2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Print {
	
	public synchronized void printNewMap(ConcurrentHashMap<String, Long> concurrentDictionaryOfFile,String fileName, String path,long start) {
		
		StringBuilder sb = new StringBuilder();
		if(fileName.contains("1")) {
			sb.append("\n _____ _                        _  __  \n|_   _| |                      | |/  | \n  | | | |__  _ __ ___  __ _  __| |`| | \n  | | | '_ \\| '__/ _ \\/ _` |/ _` | | | \n  | | | | | | | |  __/ (_| | (_| |_| |_\n  \\_/ |_| |_|_|  \\___|\\__,_|\\__,_|\\___/\n                                       \n                                       \n");
		}else {
			sb.append("\n _____ _                        _ _____ \n|_   _| |                      | |  _  |\n  | | | |__  _ __ ___  __ _  __| | |/' |\n  | | | '_ \\| '__/ _ \\/ _` |/ _` |  /| |\n  | | | | | | | |  __/ (_| | (_| \\ |_/ /\n  \\_/ |_| |_|_|  \\___|\\__,_|\\__,_|\\___/ \n                                        \n                                        \n");
		}
		sb.append("Il thread ha iniziato "+Long.toString(start))
		.append("INSERIRE ID THREAD ")
		.append("ha come variabile i = ")
		.append(String.valueOf(fileName))
		.append(" E path = ")
		.append(path);
		
		System.out.println(sb.toString());
		System.out.println("La mappa appena generata è la seguente <parola> <occorrenza>");
		
		concurrentDictionaryOfFile.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() /*+ " " +entry.getValue() */+" THREAD "+fileName);
		});
		System.out.println("/****************************************************************************/");
	}
	
public void printNewMap(ConcurrentHashMap<String, AtomicInteger> concurrentDictionaryOfFile) {
		
		System.out.println("Il risulato della concorrenza è");
		System.out.println("Numero parole salvate nel dizionario: "+concurrentDictionaryOfFile.size());
		
		concurrentDictionaryOfFile.entrySet().forEach(entry -> {
			System.out.println(entry.getKey()+ " " +entry.getValue().get());
		});
		System.out.println("/****************************************************************************/");
	}

}
