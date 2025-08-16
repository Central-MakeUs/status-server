package com.statoverflow.status.global.log;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class LogController {

	private final LogViewService logViewService;

	public LogController(LogViewService logViewService) {
		this.logViewService = logViewService;
	}

	// 예: GET /admin/logs?lines=200
	@GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> tail(@RequestParam(defaultValue = "200") int lines) {
		int safe = Math.max(1, Math.min(lines, 5000)); // 과도한 요청 방어
		return logViewService.tail(safe);
	}
}