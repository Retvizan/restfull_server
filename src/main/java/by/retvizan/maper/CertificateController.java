package by.retvizan.maper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CertificateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);

    @GetMapping("/import")
    public String importCert(@RequestParam(value = "id", defaultValue = " ") String id) {
        CertificateLogin.processCert("import", id);
        LOGGER.info("Certificate for user {} has been loaded", id);
        return String.format("Certificate of user %s has been loaded", id);
    }

    @GetMapping("/remove")
    public String removeCert() {
        CertificateLogin.processCert("remove", "all");
        LOGGER.info("All certificates have been removed");
        return "All certificates have been removed";
    }
}
