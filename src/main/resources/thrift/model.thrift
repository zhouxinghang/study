namespace java com.zhouxinghang.study.thrift.tmodel


struct LoginReq {
    1: string name;
    2: string password;
}

struct User {
    1: string name;
    //2: optional string age;
}