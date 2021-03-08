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

# Useful links
- ## [Bittorrent protocol](./Resources/bittorrentecon.pdf)
- ## [Bittorrent Specification](https://wiki.theory.org/BitTorrentSpecification)
- ## [Libtorrent](https://libtorrent.org/)
- ## [Udp tracker protocol](http://www.bittorrent.org/beps/bep_0015.html)
