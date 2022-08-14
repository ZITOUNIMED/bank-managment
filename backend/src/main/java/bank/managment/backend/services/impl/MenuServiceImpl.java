package bank.managment.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import bank.managment.backend.constants.FonctionalitiesTypes;
import bank.managment.backend.dto.LinkDto;
import bank.managment.backend.services.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Override
	public List<LinkDto> getMenu() {
		LinkDto l1 = new LinkDto();
		l1.setPath(FonctionalitiesTypes.MANAGE_USERS.getPath());
		l1.setCode(FonctionalitiesTypes.MANAGE_USERS.name());
		l1.setLabel(FonctionalitiesTypes.MANAGE_USERS.getLabel());
		
		LinkDto l2 = new LinkDto();
		l2.setPath(FonctionalitiesTypes.MANAGE_LOGS.getPath());
		l2.setCode(FonctionalitiesTypes.MANAGE_LOGS.name());
		l2.setLabel(FonctionalitiesTypes.MANAGE_LOGS.getLabel());
		
		List<LinkDto> links = new ArrayList<>();
		links.add(l1);
		links.add(l2);
		return links;
	}

}
