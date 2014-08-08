import java.io.IOException;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.IntStream;

import loggy.LogLine;
import loggy.LogReader;

public class LogExperiment {

	private final LogReader logReader = new LogReader();

	public static void main(String args[]) throws IOException {
		new LogExperiment().run();
	}

	public void run() throws IOException {
		List<LogLine> accessLog = logReader.allLogLines(LogExperiment.class.getResourceAsStream("access_log"));
		System.out.println("# entries: " + accessLog.size());
		oldSkoolPrintCountOfSuccesses(accessLog);
		newSkoolPrintCountOfSuccesses(accessLog);

		printNumberOfBytesDelivered(accessLog);
		printAverageNumberOfBytesDelivered(accessLog);

		System.out.println("Average size: " + statOnSize(accessLog, IntStream::average));
		System.out.println("Total size: " + statOnSize(accessLog, IntStream::sum));
		System.out.println("Min size: " + statOnSize(accessLog, IntStream::min));
		System.out.println("Max size: " + statOnSize(accessLog, IntStream::max));
		System.out.println("Summary: " + statOnSize(accessLog, IntStream::summaryStatistics));
	}

	private <T> T statOnSize(List<LogLine> accessLog, Function<IntStream, T> aggregate) {
		IntStream intStream = accessLog.stream()
									   .mapToInt(LogLine::getResponseSize);
		return aggregate.apply(intStream);
	}

	private void printAverageNumberOfBytesDelivered(List<LogLine> accessLog) {
		OptionalDouble size = accessLog.stream()
							.mapToInt(LogLine::getResponseSize)
							.average();
		System.out.println("Average size: " + size);
	}

	private void printNumberOfBytesDelivered(List<LogLine> accessLog) {
		int size = accessLog.stream()
				.mapToInt(LogLine::getResponseSize)
				.sum();
		System.out.println("Total size: " + size);

		int accumulator = 0;
		for (LogLine logLine : accessLog)
			accumulator += logLine.getResponseSize();
		System.out.println("Old skool: " + accumulator);
	}

	private void oldSkoolPrintCountOfSuccesses(List<LogLine> accessLog) {
		int count = 0;
		for (LogLine logLine : accessLog) {
			if (logLine.getResponseCode() == 200)
				count++;
		}
		System.out.println("# Successful (code 200): " + count);
	}

	private void newSkoolPrintCountOfSuccesses(List<LogLine> accessLog) {
		long count = accessLog.stream()
							  .filter(l -> l.getResponseCode() == 200)
							  .count();
		System.out.println("# Successful (code 200): " + count);
	}

}
