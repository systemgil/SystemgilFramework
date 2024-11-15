package utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.IOException;
import java.util.List;

public class GeneradorPDF {
    public static void generarPDF(String nombreProyecto, String testCase, List<String> pasos, List<String> capturas, String rutaPDF) {
        try {
            PdfWriter writer = new PdfWriter(rutaPDF);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Prueba: " + testCase).setBold());
            for (int i = 0; i < pasos.size(); i++) {
                document.add(new Paragraph(pasos.get(i)));
                if (i < capturas.size()) {
                    Image image = new Image(ImageDataFactory.create(capturas.get(i)));
                    image.scaleToFit(500, 300);
                    document.add(image);
                }
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
