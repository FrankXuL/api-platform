package com.project.springbootproject.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
