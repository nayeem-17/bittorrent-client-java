package main;

import torrent.Downloader;
import torrent.libs.TorrentFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Downloader downloader = new Downloader(
                "https://yts.mx//torrent//download//6BBBA12D4052E322E4A04F303581193DB957EAED");
        try {
            downloader.download();
            TorrentFile torrentFile = downloader.getTorrentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
