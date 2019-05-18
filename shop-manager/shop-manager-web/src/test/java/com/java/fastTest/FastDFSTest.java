package com.java.fastTest;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.java.common.util.FastDFSClient;

/**
 * 图片上传组件测试
 * 
 * @author GODV
 *
 */
public class FastDFSTest {
	@Test
	public void TestUpload() throws Exception {
		// 创建一个配置文件 内容是tracker服务器的地址
		// 使用全局对象加载配置文件(使用全限定名)
		ClientGlobal.init(
				"D:\\MyCode\\workspace\\SpringCode\\shop-manager\\shop-manager-web\\src\\main\\resources\\config\\client.conf");
		// 创建一个TrankerClient对象
		TrackerClient trackerClient = new TrackerClient();
		// 通过TrankerClient获得TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		// 创建一个strorageServer的引用，可以是null
		StorageServer storageServer = null;
		// 创建一个strorageClient，需要TrackerServer和strorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		// 使用strorageClient上传文件。
		String[] upload_file = storageClient.upload_file("G:\\image\\IMG_0363.JPG", "JPG", null);
		for (String string : upload_file) {
			System.out.println(string);
		}
	}
	@Test
	public void testUtil() throws Exception {
		FastDFSClient fastDFSClient=new FastDFSClient("D:\\MyCode\\workspace\\SpringCode\\shop-manager\\shop-manager-web\\src\\main\\resources\\config\\client.conf");
		String uploadFile = fastDFSClient.uploadFile("G:\\image\\内江六中高2016届3班同学分布图.jpg");
		System.out.println(uploadFile);
	}
}
