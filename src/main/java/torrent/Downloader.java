package torrent;

import com.dampcake.bencode.BencodeInputStream;
import com.dampcake.bencode.Type;
import torrent.libs.TorrentFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
