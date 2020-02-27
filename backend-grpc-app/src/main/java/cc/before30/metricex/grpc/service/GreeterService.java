package cc.before30.metricex.grpc.service;

import cc.before30.metricex.grpc.proto.GreeterGrpc;
import cc.before30.metricex.grpc.proto.GreeterOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

/**
 * GreeterService
 *
 * @author before30
 * @since 2020/02/27
 */

@GRpcService
@Slf4j
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(GreeterOuterClass.HelloRequest request, StreamObserver<GreeterOuterClass.HelloReply> responseObserver) {
        int query = request.getQuery();
        String message = String.format("Hello %s from backend-grpc-app", query);
        if (query % 100 != 10) {
            final GreeterOuterClass.HelloReply.Builder replyBuilder = GreeterOuterClass.HelloReply.newBuilder().setMessage(message);
            responseObserver.onNext(replyBuilder.build());
            responseObserver.onCompleted();
            log.info("return {}", message);
        } else {
            responseObserver.onError(new RuntimeException("Error query is " + query));
            log.error("return runtime exception cause query is {}", query);
        }
    }
}
