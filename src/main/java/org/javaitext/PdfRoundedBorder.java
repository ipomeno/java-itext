package org.javaitext;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import org.javaitext.utils.RoundedSolidBorder;

public class PdfRoundedBorder {

  public void execute() throws IOException, FileNotFoundException {
    PdfWriter writer = new PdfWriter(SystemConfig.getPath() + "rounded-border.pdf");
    PdfDocument pdfDocument = new PdfDocument(writer);
    Document document = new Document(pdfDocument);

    PdfFont boldFont = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
    PdfFont simpleFont = PdfFontFactory.createFont(FontConstants.HELVETICA);

    Paragraph paraTitle = new Paragraph("TÍTULO DO DOCUMENTO QUALQUER");

    RoundedSolidBorder border = new RoundedSolidBorder(Color.LIGHT_GRAY, 2, 7);
    border.setBackgroundColor(new DeviceRgb(230, 230, 230));

    paraTitle.setBorder(border);
    paraTitle.setPadding(20);
    paraTitle.setFont(boldFont);
    paraTitle.setFontSize(20);
    paraTitle.setTextAlignment(TextAlignment.CENTER);

    Paragraph paraSimple = new Paragraph("Subtítulo qualquer para o documento");
    paraSimple.setFont(simpleFont);
    paraSimple.setFontSize(12);

    paraTitle.add(paraSimple);
    document.add(paraTitle);
    document.close();
  }

}