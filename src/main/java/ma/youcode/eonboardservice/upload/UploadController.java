package ma.youcode.eonboardservice.upload;


import ma.youcode.eonboardservice.extract.LoadingResumeException;
import ma.youcode.eonboardservice.processor.ResumeProcessor;
import ma.youcode.eonboardservice.processor.UploadReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    ResumeProcessor resumeProcessor;

    @Autowired
    public UploadController(ResumeProcessor resumeProcessor) {
        this.resumeProcessor = resumeProcessor;
    }



    @PostMapping
    public ResponseEntity<UploadReport> uploadFiles(@RequestParam("files") MultipartFile[] files) throws  LoadingResumeException {
        return ResponseEntity.ok(resumeProcessor.processResumes(files));
    }
}
