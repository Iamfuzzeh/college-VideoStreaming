module SD2 {

    sequence<string> List;

    struct Endpoint {
        string transport;
        string ip;
        int port;
    };

    struct StreamInfo {

       string name;
       Endpoint endp;
       string videosize;
       string bitrate;
       List keywords;

    };

    sequence<StreamInfo> StreamList;

    interface Portal{

        void registerStreamer(StreamInfo s);
        void closeStreamer(StreamInfo s);
        StreamList getStreamList();

    };
};