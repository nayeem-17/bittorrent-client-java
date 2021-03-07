package torrent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import torrent.libs.TorrentFile;

public class Downloader {

    private final String FILE_NAME = "tmp.torrent";
    private String url;
    private TorrentFile torrentFile;

    public Downloader(String url) {
        this.url = url;
    }

    public void download() throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File rawFile = new File(FILE_NAME);
        torrentFile = new TorrentFile(rawFile);
        rawFile.delete();
    }

    public TorrentFile getTorrentFile() {
        return torrentFile;
    }
}
