package com.cs490.onlineshopping.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.util.ResourceUtils;

import com.cs490.onlineshopping.dto.ReportDTO;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportingService {

    @Autowired
    private ProductRepository productRepository;

    public File exportProductReport() throws IOException {
    	
        Iterable<Product> products = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        products.forEach(result::add);        
        List<ReportDTO> res = new ArrayList<>();
        for(Product p: result) {
        	String username = p.getVendor().getUsername();
        	ObjectMapper objectMapper = new ObjectMapper();
            String product = objectMapper.writeValueAsString(p);
            ObjectNode json = (ObjectNode) objectMapper.readTree(product);
            json.put("vendor_username", username);
            ReportDTO prod = objectMapper.readValue(json.toString(), ReportDTO.class);
            
            res.add(prod);            
        }
                
        File pdfFile = File.createTempFile("Product_Report", ".pdf");

        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
			// Load the invoice jrxml template.
            final JasperReport jasperReport = loadTemplate();

              // Create parameters map.
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("created_By", "Meron");

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(res);

            // Render the PDF file
            JasperReportsUtils.renderAsPdf(jasperReport, parameters, dataSource, pos);
        }
        catch (final Exception e)
        {
        	throw new RuntimeException(e);
        }

        return pdfFile;
    }
    
    private JasperReport loadTemplate() throws FileNotFoundException, JRException {

        File file = ResourceUtils.getFile("classpath:product.jrxml");
        return JasperCompileManager.compileReport(file.getAbsolutePath());
    }
}
