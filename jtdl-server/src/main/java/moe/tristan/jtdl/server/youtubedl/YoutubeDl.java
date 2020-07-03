package moe.tristan.jtdl.server.youtubedl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import moe.tristan.jtdl.server.system.require.RequireService;
import moe.tristan.jtdl.server.system.require.requirable.Requirables;

@Component
public class YoutubeDl {

    private static final Logger LOGGER = LoggerFactory.getLogger(YoutubeDl.class);

    public YoutubeDl(RequireService requireService) {
        requireService.require(Requirables.YOUTUBE_DL);
        requireService.require(Requirables.FFMPEG);
    }

    public void download(Invocation invocation) throws IOException, InterruptedException {
        LOGGER.info("Will download [{}] to [{}]", invocation.source(), invocation.destination());

        Path destination = createDirectoryIfNotExists(invocation.destination());

        String[] command = new String[]{Requirables.YOUTUBE_DL.binaryName(), invocation.source()};
        LOGGER.info("Executing: [{}]", String.join(" ", command));

        Process process = new ProcessBuilder()
            .command(command)
            .directory(destination.toFile())
            .inheritIO()
            .start();

        int exitCode = process.waitFor();

        LOGGER.info("Finished executing (exit code: {})", exitCode);
    }

    private Path createDirectoryIfNotExists(String destination) {
        try {
            Path folder = Paths.get(destination);
            return Files.createDirectories(folder);
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't create destination directory: " + destination, e);
        }
    }

}
