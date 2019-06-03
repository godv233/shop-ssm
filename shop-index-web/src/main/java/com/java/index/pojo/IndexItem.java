package com.java.index.pojo;

import java.util.Date;

import com.java.pojo.TbItem;

public class IndexItem extends TbItem {
	private Long id;

	private String title;

	private String sellPoint;

	private Long price;

	private Integer num;

	private String barcode;

	private String image;

	private Long cid;

	private Byte status;

	private Date created;

	private Date updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint == null ? null : sellPoint.trim();
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode == null ? null : barcode.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	// 返回数据库中多张图片
	public String[] getImages() {
		String image2 = this.getImage();
		if (image2 != null && !"".equals(image2)) {// 不为空
			String[] images = image2.split(",");
			return images;
		}
		return null;
	}

	public IndexItem(TbItem item) {
		this.barcode = item.getBarcode();
		this.cid = item.getCid();
		this.created = item.getCreated();
		this.id = item.getId();
		this.image = item.getImage();
		this.num = item.getNum();
		this.price = item.getPrice();
		this.sellPoint = item.getSellPoint();
		this.status = item.getStatus();
		this.title = item.getTitle();
		this.updated = item.getUpdated();
	}
}
