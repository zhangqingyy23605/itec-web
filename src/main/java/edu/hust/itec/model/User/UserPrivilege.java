package edu.hust.itec.model.User;

/**
 * Created by xsh on 2015/5/15.
 */
public enum UserPrivilege {
    ROOT, EDITOR, SELF
    //0: 最高权限, 1: 能添加新闻并修改自己添加的新闻, 2: 普通用户，登陆只能填写个人信息
}
