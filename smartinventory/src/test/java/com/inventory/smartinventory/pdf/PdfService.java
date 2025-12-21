package com.inventory.smartinventory.pdf;

import com.inventory.smartinventory.entity.Food;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class PdfService {

    public void generate(List<Food> foods) throws Exception {

        // 1️⃣ Create document & page
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        // 2️⃣ Load font safely from resources
        InputStream fontStream =
                getClass().getResourceAsStream("/fonts/VeraMono.ttf");

        if (fontStream == null) {
            throw new RuntimeException("❌ Font not found: src/main/resources/fonts/VeraMono.ttf");
        }

        PDType0Font font = PDType0Font.load(document, fontStream);

        // 3️⃣ Content stream (SAFE try-with-resources)
        try (PDPageContentStream content =
                     new PDPageContentStream(document, page)) {

            content.beginText();
            content.setFont(font, 12);
            content.setLeading(16f);
            content.newLineAtOffset(50, 750);

            // Title
            content.showText("Food Inventory Report");
            content.newLine();
            content.showText("---------------------------------------");
            content.newLine();

            int total = 0;

            // Header
            content.showText("Item | Qty | Price | Expiry");
            content.newLine();
            content.showText("---------------------------------------");
            content.newLine();

            // 4️⃣ Data
            for (Food f : foods) {
                int itemTotal = f.getPrice() * f.getQuantity();
                total += itemTotal;

                content.showText(
                        f.getItemName() + " | " +
                                f.getQuantity() + " | " +
                                f.getPrice() + " | " +
                                f.getExpiryDate()
                );
                content.newLine();
            }

            content.newLine();
            content.showText("Total Amount = " + total + " /-");
            content.endText();
        }

        // 5️⃣ Save PDF
        document.save("InventoryReport.pdf");
        document.close();
    }
}
