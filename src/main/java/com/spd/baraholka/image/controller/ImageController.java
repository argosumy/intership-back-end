package com.spd.baraholka.image.controller;

import com.spd.baraholka.image.ImageResource;
import com.spd.baraholka.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("ads/{adId}/images")
    @ResponseStatus(HttpStatus.OK)
    public void saveImages(@PathVariable long adId,
                           @RequestPart(value = "images") List<MultipartFile> images) {

        List<ImageResource> imageResources = toDomain(adId, images);

        imageService.saveAll(imageResources);
    }

    @PostMapping("ads/{adId}/image")
    public ImageResourceDto saveImage(@PathVariable long adId,
                                      @RequestParam(name = "isPrimary") boolean isPrimary,
                                      @RequestParam(name = "position") int position,
                                      @RequestPart MultipartFile image) {

        ImageResource imageResource = imageService.save(
                ImageResource.of(adId, isPrimary, position, image)
        );

        return ImageResourceDto.of(
                                    imageResource.getId(),
                                    imageResource.getAdId(),
                                    imageResource.getIsPrimary(),
                                    imageResource.getPosition(),
                                    imageResource.getImageUrl()
        );
    }

    @GetMapping("ads/{adId}/images")
    public List<ImageResourceDto> getAllByAdId(@PathVariable long adId) {
        List<ImageResource> imageResources = imageService.getAllByAdId(adId);

        return imageResources
                .stream()
                .map(imgResource -> ImageResourceDto.of(imgResource.getId(),
                                                        imgResource.getAdId(),
                                                        imgResource.getIsPrimary(),
                                                        imgResource.getPosition(),
                                                        imgResource.getImageUrl())
                )
                .collect(Collectors.toList());
    }

    @DeleteMapping("images/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable long imageId) {
        imageService.deleteImage(imageId);
    }

    @DeleteMapping("ads/{adId}/images")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllImagesByAdId(@PathVariable long adId) {
        imageService.deleteAllByAdId(adId);
    }

    private List<ImageResource> toDomain(long adId, List<MultipartFile> images) {
        List<ImageResource> imageResources = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            // Make the first image be primary
            if (i == 0) {
                imageResources.add(ImageResource.of(adId, true, i + 1, image));
                continue;
            }
            imageResources.add(ImageResource.of(adId, false, i + 1, image));
        }

        return imageResources;
    }
}
