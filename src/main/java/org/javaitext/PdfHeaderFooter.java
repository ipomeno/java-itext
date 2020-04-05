package org.javaitext;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;

import org.javaitext.utils.FooterEventHandler;
import org.javaitext.utils.HeaderEventHandler;

public class PdfHeaderFooter {

  public void execute() throws Exception {
    PdfDocument pdfDocument = new PdfDocument(new PdfWriter(SystemConfig.getPath() + "header-footer.pdf"));
    Document document = new Document(pdfDocument, PageSize.A4);
    document.setMargins(70, 36, 72, 36);

    pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());
    pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderEventHandler(document));

    for(int index = 0; index < 150; index++) {
      document.add(new Paragraph("Parágrafo qualquer " + index));
    }

    document.add(new AreaBreak());
    document.add(new Paragraph("Olá mundo!!!"));
    document.add(new AreaBreak());
    document.add(new Paragraph("Olá mundo!!!"));

    document.close();
  }

}