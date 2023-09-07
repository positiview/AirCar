package com.example.aircar.service;

import com.example.aircar.entity.Counseling;
import com.example.aircar.entity.Notices;
import com.example.aircar.repository.CounselingRepository;
import com.example.aircar.repository.NoticesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CounselingService {
    private CounselingRepository counselingRepository;

    public Page<Counseling> getCounselingList(Pageable pageable)
    {
        return counselingRepository.findAll(pageable);
    }

}
