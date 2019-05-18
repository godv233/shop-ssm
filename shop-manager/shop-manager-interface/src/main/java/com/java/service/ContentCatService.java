package com.java.service;
/**
 * CMS内容展示的接口
 * @author GODV
 *
 */

import java.util.List;

import com.java.common.pojo.UITreeNode;
import com.java.common.util.E3Result;

public interface ContentCatService {
	List<UITreeNode> getContentCatList(long parentId);
	E3Result addContentCatgory(long id,String name);
	E3Result updateContentCatgory(long id,String name);
	E3Result deleteContentCatgroy(long id);
	
}
