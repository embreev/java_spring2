package com.geekbrains.decembermarket.utils;

import com.geekbrains.decembermarket.entites.Order;
import com.geekbrains.decembermarket.entites.OrderItem;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PdfCreator {
    public static String pathPdfOrder = "pdforders";

    public void createPdfOrder(Order order) {

        try {
            PDDocument pdDoc = new PDDocument();
            PDPage page = new PDPage();
            pdDoc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(pdDoc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 14);
                cs.setLeading(14.5f);
                cs.newLineAtOffset(20, 750);
                cs.showText("Order: " + order.getId().toString());
                cs.newLine();
                cs.showText("");
                cs.newLine();
                for (OrderItem item : order.getItems()) {
                    cs.showText(item.getProduct().getTitle() + " | "
                            + item.getQuantity() + " | "
                            + item.getPrice());
                    cs.newLine();
                }
                cs.showText("");
                cs.newLine();
                cs.showText("Phone: " + order.getPhone());
                cs.newLine();
                cs.showText("Address: " + order.getAddress());
                cs.endText();
            }
            pdDoc.save(pathPdfOrder + "\\order_" + order.getId() + ".pdf");
            pdDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
