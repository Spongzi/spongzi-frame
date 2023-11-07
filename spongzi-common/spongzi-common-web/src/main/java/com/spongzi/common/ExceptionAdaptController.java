package com.spongzi.common;

import com.spongzi.bean.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常适配控制器
 *
 * @author spong
 * @date 2023/11/07
 */
@RestControllerAdvice
public class ExceptionAdaptController {

    @ExceptionHandler({RuntimeException.class})
    public Result<String> runTimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.fail("运行时异常");
    }

    @ExceptionHandler({Exception.class})
    public Result<String> runTimeException(Exception e) {
        e.printStackTrace();
        return Result.fail("系统异常");
    }

}
