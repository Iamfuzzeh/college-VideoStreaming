import Ice.Current;
import SD2.StreamInfo;

/**
 * Created by iamfuzzeh on 12/29/16.
 */
public class PortalI extends SD2._PortalDisp {

    @Override
    public void registerStreamer(StreamInfo s, Current __current) {
        Portal.registerStreamer(s, s.name);
    }

    @Override
    public void closeStreamer(StreamInfo s, Current __current) {
        Portal.closeStreamer(s);
    }

    @Override
    public StreamInfo[] getStreamList(Current __current) {
        return Portal.getStreamList();
    }

}
