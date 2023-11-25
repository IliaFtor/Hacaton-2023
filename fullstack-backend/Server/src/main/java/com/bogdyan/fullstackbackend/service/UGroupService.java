package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.UGroup;
import com.bogdyan.fullstackbackend.repository.UGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UGroupService {
    private final UGroupRepository uGroupRepository;

    @Autowired
    public UGroupService(UGroupRepository uGroupRepository){
        this.uGroupRepository = uGroupRepository;
    }

    public List<UGroup> getAll(){
        return uGroupRepository.findAll();
    }

    public UGroup createUGroup(String name){
        UGroup uGroup = new UGroup(name);
        uGroupRepository.save(uGroup);
        return uGroup;
    }

}
