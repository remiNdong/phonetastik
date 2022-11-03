package fr.phonetastik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.phonetastik.dao.NomdefichierimageRepository;
import fr.phonetastik.model.Nomdefichierimage;


@Service
public class NomdefichierimageServiceImpl implements NomdefichierimageService {
	
	
	@Autowired
	private NomdefichierimageRepository nomDeFichierImageRepository;

	
	
	@Transactional
	@Override
	public  Nomdefichierimage enregistrer(Nomdefichierimage nomdefichierimage) {
		return nomDeFichierImageRepository.save(nomdefichierimage);
	}
	
	@Override
	public Nomdefichierimage findNomDeFichierImageById(String id){
		return nomDeFichierImageRepository.findById(id).orElse(null);
	}

}
