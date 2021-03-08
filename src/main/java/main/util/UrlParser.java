package main.util;

import java.net.MalformedURLException;

public class UrlParser {
    public String tracker;
    public int port;
    public String protocol;
    public String host;

    public UrlParser(String tracker) {
        this.tracker = tracker;
    }

    public void parse() {
        /**
         * ----------------example tracker -------------------
         *
         * udp://tracker.opentrackr.org:1337/announce
         */
        String[] array = tracker.split(":");
        this.protocol = array[0];
        this.host = array[1].substring(2);
        String subarr = array[2].split("/")[0];
        this.port = Integer.parseInt(subarr);
    }

    public static void main(String[] args) throws MalformedURLException {
        UrlParser urlParser = new UrlParser("udp://tracker.opentrackr.org:1337/announce");
        urlParser.parse();
        System.out.println(urlParser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "UrlParser [host=" + host + ", port=" + port + ", protocol=" + protocol + ", tracker=" + tracker + "]";
    }
}
