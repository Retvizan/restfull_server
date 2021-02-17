package by.retvizan.maper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CertificateController {
    @GetMapping("/import")
    public void importCert(@RequestParam(value = "id", defaultValue = " ") String id) {
        CertificateLogin.processCert("import", id);
    }

}
