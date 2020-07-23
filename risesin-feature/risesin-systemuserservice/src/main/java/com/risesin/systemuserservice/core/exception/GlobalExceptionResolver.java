package com.risesin.systemuserservice.core.exception;

import com.risesin.core.tool.api.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-21
 * @DESCRIPTION 异常处理
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionResolver {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R exception(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<Map<String, Object>> errorMsgs = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError) objectError;
            map.put(fieldError.getField(), objectError.getDefaultMessage());
        });
        errorMsgs.add(map);
        return R.data(400, errorMsgs, "参数校验失败");
    }

    //处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return R.data(400, message, "参数校验失败");
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseBody
    public R ConstraintViolationExceptionHandler(javax.validation.ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return R.data(400, message, "参数校验失败");
    }

}
