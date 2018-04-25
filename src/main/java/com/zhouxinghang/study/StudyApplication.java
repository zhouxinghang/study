package com.zhouxinghang.study;

import com.google.common.annotations.VisibleForTesting;

import com.zhouxinghang.study.test.Main3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@ServletComponentScan
public class StudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);

	}


}
