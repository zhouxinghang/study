package com.zhouxinghang.study.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.io.ByteSink;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by zhouxinghang on 2018/5/21.
 * http://ifeve.com/google-guava-io/
 * guava抽象了源与汇的概念，源是可读的，汇是可写的
 *
 字节	字符
 读	ByteSource	CharSource
 写	ByteSink	CharSink
 *
 */
public class IODemo {

    public static void main(String[] args) throws IOException {
        demo();
        System.out.println("========source======");
        source();
        sink();
    }

    public static void demo() throws IOException {
        File file = new File("/Users/zhouxinghang/workspace/myworkspace/study/.gitignore");
        // 读取文件，按行 utf-8
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        for(String line : lines) {
            System.out.println(line);
        }
        System.out.println("=====");

        // 计算文件中不同单次出现次数
        Multiset<String> wordOccurrences = HashMultiset.create(
            Splitter.on(CharMatcher.WHITESPACE)
                .trimResults()
                .omitEmptyStrings()
                .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        System.out.println(wordOccurrences);
        System.out.println("=====");

        // 从url中拷贝内容到文件
        Resources.asByteSource(new URL("https://www.baidu.com/s?ie=UTF-8&wd=guava")).copyTo(Files.asByteSink(new File("data.html")));
    }

    /**
     * 源操作

     字节源	字符源
     byte[]   read()	String   read()
     N/A	ImmutableList<String>   readLines()
     N/A	String   readFirstLine()
     long   copyTo(ByteSink)	long   copyTo(CharSink)
     long   copyTo(OutputStream)	long   copyTo(Appendable)
     long   size() (in bytes)	N/A
     boolean   isEmpty()	boolean   isEmpty()
     boolean   contentEquals(ByteSource)	N/A
     HashCode   hash(HashFunction)	N/A
     */
    public static void source() throws IOException {
        File file = new File("data.txt");
        CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);
        // 读取全部
        String result = charSource.read();
        System.out.println(result);
        System.out.println("=====");

        // 逐行读取
        ImmutableList<String> lines = charSource.readLines();
        for(String line : lines) {
            System.out.println(line);
        }
        System.out.println("======");

        // 读取第一行
        String firstLine = charSource.readFirstLine();
        System.out.println(firstLine);

        // copy到汇对象中  通过文末追加的形式
        CharSink charSink = Files.asCharSink(new File("back.txt"), Charsets.UTF_8, FileWriteMode.APPEND);
        charSource.copyTo(charSink);

        System.out.println(charSource.isEmpty());
    }

    /**
     * 汇操作

     字节汇	字符汇
     void write(byte[])	void write(CharSequence)
     long writeFrom(InputStream)	long writeFrom(Readable)
     N/A	void writeLines(Iterable<? extends CharSequence>)
     N/A	void writeLines(Iterable<? extends CharSequence>, String)
     */
    public static void sink() throws IOException {
        //创建字节汇，utf-8是字符编码
        ByteSink byteSink = Files.asByteSink(new File("write.txt"));
        byteSink.write(new byte[]{'g','u','a','v','a'});
    }
}
