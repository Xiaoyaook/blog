package com.ziliang.blog.dao;


import com.ziliang.blog.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAllComment();

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
