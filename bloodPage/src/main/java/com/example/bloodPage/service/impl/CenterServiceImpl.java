package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.Center;

import com.example.bloodPage.repository.CenterRepository;
import com.example.bloodPage.service.CenterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<Center> getCenterById(UUID id) {
        return centerRepository.findById(id);
    }

    @Override
    public Center updateCapacity(UUID id) {
        Optional<Center> center=centerRepository.findById(id);
        if(center.isPresent()){
            Center center1=center.get();
            center1.setCapacity(center1.getCapacity()-1);
            return centerRepository.save(center1);
        }
        else return null;

    }


}
