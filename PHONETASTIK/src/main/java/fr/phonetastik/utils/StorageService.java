package fr.phonetastik.utils;

import org.springframework.web.multipart.MultipartFile;

import fr.phonetastik.model.Marque;

public interface StorageService {
	

	void init();
	public void deleteAll();
	void store(MultipartFile file);

}
