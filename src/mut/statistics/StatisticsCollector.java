package mut.statistics;

import java.util.HashMap;
import java.util.Map;

import mut.util.Msg;

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
	
	public void incrementKilled(String filename) {
		totalKilledCount++;
		get(filename).incrementKilled();
	}
	
	public void incrementStillborn(String filename) {
		totalStillbornCount++;
		get(filename).incrementStillborn();
	}
	
	public void logSurvivor(String filename, Survivor survivor) {
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
}
