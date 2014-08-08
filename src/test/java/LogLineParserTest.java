import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;

import loggy.LogLine;
import loggy.LogLineParser;

import static org.assertj.core.api.Assertions.assertThat;

public class LogLineParserTest {

	private final String sampleLine = "64.242.88.10 - - [07/Mar/2004:16:05:49 -0800] \"GET /twiki/bin/edit/Main/Double_bounce_sender?topicparent=Main.ConfigurationVariables HTTP/1.1\" 401 12846";
	private final ZonedDateTime expectedDate = ZonedDateTime.of(2004, 3, 7, 16, 5, 49, 0, ZoneId.of("-0800"));

	private LogLine fLogLine;

	@Before
	public void setup() {
		fLogLine = new LogLineParser().parse(sampleLine).get();
	}

	@Test
	public void extractsIpAddress() {
		assertThat(fLogLine.getIpAddress()).isEqualTo("64.242.88.10");
	}

	@Test
	public void extractsDate() {
		assertThat(fLogLine.getDate()).isEqualTo(expectedDate);
	}

	@Test
	public void extractsRequest() {
		assertThat(fLogLine.getRequest()).isEqualTo("GET /twiki/bin/edit/Main/Double_bounce_sender?topicparent=Main.ConfigurationVariables HTTP/1.1");
	}

	@Test
	public void extractsResponseCode() {
		assertThat(fLogLine.getResponseCode()).isEqualTo(401);
	}

	@Test
	public void extractsResponseSize() {
		assertThat(fLogLine.getResponseSize()).isEqualTo(12846);
	}

}
