/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadegestióndeplanesdecurso.logica;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author angel
 */
public class GeneradorPDF {
    
    public static void generarPlanDeCurso(String nombreArchivo, String nombreCurso, String duracion, String participantes, String contenido, String objetivos, String metodologia) throws SQLException{
        try{
            Document documento = new Document();
            FileOutputStream archivoPdf = new FileOutputStream("src\\" + nombreArchivo + ".pdf");
            PdfWriter.getInstance(documento, archivoPdf).setInitialLeading(20);
            documento.open();
            Paragraph titulo = new Paragraph("Plan del Curso.");
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            Paragraph saltoDeLinea = new Paragraph(" ");
            documento.add(saltoDeLinea);
            PdfPTable tabla = new PdfPTable(1);
            PdfPCell celda = new PdfPCell();
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celda.setPhrase(new Phrase("Nombre del curso"));
            tabla.addCell(celda);
            tabla.addCell(nombreCurso);
            celda.setPhrase(new Phrase("Duracion del curso"));
            tabla.addCell(celda);
            tabla.addCell(duracion);
            celda.setPhrase(new Phrase("Participantes del curso"));
            tabla.addCell(celda);
            tabla.addCell(participantes);
            celda.setPhrase(new Phrase("Objetivos del curso"));
            tabla.addCell(celda);
            tabla.addCell(objetivos);
            celda.setPhrase(new Phrase("Contenido del curso"));
            tabla.addCell(celda);
            tabla.addCell(contenido);
            celda.setPhrase(new Phrase("Contenido del curso"));
            tabla.addCell(celda);
            tabla.addCell(contenido);
            celda.setPhrase(new Phrase("Metodología del curso"));
            tabla.addCell(celda);
            tabla.addCell(metodologia);
            
            documento.add(tabla);
            documento.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeneradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
