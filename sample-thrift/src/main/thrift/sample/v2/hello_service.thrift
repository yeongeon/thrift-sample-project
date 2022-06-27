namespace java kim.lws.sample.generated.thrift.v2
namespace go generated.thrift.sample.v2

struct HelloRequest {
  1: required i16 ver;
  2: required string name;
  3: required string family;
}

struct HelloResponse {
  1: required i16 ver;
  2: required string message;
}

service HelloService {
    HelloResponse hello(1:HelloRequest request)
    HelloResponse lazyHello (1:HelloRequest request)
    HelloResponse blockingHello (1:HelloRequest request)
}