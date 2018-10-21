package multiThreadDictionary2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CalculateOccurencies implements Callable {
	ConcurrentHashMap<String, Integer> concurrentDictionary = new ConcurrentHashMap<>();
	private String fileName=null;
	private String path = "C:\\workspace\\MultiThreadDictionary\\inputFile\\";
	private Print printer;
	private Writer writer;

	
	/*
	 * verificare che ogni thread abbia il proprio nome del file
	 * verificare se la mappa è realmente condivisa
	 */

	public CalculateOccurencies(int i, Print printer, Writer writer) {
		this.fileName = String.valueOf(i)+".txt";
		this.path = this.path+this.fileName;
		this.printer = printer;
		this.writer = writer;
	}


	@Override
	public synchronized Integer call() {
		/*
		 * stampare nome thread, nome file , path file per vedere se sta leggendo correttamente
		 */
		//lancia la lettura del file
		try {
			long start = System.currentTimeMillis();
			ConcurrentHashMap<String, Long> concurrentDictionaryOfFile = Files.lines(Paths.get(this.path))
			.flatMap(line -> Arrays.stream(line.split(" ")))
			.collect(Collectors.groupingBy(word->(String) word, ConcurrentHashMap::new,  Collectors.counting() ));
			//System.out.println("sono il thread "+this.path);

			
			//printer.printNewMap(concurrentDictionaryOfFile,this.fileName,this.path,start);
			//for(int i =0; true ; i++) ;
			writer.syncFillMap(concurrentDictionaryOfFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}


//	private  void printNewMap(ConcurrentHashMap<String, Long> concurrentDictionaryOfFile) {
//		
//		StringBuilder sb = new StringBuilder();
//		if(this.fileName.contains("1")) {
//			sb.append("\n _____ _                        _  __  \n|_   _| |                      | |/  | \n  | | | |__  _ __ ___  __ _  __| |`| | \n  | | | '_ \\| '__/ _ \\/ _` |/ _` | | | \n  | | | | | | | |  __/ (_| | (_| |_| |_\n  \\_/ |_| |_|_|  \\___|\\__,_|\\__,_|\\___/\n                                       \n                                       \n");
//		}else {
//			sb.append("\n _____ _                        _ _____ \n|_   _| |                      | |  _  |\n  | | | |__  _ __ ___  __ _  __| | |/' |\n  | | | '_ \\| '__/ _ \\/ _` |/ _` |  /| |\n  | | | | | | | |  __/ (_| | (_| \\ |_/ /\n  \\_/ |_| |_|_|  \\___|\\__,_|\\__,_|\\___/ \n                                        \n                                        \n");
//		}
//		sb.append("Il thread ")
//		.append("INSERIRE ID THREAD ")
//		.append("ha come variabile i = ")
//		.append(String.valueOf(this.fileName))
//		.append(" E path = ")
//		.append(this.path);
//		
//		System.out.println(sb.toString());
//		System.out.println("La mappa appena generata è la seguente <parola> <occorrenza>");
//		
//		concurrentDictionaryOfFile.entrySet().forEach(entry -> {
//			System.out.println(entry.getKey() /*+ " " +entry.getValue() */+" THREAD "+this.fileName);
//		});
//		System.out.println("/****************************************************************************/");
//	}




}
