package com.java.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.java.common.util.FastDFSClient;

/**
 * 图片上传控制器
 * 
 * @author GODV
 *
 */
@Controller
public class PicController {
	@Value("${ImageServerUrl}")
	private String ImageServerUrl;

	@RequestMapping("/pic/upload")
	@ResponseBody // 返回一个指定的json数据 我们可以用map 或者 pojo实现
	public String uploadFile(MultipartFile uploadFile) {
		HashMap<Object, Object> resMap = new HashMap<>();
		// 图片上传到服务器
		try {
			FastDFSClient dfsClient = new FastDFSClient("classpath:config/client.conf");
			// 因为我们传上来的图片的信息全部都是在MultipartFile对象中，我们需要先处理一下取得一些信息
			// 取文件拓展名
			String originalFilename = uploadFile.getOriginalFilename();
			String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			// 得到图片的地址和文件名
			String urlString = dfsClient.uploadFile(uploadFile.getBytes(), substring);
			// 使用properties文件配置url，防止硬编码 可以使用多种选择读取properties
			// 补充完整的url
			urlString = ImageServerUrl + urlString;
			// 封装map。返回
			resMap.put("error", 0);
			resMap.put("url", urlString);

		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("error", 1);
			resMap.put("message", "上传失败，请重试");
		}
		return JSONUtils.toJSONString(resMap);//使用com.alibaba.druid.support.json.JSONUtils;中转化成json

	}
}
