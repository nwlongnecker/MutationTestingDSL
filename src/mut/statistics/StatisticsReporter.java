package mut.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

import mut.util.Msg;

/**
 * A class for printing data about a set of statistics objects
 * @author Nathan Longnecker
 */
public class StatisticsReporter {
	
	private final Msg msg;
	private final List<StatisticsCollector> statistics;
	private int total;
	private int totalSurvived;
	private int totalKilled;
	private int totalStillborn;
	
	/**
	 * Constructs a new statistics reporter
	 * @param msg The messager to write output to
	 * @param statistics The list of statistics to report on, the most recent is first in the list
	 */
	public StatisticsReporter(Msg msg, List<StatisticsCollector> statistics) {
		this.msg = msg;
		this.statistics = statistics;
		total = 0;
		totalSurvived = 0;
		totalKilled = 0;
		totalStillborn = 0;
	}

	/**
	 * Reports more detailed information about the statistics of a set of files from all statistics objects
	 * @param files The files to report more detailed information about
	 */
	public void reportAllForFiles(Collection<String> files) {
		for (String filename : files) {
			for (StatisticsCollector sc : statistics) {
				FileStatistics fs = sc.get(filename);
				processFileStatistics(fs);
			}
		}
		report(total, totalSurvived, totalKilled, totalStillborn);
	}

	/**
	 * Reports more detailed information about the statistics fo a set of files from the most recent statistics object
	 * @param files The files to report more detailed information about
	 */
	public void reportLastForFiles(Collection<String> files) {
		for (String filename : files) {
			FileStatistics fs = statistics.get(0).get(filename);
			processFileStatistics(fs);
		}
		report(total, totalSurvived, totalKilled, totalStillborn);
	}

	/**
	 * Reports general statistics from all statistics objects
	 */
	public void reportAll() {
		for (StatisticsCollector sc : statistics) {
			processStatisticsCollector(sc);
		}
		report(total, totalSurvived, totalKilled, totalStillborn);
	}

	/**
	 * Reports general statistics from the most recent statistics object
	 */
	public void reportLast() {
		StatisticsCollector sc = statistics.get(0);
		report(sc.getTotal(), sc.getSurvived(), sc.getKilled(), sc.getStillborn());
	}
	
	/**
	 * Helper function for tallying the total numbers of mutants of each type
	 * @param sc The statistics collector to tally data from
	 */
	private void processStatisticsCollector(StatisticsCollector sc) {
		total += sc.getTotal();
		totalSurvived += sc.getSurvived();
		totalKilled += sc.getKilled();
		totalStillborn += sc.getStillborn();
	}
	
	/**
	 * Helper function for tallying the total numbers of mutants of each type
	 * @param fs The file statistics object to tally data from
	 */
	private void processFileStatistics(FileStatistics fs) {
		total += fs.getTotal();
		totalSurvived += fs.getSurvivedCount();
		totalKilled += fs.getKilledCount();
		totalStillborn += fs.getStillbornCount();
		for (Mutation survivor : fs.getSurvivors()) {
			msg.msgln(fs.getFilename() + " " + survivor.getLine() + ": Survivor when mutating " + survivor.getFrom() + " to " + survivor.getTo());
		}
	}

	/**
	 * Helper function for reporting general data about a mutator run
	 * @param total The total number of mutants being reported on
	 * @param survived The number of mutants that survived
	 * @param killed The number of mutants that were killed
	 * @param stillborn The number of mutants that were stillborn
	 */
	private void report(int total, int survived, int killed, int stillborn) {
		if (total == 0) {
			msg.msgln("No tests were run!");
			return;
		}
		double percentSurvived = round(survived * 100.0 / total, 2);
		double percentKilled = round(killed * 100.0 / total, 2);
		double percentStillborn = round(stillborn * 100.0 / total, 2);
		msg.msgln("Total survived: " + survived + " / " + total + " = " + percentSurvived + "%");
		msg.msgln("Total killed: " + killed + " / " + total + " = " + percentKilled + "%");
		msg.msgln("Total stillborn: " + stillborn + " / " + total + " = " + percentStillborn + "%");
		msg.msgln("Mutation score: " + round((killed * 100.0 / (total - stillborn)), 2) + "%");
	}
	
