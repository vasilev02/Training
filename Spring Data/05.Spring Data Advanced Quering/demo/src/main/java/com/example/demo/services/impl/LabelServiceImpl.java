package com.example.demo.services.impl;

import com.example.demo.repositories.LabelRepository;
import com.example.demo.services.LabelService;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }
}
