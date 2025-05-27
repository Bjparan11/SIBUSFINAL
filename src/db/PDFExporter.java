package db;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;

public class PDFExporter {

    public static void exportSelectedRowsToPDF(JTable table, int[] selectedRows) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save PDF");
        chooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));

        int result = chooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();
        String filePath = file.getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            filePath += ".pdf";
        }

        Document document = new Document(PageSize.A4, 36, 36, 54, 54); // Margins

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Add footer with page number
            writer.setPageEvent(new PdfPageEventHelper() {
                public void onEndPage(PdfWriter writer, Document document) {
                    PdfPTable footer = new PdfPTable(1);
                    try {
                        footer.setTotalWidth(527);
                        footer.setWidths(new int[]{1});
                        footer.setLockedWidth(true);
                        footer.getDefaultCell().setFixedHeight(30);
                        footer.getDefaultCell().setBorder(Rectangle.TOP);
                        footer.getDefaultCell().setBorderColor(Color.LIGHT_GRAY);
                        footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                        footer.addCell(new Phrase(String.format("Page %d", writer.getPageNumber()), new Font(Font.TIMES_ROMAN, 10, Font.ITALIC, Color.GRAY)));
                        footer.writeSelectedRows(0, -1, 34, 50, writer.getDirectContent());
                    } catch (DocumentException de) {
                        de.printStackTrace();
                    }
                }
            });

            document.open();

            // Add logo if exists
            try {
                Image logo = Image.getInstance("Images/logo.png");
                logo.scaleToFit(80, 80);
                logo.setAlignment(Image.LEFT);
                document.add(logo);
            } catch (Exception e) {
                System.out.println("Logo not found or failed to load.");
            }

            // Header bar
            PdfPTable headerBar = new PdfPTable(1);
            headerBar.setWidthPercentage(100);
            PdfPCell headerCell = new PdfPCell(new Phrase("PURCHASED PROPERTY RECEIPT", new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.WHITE)));
            headerCell.setBackgroundColor(new Color(30, 144, 255)); // Dodger Blue
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setPadding(10f);
            headerCell.setBorder(Rectangle.NO_BORDER);
            headerBar.addCell(headerCell);
            document.add(headerBar);

            document.add(Chunk.NEWLINE);

            // Company info
            Paragraph companyInfo = new Paragraph("Paran,Benjohn Realty Co. \n123 Property Lane, Phillipines\nPhone: (123) 456-7890\nEmail: info@legendaryrealty.com", new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.DARK_GRAY));
            companyInfo.setAlignment(Element.ALIGN_CENTER);
            document.add(companyInfo);
            document.add(Chunk.NEWLINE);

            TableModel model = table.getModel();

            Font labelFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);
            Font valueFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.DARK_GRAY);

            for (int rowIndex : selectedRows) {
                PdfPTable infoTable = new PdfPTable(2);
                infoTable.setWidthPercentage(100);
                infoTable.setSpacingBefore(15f);
                infoTable.setSpacingAfter(15f);
                infoTable.getDefaultCell().setPadding(8);
                infoTable.setWidths(new float[]{1f, 2f});

                // Add a light border around the table
                infoTable.getDefaultCell().setBorder(Rectangle.BOX);
                infoTable.getDefaultCell().setBorderColor(new Color(192, 192, 192));

                for (int col = 0; col < model.getColumnCount(); col++) {
                    String label = model.getColumnName(col);
                    Object valueObj = model.getValueAt(rowIndex, col);
                    String value = valueObj != null ? valueObj.toString() : "";

                    PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
                    PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));

                    labelCell.setBackgroundColor(new Color(230, 230, 250)); // Lavender
                    labelCell.setBorder(Rectangle.BOX);
                    labelCell.setBorderColor(new Color(192, 192, 192));
                    labelCell.setPadding(8);

                    valueCell.setBackgroundColor(Color.WHITE);
                    valueCell.setBorder(Rectangle.BOX);
                    valueCell.setBorderColor(new Color(192, 192, 192));
                    valueCell.setPadding(8);

                    infoTable.addCell(labelCell);
                    infoTable.addCell(valueCell);
                }

                document.add(infoTable);

                // Dashed separator line
                PdfContentByte canvas = writer.getDirectContent();
                canvas.setLineWidth(0.5f);
                canvas.setColorStroke(new Color(160, 160, 160));
                float y = document.getPageSize().getHeight() - document.topMargin() - 250 - (rowIndex * 120);
                canvas.setLineDash(3, 3, 0);
                canvas.moveTo(document.leftMargin(), y);
                canvas.lineTo(document.getPageSize().getWidth() - document.rightMargin(), y);
                canvas.stroke();
                canvas.setLineDash(1, 0);

            }

            document.add(Chunk.NEWLINE);

            // Footer message
            Paragraph footer = new Paragraph("Thank you for your purchase! We appreciate your business.", new Font(Font.TIMES_ROMAN, 12, Font.ITALIC, Color.GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            // Date at bottom right
            Paragraph date = new Paragraph("Generated on: " + java.time.LocalDate.now(), new Font(Font.TIMES_ROMAN, 10, Font.NORMAL, Color.GRAY));
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            document.close();

            JOptionPane.showMessageDialog(null, "PDF exported successfully to:\n" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage(), "PDF Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
