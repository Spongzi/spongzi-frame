package com.spongzi.user.designPattern.templatePattern.prod;

import com.spongzi.bean.Result;

public class ApiTemplateDemo {
    public static void main(String[] args) {
        ApiTemplate apiTemplate = new ApiTemplate();
        Result<Object> result = Result.ok();
        apiTemplate.execute(result, new Api() {
            @Override
            public void validate() {
                System.out.println("参数校验");
            }

            @Override
            public void execute() {
                System.out.println("执行程序");
            }

            @Override
            public void after() {
                System.out.println("程序执行完毕");
            }
        });
    }
}
