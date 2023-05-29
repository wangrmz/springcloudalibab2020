package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wrmeng
 * @create 2023-05-21 -13:37
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private  Long id;
    private  String serial;


}
