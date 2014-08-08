package loggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LogReader {

	private final LogLineParser fLogLineParser = new LogLineParser();

	public List<LogLine> allLogLines(InputStream logFile) throws IOException {
		List<LogLine> logLines = new LinkedList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(logFile))) {

			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				Optional<LogLine> logLine = fLogLineParser.parse(line);
				if (logLine.isPresent())
					logLines.add(logLine.get());
				else
					System.err.println("Failed to read line: " + line);
			}
		}

		return logLines;
	}

}
