package com.spd.baraholka.advertisement.controller;

import com.spd.baraholka.advertisement.controller.dto.*;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementUserEmailMapper;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.annotation.advertisement.AdvertisementExist;
import com.spd.baraholka.annotation.advertisement.ChangedStatus;
import com.spd.baraholka.pagination.entities.PageRequest;
import com.spd.baraholka.pagination.services.PageRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/advertisement")
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

    @PostMapping
    public int saveAdvertisement(@RequestBody InitialAdvertisementDTO advertisementDTO) {
        return advertisementService.saveAdvertisement(advertisementDTO);
    }

    @PutMapping
    public int updateAdvertisement(@RequestBody @Valid EditedAdvertisementDTO advertisementDTO) {
        return advertisementService.updateAdvertisement(advertisementDTO);
    }

    @PutMapping("/{id}")
    public int updateAdvertisementStatus(@PathVariable int id, @RequestParam("status") @ChangedStatus AdvertisementStatus status) {
        return advertisementService.updateAdvertisementStatus(id, status);
    }

    @GetMapping("/search")
    public PageRequest<AdvertisementDTO> getFilteredAdsByTitle(@RequestParam("keyword") String keyword,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "recent") String sortOrder,
                                                               @RequestBody FilterDTO filterDTO,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                               @RequestParam("pageNumber") int pageNumber) {
        List<Advertisement> filteredAdvertisements = advertisementService.getPublishedFilteredAds(filterDTO);
        List<Advertisement> advertisementsFoundByKeyword = advertisementService.getFilteredAdsByKeyword(filteredAdvertisements, keyword);
        List<Advertisement> sortedAdvertisements = advertisementService.sort(advertisementsFoundByKeyword, sortOrder);
        PageRequest<Advertisement> pageRequest = pageRequestService.getPageRequest(pageSize, pageNumber, sortedAdvertisements);
        return pageRequest.map(advertisementMapper::getAdvertisementDto);
    }

    @GetMapping
    public PageRequest<AdvertisementUserEmailDTO> getAllActiveAds(@RequestParam(value = "sort", required = false, defaultValue = "recent") String sortOrder,
                                                                  @RequestBody FilterDTO filterDTO,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                                  @RequestParam("pageNumber") int pageNumber) {
        List<Advertisement> filteredAdvertisements = advertisementService.getPublishedFilteredAds(filterDTO);
        List<Advertisement> sortedAdvertisementList = advertisementService.sort(filteredAdvertisements, sortOrder);
        PageRequest<Advertisement> pageRequest = pageRequestService.getPageRequest(pageSize, pageNumber, sortedAdvertisementList);
        return pageRequest.map(advertisementUserEmailMapper::getAdvertisementUserEmailDto);
    }

    @GetMapping("/profile")
    public PageRequest<AdvertisementUserEmailDTO> getAllAdsByType(@RequestParam(value = "type", required = true, defaultValue = "my") String type,
                                                                  @RequestParam(value = "sort", required = false, defaultValue = "recent") String sortOrder,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                                  @RequestParam("pageNumber") int pageNumber) {
        List<Advertisement> filteredAdvertisements = advertisementService.getAdvertisementsByType(type);
        List<Advertisement> sortedAdvertisementList = advertisementService.sort(filteredAdvertisements, sortOrder);
        PageRequest<Advertisement> pageRequest = pageRequestService.getPageRequest(pageSize, pageNumber, sortedAdvertisementList);
        return pageRequest.map(advertisementUserEmailMapper::getAdvertisementUserEmailDto);
    }

    @PutMapping("/{id}/publish-delayed")
    public int editPublicationDate(@PathVariable("id") int id,
                                   @RequestParam("publicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String publicationDate) {
        return advertisementService.editPublicationDate(id, publicationDate);
    }

    @PutMapping("/{id}/cancel-delayed")
    public int cancelDelayedPublicationOfExistsAd(@PathVariable("id") int id) {
        return advertisementService.cancelDelayedPublication(id);
    }

    @GetMapping("/{id}")
    public FullAdvertisementDTO getAdvertisement(@PathVariable("id") @AdvertisementExist int id) {
        return advertisementService.getAdvertisementById(id);
    }

    @PutMapping("/{id}/promotion")
    public void promotionAd(@PathVariable(value = "id") int adId) {
        advertisementService.promotionAd(adId);
    }
}
