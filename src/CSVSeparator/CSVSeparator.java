package CSVSeparator;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CSVSeparator {
	public static void main(String[] args) {
		
		if (args.length > 0) {
			
			var pathToCsv = args[0];
			
			try {
				var csvReader = new BufferedReader(new FileReader(pathToCsv));
				var map = new HashMap<String, User>();
				
				String row;
				while ((row = csvReader.readLine()) != null) {
					String[] data = row.split(",");
				
					var user = new User(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
					
					if (map.containsKey(user.userId())) {
						var curUser = map.get(user.userId());
						
						if (curUser.version() < user.version())
							map.put(user.userId(), user);
						
					} else {
						map.put(user.userId(), user);
					}
				}:
				
				csvReader.close();
				
				var comparator = Comparator
									.comparing(User::lastName)
									.thenComparing(User::firstName);
				
				var separatedCompanies = map.values().stream()
						.sorted(comparator)
						.collect(Collectors.groupingBy(User::insuranceCompany))
						;

				separatedCompanies.keySet().stream().forEach(key -> {
					try {
						var csvWriter = new FileWriter(key + ".csv");

						for (var user: separatedCompanies.get(key)) {
							csvWriter.append(user.toCSV());
						}

						csvWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				
			} catch (FileNotFoundException ex) {
				System.out.println(pathToCsv + " does not exist.");
			} catch (IOException ex) {
				System.out.println("A problem has occured while reading " + pathToCsv);
			}
		} else {
			System.out.println("Please send path of file as a command line arguement when running this Java program.");
		}

	}
}
