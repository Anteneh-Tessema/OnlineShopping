package com.cs490.onlineshopping.report;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_PDF;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportingService;

    @GetMapping(value = "/products", produces = "application/pdf")
    public ResponseEntity<InputStreamResource> listOfProduct() throws IOException {
        final File productPdf = reportingService.generateProductReport();
        final HttpHeaders httpHeaders = getHttpHeaders(productPdf, "List Of Product");
        return new ResponseEntity<>(new InputStreamResource(new FileInputStream(productPdf)), httpHeaders, OK);
    }

    @GetMapping(value = {"/price", "/price/{vendorId}"}, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> statusByPrice(@PathVariable(required = false) Long vendorId) throws IOException {
         File productPdf;
        if(vendorId == null)
            productPdf = reportingService.generatePriceReport();
        else
            productPdf = reportingService.generatePriceByVendorReport(vendorId);

        final HttpHeaders httpHeaders = getHttpHeaders(productPdf, "Status Of Sale By Price");
        return new ResponseEntity<>(new InputStreamResource(new FileInputStream(productPdf)), httpHeaders, OK);
    }

    @GetMapping(value = {"/product", "/product/{vendorId}"}, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> statusByProduct(@PathVariable(required = false) Long vendorId) throws IOException {
         File productPdf;
        if(vendorId == null)
            productPdf = reportingService.generateProductsReport();
        else
            productPdf = reportingService.generateProductsByVendorReport(vendorId);

        final HttpHeaders httpHeaders = getHttpHeaders(productPdf, "Status Of Sale By Product");
        return new ResponseEntity<>(new InputStreamResource(new FileInputStream(productPdf)), httpHeaders, OK);
    }

    private HttpHeaders getHttpHeaders(File productPdf, String title) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(APPLICATION_PDF);
        respHeaders.setContentLength(productPdf.length());
        respHeaders.setContentDispositionFormData("attachment", format("%s-%s.pdf", title, "Report"));
        return respHeaders;
    }

}
