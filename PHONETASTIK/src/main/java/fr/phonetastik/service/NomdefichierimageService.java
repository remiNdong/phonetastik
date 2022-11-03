package fr.phonetastik.service;

import fr.phonetastik.model.Nomdefichierimage;

public interface NomdefichierimageService {
	
	Nomdefichierimage enregistrer (Nomdefichierimage nomdefichierimage);
	
	Nomdefichierimage  findNomDeFichierImageById(String id);

}
