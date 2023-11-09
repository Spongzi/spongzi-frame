package com.spongzi.user.designPattern.templatePattern.easy;

/**
 * 蓝球
 *
 * @author spong
 * @date 2023/11/09
 */
public class Basketball extends Game{
    @Override
    void initialize() {
        System.out.println("准备打篮球");
    }

    @Override
    void startPlay() {
        System.out.println("开始打篮球");
    }

    @Override
    void endPlay() {
        System.out.println("篮球游戏结束");
    }
}
