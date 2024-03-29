package com.java.service;
/**
 * 内容管理接口
 * 网站内容服务接口
 * @author GODV
 *
 */

import java.util.List;

import com.java.common.pojo.UIDataResult;
import com.java.common.util.E3Result;
import com.java.pojo.TbContent;

public interface ContentService {
	UIDataResult getContentList(long categoryId, int page, int rows);
	E3Result addContent(TbContent content);
	List<TbContent> getContentListByCid(long cid);
	
}
