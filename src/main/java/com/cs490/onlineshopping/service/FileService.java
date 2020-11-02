package com.cs490.onlineshopping.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cs490.onlineshopping.exception.FileStorageException;

@Service
public class FileService {

	@Value("${app.upload.dir}")
	public String uploadDir;

	public String uploadFile(MultipartFile file) {

		try {

			Path copyLocation = Paths.get(System.getProperty("user.dir") + File.separator + uploadDir + File.separator
					+ StringUtils.cleanPath(file.getOriginalFilename())).toAbsolutePath().normalize();
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

			return copyLocation.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileStorageException(
					"Could not store file " + file.getOriginalFilename() + ". Please try again!");
		}
	}
}