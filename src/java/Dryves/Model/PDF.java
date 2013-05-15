/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Model;

/**
 *
 * @author RickSpijker
 */

import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.MalformedURLException;


public class PDF {
  private static String FILE = "/Users/RickSpijker/Desktop/FirstPdf.pdf";
  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL, BaseColor.RED);
  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11,
      Font.BOLD);
  
  //Hieronder worden de variabelen gedeclareerd, die gebruikt worden om de lidgegevens te kunnen gebruiken in de PDF.
  
  private String voornaam;
  private String achternaam;
  private String email;
  
  //gegevens van de rit
  private int ritnummer;
  
  //Hieronder worden de gegevens van de aankoop opgehaald.
  private String betaalwijze;
  private int factuurnr;
  
  public void vulDePDF(String vnaam, String anaam, String mail, int ritnr, String betaalw, int fnr){
  
   this.voornaam = vnaam;
   this.achternaam = anaam;
   this.email = mail;
   this.ritnummer = ritnr;
   this.betaalwijze = betaalw;
   this.factuurnr = fnr;
  
  }
  
  
  public void bouwPDF(){
  
      try {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream(FILE));
      document.open();
      addMetaData(document);
      addTitlePage(document);
      //addContent(document);
      document.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  
      
  }


  // iText allows to add metadata to the PDF which can be viewed in your Adobe
  // Reader
  // under File -> Properties
  private void addMetaData(Document document) {
    document.addTitle("Dryves.eu");
    document.addSubject("Factuur");
    document.addKeywords("Factuur, Dryves, Ritten");
    document.addAuthor("Dryves.eu");
    document.addCreator("Dryves.eu");
  }

  private void addTitlePage(Document document)
      throws DocumentException, BadElementException, MalformedURLException, IOException {
    Paragraph preface = new Paragraph();
    // We add one empty line
    addEmptyLine(preface, 1);
      
    //resourceloader.class.getClassLoader().getResource("images/Logo_Dryves.png").toString();
    
      //String toString = ClassLoader.getResource("images/Logo_Dryves.png");
    
    
    //Image logodryves = Image.getInstance("./images/Logo_Dryves.png");
    //preface.add(logodryves);
    
    // Lets write a big header
    preface.add(new Paragraph("Factuur - Dryves.eu", catFont));
    preface.add(new Paragraph("Factuurnummer :" + factuurnr, smallBold));

    addEmptyLine(preface, 1);

    preface.add(new Paragraph("Uw gegevens:",
        smallBold));
    
    addEmptyLine(preface, 1);
    
    
    preface.add(new Paragraph(voornaam + " " + achternaam,
        smallBold));
    
    preface.add(new Paragraph(email,
        smallBold)); 
    
    addEmptyLine(preface, 1);

    preface.add(new Paragraph("Uw aankoop:",
        smallBold));
    
    addEmptyLine(preface, 1);
    
    preface.add(new Paragraph("Ritnummer: " + ritnummer,
        smallBold));
    
    preface.add(new Paragraph("Betaalwijze: " + betaalwijze,
        smallBold));

    document.add(preface);

  }

  private void addContent(Document document) throws DocumentException {
    Anchor anchor = new Anchor("Factuurgegevens", catFont);
    anchor.setName("Factuurgegevens");

    // Second parameter is the number of the chapter
    Chapter catPart = new Chapter(new Paragraph(anchor), 1);

    Paragraph subPara = new Paragraph("Subcategory 1", subFont);
    Section subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Hello"));

    subPara = new Paragraph("Subcategory 2", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Paragraph 1"));
    subCatPart.add(new Paragraph("Paragraph 2"));
    subCatPart.add(new Paragraph("Paragraph 3"));

    // Add a list
    createList(subCatPart);
    Paragraph paragraph = new Paragraph();
    addEmptyLine(paragraph, 5);
    subCatPart.add(paragraph);

    // Add a table
    createTable(subCatPart);

    // Now add all this to the document
    document.add(catPart);

    // Next section
    anchor = new Anchor("Second Chapter", catFont);
    anchor.setName("Second Chapter");

    // Second parameter is the number of the chapter
    catPart = new Chapter(new Paragraph(anchor), 1);

    subPara = new Paragraph("Subcategory", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("This is a very important message"));

    // Now add all this to the document
    document.add(catPart);

  }

  private void createTable(Section subCatPart)
      throws BadElementException {
    PdfPTable table = new PdfPTable(3);

    // t.setBorderColor(BaseColor.GRAY);
    // t.setPadding(4);
    // t.setSpacing(4);
    // t.setBorderWidth(1);

    PdfPCell c1 = new PdfPCell(new Phrase("Uw Gegevens"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Table Header 2"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Table Header 3"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    table.setHeaderRows(1);

    table.addCell("Naam");
    table.addCell(voornaam + " " + achternaam);
    table.addCell("1.2");
    table.addCell("2.1");
    table.addCell("2.2");
    table.addCell("2.3");

    subCatPart.add(table);

  }

  private static void createList(Section subCatPart) {
    List list = new List(true, false, 10);
    list.add(new ListItem("First point"));
    list.add(new ListItem("Second point"));
    list.add(new ListItem("Third point"));
    subCatPart.add(list);
  }

  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
} 
