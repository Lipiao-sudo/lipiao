package com.gec.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCustomerException extends RuntimeException {
    private Integer code;
    private String msg;






}