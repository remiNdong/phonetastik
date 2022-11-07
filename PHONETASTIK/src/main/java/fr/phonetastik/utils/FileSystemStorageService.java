package fr.phonetastik.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path root = Paths.get("src/main/resources/static/images/imagesDomaine");
	private String dossierTemporaire = "src/main/resources/static/images/imagesDomaine";
	private String bucketName = "phonetastikBucket";
	private String accesskey = "";
	private String secretkey = "";
	private AmazonS3 s3client;
	private  Map<String, File> map;



	public AmazonS3 s3client() {

		if (s3client == null) {
			AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
			s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion("eu-west-3").build();
		}
		return s3client;

	}

	@Override
	public void init() throws IOException {

		map=getFiles();

	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public void store(MultipartFile multipartFile, String nomFichier) {
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

			enregistrer(multipartFile, nomFichier);

		} catch (Exception e) {
			throw new StorageException("Failed to store file. " + e.getMessage(), e);
		}
	}

	public void enregistrer(MultipartFile multipartFile, String nomFichier) {
		try {
			// Files.copy(multipartFile.getInputStream(),
			// this.root.resolve(multipartFile.getOriginalFilename()));

			File file = transfertTo(multipartFile);

			s3client().putObject(bucketName, nomFichier, file);
			map=getFiles();

			deleteAll();

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public File transfertTo(MultipartFile multipartfile) throws IllegalStateException, IOException {

		File file = new File(dossierTemporaire);

		multipartfile.transferTo(file);

		return file;
	}

	public boolean isImage(MultipartFile multipartFile) {

		String mimeType = multipartFile.getContentType();

		return mimeType.substring(0, 5).equalsIgnoreCase("image");

	}

	public boolean isAtSize(MultipartFile multipartFile) {
		long maxsize = 128 * 1024;
		return maxsize >= multipartFile.getSize();

	}

	public File getFile(String nomFile) throws IOException {
		S3Object s3object = s3client().getObject(bucketName, nomFile);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		File file = new File(root + "nomFile");
		FileUtils.copyInputStreamToFile(inputStream, file);
		return file;

	}

	public Map<String, File> getFiles() throws IOException {

		Map<String, File> map = new HashMap<String, File>();

		ObjectListing objectListing = s3client.listObjects(bucketName);
		for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
			map.put(os.getKey(), getFile(os.getKey()));
		}

		return map;

	}
	
	public Map<String, File> getMap(){
		return map;
	}

}
