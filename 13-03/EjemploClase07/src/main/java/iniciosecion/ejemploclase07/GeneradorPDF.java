/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iniciosecion.ejemploclase07;
import com.itextpdf.io.image.ImageDataFactory; // imagen
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument; //
import com.itextpdf.kernel.pdf.PdfWriter; // Escribir el pdf
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
/**
 *
 * @author Pacos
 */
public class GeneradorPDF {
    public void generarPDF(String destino, String[] encabezado, String[][] data, String rutaImagen, String texto1, String texto2) throws FileNotFoundException, IOException, MalformedURLException {
        PdfWriter writer = new PdfWriter(destino);
        PdfDocument pdf = new PdfDocument(writer);
        Document documento = new Document(pdf);



        // Segunda página (tablas, titulo, texto, imagen)
        String[] encabezado2 = {"GUI", "Tipo"};
        String[][] data2 = {
            {"1", "Depósito"},
            {"2", "Retiro"},
            {"3", "Transferencia"},
            {"4", "Consulta"}
        };
        String textoT = "En esta sección se muestra el historial de transacciones realizadas por el usuario. "
                + "Se muestra la fecha, hora, monto y tipo de transacción. "
                + "Además, se muestra el saldo actual de la cuenta.";
        String texto2T = "En esta sección se muestra el historial de transacciones realizadas por el usuario. "
                + "Se muestra la fecha, hora, monto y tipo de transacción. "
                + "Además, se muestra el saldo actual de la cuenta.";
        this.Pagina(documento, rutaImagen, "Reporte historial transacciones", encabezado2, data2, textoT, texto2T);

        documento.close();
    }



    private void Pagina(Document documento, String ruta, String titulo, String[] encabezado, String[][] datos, String texto1, String texto2) throws MalformedURLException {
        documento.add(new AreaBreak());

        documento.add(new Paragraph(titulo)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(22)
                .setBold());

        documento.add(new Paragraph(texto1)
                .setTextAlignment(TextAlignment.JUSTIFIED));

        Table tabla1 = this.crearTabla(encabezado, datos);
        documento.add(tabla1);

        documento.add(new Paragraph("")
                .setTextAlignment(TextAlignment.JUSTIFIED));



        documento.add(new Paragraph(texto2)
                .setTextAlignment(TextAlignment.JUSTIFIED));

        Table tabla2 = this.crearTabla(encabezado, datos);
        documento.add(tabla2);

        documento.add(new Paragraph("")
                .setTextAlignment(TextAlignment.JUSTIFIED));

        Image imagen2 = new Image(ImageDataFactory.create(ruta))
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setWidth(250);
        documento.add(imagen2);
    }

    private Table crearTabla(String[] encabezado, String[][] data) {
        Table tabla = new Table(UnitValue.createPercentArray(encabezado.length)).useAllAvailableWidth();

        Color headerColor = new DeviceRgb(63, 81, 181);
        Color cellColor = new DeviceRgb(224, 224, 224);
        Color whiteColor = new DeviceRgb(255, 255, 255);

        for (String valor : encabezado) {
            tabla.addHeaderCell(new Cell().add(new Paragraph(valor).setBold().setFontColor(whiteColor)).setBackgroundColor(headerColor));
        }

        for (String[] valor : data) {
            for (String celda : valor) {
                tabla.addCell(new Cell().add(new Paragraph(celda)).setBackgroundColor(cellColor));
            }
        }

        return tabla;
    }
}
