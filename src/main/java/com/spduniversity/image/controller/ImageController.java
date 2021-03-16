package com.spduniversity.image.controller;

import com.spduniversity.image.ImageResource;
import com.spduniversity.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("images/ads/{adId}")
    @ResponseStatus(HttpStatus.OK)
    public void save(@PathVariable /*@ValidateAdId*/ long adId,
                     @RequestPart(value = "images") List<MultipartFile> images) {

        List<ImageResource> imageResources = toDomain(adId, images);

        imageService.saveAll(imageResources);
    }

    /* Validate if it is possible to add a new image */
    @PostMapping("ads/images/{adId}")
    public ImageResourceDto saveSingleImage(@PathVariable long adId,
                                            @RequestPart(name = "isPrimary") String isPrimary,
                                            @RequestPart(name = "positionOrder") String positionOrder,
                                            @RequestPart MultipartFile image) {

        ImageResource imageResource = imageService.save(ImageResource.of(adId, Boolean.getBoolean(isPrimary), Integer.parseInt(positionOrder), image));

        return ImageResourceDto.of(
                                    imageResource.getId(),
                                    imageResource.getAdId(),
                                    imageResource.getIsPrimary(),
                                    imageResource.getPositionOrder(),
                                    imageResource.getImageUrl()
        );
    }

    @GetMapping("images/ads/{adId}")
    public List<ImageResourceDto> getAllByAdId(@PathVariable long adId) {
        List<ImageResource> imageResources = imageService.getAllByAdId(adId);

        return imageResources
                .stream()
                .map(imgResource -> ImageResourceDto.of(imgResource.getId(),
                                                        imgResource.getAdId(),
                                                        imgResource.getIsPrimary(),
                                                        imgResource.getPositionOrder(),
                                                        imgResource.getImageUrl())
                )
                .collect(Collectors.toList());
    }

    @GetMapping("images/ads/{adId}/primary")
    public ResponseEntity<ImageResource> getPrimary(@PathVariable long adId) {
        Optional<ImageResource> imageResourceHolder = imageService.getPrimary(adId);

        if (imageResourceHolder.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ImageResource imageResource = imageResourceHolder.get();

        return new ResponseEntity(
                PrimaryImageResourceDto.of(
                        imageResource.getId(),
                        imageResource.getAdId(),
                        imageResource.getImageUrl()),
                HttpStatus.OK
        );
    }

//    @DeleteMapping("images/ads/{adId}")
//    public void deleteAllByAdId(@PathVariable /*@ValidateAdId*/ long adId) {
//
//    }

    @DeleteMapping("/images/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable long imageId) {
        imageService.delete(imageId);
    }

    private List<ImageResource> toDomain(long adId, List<MultipartFile> images) {
        List<ImageResource> imageResources = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            // Makes the first image be primary
            if (i == 0) {
                imageResources.add(ImageResource.of(adId, true, i + 1, image));
                continue;
            }
            imageResources.add(ImageResource.of(adId, false, i + 1, image));
        }

        return imageResources;
    }
}
