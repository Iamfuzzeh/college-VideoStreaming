import SD2.StreamInfo;

import java.util.HashMap;

/**
 * Created by iamfuzzeh on 11/21/16.
 */
public class Portal {

    public static HashMap<StreamInfo, String> streamerList = new HashMap<>();

    public static void main(String[] args) {


        int status = 0;
        Ice.Communicator ic = null;
        try {
            ic = Ice.Util.initialize(args);

            Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("PortalAdapter", "default -p 10000");
            Ice.Object object = new PortalI();
            adapter.add(object, ic.stringToIdentity("Portal"));
            adapter.activate();
            ic.waitForShutdown();
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }

    public static void registerStreamer(StreamInfo streaminfo, String name) {
        streamerList.put(streaminfo, name);
        System.out.println(name + "@" + streaminfo.endp.ip + ":" + streaminfo.endp.port + " added to map. current streamers: " + streamerList.size());
    }

    public static void closeStreamer(StreamInfo streaminfo) {
        if (streamerList.containsKey(streaminfo)) {
            streamerList.remove(streaminfo);
            System.out.println(streaminfo.name + " has been removed from stream list. current streamers: " + streamerList.size());
        } else {
            System.out.println("attempted to remove " + streaminfo.name + " but it was not found.");
        }
    }

    public static StreamInfo[] getStreamList() {
        StreamInfo[] streamarray = streamerList.keySet().toArray(new StreamInfo[streamerList.size()]);
        return streamarray;
    }

}
