package com.rivero.springboot.backend.apirest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.rivero.springboot.backend.apirest.SpringBootBackendApirestApplication;
import com.rivero.springboot.backend.apirest.models.entity.Cliente;
import com.rivero.springboot.backend.apirest.models.service.IClienteService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootBackendApirestApplication.class)
@Transactional
public class ClienteServiceTest {
	
	@Autowired
	private IClienteService service;
	
	@BeforeAll
	private static void setup() {
		
	}
	
	@Test
	public void testFindAllSuccess() {
		List<Cliente> result = service.findAll();
		assertThat(result).isNotEmpty();
		assertThat(result.size()).isEqualTo(50);
	}
	
	@Test
	public void testFindById() {
		Cliente result = service.findById(1L);
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1L);
	}
	
	@Test
	public void testCreateCliente() {
		Cliente cliente = Cliente.builder().apellido("test").nombre("test").email("test@test.test").build();
		Cliente result = service.save(cliente);
		assertThat(result).isNotNull();
		assertThat(result.getApellido()).isEqualTo(cliente.getApellido());
	}
	
	@Test
	public void testUpdateCliente() {
		Cliente cliente = Cliente.builder().apellido("test").nombre("test").email("test@test.test").build();
		Cliente resultSave = service.save(cliente);
		resultSave.setApellido("update");
		Cliente resultUpdate = service.update(resultSave, resultSave.getId());
		assertThat(resultUpdate).isNotNull();
		assertThat(resultUpdate.getApellido()).isEqualTo(resultSave.getApellido());
	}
	
	@Test
	public void testDelete() {
		service.delete(1L);
		Cliente result = service.findById(1L);
		assertThat(result).isNull();
	}

}
