package loggy;

import java.time.ZonedDateTime;

public class LogLine {

	private final String fIpAddress;
	private final ZonedDateTime fDate;
	private final String fRequest;
	private final int fResponseCode;
	private final int fResponseSize;

	public LogLine(String ipAddress, ZonedDateTime date, String request, int responseCode, int responseSize) {
		fIpAddress = ipAddress;
		fDate = date;
		fRequest = request;
		fResponseCode = responseCode;
		fResponseSize = responseSize;
	}

	public String getIpAddress() {
		return fIpAddress;
	}
	public ZonedDateTime getDate() {
		return fDate;
	}

	public String getRequest() {
		return fRequest;
	}

	public int getResponseCode() {
		return fResponseCode;
	}

	public int getResponseSize() {
		return fResponseSize;
	}
}
