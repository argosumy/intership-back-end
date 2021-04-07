package com.spd.baraholka.pagination.services;

import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.exceptions.NotFoundException;
import com.spd.baraholka.pagination.entities.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PageRequestService {

    private final AdvertisementService advertisementService;

    public PageRequestService(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    public PageRequest<Advertisement> getPageRequest(int pageSize, int pageNumber, List<Advertisement> advertisementList) {
        int size = advertisementList.size();
        int offset = getOffset(pageSize, pageNumber);
        int adLastIndex = checkedLastIndex(getLastIndex(pageSize, offset), size);
        int totalPages = getTotalPages(pageSize, size);
        validatePageNumber(pageNumber, totalPages);
        List<Advertisement> content = getContentList(advertisementList, offset, adLastIndex);

        return PageRequest.of(pageNumber, pageSize, totalPages, content);
    }

    private int getTotalPages(double pageSize, int size) {
        return (int) Math.ceil(size / pageSize);
    }

    private List<Advertisement> getContentList(List<Advertisement> advertisements, int adStartIndex, int adLastIndex) {
        return IntStream.range(adStartIndex, adLastIndex)
                .mapToObj(advertisements::get)
                .collect(Collectors.toList());
    }

    private int getOffset(int pageSize, int pageNumber) {
        return pageNumber == 1 ? 0 : (pageNumber * pageSize) - pageSize;
    }

    private int getLastIndex(int pageSize, int offset) {
        return offset + pageSize;
    }

    private int checkedLastIndex(int lastIndex, int size) {
        return Math.min(lastIndex, size);
    }

    private void validatePageNumber(int pageNumber, int totalPages) {
        if (pageNumber > totalPages || pageNumber == 0) {
            throw new NotFoundException();
        }
    }
}
