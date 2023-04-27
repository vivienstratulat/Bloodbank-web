package com.example.bloodPage.entity;

import jakarta.persistence.*;
import com.example.bloodPage.entity.Center;

@Entity(name = "Doctor")
@Table(name = "app_doctor")
public class Doctor extends User{

  // @OneToOne
    //@JoinColumn(name = "center_id")
   //public Center center;

  //  public Center getCenter() {
      //  return center;
 //   }

   // public void setCenter(Center center) {
    //    this.center = center;
 //   }
    String center;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Doctor() {
    }

    public Doctor(String email,String password,String firstName,String lastName,String userType) {
        super(email,password,firstName,lastName,userType);
    }

}
