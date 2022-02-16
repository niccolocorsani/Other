import org.apache.http.HttpHost;
import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;


public class Main2 {

    public static void main(String[] args) throws UnknownHostException, PcapNativeException, NotOpenException, EOFException, TimeoutException {


        InetAddress addr = InetAddress.getByName("localhost");
        PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);
        int snapLen = 65536;
        PcapNetworkInterface.PromiscuousMode mode = PcapNetworkInterface.PromiscuousMode.PROMISCUOUS;
        int timeout = 10;
        PcapHandle handle = nif.openLive(snapLen, mode, timeout);
        Packet packet = handle.getNextPacketEx();
        System.out.println(packet.getPayload());
        handle.close();

    }
}
