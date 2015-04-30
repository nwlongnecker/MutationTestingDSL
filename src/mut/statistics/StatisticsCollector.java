package mut.statistics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StatisticsCollector {

	private int totalSurvivedCount;
	private int totalStillbornCount;
	private int totalKilledCount;
	private Map<String, FileStatistics> fileStatistics;
	
	public StatisticsCollector() {
		totalSurvivedCount = 0;
		totalStillbornCount = 0;
		totalKilledCount = 0;
		fileStatistics = new HashMap<String, FileStatistics>();
	}
	
	public void logKilled(String filename, Mutation killed) {
		totalKilledCount++;
		get(filename).incrementKilled(killed);
	}
	
	public void logStillborn(String filename, Mutation stillborn) {
		totalStillbornCount++;
		get(filename).incrementStillborn(stillborn);
	}
	
	public void logSurvivor(String filename, Mutation survivor) {
		totalSurvivedCount++;
		get(filename).logSurvivor(survivor);
	}
	
	public FileStatistics get(String filename) {
		if (!fileStatistics.containsKey(filename)) {
			fileStatistics.put(filename, new FileStatistics(filename));
		}
		return fileStatistics.get(filename);
	}
	
	public int getTotal() {
		return totalSurvivedCount + totalStillbornCount + totalKilledCount;
	}
	
	public int getSurvived() {
		return totalSurvivedCount;
	}
	
	public int getKilled() {
		return totalKilledCount;
	}
	
	public int getStillborn() {
		return totalStillbornCount;
	}
	
	public Collection<FileStatistics> getFileStatistics() {
		return fileStatistics.values();
	}
}
