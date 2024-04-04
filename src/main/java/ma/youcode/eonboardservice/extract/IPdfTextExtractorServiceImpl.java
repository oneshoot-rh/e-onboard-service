package ma.youcode.eonboardservice.extract;


import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class IPdfTextExtractorServiceImpl implements IPdfTextExtractorService {

    List<String> headers = new ArrayList<>();

    {
        headers.add("Contact");
        headers.add("Top Skills");
        headers.add("Languages");
        headers.add("Certifications");
        headers.add("Summary");
        headers.add("Experience");
        headers.add("Education");
    }

    @Override
    public  Map<String,String> extractTextFromPdf(String pdfPath) throws FileNotFoundException {
        File file = new File(pdfPath);
        log.info("Extracting text from pdf file: {}", file.getName());
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper textStripper = new PDFTextStripper() {
                private boolean isHeader = false;

                @Override
                protected void startPage(PDPage pageNo) throws IOException {
                    super.startPage(pageNo);
                    isHeader = false;
                }

                @Override
                protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
                    if (isHeader(textPositions)) {
                        writeLineSeparator();
                        isHeader = true;
                    }
                    super.writeString(string, textPositions);
                }

                private boolean isHeader(List<TextPosition> textPositions) {
                    return textPositions.stream().anyMatch(position -> position.getFontSize() > 16);
                }
            };

            textStripper.setStartPage(1);
            textStripper.setEndPage(document.getNumberOfPages());
            textStripper.setAddMoreFormatting(true);

            String text = textStripper.getText(document);
            var categorized=  categorizeText(text);
            categorized.put("resumeId", file.getName()+ "-"+LocalDateTime.now());
            categorized.put("Candidate ID", UUID.randomUUID().toString());
            categorized.put("name", this.extractNameFromPdf(pdfPath));
            return categorized;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Map<String,String> categorizeText(String text){
        Map<String,String> categorizedText = new HashMap<>();
        for (int i = headers.size()-1 ; i>= 0; i--){
            String header = "(?<=\\r\\n)"+headers.get(i)+"(?=\\r\\n)";
            Pattern pattern = Pattern.compile(header);
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()){
                String target = matcher.group();
                String[] splittedText = text.split(Pattern.quote(target));
                String bodyText = splittedText[1];
                // remove content extracted
                text = text.replace(target, "");// to avoid removing the same word if repeated in the body
                text = text.replace(bodyText, "");
                categorizedText.put(target, bodyText);
            }
        }
        return categorizedText;
    }


    @Override
    public String extractNameFromPdf(String pdfPath){
        File file = new File(pdfPath);
        log.info("Extracting candidate name from resume file: {}", file.getName());
        try (PDDocument document = PDDocument.load(file)) {
            NameFromResumeExtractor stripper = new NameFromResumeExtractor();
            stripper.setStartPage(1);
            stripper.setEndPage(1);
            stripper.getText(document);

            return stripper.getTextWithHighestFontSize();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


    private class NameFromResumeExtractor extends PDFTextStripper {
        public NameFromResumeExtractor() throws IOException {
            super();
        }
        String textWithHighestFontSize = "";
        float highestFontSize = 0;
        boolean reached = false ;

        @Override
        protected void processTextPosition(TextPosition text) {
            // Get font size of current text position
            float fontSize = text.getFontSize();

            // Compare font size with the highest font size
            if (fontSize > highestFontSize) {
                highestFontSize = fontSize;
                textWithHighestFontSize = text.getUnicode();
                reached = true;
            }else if(reached && fontSize==highestFontSize){
                textWithHighestFontSize+= text.toString();
            }else {
                reached = false ; 
            }

        }
        public String getTextWithHighestFontSize(){
            return this.textWithHighestFontSize;
        }
        
    }

}
