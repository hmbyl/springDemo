package org.springDemo.common.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class student  implements Serializable {
    private int id;
    private String username;
    private String password;

    private Integer Age;

}
