package com.ziliang.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 带题图信息的文章基础信息分装类
 */
public class ArticleWithPictureDto {
    // article_info基础字段
    private Long id;                    // ArticleInfo表主键
    private String title;               // 文章标题
    private String summary;             // 文章简介
    private Boolean isTop;              // 文章是否置顶
    private Integer traffic;            // 文章阅读量
    private Date createBy;              // 文章创建时间

    // category_info基础字段
    private Long categoryId;            // 分类ID
    private String categoryName;        // 分类名称

    // article_picture基础字段
    private Long articlePictureId;      // ArticlePicture主键
    private String pictureUrl;          // 文章题图URL


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public Long getArticlePictureId() {
        return articlePictureId;
    }

    public void setArticlePictureId(Long articlePictureId) {
        this.articlePictureId = articlePictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "ArticleWithPictureDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", isTop=" + isTop +
                ", traffic=" + traffic +
                ", createBy=" + createBy +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", articlePictureId=" + articlePictureId +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
