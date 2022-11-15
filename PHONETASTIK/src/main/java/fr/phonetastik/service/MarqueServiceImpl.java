package fr.phonetastik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.phonetastik.dao.MarqueRepository;
import fr.phonetastik.model.Marque;

@Service
public class MarqueServiceImpl implements MarqueService {

	@Autowired
	private MarqueRepository marqueRepository;

	@Transactional
	@Override
	public Marque enregistrer(Marque marque) {
		return marqueRepository.save(marque);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Marque> lister() {
		return (List<Marque>) marqueRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Marque findMarqueById(Long id) {
		return marqueRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Marque> findMarqueByNom(String nom) {
		return marqueRepository.findByNom(nom);
	}
	

	@Transactional(readOnly = true)
	@Override
	public List<Marque> listerAvecModeles(){
		return  marqueRepository.findAllModeles();
	}
}
