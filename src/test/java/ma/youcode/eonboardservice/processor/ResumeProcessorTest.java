package ma.youcode.eonboardservice.processor;



import ma.youcode.eonboardservice.analyse.ResumeContentAnalyserImpl;
import ma.youcode.eonboardservice.extract.IPdfTextExtractorService;
import ma.youcode.eonboardservice.upload.IStorageService;
import ma.youcode.eonboardservice.upload.Upload;
import ma.youcode.eonboardservice.upload.UploadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ResumeProcessorTest {

    @Mock
    private IStorageService storageService;

    @Mock
    private IPdfTextExtractorService extractorService;

    @Mock
    private ResumeContentAnalyserImpl resumeContentAnalyser;

    @Mock
    private UploadRepository uploadRepository;

    @InjectMocks
    private ResumeProcessor resumeProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessResumes_Success() throws Exception {
        var path = Path.of("/upload-dir");
        MultipartFile mockFile = new MockMultipartFile("test.pdf", new byte[]{});
        when(storageService.store(mockFile)).thenReturn("path/to/file");
        when(storageService.getUploadDistination()).thenReturn(path);
        when(extractorService.extractTextFromPdf(anyString())).thenReturn(Map.of("name", "Mounir"));

        UploadReport result = resumeProcessor.processResumes(new MultipartFile[]{mockFile});

        verify(storageService, times(1)).store(mockFile);
        verify(extractorService, times(1)).extractTextFromPdf("path/to/file");
        verify(resumeContentAnalyser, times(1)).analyse(Map.of("name", "Mounir"));
        verify(uploadRepository, times(1)).save(any(Upload.class));

        assertEquals(1,result.uploadedFiles().size());

    }

    @Test
    public void testProcessResumes_StorageServiceFails() throws Exception {
        MultipartFile mockFile = new MockMultipartFile("test.pdf", new byte[]{});
        when(storageService.store(mockFile)).thenThrow(new RuntimeException("Storage error"));

        UploadReport result = resumeProcessor.processResumes(new MultipartFile[]{mockFile});

        verify(storageService, times(1)).store(mockFile);
        verifyNoInteractions(extractorService);
        verifyNoInteractions(resumeContentAnalyser);
        verifyNoInteractions(uploadRepository);
    }

}
