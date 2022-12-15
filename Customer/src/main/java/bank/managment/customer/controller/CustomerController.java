//package bank.managment.customer.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import bank.managment.customer.dto.UserDTO;
//import bank.managment.customer.feign.CentralAdminInterface;
//
//@RestController
//@RequestMapping("/api/customer")
//public class CustomerController {
//	@Autowired
//	private CentralAdminInterface centralAdminInterface;
//	
//	@GetMapping("/users")
//	public List<UserDTO> getUsers(){
//		return centralAdminInterface.getUsers();
//	}
//}
