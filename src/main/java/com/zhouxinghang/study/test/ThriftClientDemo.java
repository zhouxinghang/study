package com.zhouxinghang.study.test;


import com.zhouxinghang.thrift.service.HelloWorldService;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by zhouxinghang on 2018/4/9.
 */
public class ThriftClientDemo {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9001;
    private static final int TIME_OUT = 30000;

    private void startClient(String username) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIME_OUT);
            //协议与服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            transport.open();
            String result = client.sayHello(username);
            System.out.println("Thrift Client result : " + result);
        } catch (TTransportException e) {
            System.out.println("Thrift Client start error");
            e.printStackTrace();
        } catch (TException e) {
            System.out.println("RPC ERROR");
            e.printStackTrace();
        } finally {
            if (transport != null) {
                transport.close();
            }
        }
    }

    public static void main(String[] args) {
        ThriftClientDemo clientDemo = new ThriftClientDemo();
        clientDemo.startClient("zxh");
    }
}
