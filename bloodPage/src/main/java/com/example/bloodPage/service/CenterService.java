package com.example.bloodPage.service;

import com.example.bloodPage.entity.Center;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CenterService {
    List<Center> getCenters();
    Center addCenter(Center center);
    Optional<Center> getCenterById(UUID id);
    Center updateCapacity(UUID id);
}
