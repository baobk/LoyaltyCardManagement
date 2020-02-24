package com.neo.project.Dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private T innerData;
}
