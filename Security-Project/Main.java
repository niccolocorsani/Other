import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.littleshoot.proxy.*;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;



import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

public class Main {

    public static void main(String[] args)  {


        try {
            HttpProxyServer server =
                    DefaultHttpProxyServer.bootstrap()
                            .withAddress(new InetSocketAddress("localhost", 8080))
                            .withTransparent(true)
                         //   .withManInTheMiddle(new CertificateSniffingMitmManager())
                            .withFiltersSource(new HttpFiltersSourceAdapter() {
                                @Override
                                public int getMaximumRequestBufferSizeInBytes() {
                                    return 1048576;

                                }

                                @Override
                                public int getMaximumResponseBufferSizeInBytes() {
                                    return 1048576;

                                }

                                public HttpFilters filterResponse(){
                                    return null;
                                }
                                public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {

                             /*       try {
                                        for (Map.Entry<String, String> entryMap : originalRequest.headers().entries())
                                            System.out.println("...stato................ \n\n"+originalRequest.headers().entries());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
*/

                                    return new HttpFiltersAdapter(originalRequest) {
                                        @Override
                                        public HttpResponse clientToProxyRequest(HttpObject httpObject) {

                                            FullHttpRequest request = (FullHttpRequest) httpObject;




                                            CompositeByteBuf contentBuf = (CompositeByteBuf) request.content();


                                            System.out.println("clientToProxyRequest ");
                                            System.err.println(request.getUri());

                                            for (Map.Entry<String, String> entryMap : request.headers().entries())
                                                System.err.println(LocalDateTime.now() + "    entry    " + entryMap.getKey() + "      " + entryMap.getValue());

                                            return null;
                                        }

                                        @Override
                                        public HttpResponse proxyToServerRequest(HttpObject httpObject) {

                                            FullHttpRequest request = (FullHttpRequest) httpObject;
                                            CompositeByteBuf contentBuf = (CompositeByteBuf) request.content();
                               /*             System.out.println("................... \n\n");
                                            System.out.println("................... \n\n");
                                            System.err.println("proxyToServer \n\n");
                                            System.out.println("Uri    "+request.getUri());*/



                                           // request.headers().set(HttpHeaders.Names.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36'");
                                           /* for (Map.Entry<String, String> entryMap : request.headers().entries())
                                                System.err.println(LocalDateTime.now() + "    entry    " + entryMap.getKey() + "      " + entryMap.getValue());
*/
                                            return null;
                                        }
                                        @Override
                                        public HttpObject serverToProxyResponse(HttpObject httpObject) {

                                            FullHttpRequest request = (FullHttpRequest) httpObject;
                                            CompositeByteBuf contentBuf = (CompositeByteBuf) request.content();
                                            /*System.out.println("................... \n\n");
                                            System.out.println("................... \n\n");
                                            System.out.println("serverToProxy");
                                            System.out.println("Uri    "+request.getUri());*/

                                            System.out.println("................... \n\n");
                                           /* try {
                                                FullHttpResponse response = (FullHttpResponse) httpObject;
                                                System.out.println("Stato...........::::"+response.headers().entries().get(0));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
*/
                                         /*   for (Map.Entry<String, String> entryMap : request.headers().entries())
                                                System.err.println(LocalDateTime.now() + "    entry    " + entryMap.getKey() + "      " + entryMap.getValue());
*/

                                            return httpObject;
                                        }



                                        @Override
                                        public HttpObject proxyToClientResponse(HttpObject httpObject) {


                                            FullHttpRequest request = (FullHttpRequest) httpObject;
                                          /*  System.out.println("................... \n\n");
                                            System.out.println("................... \n\n");
                                            System.err.println("proxyToClient \n\n");
                                            for (Map.Entry<String, String> entryMap : request.headers().entries())
                                                System.err.println(LocalDateTime.now() + "    entry    " + entryMap.getKey() + "      " + entryMap.getValue());
*/
                                            return httpObject;
                                        }
                                    };
                                }
                            })
                            .start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
