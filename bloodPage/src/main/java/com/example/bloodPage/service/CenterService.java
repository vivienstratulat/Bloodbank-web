package com.example.bloodPage.service;

import com.example.bloodPage.entity.Center;


import java.util.List;

public interface CenterService {
    List<Center> getCenters();
    Center addCenter(Center center);
}
