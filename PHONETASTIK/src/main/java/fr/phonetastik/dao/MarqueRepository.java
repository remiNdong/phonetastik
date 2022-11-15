package fr.phonetastik.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.phonetastik.model.Marque;


public interface MarqueRepository extends CrudRepository<Marque, Long> {
	
	
	public List<Marque> findByNom(String nom);
	
	
	@Query("select m from Marque m , Modele mo where m=mo.marque and mo.visible='TRUE'")
	public List<Marque> findAllModeles();

}