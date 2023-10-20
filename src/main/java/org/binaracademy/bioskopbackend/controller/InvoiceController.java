package org.binaracademy.bioskopbackend.controller;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binaracademy.bioskopbackend.model.response.OrderDetail;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/invoice")
public class InvoiceController {

    @GetMapping(value = "/generate-invoice", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generateInvoice() throws JRException, FileNotFoundException {
        List<OrderDetail> orderDetails = Arrays.asList(
                OrderDetail.builder().productName("Nasi Goreng").price("Rp. 30.000").quantity("2").build(),
                OrderDetail.builder().productName("Mie Goreng").price("Rp. 20.000").quantity("2").build(),
                OrderDetail.builder().productName("Es Teh Manis").price("Rp. 20.000").quantity("4").build()
        );

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userName", "Rizky Fauzi");
        dataMap.put("finalPrice", "Rp. 70.000");
        dataMap.put("orderDetail", orderDetails);
        JasperPrint invoice = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:invoice.jrxml").getAbsolutePath()),
                dataMap,
                new JRBeanCollectionDataSource(orderDetails)
        );
        return ResponseEntity.ok()
                .body(JasperExportManager.exportReportToPdf(invoice));
    }
}
