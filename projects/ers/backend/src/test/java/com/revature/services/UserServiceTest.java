package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.UserDao;
import com.revature.models.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserDao ud;
	
	@InjectMocks
	private UserService us;
	
	@Test
	void testAddUser() {
		Mockito.lenient().when(ud.add(new User())).thenReturn(2);
		int actual = us.addUser(new User());
		assertNotEquals(actual, 2);
	}

	@Test
	void testGetByUsername() {
		Mockito.lenient().when(ud.getByUsername("test")).thenReturn(new User());
		User actual = us.getByUsername("test");
		assertEquals(actual.getUsername(), new User().getUsername());
	}

	@Test
	void testGetUserById() {
		User actual = new User();
		Mockito.lenient().when(ud.getUserById(actual.getId())).thenReturn(actual);
		assertEquals(us.getUserById(actual.getId()), actual);
	}

	@Test
	void testGetUsers() {
		List<User> actual = new ArrayList<>();
		Mockito.lenient().when(ud.getAll()).thenReturn(actual);
		List<User> expected = us.getUsers();
		assertEquals(expected, actual);
	}

	@Test
	void testUpdateUser() {
		User u = new User();
		u.setId(5);
		Mockito.lenient().when(ud.update(u)).thenReturn(5);
		int expected = us.updateUser(u);
		assertEquals(expected, 5);
	}
	
}