package org.javaitext.utils;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class FooterEventHandler implements IEventHandler {

  @Override
  public void handleEvent(Event event) {
    PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
    PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
    PdfPage page = pdfDocumentEvent.getPage();

    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDocument);
    Paragraph para = new Paragraph("Footer content for page " + pdfDocument.getPageNumber(page));
    para.setTextAlignment(TextAlignment.CENTER);
    para.setFontSize(9);

    Canvas canvas = new Canvas(pdfCanvas, pdfDocument, new Rectangle(36, 20, page.getPageSize().getWidth() - 72, 50));
    canvas.add(para);
  }

}