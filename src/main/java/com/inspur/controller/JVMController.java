package com.inspur.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: YANG
 * Date: 2019/7/10-11:07
 * Description: No Description
 */

@Controller
public class JVMController {

	private static final Lock firstLock = new ReentrantLock();
	private static final Lock secondLock = new ReentrantLock();

	/**
	 * 通过top 命令来看CPU的参数
	 */
	@RequestMapping("/jvm-cpu")
	public void testCPU(){
		while(true) {

		}
	}

	@RequestMapping("/jvm-mem")
	public void testMem() throws InterruptedException {
		List<byte[]> list = new ArrayList<byte[]>();
		while (true){
			byte[] bytes = new byte[1024 * 1024];
			list.add(bytes);
			Thread.sleep(500);
		}
	}

	@RequestMapping("/jvm-io-write")
	public void testIOWrite() throws Exception {
		String writeText = "Hello Write Message Hello Write Message " +
				           "Hello Write Message Hello Write Message " +
				           "Hello Write Message Hello Write Message " +
				           "Hello Write Message Hello Write Message " +
				           "Hello Write Message Hello Write Message ";
		byte[] buffer = writeText.getBytes();
		while(true){
			File file = new File("/tmp/" + System.currentTimeMillis() + ".txt");
			if(!file.exists()) {
				file.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(buffer);
				fileOutputStream.flush();
			}
		}
	}

	@RequestMapping("/jvm-io-read")
	public void testIORead() throws Exception {
		while(true){
			File file = new File("/tmp/testIORead.txt");
			byte[] buffer = new byte[1024];
			if(!file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				fileInputStream.read(buffer);
			}
		}
	}

	@ResponseBody
	@RequestMapping("/jvm-getGC")
	public String testGC() {
		StringBuffer buffer = new StringBuffer();
		List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
		for(GarbageCollectorMXBean bean : garbageCollectorMXBeans){
			buffer.append("----->");
			buffer.append(bean.getName() + "," + bean.getObjectName());
			buffer.append("\r\n");
		}

		return buffer.toString();
	}


	@RequestMapping("/jvm-lock")
	public void testJVMLock(){
		Thread firstThread = new Thread(() -> {
			synchronized (firstLock) {
				System.out.println("enter monitor first lock entry");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (secondLock) {
					System.out.println("enter monitor second lock entry");
				}
			}
		});

		Thread secondThread = new Thread(() -> {
			synchronized (secondLock) {
				System.out.println("enter monitor first lock entry");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (firstLock) {
					System.out.println("enter monitor second lock entry");
				}
			}
		});

		firstThread.start();
		secondThread.start();
	}

}
