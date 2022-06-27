package kim.lws.sample.server.thrift.v2;

import com.linecorp.armeria.server.ServiceRequestContext;
import kim.lws.sample.generated.thrift.v2.HelloRequest;
import kim.lws.sample.generated.thrift.v2.HelloResponse;
import kim.lws.sample.generated.thrift.v2.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.TimeUnit;

public class THelloAsyncService implements HelloService.AsyncIface {
    @Override
    public void hello(HelloRequest request, AsyncMethodCallback<HelloResponse> resultHandler) throws TException {
        HelloResponse res = new HelloResponse();
        res.setVer((short) 2);
        res.setMessage(request.getName());

        // Make sure that current thread is request context aware
        ServiceRequestContext.current();
        resultHandler.onComplete(res);
    }

    @Override
    public void lazyHello(HelloRequest request, AsyncMethodCallback<HelloResponse> resultHandler) throws TException {
        HelloResponse res = new HelloResponse();
        res.setVer((short) 2);
        res.setMessage(request.getName());

        // You can use the event loop for scheduling a task.
        ServiceRequestContext.current().eventLoop().schedule(() -> {
            resultHandler.onComplete(res);
        }, 3, TimeUnit.SECONDS);
    }

    @Override
    public void blockingHello(HelloRequest request, AsyncMethodCallback<HelloResponse> resultHandler) throws TException {
        HelloResponse res = new HelloResponse();
        res.setVer((short) 2);
        res.setMessage(request.getName());

        ServiceRequestContext.current().blockingTaskExecutor().execute(() -> {
            try {
                // Simulate a blocking API call.
                Thread.sleep(3000);
            } catch (Exception ignored) {
                // Do nothing.
            }
            resultHandler.onComplete(res);
        });
    }
}
