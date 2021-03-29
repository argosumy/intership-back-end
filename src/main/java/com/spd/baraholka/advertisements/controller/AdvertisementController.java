package com.spd.baraholka.advertisements.controller;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.service.*;
import com.spd.baraholka.pagination.entities.PageRequest;
import com.spd.baraholka.pagination.services.PageRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final AdvertisementMapper advertisementMapper;
    private final AdvertisementUserEmailMapper advertisementUserEmailMapper;
    private final PageRequestService pageRequestService;

    public AdvertisementController(AdvertisementService advertisementService,
                                   AdvertisementMapper advertisementMapper,
                                   AdvertisementUserEmailMapper advertisementUserEmailMapper,
                                   PageRequestService pageRequestService) {
        this.advertisementService = advertisementService;
        this.advertisementMapper = advertisementMapper;
        this.advertisementUserEmailMapper = advertisementUserEmailMapper;
        this.pageRequestService = pageRequestService;
    }

    @GetMapping("/search")
    public List<AdvertisementDTO> getFilteredAdsByTitle(@RequestParam("keyword") String keyword,
                                                        @RequestParam(value = "size", required = false) Integer size) {
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword(keyword, size);
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
    }

    @GetMapping
    public PageRequest<AdvertisementUserEmailDTO> getAllActiveAds(@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                                  @RequestParam("pageNumber") int pageNumber) {
        PageRequest<Advertisement> pageRequest = pageRequestService.getPageRequest(pageSize, pageNumber);
        return pageRequest.map(advertisementUserEmailMapper::getAdvertisementUserEmailDto);
    }

    @PutMapping("/{id}/publish-delayed")
    public int editPublicationDate(@PathVariable("id") int id,
                                   @RequestParam("publicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String publicationDate) {
        return advertisementService.editPublicationDate(id, publicationDate);
    }

    @PutMapping("/{id}/cancel-delayed")
    public int cancelDelayedPublicationOfExistsAd(@PathVariable("id") int id) {
        String presentDate = String.valueOf(LocalDateTime.now());
        return advertisementService.editPublicationDate(id, presentDate);
    }

    @PostMapping
    public int saveAdvertisement(@RequestBody @Valid AdvertisementDTO advertisementDTO) {
        return advertisementService.saveAdvertisement(advertisementDTO);
    }

    @PutMapping
    public int updateAdvertisement(@RequestBody @Valid AdvertisementDTO advertisementDTO) {
        return advertisementService.updateAdvertisement(advertisementDTO);
    }

    @PutMapping("/{id}/{status}")
    public int updateAdvertisementStatus(@PathVariable int id, @PathVariable AdvertisementStatus status) {
        return advertisementService.updateAdvertisementStatus(id, status);
    }
}
