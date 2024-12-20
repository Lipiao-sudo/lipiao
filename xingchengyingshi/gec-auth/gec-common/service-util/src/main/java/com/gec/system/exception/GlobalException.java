package com.gec.system.exception;


import com.gec.system.util.Result;
import com.gec.system.util.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    //1.全局异常
    @ExceptionHandler(Exception.class)//只要出现了任何的异常，该方法都会被触发
    @ResponseBody
    public Result error(Exception e)
    {
        System.out.println("全局执行....");;
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理...");
    }


    //2.特定异常处理：只有当异常是ArithmeticException，该方法才会被触发，从而给出不同的提示信息
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e)
    {
        System.out.println("特定执行.");
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理..");
    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前操作权限");
    }


    //3.执行自定义异常
    @ExceptionHandler(MyCustomerException.class)
    @ResponseBody
    public Result  error(MyCustomerException e)
    {
        System.out.println("自定义异常执行.");
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

}
