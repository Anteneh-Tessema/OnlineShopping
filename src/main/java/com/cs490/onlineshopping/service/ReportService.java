package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.repository.OrderRepository;
import com.cs490.onlineshopping.repository.ProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    public String productReport(String format) throws FileNotFoundException, JRException {
        Iterable<Product> products = productRepository.findAll();
        File file = ResourceUtils.getFile("classpath:static/reports/products.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(IterableUtils.toList(products));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created By", "Online Shop");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path = file.getParent();

        if(format.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/product_report.html");
            return path + File.separator + "product_report.html";
        }
        if(format.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+ "/product_report.pdf");
            return path + File.separator + "product_report.pdf";
        }
        else {
        	throw new IllegalArgumentException("No reports found");
        }
        
    }
    public String orderReport(String format) throws FileNotFoundException, JRException {

        Iterable<Order> orders = orderRepository.findAll();
        File file = ResourceUtils.getFile("classpath:static/reports/orders.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(IterableUtils.toList(orders));
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Created By", "Online Shop");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path = file.getParent();

        if(format.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/orders_report.html");
            return path.toString() + File.separator + "orders_report.html";
        }
        if(format.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+ "/orders_report.pdf");
            return path.toString() + File.separator + "orders_report.pdf";
        }
        else {
        	throw new IllegalArgumentException("No reports found");
        }
    }

}
