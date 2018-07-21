package com.ziliang.blog.result;

/**
 * 结果信息类
 *
 */
public class CodeMsg {
    private int code;
    private String msg;

    //文章操作信息
    public static CodeMsg ADD_ARTICLE_SUCCESS = new CodeMsg(0, "文章添加成功");
    public static CodeMsg DELETE_ARTICLE_SUCCESS = new CodeMsg(0, "文章删除成功");
    public static CodeMsg UPDATE_ARTICLE_SUCCESS = new CodeMsg(0, "文章更新成功");
    public static CodeMsg UPDATE_ARTICLE_CATEGORY_SUCCESS = new CodeMsg(0, "改变文章分类成功");
    public static CodeMsg UPDATE_ARTICLE_PICTURE_SUCCESS = new CodeMsg(0, "改变文章题图成功");

    //分类操作信息
    public static CodeMsg ADD_CATEGORY_SUCCESS = new CodeMsg(0, "分类添加成功");
    public static CodeMsg DELETE_CATEGORY_SUCCESS = new CodeMsg(0, "分类删除成功");
    public static CodeMsg UPDATE_CATEGORY_SUCCESS = new CodeMsg(0, "分类更新成功");

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
