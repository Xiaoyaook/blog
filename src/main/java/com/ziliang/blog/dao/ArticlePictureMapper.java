package com.ziliang.blog.dao;


import com.ziliang.blog.entity.ArticlePicture;

import java.util.List;

public interface ArticlePictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticlePicture record);

    int insertSelective(ArticlePicture record);


    ArticlePicture selectByPrimaryKey(Long id);

    ArticlePicture selectByArticleId(Long articleId);

    int updateByPrimaryKeySelective(ArticlePicture record);

    int updateByPrimaryKey(ArticlePicture record);
}