	/**
	 * Rounds a number to the specified number of places
	 * @param value The number to round
	 * @param places The number of places to round to
	 * @return The rounded number
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
 
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	/**
	 * Reports detailed statistics about all mutants that survived across all statistics objects
	 */
	public void reportAllSurvived() {
		for (StatisticsCollector sc : statistics) {
			for (FileStatistics fs : sc.getFileStatistics()) {
				total += fs.getTotal();
				totalSurvived += fs.getSurvivedCount();
				for (Mutation survivor : fs.getSurvivors()) {
					msg.msgln(fs.getFilename() + " " + survivor.getLine() + ": Survivor when mutating " + survivor.getFrom() + " to " + survivor.getTo());
				}
			}
		}
		double percentSurvived = round(totalSurvived * 100.0 / total, 2);
		msg.msgln("Total survived: " + totalSurvived + " / " + total + " = " + percentSurvived + "%");
	}

	/**
	 * Reports detailed statistics about all mutants that survived in the most recent statistics object
	 */
	public void reportLastSurvived() {
		for (FileStatistics fs : statistics.get(0).getFileStatistics()) {
			total += fs.getTotal();
			totalSurvived += fs.getSurvivedCount();
			for (Mutation survivor : fs.getSurvivors()) {
				msg.msgln(fs.getFilename() + " " + survivor.getLine() + ": Survivor when mutating " + survivor.getFrom() + " to " + survivor.getTo());
			}
		}
		double percentSurvived = round(totalSurvived * 100.0 / total, 2);
		msg.msgln("Total survived: " + totalSurvived + " / " + total + " = " + percentSurvived + "%");
	}

	/**
	 * Reports detailed statistics about all mutants that were stillborn across all statistics objects
	 */
	public void reportAllStillborn() {
		for (StatisticsCollector sc : statistics) {
			for (FileStatistics fs : sc.getFileStatistics()) {
				total += fs.getTotal();
				totalStillborn += fs.getStillbornCount();
				for (Mutation stillborn : fs.getStillborn()) {
					msg.msgln(fs.getFilename() + " " + stillborn.getLine() + ": Stillborn when mutating " + stillborn.getFrom() + " to " + stillborn.getTo());
				}
			}
		}
		double percentStillborn = round(totalStillborn * 100.0 / total, 2);
		msg.msgln("Total stillborn: " + totalStillborn + " / " + total + " = " + percentStillborn + "%");
	}
	
	/**
	 * Reports detailed statistics about all mutants that were stillborn in the most recent statistics object
	 */
	public void reportLastStillborn() {
		for (FileStatistics fs : statistics.get(0).getFileStatistics()) {
			total += fs.getTotal();
			totalStillborn += fs.getStillbornCount();
			for (Mutation stillborn : fs.getStillborn()) {
				msg.msgln(fs.getFilename() + " " + stillborn.getLine() + ": Stillborn when mutating " + stillborn.getFrom() + " to " + stillborn.getTo());
			}
		}
		double percentStillborn = round(totalStillborn * 100.0 / total, 2);
		msg.msgln("Total stillborn: " + totalStillborn + " / " + total + " = " + percentStillborn + "%");
	}

	/**
	 * Reports detailed statistics about all mutants that were killed across all statistics objects
	 */
	public void reportAllKilled() {
		for (StatisticsCollector sc : statistics) {
			for (FileStatistics fs : sc.getFileStatistics()) {
				total += fs.getTotal();
				totalKilled += fs.getKilledCount();
				for (Mutation killed : fs.getKilled()) {
					msg.msgln(fs.getFilename() + " " + killed.getLine() + ": Mutant killed when mutating " + killed.getFrom() + " to " + killed.getTo());
				}
			}
		}
		double percentKilled = round(totalKilled * 100.0 / total, 2);
		msg.msgln("Total killed: " + totalKilled + " / " + total + " = " + percentKilled + "%");
	}

	/**
	 * Reports detailed statistics about all mutants that were killed in the most recent statistics object
	 */
	public void reportLastKilled() {
		for (FileStatistics fs : statistics.get(0).getFileStatistics()) {
			total += fs.getTotal();
			totalKilled += fs.getKilledCount();
			for (Mutation killed : fs.getKilled()) {
				msg.msgln(fs.getFilename() + " " + killed.getLine() + ": Mutant killed when mutating " + killed.getFrom() + " to " + killed.getTo());
			}
		}
		double percentKilled = round(totalKilled * 100.0 / total, 2);
		msg.msgln("Total killed: " + totalKilled + " / " + total + " = " + percentKilled + "%");
	}
}
