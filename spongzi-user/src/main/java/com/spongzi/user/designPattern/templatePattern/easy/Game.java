package com.spongzi.user.designPattern.templatePattern.easy;

/**
 * 游戏
 *
 * @author spong
 * @date 2023/11/09
 */
public abstract class Game {

    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    // 模板
    public final void play() {
        // 游戏初始化
        initialize();

        // 开始游戏
        startPlay();

        // 游戏结束
        endPlay();
    }

}
