package com.spongzi.user.designPattern.templatePattern.easy;

/**
 * 足球
 *
 * @author spong
 * @date 2023/11/09
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("准备踢球");
    }

    @Override
    void startPlay() {
        System.out.println("开始踢球");
    }

    @Override
    void endPlay() {
        System.out.println("比赛结束");
    }
}
