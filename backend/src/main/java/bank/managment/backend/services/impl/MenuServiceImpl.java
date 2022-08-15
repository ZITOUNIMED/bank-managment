package bank.managment.backend.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bank.managment.backend.constants.FonctionalitiesTypes;
import bank.managment.backend.dto.LinkDto;
import bank.managment.backend.services.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Override
	public List<LinkDto> getMenu() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<LinkDto> links = new ArrayList<>();
		if(findPermission("ADMIN", authentication.getAuthorities())) {
			links.add(convertFonctionaltyToLinkDto(FonctionalitiesTypes.MANAGE_USERS));
			links.add(convertFonctionaltyToLinkDto(FonctionalitiesTypes.MANAGE_LOGS));
			links.add(convertFonctionaltyToLinkDto(FonctionalitiesTypes.MANAGE_PERMISSIONS));
		}
		
		return links;
	}
	
	private LinkDto convertFonctionaltyToLinkDto(FonctionalitiesTypes fonctionality) {
		LinkDto link = new LinkDto();
		link.setPath(fonctionality.getPath());
		link.setCode(fonctionality.name());
		link.setLabel(fonctionality.getLabel());
		return link;
	}
	
	private boolean findPermission(String permission, Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream().filter(authority -> permission.equals(authority.getAuthority())).count()>0;
	}

}
