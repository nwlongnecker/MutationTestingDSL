package mut.statistics;

import java.util.ArrayList;
import java.util.List;

public class FileStatistics {

	private int killedCount;
	private int stillbornCount;
	private int survivedCount;
	private final List<Mutation> survivors;
	private final List<Mutation> killed;
	private final List<Mutation> stillborn;
	private String filename;
	
	public FileStatistics(String filename) {
		this.filename = filename;
		killedCount = 0;
		stillbornCount = 0;
		survivedCount = 0;
		survivors = new ArrayList<Mutation>();
		stillborn = new ArrayList<Mutation>();
		killed = new ArrayList<Mutation>();
	}
	
	public void incrementKilled(Mutation killed) {
		killedCount++;
		this.killed.add(killed);
	}
	
	public void incrementStillborn(Mutation stillborn) {
		stillbornCount++;
		this.stillborn.add(stillborn);
	}
	
	public void logSurvivor(Mutation survivor) {
		survivedCount++;
		survivors.add(survivor);
	}
	
	public List<Mutation> getSurvivors() {
		return survivors;
	}
	
	public List<Mutation> getStillborn() {
		return stillborn;
	}
	
	public List<Mutation> getKilled() {
		return killed;
	}
	
	public int getTotal() {
		return survivedCount + stillbornCount + killedCount;
	}
	
	public int getSurvivedCount() {
		return survivedCount;
	}
	
	public int getKilledCount() {
		return killedCount;
	}
	
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
