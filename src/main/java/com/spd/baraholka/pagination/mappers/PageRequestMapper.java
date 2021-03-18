package com.spd.baraholka.pagination.mappers;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.pagination.dto.PageRequestDto;
import com.spd.baraholka.pagination.entities.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PageRequestMapper {

    public PageRequestDto<Advertisement> getPageRequestDto(PageRequest<Advertisement> pageRequest) {
        PageRequestDto<Advertisement> pageRequestDto = new PageRequestDto<>();
        pageRequestDto.setPageNumber(pageRequest.getPageNumber());
        pageRequestDto.setPageSize(pageRequest.getPageSize());
        pageRequestDto.setTotalPages(pageRequest.getTotalPages());
        pageRequestDto.setContent(pageRequest.getContent());
        return pageRequestDto;
    }
}
