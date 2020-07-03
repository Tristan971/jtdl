package moe.tristan.jtdl.server.api;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moe.tristan.jtdl.server.api.model.DownloadCommand;
import moe.tristan.jtdl.server.youtubedl.Invocation;
import moe.tristan.jtdl.server.youtubedl.YoutubeDl;

@RestController
@RequestMapping("/api/ytdl")
public class YoutubeDlController {

    private final YoutubeDl youtubeDl;

    public YoutubeDlController(YoutubeDl youtubeDl) {
        this.youtubeDl = youtubeDl;
    }

    @PostMapping("/download")
    public void download(@RequestBody DownloadCommand downloadCommand) throws IOException, InterruptedException {
        youtubeDl.download(new Invocation(downloadCommand.getSource(), downloadCommand.getDestination()));
    }

}
