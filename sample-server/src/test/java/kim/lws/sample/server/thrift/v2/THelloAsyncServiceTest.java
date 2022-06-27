package kim.lws.sample.server.thrift.v2;

import com.linecorp.armeria.client.Clients;
import com.linecorp.armeria.common.thrift.ThriftFuture;
import kim.lws.sample.generated.thrift.v2.HelloRequest;
import kim.lws.sample.generated.thrift.v2.HelloResponse;
import kim.lws.sample.generated.thrift.v2.HelloService;
import org.apache.thrift.TException;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class THelloAsyncServiceTest {

    @org.junit.jupiter.api.Test
    void helloAsync() throws ExecutionException, InterruptedException, TException {
        HelloRequest req = new HelloRequest();
        req.setVer((short)1);
        req.setName("hello");
        req.setFamily("world");

        HelloService.AsyncIface helloService = Clients.newClient(
                uri(),
                HelloService.AsyncIface.class);

        ThriftFuture<HelloResponse> future = new ThriftFuture<>();
        helloService.hello(req, future);
        HelloResponse response = future.get();
        assert response.getMessage().equals("hello");
    }

    private static String uri() {
        return "tbinary+http://127.0.0.1:9999/hello";
    }
}