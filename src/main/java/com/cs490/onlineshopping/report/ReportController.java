package com.cs490.onlineshopping.report;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportController {
   
    @Autowired
    private ReportingService productReportingService;
    
    @GetMapping(value = "/generate", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> invoiceGenerate() throws IOException, JRException {
        final File productPdf = productReportingService.exportProductReport();

        final HttpHeaders httpHeaders = getHttpHeaders(productPdf);
        return new ResponseEntity<>(new InputStreamResource(new FileInputStream(productPdf)), httpHeaders, OK);
    }
    
    private HttpHeaders getHttpHeaders(File productPdf) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(APPLICATION_PDF);
        respHeaders.setContentLength(productPdf.length());
        respHeaders.setContentDispositionFormData("attachment", format("%s-%s.pdf", "product", "Report"));
        return respHeaders;
    }
}
