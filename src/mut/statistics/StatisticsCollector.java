package mut.statistics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * An object for keeping track of the statistics 
 * @author Nathan Longnecker
 */
public class StatisticsCollector {

	private int totalSurvivedCount;
	private int totalStillbornCount;
	private int totalKilledCount;
	private Map<String, FileStatistics> fileStatistics;
	
	/**
	 * Constructor
	 */
	public StatisticsCollector() {
		totalSurvivedCount = 0;
		totalStillbornCount = 0;
		totalKilledCount = 0;
		fileStatistics = new HashMap<String, FileStatistics>();
	}
	
	/**
	 * Records that a mutant was killed
	 * @param filename The name of the file mutated
	 * @param killed The mutant that was killed
	 */
	public void logKilled(String filename, Mutation killed) {
		totalKilledCount++;
		get(filename).incrementKilled(killed);
	}
	
	/**
	 * Records that a mutant was stillborn
	 * @param filename The name of the file mutated
	 * @param stillborn The mutant that was stillborn
	 */
	public void logStillborn(String filename, Mutation stillborn) {
		totalStillbornCount++;
		get(filename).incrementStillborn(stillborn);
	}
	
	/**
	 * Records that a mutant survived
	 * @param filename The name of the file mutated
	 * @param survivor The mutant that survived
	 */
	public void logSurvivor(String filename, Mutation survivor) {
		totalSurvivedCount++;
		get(filename).logSurvivor(survivor);
	}
	
	/**
	 * Gets the statistics for a particular file
	 * @param filename The name of the file
	 * @return The file statistics for that file
	 */
	public FileStatistics get(String filename) {
		if (!fileStatistics.containsKey(filename)) {
			fileStatistics.put(filename, new FileStatistics(filename));
		}
		return fileStatistics.get(filename);
	}
	
	/**
	 * @return The total number of mutants recorded by this collector
	 */
	public int getTotal() {
		return totalSurvivedCount + totalStillbornCount + totalKilledCount;
	}
	
	/**
	 * @return The number of mutants that survived
	 */
	public int getSurvived() {
		return totalSurvivedCount;
	}
	
	/**
	 * @return The number of mutants that were killed
	 */
	public int getKilled() {
		return totalKilledCount;
	}
	
	/**
	 * @return The number of mutants that were stillborn
	 */
	public int getStillborn() {
		return totalStillbornCount;
	}
	
	/**
	 * @return The list of file statistics
	 */
	public Collection<FileStatistics> getFileStatistics() {
		return fileStatistics.values();
	}
}
