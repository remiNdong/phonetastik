package fr.phonetastik.service;

import java.io.File;
import java.util.Map;

import fr.phonetastik.model.Nomdefichierimage;

public interface NomdefichierimageService {
	
	Nomdefichierimage enregistrer (Nomdefichierimage nomdefichierimage);
	
	Nomdefichierimage  findNomDeFichierImageById(String id);
	
	

}
