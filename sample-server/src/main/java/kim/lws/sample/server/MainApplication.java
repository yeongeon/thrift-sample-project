package kim.lws.sample.server;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.thrift.ThriftSerializationFormats;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.thrift.THttpService;
import kim.lws.sample.server.thrift.v2.THelloAsyncService;
import kim.lws.sample.server.thrift.v2.THelloService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class MainApplication {
    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args){
        ServerBuilder sb = Server.builder();
        sb.http(9999);

        THttpService thriftService =
                THttpService.builder()
                        .addService(new THelloAsyncService())
                        .otherSerializationFormats(ThriftSerializationFormats.BINARY)
                        .build();

        // Add a simple 'Hello, world!' service.
        sb.service("/", (ctx, req) -> HttpResponse.of("Hello, world!"));
        sb.service("/hello", thriftService);

        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join();
        logger.info("ok server");
    }
}
