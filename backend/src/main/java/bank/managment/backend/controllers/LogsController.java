package bank.managment.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.managment.backend.entities.TraceData;
import bank.managment.backend.services.ITraceDataService;
@RestController
@RequestMapping("/api/logs")
@PreAuthorize("hasAuthority('MANAGE_LOGS')")
public class LogsController {
	@Autowired
	private ITraceDataService traceDataService;
	
	@GetMapping
	public ResponseEntity<List<TraceData>> getTracesData(){
		return ResponseEntity.ok(traceDataService.findAll());
	}
}
