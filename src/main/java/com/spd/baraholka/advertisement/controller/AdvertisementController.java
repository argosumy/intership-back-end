package com.spd.baraholka.advertisement.controller;

import com.spd.baraholka.advertisement.controller.dto.*;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementMapper;
import com.spd.baraholka.advertisement.controller.mappers.AdvertisementUserEmailMapper;
import com.spd.baraholka.advertisement.persistance.entities.Advertisement;
import com.spd.baraholka.advertisement.persistance.entities.AdvertisementStatus;
import com.spd.baraholka.advertisement.service.AdvertisementService;
import com.spd.baraholka.annotation.advertisement.AdvertisementExist;
import com.spd.baraholka.annotation.advertisement.ChangedStatus;
import com.spd.baraholka.characteristic.persistance.entities.Category;
import com.spd.baraholka.pagination.entities.PageRequest;
import com.spd.baraholka.pagination.services.PageRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int saveAdvertisement(@RequestBody @Valid InitialAdvertisementDTO advertisementDTO) {
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

    @SuppressWarnings("checkstyle:parameternumber")
    @GetMapping("/search")
    public PageRequest<AdvertisementDTO> getFilteredAdsByTitle(@RequestParam("keyword") String keyword,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                               @RequestParam(value = "sort", required = false, defaultValue = "recent") String sortOrder,
                                                               @RequestParam(value = "category", required = false) Category category,
                                                               @RequestParam(value = "city", required = false) String city,
                                                               @RequestParam(value = "min_price", required = false) Double minPrice,
                                                               @RequestParam(value = "max_price", required = false) Double maxPrice,
                                                               @RequestParam(value = "characteristics", required = false) Map<String, String> characteristics,
                                                               @RequestParam("pageNumber") int pageNumber) {
        FilterDTO filterDTO = initFilterDTO(category, city, minPrice, maxPrice, characteristics);
        List<Advertisement> filteredAdvertisements = advertisementService.getPublishedFilteredAds(filterDTO);
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByKeyword(filteredAdvertisements, keyword);
        List<Advertisement> sortedAdvertisementList = advertisementService.sort(advertisementList, sortOrder);
        PageRequest<Advertisement> pageRequest = pageRequestService.getPageRequest(pageSize, pageNumber, sortedAdvertisementList);
        return pageRequest.map(advertisementMapper::getAdvertisementDto);
    }

    @SuppressWarnings("checkstyle:parameternumber")
    @GetMapping
    public PageRequest<AdvertisementUserEmailDTO> getAllActiveAds(@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                                  @RequestParam(value = "sort", required = false, defaultValue = "recent") String sortOrder,
                                                                  @RequestParam(value = "category", required = false) Category category,
                                                                  @RequestParam(value = "city", required = false) String city,
                                                                  @RequestParam(value = "min_price", required = false) Double minPrice,
                                                                  @RequestParam(value = "max_price", required = false) Double maxPrice,
                                                                  @RequestParam(value = "characteristic_names", required = false) String[] characteristicNames,
                                                                  @RequestParam(value = "characteristic_values", required = false) String[] characteristicValues,
                                                                  @RequestParam("pageNumber") int pageNumber) {
        Map<String, String> characteristics = initCharacteristics(characteristicNames, characteristicValues);
        FilterDTO filterDTO = initFilterDTO(category, city, minPrice, maxPrice, characteristics);
        List<Advertisement> advertisementList = advertisementService.getPublishedFilteredAds(filterDTO);
        List<Advertisement> sortedAdvertisementList = advertisementService.sort(advertisementList, sortOrder);
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

    private FilterDTO initFilterDTO(Category category, String city, Double minPrice, Double maxPrice, Map<String, String> characteristics) {
        FilterDTO filterDTO = new FilterDTO();
        if (category != null) {
            filterDTO.setCategory(category);
        }
        filterDTO.setCity(city);
        filterDTO.setMinPrice(minPrice);
        filterDTO.setMaxPrice(maxPrice);
        filterDTO.setCharacteristics(characteristics);
        return filterDTO;
    }

    private Map<String, String> initCharacteristics(String[] characteristicNames, String[] characteristicValues) {
        Map<String, String> characteristics = new HashMap<>();
        if ((characteristicNames != null) && (characteristicValues != null)) {
            for (int i = 0; i < characteristicNames.length; i++) {
                characteristics.put(characteristicNames[i], characteristicValues[i]);
            }
        } else {
            characteristics = Collections.emptyMap();
        }
        return characteristics;
    }
}
