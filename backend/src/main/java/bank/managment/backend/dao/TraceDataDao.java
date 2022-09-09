package bank.managment.backend.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.managment.backend.entities.TraceData;

@Repository
public interface TraceDataDao extends CrudRepository<TraceData, Long> {
	List<TraceData> findByDateLessThan(Date date);
	void deleteByDateLessThan(Date date);
}
