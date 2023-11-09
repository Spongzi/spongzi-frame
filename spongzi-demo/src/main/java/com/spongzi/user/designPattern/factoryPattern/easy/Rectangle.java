package com.spongzi.user.designPattern.factoryPattern.easy;

/**
 * 长方形
 *
 * @author spong
 * @date 2023/11/09
 */
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
