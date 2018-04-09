package com.zhouxinghang.study.test;

import com.zhouxinghang.study.thrift.tservice.HelloWorldService;
import com.zhouxinghang.study.tservice.HelloWorldServiceImpl;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by zhouxinghang on 2018/4/9.
 */
public class ThriftServerDemo {
    private static final int SERVER_PORT = 9001;

    private  void startServer() {
        try {
            System.out.println("HelloWorld server start .....");
            TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
            //简单的单线程模式
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (TTransportException e) {
            System.out.println("Server start error......");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThriftServerDemo serverDemo = new ThriftServerDemo();
        serverDemo.startServer();
    }

}
