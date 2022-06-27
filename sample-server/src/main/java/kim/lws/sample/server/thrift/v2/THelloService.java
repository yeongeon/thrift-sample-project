package kim.lws.sample.server.thrift.v2;

import com.linecorp.armeria.server.ServiceRequestContext;
import kim.lws.sample.generated.thrift.v2.HelloRequest;
import kim.lws.sample.generated.thrift.v2.HelloResponse;
import kim.lws.sample.generated.thrift.v2.HelloService;
import org.apache.thrift.TException;

public class THelloService implements HelloService.Iface {

    @Override
    public HelloResponse hello(HelloRequest request) throws TException {
        HelloResponse res = new HelloResponse();
        res.setVer((short) 2);
        res.setMessage(request.getName());

        // Make sure that current thread is request context aware
        ServiceRequestContext.current();

        return res;
    }

    @Override
    public HelloResponse lazyHello(HelloRequest request) throws TException {
        return null;
    }

    @Override
    public HelloResponse blockingHello(HelloRequest request) throws TException {
        return null;
    }
}
