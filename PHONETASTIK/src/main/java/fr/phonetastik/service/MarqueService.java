package fr.phonetastik.service;

import java.util.List;
import java.util.Optional;

import fr.phonetastik.model.Marque;


public interface MarqueService {

	Marque enregistrer(Marque marque);
	
	
	Marque  findMarqueById(Long id);
	
	List<Marque>  findMarqueByNom(String nom);
	
	List<Marque> lister();
	
}
