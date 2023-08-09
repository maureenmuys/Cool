package com.mmuys.cool.dto;

import com.mmuys.cool.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

      private String email;
      private String password;

      private List<Role> roles;

}
