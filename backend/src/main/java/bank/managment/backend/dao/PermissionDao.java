package bank.managment.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.managment.backend.entities.Permission;

@Repository
public interface PermissionDao extends CrudRepository<Permission, Long>{
}
