package com.spongzi.user.designPattern.factoryPattern.easy;

/**
 * 圆
 *
 * @author spong
 * @date 2023/11/09
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
