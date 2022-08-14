package bank.managment.backend.services;

import java.util.List;

import bank.managment.backend.dto.LinkDto;

public interface IMenuService {
	List<LinkDto> getMenu();
}
