package by.retvizan.maper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CertificateLogin {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateLogin.class);

    private CertificateLogin() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Imports or removes certificate into/from win storage
     * appropriate scripts to run and certificates must be located at C:\certs folder of the execution machine
     *
     * @param certCommand values 'import' to import certificate or 'remove' to remove certificate from the storage
     */
    public static void processCert(String certCommand, String userId) {
        String line;
        String command = "remove".equals(certCommand) ? String.format("powershell.exe \"C:\\certs\\%s.ps1\" ",
                certCommand) : String.format("powershell.exe \"C:\\certs\\%1$s.ps1 %2$s.pfx\"", certCommand, userId);
        try {
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            powerShellProcess.getOutputStream().close();

            LOGGER.info("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                LOGGER.info(line);
            }
            stdout.close();
            LOGGER.info("Standard Error:");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                LOGGER.info(line);
            }
            stderr.close();
            LOGGER.info("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
