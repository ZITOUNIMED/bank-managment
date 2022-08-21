package bank.managment.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.managment.backend.entities.TraceData;

@Repository
public interface TraceDataDao extends CrudRepository<TraceData, Long> {
}
