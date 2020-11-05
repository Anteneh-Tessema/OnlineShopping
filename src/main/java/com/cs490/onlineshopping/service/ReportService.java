package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.dto.ReportDTO;
import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.OrderItem;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Status;
import com.cs490.onlineshopping.repository.OrderRepository;
import com.cs490.onlineshopping.repository.ProductRepository;
import com.cs490.onlineshopping.repository.ReportRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReportRepository reportRepository;

    public File generateProductReport() throws IOException {

        Iterable<Product> products = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        products.forEach(result::add);
        List<ReportDTO> res = new ArrayList<>();
        for(Product p: result) {
            String username = p.getVendor().getUsername();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String product = objectMapper.writeValueAsString(p);
            ObjectNode json = (ObjectNode) objectMapper.readTree(product);
            json.put("vendor_username", username);
            ReportDTO prod = objectMapper.readValue(json.toString(), ReportDTO.class);
            res.add(prod);
        }

        File pdfFile = File.createTempFile("List of Product Report", ".pdf");
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("created_By", "Meron");
        String path = "product.jrxml";
        renderPDFFile(pdfFile,parameters, path, res);
        return pdfFile;
    }

    public File generatePriceReport() throws IOException{
        Iterable<Product> products = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        products.forEach(result::add);
        List<ReportDTO> res = new ArrayList<>();
        for(Product p: result) {
            String price_earned = "$" + checkOrderItem(p.getId(), "price");
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String product = objectMapper2.writeValueAsString(p);
            ObjectNode json = (ObjectNode) objectMapper2.readTree(product);
            json.put("price_earned", price_earned);
            ReportDTO prod = objectMapper2.readValue(json.toString(), ReportDTO.class);
            res.add(prod);
        }

        File pdfFile = File.createTempFile("Financial Report", ".pdf");
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("created_By", "Anteneh Team");
        String path = "statusprice.jrxml";
        renderPDFFile(pdfFile,parameters, path, res);
        return pdfFile;
    }

    public File generatePriceByVendorReport(Long vendorId) throws IOException{
        List<Product> result = productRepository.findByVendorId(vendorId);
        List<ReportDTO> res = new ArrayList<>();
        for(Product p: result) {
            String price_earned = checkOrderItem(p.getId(), "price");
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String product = objectMapper2.writeValueAsString(p);
            ObjectNode json = (ObjectNode) objectMapper2.readTree(product);
            json.put("price_earned", price_earned);
            ReportDTO prod = objectMapper2.readValue(json.toString(), ReportDTO.class);
            res.add(prod);
        }

        File pdfFile = File.createTempFile("Financial Report", ".pdf");
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("created_By", "Anteneh Team");
        parameters.put("vedorId", vendorId);
        String path = "statuspricebyvendor.jrxml";
        renderPDFFile(pdfFile,parameters, path, res);
        return pdfFile;
    }

    public File generateProductsReport() throws IOException{
            Iterable<Product> products = productRepository.findAll();
            List<Product> result = new ArrayList<>();
            products.forEach(result::add);
            List<ReportDTO> res = new ArrayList<>();
            for(Product p: result) {
                String sold_No = checkOrderItem(p.getId(), "product");
                ObjectMapper objectMapper2 = new ObjectMapper();
                objectMapper2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                String product = objectMapper2.writeValueAsString(p);
                ObjectNode json = (ObjectNode) objectMapper2.readTree(product);
                json.put("sold_No", sold_No);
                ReportDTO prod = objectMapper2.readValue(json.toString(), ReportDTO.class);
                res.add(prod);
            }

            File pdfFile = File.createTempFile("Status Of Sale By Product Report", ".pdf");
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("created_By", "Anteneh Team");
            String path = "statusproduct.jrxml";
            renderPDFFile(pdfFile,parameters, path, res);
            return pdfFile;
    }

    public File generateProductsByVendorReport(Long vendorId) throws IOException{
        List<Product> result = productRepository.findByVendorId(vendorId);
        List<ReportDTO> res = new ArrayList<>();
        for(Product p: result) {
            String sold_No = checkOrderItem(p.getId(), "product");
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String product = objectMapper2.writeValueAsString(p);
            ObjectNode json = (ObjectNode) objectMapper2.readTree(product);
            json.put("sold_No", sold_No);
            ReportDTO prod = objectMapper2.readValue(json.toString(), ReportDTO.class);
            res.add(prod);
        }

        File pdfFile = File.createTempFile("Status Of Sale By Product Report", ".pdf");
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("created_By", "Anteneh Team");
        String path = "statusproductbyvendor.jrxml";
        renderPDFFile(pdfFile,parameters, path, res);
        return pdfFile;
    }

    private void renderPDFFile(File pdfFile, Map<String, Object> parameters, String path, List<ReportDTO> res) {
        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
            final JasperReport jasperReport = loadTemplate(path);
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(res);
            JasperReportsUtils.renderAsPdf(jasperReport, parameters, dataSource, pos);
        }
        catch (final Exception e){throw new RuntimeException(e);}
    }

    private JasperReport loadTemplate(String path) throws FileNotFoundException, JRException {

        File file = ResourceUtils.getFile("classpath:" + path);
        return JasperCompileManager.compileReport(file.getAbsolutePath());
    }

    private String checkOrderItem(Long id, String type) {
        String result = "";
        double sum =0;
        List<OrderItem> orderItems = reportRepository.findOrderItem(id);
        if(orderItems != null)
        {
            for (OrderItem orderItem: orderItems) {
                if(type.equals("price"))
                    sum = sum + orderItem.getPrice() * orderItem.getQuantity();
                else if(type.equals("product"))
                    sum += orderItem.getQuantity();
            }
        }
        return result + sum;
    }
}
