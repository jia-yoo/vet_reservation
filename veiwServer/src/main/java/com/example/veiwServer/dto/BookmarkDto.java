package com.example.veiwServer.dto;

import lombok.Data;


public interface BookmarkDto  {

 
   Long getId();
     Long getHospital_id();
     Long getUser_id();
    
     String getHospital_name();
     String getAddress();
     String getPhone();
    
    //private LocalDateTime created_at;
    //private LocalDateTime updated_at;
    
   
   
    
}
