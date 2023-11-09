package com.spongzi.user.designPattern.templatePattern.prod;

import com.spongzi.bean.Result;
import com.spongzi.bean.ResultCode;

public class ApiTemplate {

    public void execute(Result<Object> result,final Api api) {
        try {
            api.validate();
            api.execute();
            api.after();
            result.setCode(ResultCode.SUCCESS);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setCode(ResultCode.ERROR);
            result.setSuccess(false);
        }
    }

}
