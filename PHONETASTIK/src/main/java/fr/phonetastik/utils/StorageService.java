package fr.phonetastik.utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import fr.phonetastik.model.Marque;

public interface StorageService {
	

	void init() throws IOException;
	public void deleteAll();
	void store(MultipartFile file, String nomFichier);

}
