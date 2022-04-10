package com.rivero.springboot.backend.apirest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rivero.springboot.backend.apirest.models.dao.IClienteDao;
import com.rivero.springboot.backend.apirest.models.entity.Cliente;
import com.rivero.springboot.backend.apirest.models.service.impl.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
	
	@Mock
	private IClienteDao dao;
	
	@InjectMocks
	private ClienteServiceImpl clienteService;
	
	@BeforeEach
	private void setup() {
		clienteService = new ClienteServiceImpl(dao);
	}
	
	@Test
	public void testFindAllSuccess() {
		Cliente cliente = Cliente.builder().id(1L).nombre("test").apellido("test").email("test").createdAt(new Date()).build();
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		Mockito.when(dao.findAll()).thenReturn(clientes);
		List<Cliente> result = clienteService.findAll();
		assertThat(result).isNotEmpty();
		assertThat(result.get(0).getId()).isEqualTo(cliente.getId());
	}

}
