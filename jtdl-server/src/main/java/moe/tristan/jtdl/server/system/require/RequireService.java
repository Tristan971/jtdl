package moe.tristan.jtdl.server.system.require;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import moe.tristan.jtdl.server.system.require.requirable.Requirable;

/**
 * Helps ensure a binary that is prerequisited to be on the execution path is indeed accessible.
 */
@Service
public class RequireService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequireService.class);

    public void require(Requirable requirable) {
        LOGGER.info("Looking for prerequisite [{}]", requirable.binaryName());
        try {
            var process = which(requirable).start();

            // arbitrary "maximal time we are ok waiting for"
            process.waitFor(5, TimeUnit.SECONDS);

            process.getOutputStream();

            if (process.exitValue() == 0) {
                LOGGER.info("Found [{}]", requirable.binaryName());
            } else {
                throw new IllegalStateException("[" + requirable.binaryName() + "] not found.");
            }
        } catch (InterruptedException | IOException e) {
            throw new IllegalStateException("Could not perform lookup of prerequisite [" + requirable.binaryName() + "].", e);
        }
    }

    private ProcessBuilder which(Requirable requirable) {
        return new ProcessBuilder()
            .command("which", requirable.binaryName())
            .inheritIO();
    }

}
