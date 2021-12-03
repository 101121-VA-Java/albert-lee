package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.ReceiptDao;
import com.revature.models.Reimbursement;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceTest {
	@Mock
	private ReceiptDao rd;
	
	@InjectMocks
	private ReceiptService rs;

    @Test
    void testGetById() {
        Reimbursement r = new Reimbursement();
        Mockito.lenient().when(rd.getById(1)).thenReturn(r.getImage());
		InputStream actual = rs.getById(1);
		assertEquals(actual, r.getImage());
    }

}