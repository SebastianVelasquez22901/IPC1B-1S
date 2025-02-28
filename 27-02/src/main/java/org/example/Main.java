package org.example;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        String dest = "user_report.pdf";
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Añadir título
            Paragraph title = new Paragraph(new Text("User Report").setBold())
                    .setFontSize(20);
            document.add(title);

            // Agregar información del usuario
            document.add(new Paragraph("Name: John Doe"));
            document.add(new Paragraph("Date: " + LocalDate.now()));

            // Crear la tabla
            float[] columnWidths = {1, 5, 2};
            Table table = new Table(columnWidths);

            // Agregar encabezados con fondo gris
            table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add(new Paragraph("Description")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add(new Paragraph("Amount")).setBackgroundColor(ColorConstants.LIGHT_GRAY));

            // Agregar filas de datos
            table.addCell(new Cell().add(new Paragraph("1")));
            table.addCell(new Cell().add(new Paragraph("Item 1 description")));
            table.addCell(new Cell().add(new Paragraph("$10.00")));

            table.addCell(new Cell().add(new Paragraph("2")));
            table.addCell(new Cell().add(new Paragraph("Item 2 description")));
            table.addCell(new Cell().add(new Paragraph("$20.00")));

            table.addCell(new Cell().add(new Paragraph("3")));
            table.addCell(new Cell().add(new Paragraph("Item 3 description")));
            table.addCell(new Cell().add(new Paragraph("$30.00")));

            table.addCell(new Cell().add(new Paragraph("4")));
            table.addCell(new Cell().add(new Paragraph("Item 4 description")));
            table.addCell(new Cell().add(new Paragraph("$100.00")));

            document.add(table);

            document.close();
            System.out.println("PDF created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
