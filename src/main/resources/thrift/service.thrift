namespace java com.zhouxinghang.study.thrift.tservice
include 'model.thrift'


service  HelloWorldService {
  string sayHello(1:string username);
  string getUser(1:model.User user);
}