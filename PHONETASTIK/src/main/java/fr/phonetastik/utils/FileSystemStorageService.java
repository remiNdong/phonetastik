package fr.phonetastik.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileSystemStorageService implements StorageService {
	
	


	private final Path root = Paths.get("src/main/resources/static/images/imagesDomaine");
	
	

	@Override
	public void init() {
		try {
			Path path=Files.createDirectory(root);
			
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}
	
	 @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }


	@Override
	public void store(MultipartFile multipartFile) {
		try {
		
			
			
			if (multipartFile.isEmpty()) {
				throw new StorageException("Le fichier est vide");
			}

			if (!isImage(multipartFile)) {
				throw new StorageException("Le fichier n'est pas une image");
			}

			if (!isAtSize(multipartFile)) {
				throw new StorageException("Le fichier ne doit pas depasser 128 Ko");
			}
			

			enregistrer(multipartFile);
			
		} catch (Exception e) {
			throw new StorageException("Failed to store file. " + e.getMessage(), e);
		}
	}

	public void enregistrer(MultipartFile multipartFile) {
		try {
			Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
			System.out.print("Rangement");
			System.out.println(this.root.resolve(multipartFile.getOriginalFilename()).getRoot());
			
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
	
	

	public boolean isImage(MultipartFile multipartFile) {

		String mimeType = multipartFile.getContentType();

		return mimeType.substring(0, 5).equalsIgnoreCase("image");

	}

	public boolean isAtSize(MultipartFile multipartFile) {
		long maxsize = 128 * 1024;
		return maxsize >= multipartFile.getSize();

	}


}
