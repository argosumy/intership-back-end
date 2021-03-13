package com.spd.baraholka.advertisements.controllers;

import com.spd.baraholka.advertisements.persistance.Advertisement;
import com.spd.baraholka.advertisements.persistance.AdvertisementStatus;
import com.spd.baraholka.advertisements.services.AdvertisementDTO;
import com.spd.baraholka.advertisements.services.AdvertisementMapper;
import com.spd.baraholka.advertisements.services.AdvertisementService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{title}")
    public List<AdvertisementDTO> getFilteredAdsByTitle(@PathVariable("title") String title) {
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByTitle(title);
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
    }

    @GetMapping("/{description}")
    public List<AdvertisementDTO> getFilteredAdsByDescription(@PathVariable("description") String description) {
        List<Advertisement> advertisementList = advertisementService.getFilteredAdsByDescription(description);
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
    }

    @GetMapping
    public List<AdvertisementDTO> getAllActive() {
        List<Advertisement> advertisementList = advertisementService.getAllActive();
        return advertisementMapper.toAdvertisementDtoList(advertisementList);
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
