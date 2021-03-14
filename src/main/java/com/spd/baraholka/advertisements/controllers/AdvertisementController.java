package com.spd.baraholka.advertisements.controllers;

import com.spd.baraholka.advertisements.exceptions.AdNotFoundException;
import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.services.AdvertisementDTO;
import com.spd.baraholka.advertisements.services.AdvertisementMapper;
import com.spd.baraholka.advertisements.services.AdvertisementService;
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

    public AdvertisementController(AdvertisementService advertisementService, AdvertisementMapper advertisementMapper) {
        this.advertisementService = advertisementService;
        this.advertisementMapper = advertisementMapper;
    }

    @GetMapping("/title-search")
    public List<AdvertisementDTO> getFilteredAdsByTitle(@RequestParam("title") String title) {
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByTitle(title);
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
    }

    @GetMapping("/description-search")
    public List<AdvertisementDTO> getFilteredAdsByDescription(@RequestParam("description") String description) {
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByDescription(description);
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
    }

    @GetMapping
    public List<AdvertisementDTO> getAllActiveAds() {
        List<Advertisement> advertisementList = advertisementService.getAllActive();
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
    }

    @PutMapping("/{id}/publish-delayed")
    public int editPublicationDate(@PathVariable("id") int id,
                                   @RequestParam("publicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String publicationDate) {
        Advertisement advertisement = advertisementService.findDraftAdById(id)
                .orElseThrow(() -> new AdNotFoundException(id));
        return advertisementService.editPublicationDate(advertisement, publicationDate);
    }

    @PutMapping("/{id}/cancel-delayed")
    public int cancelDelayedPublicationOfExistsAd(@PathVariable("id") int id) {
        Advertisement advertisement = advertisementService.findDraftAdById(id)
                .orElseThrow(() -> new AdNotFoundException(id));
        String presentDate = String.valueOf(LocalDateTime.now());
        return advertisementService.editPublicationDate(advertisement, presentDate);
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
