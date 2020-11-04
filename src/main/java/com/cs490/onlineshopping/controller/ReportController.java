package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.service.ReportService;
import net.sf.jasperreports.engine.JRException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	@Autowired
	ReportService reportService;

	@GetMapping("/products")
	@Secured({"ROLE_ADMIN"})
	public String generateReport() throws IOException, FileNotFoundException, JRException {
			
		return reportService.productReport("pdf");
	
	}

	@GetMapping("/orders")
	@Secured({"ROLE_ADMIN"})
	public String ordersReport() throws FileNotFoundException, JRException {
		return reportService.orderReport("pdf");

	}

}
