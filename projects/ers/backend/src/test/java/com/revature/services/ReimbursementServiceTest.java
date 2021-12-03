package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

@ExtendWith(MockitoExtension.class)
public class ReimbursementServiceTest {
	@Mock
	private ReimbursementDao rd;
	
	@InjectMocks
	private ReimbursementService rs;
	
    @Test
    void testAdd() {
        Reimbursement r = new Reimbursement();
        r.setId(2);
        Mockito.lenient().when(rd.add(r)).thenReturn(2);
		int actual = rs.add(r);
		assertEquals(actual, 2);
    }

    @Test
    void testGetById() {
        Reimbursement r = new Reimbursement();
        r.setId(2);
        Mockito.lenient().when(rd.getById(2)).thenReturn(r);
		Reimbursement actual = rs.getById(2);
		assertEquals(actual, r);
    }

    @Test
    void testGetReimbursements() {
        List<Reimbursement> rmbs = new ArrayList<>();
        Reimbursement r = new Reimbursement();
        r.setId(2);
        rmbs.add(r);
        Mockito.lenient().when(rd.getAll()).thenReturn(rmbs);
		List<Reimbursement> actual = rs.getReimbursements();
		assertEquals(actual, rmbs);
    }

    @Test
    void testGetReimbursementsByEmployeeId() {
        List<Reimbursement> rmbs = new ArrayList<>();
        Reimbursement r = new Reimbursement();
        r.setId(2);
        r.setAuthorId(1);
        rmbs.add(r);
        Mockito.lenient().when(rd.getByEmployeeId(1)).thenReturn(rmbs);
		List<Reimbursement> actual = rs.getReimbursementsByEmployeeId(1);
		assertEquals(actual, rmbs);
    }

    @Test
    void testUpdate() {
        Reimbursement r = new Reimbursement();
		r.setId(5);
		Mockito.lenient().when(rd.update(r)).thenReturn(5);
		int expected = rs.update(r);
		assertEquals(expected, 5);
    }
    
}
