package com.cs490.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs490.onlineshopping.service.FileService;

@RestController
@RequestMapping("/api/uploads")
public class FileUploadController {

	    @Autowired
	    FileService fileService;

	    @GetMapping()
	    @Secured({"ROLE_VENDOR, ROLE_ADMIN"})
	    public String index() {
	        return "upload";
	    }

	    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    @Secured({"ROLE_VENDOR, ROLE_ADMIN"})
	    public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
	    	
	        String filePath = fileService.uploadFile(file);

	        redirectAttributes.addFlashAttribute("message",
	            "You successfully uploaded " + file.getOriginalFilename() + "!");

	        return file.getOriginalFilename();
	    }
	}