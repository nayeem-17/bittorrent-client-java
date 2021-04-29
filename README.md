# Trying to make a bittorrent client 
# Workflow
- ## **Parsing the torrent file**
First, we have to parse a torrent file for metadata of the actual file. The torrent file is encoded with [Bencode](https://en.wikipedia.org/wiki/Bencode)  (pronounced like B-encode). After decoding the torrent file, we'll get these pieces of information.
- `info`: a dictionary that describes the file(s) of the torrent.   
    ### Info Dictionary
    This section contains the field which is common

    - `piece length`: number of bytes in each piece (integer)
    - `pieces`: string consisting of the concatenation of all 20-byte SHA1 hash values, one per piece (byte string)

    ### Info in Single File Mode

    - `name`: the filename. This is purely advisory. (string)
    - `length`: length of the file in bytes (integer)
    ### Info in Multiple File Mode

    - `name`: the name of the directory in which to store all the files (string)
    - `files`: a list of dictionaries, one for each file. Each dictionary in this list contains 
        - `length`: length of the file in bytes (integer)
        - `path`: a list containing one or more string elements that together represent the path and filename. Each element in the list corresponds to either a directory name or   (in the case of the final element) the filename. For example, a the file "dir1/dir2/  file.ext" would consist of three string elements: "dir1", "dir2", and "file.ext". This is encoded as a bencoded list of strings such as l4:dir14:dir28:file.exte

- `announce`: The announce URL of the tracker (string)
- `creation date:` (optional) the creation time of the torrent
- `comment`: (optional) free-form textual comments of the author (string)
- `created by`: (optional) name and version of the program used to create the .torrent (string)


- ## **Connecting with tracker**
We get the `tracker url` from `announce` in .torrent file. We can communicate with the tracker using this url. The basic structure of a tracker url is    
`PROTOCOL://TRACKER_HOST:TRACKER_PORT/announce`     
Here, the protocol can be `http` ,`https` or `udp`
Depending on the tracker protocol, we'll communicate with the tracker differently. 

- ### Connecting to udp tracker:
    Udp connection will establish with `TRACKER_HOST` and `TRACKER_PORT`
    - **Connection Request**    
        Request message contains    
        | Offset  |  Size        | Name        | Value       |
        |---------|--------------|-------------|-------------|
        | 0       |64-bit integer|connection id|0x41727101980 (magic constant )|
        |8|32-bit integer|action|0 (=connect)|
        |12|32-bit integer|transaction id|(randomly chosen)|

    - **Connection Response**   
    The response of the request message contains
        | Offset  |  Size        | Name        | Value       |
        |---------|--------------|-------------|-------------|
        | 0       |32-bit integer|action|0 (=connect)|
        |4|32-bit integer|transaction id|(chosen in request)|
        |8|64-bit integer|connection id|(chosen by tracker)|
    - **Announce request**  
    The connection response contains a new connection id which allows us to send to the tracker an announce request 
        | Offset  |  Size        | Name        | Value       |
        |---------|--------------|-------------|-------------|
        |0|64-bit integer|connection id|from connection response |
        |8|32-bit integer|action|1 (=announce)|
        |12|32-bit integer|transaction id|(randomly chosen)|
        |16|20-byte integer|info hash|(.torrent info hash)|
        |36|20-byte integer|peer id|(ID of the peer)|
        |56|64-bit integer|downloaded|(bytes downloaded)|
        |64|64-bit integer|left|(bytes left)|
        |72|64-bit integer|uploaded|(bytes uploaded)|
        |80|32-bit integer|event|0 (=default)|
        |84|32-bit integer|IP address|0 (=default)|
        |88|32-bit integer|key|(unique client key)|
        |92|32-bit integer|number wanted|1 (=default)|
        |96|16-bit integer|port|(port of the peer)|
    - **Announce response** 
        | Offset  |  Size        | Name        | Value       |
        |---------|--------------|-------------|-------------|
        0         |  32-bit integer | action |         1 // announce
        4         |  32-bit integer | transaction_id|(chosen in request)
        8         |  32-bit integer | interval| (waiting interval)
        12        |  32-bit integer | leechers | (number of leecher)
        16        |  32-bit integer | seeders |  (number of seeder)
        20 + 6 * n|  32-bit integer | IP address|(list of IP addresses)
        24 + 6 * n|  16-bit integer | TCP port|(List of port numbers)
- ### Connecting to http / https tracker:

# Useful links
- ## [Bittorrent protocol](./Resources/bittorrentecon.pdf)
- ## [Bittorrent Specification](https://wiki.theory.org/BitTorrentSpecification)
- ## [Libtorrent](https://libtorrent.org/)
- ## [Udp tracker protocol](http://www.bittorrent.org/beps/bep_0015.html)
