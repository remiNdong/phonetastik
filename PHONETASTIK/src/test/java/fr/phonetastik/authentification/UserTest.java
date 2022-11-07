package fr.phonetastik.authentification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {
	
	
	@Autowired
	private UserService userService;
	
	/*
	@Test
	 public void testVisible() {
		User userUnique=userService.findUserById("phonetastik@yahoo.com");
		assertEquals("Rachid",userUnique.getPseudo());
		assertFalse(userUnique.isAdmin());
	}
	*/

}
