package models.service;

import java.util.List;

import models.entity.Prueba;

public interface IPruebaService {
	
	public List<Prueba> findAll();
	
	public void save(Prueba prueba);
	
	public Prueba findOne(Long id);
	
	public void delete(Long id);	
	

}
