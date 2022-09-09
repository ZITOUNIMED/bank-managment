package bank.managment.backend.services;

import java.sql.Date;
import java.util.List;

import bank.managment.backend.entities.TraceData;

public interface ITraceDataService {
	TraceData save(TraceData traceData);
	List<TraceData> findAll();
	List<TraceData> findByDateLessThan(Date date);
	void deleteByDateLessThan(Date date);
}
