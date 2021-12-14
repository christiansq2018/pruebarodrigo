package models.dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import models.entity.Prueba;

import java.util.List;


public interface IPruebaDao extends CrudRepository<Prueba, Long>{
	

	@Query("select p from Prueba p where p.nombre like %?1%")
	public List<Prueba> findByNombre(String term);
	
	public List<Prueba> findByNombreLikeIgnoreCase(String term);

	
}
