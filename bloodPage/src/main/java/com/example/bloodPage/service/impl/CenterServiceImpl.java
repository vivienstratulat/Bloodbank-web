package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.Center;

import com.example.bloodPage.repository.CenterRepository;
import com.example.bloodPage.service.CenterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;

    public CenterServiceImpl(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @Override
    public List<Center> getCenters() {
        List<Center> locations=centerRepository.findAll();
        return locations;
    }

    @Override
    public Center addCenter(Center center) {
        return centerRepository.save(center);
    }
}
