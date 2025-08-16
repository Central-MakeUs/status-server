package com.statoverflow.status.global.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.read.CyclicBufferAppender;
import ch.qos.logback.classic.PatternLayout;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogViewService {

	private static final String APPENDER_NAME = "INMEMORY";
	private static final String PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %logger{36} [%thread] - %msg%n%ex{full}";

	public List<String> tail(int lines) {
		LoggerContext ctx = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger root = ctx.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

		@SuppressWarnings("unchecked")
		CyclicBufferAppender<ILoggingEvent> buf =
			(CyclicBufferAppender<ILoggingEvent>) root.getAppender(APPENDER_NAME);

		List<String> out = new ArrayList<>();
		if (buf == null) return out;

		int length = buf.getLength();
		int from = Math.max(0, length - lines);

		// 포맷터 준비
		PatternLayout layout = new PatternLayout();
		layout.setContext(ctx);
		layout.setPattern(PATTERN);
		layout.start();

		for (int i = from; i < length; i++) {
			ILoggingEvent event = buf.get(i);
			out.add(layout.doLayout(event));
		}
		return out;
	}
}