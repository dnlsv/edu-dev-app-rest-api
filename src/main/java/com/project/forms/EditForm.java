package com.project.forms;

import lombok.Data;

@Data
public class EditForm {

  private String oldLogin;

  private String firstName;

  private String lastName;

  private int age;

  private String newLogin;
}
