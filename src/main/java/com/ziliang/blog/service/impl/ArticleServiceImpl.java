package com.ziliang.blog.service.impl;


import com.ziliang.blog.dao.*;
import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.dto.ArticleWithPictureDto;
import com.ziliang.blog.entity.*;
import com.ziliang.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 文章Service实现类
 * 说明：ArticleInfo里面封装了picture/content/category等信息
 *
 * 使用事务，保证出现数据异常时进行回滚，防止各个表数据不一致
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;
    @Autowired
    ArticlePictureMapper articlePictureMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;
    @Autowired
    CategoryInfoMapper categoryInfoMapper;

    private static byte MAX_LASTEST_ARTICLE_COUNT = 5;

    /**
     * 增加一篇文章信息
     * 说明：需要对应的写入article_picture/article_content/article_category表
     * 注意：使用的是Article封装类
     *
     * @param articleDto 文章封装类
     */
    @Override
    public void addArticle(ArticleDto articleDto) {
        // 增加文章信息表 - title/summary
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfoMapper.insertSelective(articleInfo);
        // 获取刚才插入文章ArticleWithPictureDto信息的ID
        // Long articleId = getArticleLastestId();
        Long articleId = articleInfo.getId();
        // 增加文章题图信息 - pictureUrl/articleId
        ArticlePicture articlePicture = new ArticlePicture();
        articlePicture.setPictureUrl(articleDto.getPictureUrl());
        articlePicture.setArticleId(articleId);
        articlePictureMapper.insertSelective(articlePicture);
        // 增加文章内容信息表 - content/articleId
        ArticleContent articleContent = new ArticleContent();
        articleContent.setContent(articleDto.getContent());
        articleContent.setArticleId(articleId);
        articleContentMapper.insertSelective(articleContent);
        // 增加文章分类信息表 - articleId/categoryId
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleId);
        articleCategory.setCategoryId(articleDto.getCategoryId());
        articleCategoryMapper.insertSelective(articleCategory);
        // 对应文章的数量要加1
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    /**
     * 删除一篇文章
     * 说明：需要对应删除article_picture/article_content/article_category表中的内容
     *
     * @param id
     */
    @Override
    public void deleteArticleById(Long id) {
        // 获取对应的文章信息
        ArticleDto articleDto = getOneById(id);
        // 删除文章信息中的数据
        articleInfoMapper.deleteByPrimaryKey(articleDto.getId());
        // 删除文章题图信息数据
        articlePictureMapper.deleteByPrimaryKey(articleDto.getArticlePictureId());
        // 删除文章内容信息表
        articleContentMapper.deleteByPrimaryKey(articleDto.getArticleContentId());
        // 删除文章分类信息表
        articleCategoryMapper.deleteByPrimaryKey(articleDto.getArticleCategoryId());
    }

    /**
     * 更改文章的分类信息
     *
     * @param articleId
     * @param categoryId
     */
    @Override
    public void updateArticleCategory(Long articleId, Long categoryId) {
        // 文章和分类我们现在看作一一对应的关系，这里用文章id获取文章分类信息
        ArticleCategory articleCategory = articleCategoryMapper.selectArticleCategoryByArticleId(articleId);
        // 对应改变分类下的文章数目
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() - 1));
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        categoryInfo = categoryInfoMapper.selectByPrimaryKey(categoryId);
        categoryInfo.setNumber((byte) (categoryInfo.getNumber() + 1));
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        // 更新article_category表字段
        articleCategory.setCategoryId(categoryId);
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    /**
     * 更新文章信息
     * 说明：需要对应更改tbl_article_picture/tbl_article_content/tbl_article_category表中的内容
     * 注意：我们使用的是封装好的Article文章信息类
     *
     * @param articleDto 自己封装的Article信息类
     */
    @Override
    public void updateArticle(ArticleDto articleDto) {
        // 更新文章信息中的数据
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(articleDto.getId());
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setIsTop(articleDto.getTop());
        articleInfo.setTraffic(articleDto.getTraffic());
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
        // 更新文章题图信息数据
        ArticlePicture articlePicture = articlePictureMapper.selectByPrimaryKey(articleDto.getArticlePictureId());
        articlePicture.setPictureUrl(articleDto.getPictureUrl());
        articlePictureMapper.updateByPrimaryKeySelective(articlePicture);
        // 更新文章内容信息数据
        ArticleContent articleContent = articleContentMapper.selectByPrimaryKey(articleDto.getArticleContentId());
        articleContent.setContent(articleDto.getContent());
        articleContentMapper.updateByPrimaryKeySelective(articleContent);
        // 更新文章分类信息表
        ArticleCategory articleCategory = articleCategoryMapper.selectByPrimaryKey(articleDto.getArticleCategoryId());
        articleCategory.setCategoryId(articleDto.getCategoryId());
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    /**
     * 获取一篇文章内容
     * 说明：需要对应从article_picture/article_content/article_category表中获取内容
     * 并封装好
     *
     * @param id 文章ID
     * @return 填充好数据的ArticleInfo
     */
    @Override
    public ArticleDto getOneById(Long id) {
        ArticleDto articleDto = new ArticleDto();
        // 填充文章基础信息
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        articleDto.setId(articleInfo.getId());
        articleDto.setTitle(articleInfo.getTitle());
        articleDto.setSummary(articleInfo.getSummary());
        articleDto.setTop(articleInfo.getIsTop());
        articleDto.setCreateBy(articleInfo.getCreateBy());
        // 文章访问量要加1
        articleInfo.setTraffic(articleInfo.getTraffic() + 1);
        articleDto.setTraffic(articleInfo.getTraffic() + 1);
        articleInfoMapper.updateByPrimaryKey(articleInfo);
        // 填充文章内容信息
        ArticleContent articleContent = articleContentMapper.selectByArticleId(id);
        articleDto.setContent(articleContent.getContent());
        articleDto.setArticleContentId(articleContent.getId());
        // 填充文章题图信息
        ArticlePicture articlePicture = articlePictureMapper.selectByArticleId(id);
        articleDto.setPictureUrl(articlePicture.getPictureUrl());
        articleDto.setArticlePictureId(articlePicture.getId());
        // 填充文章分类信息
        ArticleCategory articleCategory = articleCategoryMapper.selectArticleCategoryByArticleId(id);
        articleDto.setArticleCategoryId(articleCategory.getId());
        // 填充文章分类基础信息
        // 从ArticleCategory中取得CategoryInfo的主键
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());
        articleDto.setCategoryId(categoryInfo.getId());
        articleDto.setCategoryName(categoryInfo.getName());
        articleDto.setCategoryNumber(categoryInfo.getNumber());
        return articleDto;
    }

    /**
     * 获取所有的文章内容
     *
     * @return 封装好的Article集合
     */
    @Override
    public List<ArticleWithPictureDto> listAll() {
        // 1.先获取所有的数据
        List<ArticleWithPictureDto> articles = listAllArticleWithPicture();
        // 2.然后再对集合进行重排，置顶的文章在前
        LinkedList<ArticleWithPictureDto> list = new LinkedList<>();
        for (ArticleWithPictureDto article: articles) {
            if (article.getTop()) {
                list.addFirst(article);
            } else {
                list.addLast(article);
            }
        }
        articles = new ArrayList<>(list);

        return articles;
    }

    /**
     * 通过分类id返回该分类下的所有文章
     *
     * @param id 分类ID
     * @return 对应分类ID下的所有文章(带题图)
     */
    @Override
    public List<ArticleWithPictureDto> listByCategoryId(Long id) {
        // 由categoryId取到所有此分类下的文章
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectAllArticleCategoryByCategoryId(id);
        List<ArticleWithPictureDto> articles = new ArrayList<>();
        for (int i = 0; i < articleCategories.size(); i++) {
            Long articleId = articleCategories.get(i).getArticleId();
            ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();
            // 填充文章基础信息
            ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleId);
            articleWithPictureDto.setId(articleInfo.getId());
            articleWithPictureDto.setTitle(articleInfo.getTitle());
            articleWithPictureDto.setSummary(articleInfo.getSummary());
            articleWithPictureDto.setTop(articleInfo.getIsTop());
            articleWithPictureDto.setTraffic(articleInfo.getTraffic());
            articleWithPictureDto.setCreateBy(articleInfo.getCreateBy());
            // 填充文章分类信息
            CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(id);
            articleWithPictureDto.setCategoryId(id);
            articleWithPictureDto.setCategoryName(categoryInfo.getName());
            // 填充文章图片信息
            ArticlePicture articlePicture = articlePictureMapper.selectByArticleId(articleId);
            articleWithPictureDto.setArticlePictureId(articlePicture.getId());
            articleWithPictureDto.setPictureUrl(articlePicture.getPictureUrl());
            articles.add(articleWithPictureDto);
        }


        // 对集合进行重排，置顶的文章在前
        LinkedList<ArticleWithPictureDto> list = new LinkedList<>();
        for (ArticleWithPictureDto article: articles) {
            if (article.getTop()) {
                list.addFirst(article);
            } else {
                list.addLast(article);
            }
        }
        articles = new ArrayList<>(list);

        return articles;
    }

    /**
     * 获取最新的文章信息，默认为5篇
     * 说明：listAll默认已经按照id排序了，所以我们只需要返回前五篇就可以了
     * 注意：需要判断当前的文章是否大于5篇
     *
     * @return 返回五篇最新的文章数据
     */
    @Override
    public List<ArticleWithPictureDto> listLastest() {
        // 1.先获取所有的数据
        List<ArticleWithPictureDto> articles = listAllArticleWithPicture();
        // 2.判断是否满足5个的条件
        if (articles.size() >= MAX_LASTEST_ARTICLE_COUNT) {
            // 3.大于5个则返回前五个数据
            List<ArticleWithPictureDto> tempArticles = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                tempArticles.add(articles.get(i));
            }
            return tempArticles;
        }
        // 4.不大于五个则直接返回
        return articles;
    }

    /**
     * 通过文章ID获取对应的文章题图信息
     *
     * @param id 文章ID
     * @return 文章ID对应的文章题图信息
     */
    @Override
    public ArticlePicture getPictureByArticleId(Long id) {
        return articlePictureMapper.selectByArticleId(id);
    }

    /**
     * 获取所有的文章信息（带题图）
     *
     * @return
     */
    private List<ArticleWithPictureDto> listAllArticleWithPicture() {
        // 无添加查询即返回所有
        List<ArticleInfo> articleInfos = articleInfoMapper.selectAllArticleInfo();
        List<ArticleWithPictureDto> articles = new ArrayList<>();
        for (ArticleInfo articleInfo : articleInfos) {
            ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();
            // 填充文章基础信息
            articleWithPictureDto.setId(articleInfo.getId());
            articleWithPictureDto.setTitle(articleInfo.getTitle());
            articleWithPictureDto.setSummary(articleInfo.getSummary());
            articleWithPictureDto.setTop(articleInfo.getIsTop());
            articleWithPictureDto.setTraffic(articleInfo.getTraffic());
            articleWithPictureDto.setCreateBy(articleInfo.getCreateBy());
            // 填充文章分类信息
            ArticleCategory articleCategory = articleCategoryMapper.selectArticleCategoryByArticleId(articleInfo.getId());
            CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(articleCategory.getCategoryId());

            articleWithPictureDto.setCategoryId(categoryInfo.getId());
            articleWithPictureDto.setCategoryName(categoryInfo.getName());
            // 填充文章题图信息
            ArticlePicture articlePicture = articlePictureMapper.selectByArticleId(articleInfo.getId());
            articleWithPictureDto.setArticlePictureId(articlePicture.getId());
            articleWithPictureDto.setPictureUrl(articlePicture.getPictureUrl());
            articles.add(articleWithPictureDto);
        }
        return articles;
    }

}
