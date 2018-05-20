package com.zhouxinghang.study.guava;

import com.google.common.base.CaseFormat;

/**
 * Created by zhouxinghang on 2018/5/18.
 *
 格式	范例
 LOWER_CAMEL	lowerCamel  驼峰
 LOWER_HYPHEN	lower-hyphen    连词符
 LOWER_UNDERSCORE	lower_underscore    下划线
 UPPER_CAMEL	UpperCamel
 UPPER_UNDERSCORE	UPPER_UNDERSCORE
 */
public class CaseFormatDemo {

    public static void main(String[] args) {
        //从大写下划线格式 ---》 小写驼峰
        String formatStr = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        System.out.println(formatStr);
    }
}
