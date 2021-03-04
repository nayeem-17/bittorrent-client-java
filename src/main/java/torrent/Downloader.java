package torrent;

import com.dampcake.bencode.BencodeInputStream;
import com.dampcake.bencode.Type;
import torrent.libs.TorrentFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class Downloader {

    private final String FILE_NAME="tmp.torrent";
    private String url;
    public Downloader(String url){
        this.url=url;

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
            // handle exception
            e.printStackTrace();
        }

        File rawFile = new File(FILE_NAME);
        TorrentFile torrentFile=new TorrentFile(rawFile);
        System.out.println(torrentFile.getNumPieces());



        /*String input = torrentString;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        BencodeInputStream bencode = new BencodeInputStream(in);

        Type type = bencode.nextType(); // Returns Type.DICTIONARY
        Map<String, Object> dict = bencode.readDictionary();

        System.out.println(dict);*/
    }
}
