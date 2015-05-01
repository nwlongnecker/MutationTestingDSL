package mut.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracks the statistics for a specific file
 * @author Nathan Longnecker
 */
public class FileStatistics {

	private int killedCount;
	private int stillbornCount;
	private int survivedCount;
	private final List<Mutation> survivors;
	private final List<Mutation> killed;
	private final List<Mutation> stillborn;
	private String filename;
	
	/**
	 * Constructor
	 * @param filename The file this statistics object is associated with
	 */
	public FileStatistics(String filename) {
		this.filename = filename;
		killedCount = 0;
		stillbornCount = 0;
		survivedCount = 0;
		survivors = new ArrayList<Mutation>();
		stillborn = new ArrayList<Mutation>();
		killed = new ArrayList<Mutation>();
	}
	
	/**
	 * Log that a mutant was killed
	 * @param killed The mutant that was killed
	 */
	public void incrementKilled(Mutation killed) {
		killedCount++;
		this.killed.add(killed);
	}
	
	/**
	 * Log that a mutant was stillborn
	 * @param stillborn The mutant that was stillborn
	 */
	public void incrementStillborn(Mutation stillborn) {
		stillbornCount++;
		this.stillborn.add(stillborn);
	}
	
	/**
	 * Log that a mutant survived
	 * @param survivor The mutant that survived
	 */
	public void logSurvivor(Mutation survivor) {
		survivedCount++;
		survivors.add(survivor);
	}
	
	/**
	 * @return The survivors
	 */
	public List<Mutation> getSurvivors() {
		return survivors;
	}
	
	/**
	 * @return The stillborn mutants
	 */
	public List<Mutation> getStillborn() {
		return stillborn;
	}
	
	/**
	 * @return The killed mutants
	 */
	public List<Mutation> getKilled() {
		return killed;
	}
	
	/**
	 * @return The total number of mutants tracked by this statistics object
	 */
	public int getTotal() {
		return survivedCount + stillbornCount + killedCount;
	}
	
	/**
	 * @return The number of mutants that survived
	 */
	public int getSurvivedCount() {
		return survivedCount;
	}
	
	/**
	 * @return The number of mutants that were killed
	 */
	public int getKilledCount() {
		return killedCount;
	}
	
	/**
	 * @return The number of mutants that were stillborn
	 */
	public int getStillbornCount() {
		return stillbornCount;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
}
