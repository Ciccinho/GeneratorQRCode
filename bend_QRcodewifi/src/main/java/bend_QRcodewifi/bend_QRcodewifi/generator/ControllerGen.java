package bend_QRcodewifi.bend_QRcodewifi.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin (origins = "http://localohost:4200", allowCredentials = "true")
public class ControllerGen {
    

    @Autowired
    private ServiceGen servive;


    @PostMapping("/generateQRC")
    public ResponseEntity<Object> generatorQRC(@RequestBody CredenzRequest request ) throws Exception {
        
        try {
            HttpHeaders head = new HttpHeaders();
            head.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<Object>(getImageResponse(request), head, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getClass(), HttpStatus.CONFLICT);
        }
    }
    
    private ResponseEntity<Object> getImageResponse ( CredenzRequest request) throws Exception{
    
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().headers(header).body(servive.generateQRCode(request));
    }
}