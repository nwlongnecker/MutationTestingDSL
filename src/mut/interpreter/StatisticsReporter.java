package mut.interpreter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

import mut.statistics.FileStatistics;
import mut.statistics.StatisticsCollector;
import mut.statistics.Mutation;
import mut.util.Msg;

public class StatisticsReporter {
	
	private final Msg msg;
	private final List<StatisticsCollector> statistics;
	private int total;
	private int totalSurvived;
	private int totalKilled;
	private int totalStillborn;
	
	public StatisticsReporter(Msg msg, List<StatisticsCollector> statistics) {
		this.msg = msg;
		this.statistics = statistics;
		total = 0;
		totalSurvived = 0;
		totalKilled = 0;
		totalStillborn = 0;
	}

	public void reportAllForFiles(Collection<String> files) {
		for (String filename : files) {
			for (StatisticsCollector sc : statistics) {
				FileStatistics fs = sc.get(filename);
				processFileStatistics(fs);
			}
		}
		report(total, totalSurvived, totalKilled, totalStillborn);
	}

	public void reportLastForFiles(Collection<String> files) {
		for (String filename : files) {
			FileStatistics fs = statistics.get(0).get(filename);
			processFileStatistics(fs);
		}
		report(total, totalSurvived, totalKilled, totalStillborn);
	}

	public void reportAll() {
		for (StatisticsCollector sc : statistics) {
			processStatisticsCollector(sc);
		}
		report(total, totalSurvived, totalKilled, totalStillborn);
	}

	public void reportLast() {
		StatisticsCollector sc = statistics.get(0);
		report(sc.getTotal(), sc.getSurvived(), sc.getKilled(), sc.getStillborn());
	}
	
	private void processStatisticsCollector(StatisticsCollector sc) {
		total += sc.getTotal();
		totalSurvived += sc.getSurvived();
		totalKilled += sc.getKilled();
		totalStillborn += sc.getStillborn();
	}
	
	private void processFileStatistics(FileStatistics fs) {
		total += fs.getTotal();
		totalSurvived += fs.getSurvivedCount();
		totalKilled += fs.getKilledCount();
		totalStillborn += fs.getStillbornCount();
		for (Mutation survivor : fs.getSurvivors()) {
			msg.msgln(fs.getFilename() + " " + survivor.getLine() + ": Survivor when mutating " + survivor.getFrom() + " to " + survivor.getTo());
		}
	}

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
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
 
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

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
