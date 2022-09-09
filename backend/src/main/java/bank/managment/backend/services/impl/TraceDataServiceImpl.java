package bank.managment.backend.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import bank.managment.backend.dao.TraceDataDao;
import bank.managment.backend.entities.TraceData;
import bank.managment.backend.services.ITraceDataService;

@Service
public class TraceDataServiceImpl implements ITraceDataService {
	@Autowired
	private TraceDataDao traceDataDao;
	@Override
	public TraceData save(TraceData traceData) {
		return traceDataDao.save(traceData);
	}

	@Override
	public List<TraceData> findAll() {
		return Streamable.of(traceDataDao.findAll()).toList();
	}
	
	@Override
	public List<TraceData> findByDateLessThan(Date date) {
		return traceDataDao.findByDateLessThan(date);
	}
	
	@Override
	public void deleteByDateLessThan(Date date) {
		traceDataDao.deleteByDateLessThan(date);
	}
}
