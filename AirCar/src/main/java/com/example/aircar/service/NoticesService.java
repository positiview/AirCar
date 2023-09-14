package com.example.aircar.service;

import com.example.aircar.domain.NoticesDTO;
import com.example.aircar.entity.Notices;
import com.example.aircar.repository.NoticesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.traversal.NodeIterator;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class NoticesService {

    @Autowired
    private NoticesRepository noticesRepository;

    public Page<Notices> getNoticesList(Pageable pageable)
    {
        return noticesRepository.findAll(pageable);
    }


    public Page<Notices> getNoticesTitleList(String keyword, Pageable pageable){
        return noticesRepository.getByNotices_titleLike(keyword, pageable);
    }

    public Page<Notices> getNoticesContentList(String keyword, Pageable pageable) {
        return noticesRepository.getByNotices_contentLike(keyword, pageable);
    }

    public Page<Notices> getNoticesTitleList1(String keyword, Pageable pageable) {
        return noticesRepository.findByNotices_categoryContaining(keyword, pageable);
    }

}