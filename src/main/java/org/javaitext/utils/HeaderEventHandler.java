package org.javaitext.utils;

import java.io.ByteArrayOutputStream;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.ParagraphRenderer;

public class HeaderEventHandler implements IEventHandler {

  private Document document = null;

  public HeaderEventHandler(Document document) {
    this.setDocument(document);
  }

  @Override
  public void handleEvent(Event event) {
    PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
    PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
    PdfPage pdfPage = pdfDocumentEvent.getPage();

    PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamBefore(), pdfPage.getResources(), pdfDocument);

    Paragraph para = this.getHeaderContent( pdfDocument.getPageNumber(pdfPage) );
    float paraHeight = this.getHeaderheight(para);

    Rectangle rect = new Rectangle(
      pdfDocument.getDefaultPageSize().getX() + this.getDocument().getLeftMargin(),
      pdfDocument.getDefaultPageSize().getTop() - this.getDocument().getTopMargin(),
      523, paraHeight);

    Canvas canvas = new Canvas(pdfCanvas, pdfDocument, rect);
    canvas.setBackgroundColor(Color.LIGHT_GRAY);
    canvas.add(para);
  }

  private Paragraph getHeaderContent(int pageNamber) {
    Paragraph content = new Paragraph();
    content.setTextAlignment(TextAlignment.CENTER);
    content.setFontSize(10);
    content.setMargin(3);

    Paragraph para = new Paragraph("Header page for page: " + pageNamber);
    para.setBold();
    content.add(para);

    content.add(new Text("\n"));

    para = new Paragraph("The subtitle for header");
    content.add(para);

    return content;
  }

  private float getHeaderheight(Paragraph content) {
    ParagraphRenderer renderer = (ParagraphRenderer) content.createRendererSubTree();
    renderer.setParent( new Document(new PdfDocument(new PdfWriter(new ByteArrayOutputStream()))).getRenderer() );
    return renderer.layout(new LayoutContext(new LayoutArea(0, PageSize.A4))).getOccupiedArea().getBBox().getHeight();
  }

  /**
   * @return the document
   */
  private Document getDocument() {
    return document;
  }

  /**
   * @param document the document to set
   */
  private void setDocument(Document document) {
    this.document = document;
  }

}