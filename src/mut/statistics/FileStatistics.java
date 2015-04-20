package mut.statistics;

import java.util.ArrayList;
import java.util.List;

public class FileStatistics {

	private int killedCount;
	private int stillbornCount;
	private int survivedCount;
	private final List<Survivor> survivors;
	private String filename;
	
	public FileStatistics(String filename) {
		this.filename = filename;
		killedCount = 0;
		stillbornCount = 0;
		survivedCount = 0;
		survivors = new ArrayList<Survivor>();
	}
	
	public void incrementKilled() {
		killedCount++;
	}
	
	public void incrementStillborn() {
		stillbornCount++;
	}
	
	public void logSurvivor(Survivor survivor) {
		survivedCount++;
		survivors.add(survivor);
	}
	
	public List<Survivor> getSurvivors() {
		return survivors;
	}
	
	public int getTotal() {
		return survivedCount + stillbornCount + killedCount;
	}
	
	public int getSurvived() {
		return survivedCount;
	}
	
	public int getKilled() {
		return killedCount;
	}
	
	public int getStillborn() {
		return stillbornCount;
	}
}
