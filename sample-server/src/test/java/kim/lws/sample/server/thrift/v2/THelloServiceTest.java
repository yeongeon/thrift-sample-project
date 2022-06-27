package kim.lws.sample.server.thrift.v2;

import com.linecorp.armeria.client.Clients;
import com.linecorp.armeria.common.thrift.ThriftFuture;
import kim.lws.sample.generated.thrift.v2.HelloRequest;
import kim.lws.sample.generated.thrift.v2.HelloResponse;
import kim.lws.sample.generated.thrift.v2.HelloService;
import org.apache.thrift.TException;

import java.util.concurrent.ExecutionException;

class THelloServiceTest {

    @org.junit.jupiter.api.Test
    void hello() throws ExecutionException, InterruptedException, TException {
        HelloRequest req = new HelloRequest();
        req.setVer((short)1);
        req.setName("hello");
        req.setFamily("world");

        HelloService.Iface helloService = Clients.newClient(
                uri(),
                HelloService.Iface.class); // or AsyncIface.class

        HelloResponse response = helloService.hello(req);
        assert response.getMessage().equals("hello");
    }


    private static String uri() {
        return "tbinary+http://127.0.0.1:9999/hello";
    }
}