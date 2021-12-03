package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.UserDao;
import com.revature.models.Role;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	@Mock
	private UserDao ud;
	
	@InjectMocks
	private AuthService as;
	
	@Test
    void testCheckPermission() {
        Role[] roles = {Role.MANAGER};
        Boolean expected = as.checkPermission("1:BASIC", roles);
        assertEquals(expected, false);
    }

    @Test
    void testLogin() {
        String actual = as.login("username", "password");
        assertEquals(as.login("username", "password"), actual);
    }
}