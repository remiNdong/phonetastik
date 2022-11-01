package fr.phonetastik.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.phonetastik.model.Marque;


public interface MarqueRepository extends CrudRepository<Marque, Long> {
	
	
	public List<Marque> findByNom(String nom);

}