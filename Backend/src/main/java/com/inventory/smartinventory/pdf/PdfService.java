package com.inventory.smartinventory.pdf;

import com.inventory.smartinventory.entity.Food;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font; 
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PdfService {

    public void generate(List<Food> foods, HttpServletResponse response) throws Exception {

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDType1Font font = PDType1Font.HELVETICA_BOLD;

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(font, 12);
                content.setLeading(16f);
                content.newLineAtOffset(50, 750);

                content.showText("Smart Inventory Report");
                content.newLine();
                content.showText("---------------------------------------");
                content.newLine();
                content.showText("Item | Qty | Price | Expiry");
                content.newLine();
                content.showText("---------------------------------------");
                content.newLine();

                int total = 0;
                for (Food f : foods) {
                    int itemTotal = f.getPrice() * f.getQuantity();
                    total += itemTotal;
                    
                    String name = f.getItemName() != null ? f.getItemName() : "Unknown";
                    String qty = String.valueOf(f.getQuantity());
                    String price = String.valueOf(f.getPrice());
                    String date = f.getExpiryDate() != null ? f.getExpiryDate().toString() : "N/A";

                    content.showText(name + " | " + qty + " | " + price + " | " + date);
                    content.newLine();
                }
                content.newLine();
                content.showText("Grand Total: " + total + " /-");
                content.endText();
            }

            document.save(response.getOutputStream());
        }
    }
}