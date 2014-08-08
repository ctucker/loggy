package loggy;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLineParser {

	Pattern regex = Pattern.compile("^([\\S+.]+) \\S \\S \\[(.*?)\\] \"(.*?)\" (\\d+) (\\d+|-)$");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

	public Optional<LogLine> parse(String line) {
		Matcher matcher = regex.matcher(line);
		if (matcher.matches()) {
			String ipAddress = matcher.group(1);
			String date = matcher.group(2);
			String request = matcher.group(3);
			String responseCode = matcher.group(4);
			String responseSize = matcher.group(5);
			if (responseSize.equals("-"))
				responseSize = "0"; // Hack to deal with empty responses on redirects
			return Optional.of(new LogLine(ipAddress, dateFormat.parse(date, ZonedDateTime::from), request,
										   Integer.parseInt(responseCode), Integer.parseInt(responseSize)));
		}
		return Optional.empty();
	}
}
