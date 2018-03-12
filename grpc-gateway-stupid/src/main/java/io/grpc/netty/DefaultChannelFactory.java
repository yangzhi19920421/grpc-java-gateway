package io.grpc.netty;

import com.friddle.IChannelFactory;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

/**
 *
 * usage pool to do
 * 这里只做简单Gateway吧
 *
 * Created by talkweb on 2018/3/2.
 */
public class DefaultChannelFactory implements IChannelFactory {

    public  static ManagedChannel channel;
    public int port;
    public String address;

    private DefaultChannelFactory(String address, int port){this.port=port;this.address=address;}

    @Override
    public ManagedChannel GetChannel(MethodDescriptor method, Metadata headers) {
        if(channel!=null){return channel;}
        channel=NettyChannelBuilder.
                forAddress(address,port).usePlaintext(true).build();
        return channel;
    }

    @Override
    public void FeedChannelStatus(ManagedChannel channel, Status status) {

    }

    public static IChannelFactory Default(String address, int port)
    {
        return new DefaultChannelFactory(address,port);
    }
}
