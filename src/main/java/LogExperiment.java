import java.io.IOException;
import java.util.List;

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
	}

}
