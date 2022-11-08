package fr.phonetastik.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

	void init() throws IOException;

	public void deleteAll();

	void store(MultipartFile file, String nomFichier);

	Map<String, File> getMap();

}
