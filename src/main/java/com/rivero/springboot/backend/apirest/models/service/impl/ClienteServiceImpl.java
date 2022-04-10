package com.rivero.springboot.backend.apirest.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivero.springboot.backend.apirest.models.dao.IClienteDao;
import com.rivero.springboot.backend.apirest.models.entity.Cliente;
import com.rivero.springboot.backend.apirest.models.service.IClienteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
		
	}

	@Override
	@Transactional()
	public Cliente save(Cliente cliente) {
		
		return clienteDao.save(cliente);
		
	}
	

	@Override
	@Transactional()
	public void delete(Long id) {

		clienteDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		
		return clienteDao.findById(id).orElse(null);
		
	}

	@Override
	public Cliente update(Cliente clienteAPI, Long id) {
		
		Cliente clienteBBDD = clienteDao.findById(id).orElse(null);
		
		if(clienteBBDD!=null) {
			writeAttributesForUpdate(clienteAPI, clienteBBDD);
			return clienteDao.save(clienteBBDD);
		}
		
		return null;
	}
	
	private void writeAttributesForUpdate(Cliente clienteAPI, Cliente clienteBBDD) {

		clienteBBDD.setApellido(clienteAPI.getApellido());
		clienteBBDD.setEmail(clienteAPI.getEmail());
		clienteBBDD.setNombre(clienteAPI.getNombre());

	}

}
