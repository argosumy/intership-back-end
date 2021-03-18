package com.spd.baraholka.pagination.mappers;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.services.AdvertisementUserEmailDTO;
import com.spd.baraholka.advertisements.services.AdvertisementUserEmailMapper;
import com.spd.baraholka.pagination.dto.PageRequestDto;
import com.spd.baraholka.pagination.entities.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageRequestMapper {

    private final AdvertisementUserEmailMapper advertisementUserEmailMapper;

    public PageRequestMapper(AdvertisementUserEmailMapper advertisementUserEmailMapper) {
        this.advertisementUserEmailMapper = advertisementUserEmailMapper;
    }

    public PageRequestDto<AdvertisementUserEmailDTO> getPageRequestDto(PageRequest<Advertisement> pageRequest) {
        PageRequestDto<AdvertisementUserEmailDTO> pageRequestDto = new PageRequestDto<>();
        pageRequestDto.setPageNumber(pageRequest.getPageNumber());
        pageRequestDto.setPageSize(pageRequest.getPageSize());
        pageRequestDto.setTotalPages(pageRequest.getTotalPages());
        pageRequestDto.setContent(getDtoContent(pageRequest));
        return pageRequestDto;
    }

    private List<AdvertisementUserEmailDTO> getDtoContent(PageRequest<Advertisement> pageRequest) {
        return advertisementUserEmailMapper.toAdvertisementUserEmailDtoList(pageRequest.getContent());
    }
}
