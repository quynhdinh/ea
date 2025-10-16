package edu.miu.spring_ai_demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;

public class PdfReader {

    private static final Logger logger = LoggerFactory.getLogger(PdfReader.class);

    private String filename;

    public PdfReader(String file) {
        this.filename = file;
    }

    public List<Document> read() {
        logger.info("Attempting to open: {}", filename);
        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile("target/classes/" + filename))) {
            logger.info("Reading from: {}", filename);
            List<Document> docs = new ArrayList<>();

            // Create PDFTextStripper to extract text
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // setup metadata
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("file", filename);
            metadata.put("page", 0);
            metadata.put("timestamp", System.currentTimeMillis());

            // Extract text from the PDF page by page
            int pages = document.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                logger.trace("Reading page {}", i);
                pdfStripper.setStartPage(i);
                pdfStripper.setEndPage(i);
                String text = pdfStripper.getText(document);

                // skip pages that are mostly empty (only page number or very short content)
                if (text.trim().length() <= 4) {
                    logger.trace("Skipping page: {}", i);
                    continue;
                }

                // Store pages as Spring AI documents
                Map<String, Object> pageMetadata = new HashMap<>(metadata); // Create a new map for each page
                pageMetadata.put("page", i);
                Document doc = new Document(text.trim(), pageMetadata);
                docs.add(doc);
            }
            return docs;
        } catch (IOException ex) {
            String cwd = System.getProperty("user.dir");
            logger.warn("Unable to read: {} from {}", filename, cwd, ex);
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage: Place a PDF file in src/main/resources
        PdfReader reader = new PdfReader("W1D1.pdf"); // Change to your PDF filename
        List<Document> docs = reader.read();
        if (docs != null) {
            int pageNum = 1;
            for (Document doc : docs) {
                System.out.printf("....... page: %2d ..........\n", pageNum++);
                System.out.println(doc.getFormattedContent());
            }
        } else {
            System.err.println("Failed to read PDF.");
        }
    }
}