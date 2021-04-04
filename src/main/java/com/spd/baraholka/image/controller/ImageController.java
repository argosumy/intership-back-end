package com.spd.baraholka.image.controller;

import com.spd.baraholka.config.exceptions.*;
import com.spd.baraholka.image.controller.annotation.*;
import com.spd.baraholka.image.controller.dto.*;
import com.spd.baraholka.image.persistance.entity.*;
import com.spd.baraholka.image.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.*;

@RestController
@Validated
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @ApiOperation(value = "Upload images of an advertisement to the server", consumes = "multipart/form-data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Images are successfully uploaded"),
            @ApiResponse(code = 400, message = "Can't process the request. Images number min = 1, max = 10"),
            @ApiResponse(code = 400, message = "Maximum upload size exceeded; The image exceeds its maximum permitted size of 5 Megabytes.")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "ads/{adId}/images", consumes = "multipart/form-data")
    public void saveImages(@PathVariable long adId,
                           @Size(min = 1, max = 10, message = "Can't process the request. Images number min = 1, max = 10")
                           @RequestPart(value = "images") List<MultipartFile> images) {
        List<ImageResource> imageResources = toDomain(adId, images);

        imageService.saveAll(imageResources);
    }

    @ApiOperation(value = "Fetch all images of an advertisement", response = ImageResourceDto.class, responseContainer = "List")
    @GetMapping("ads/{adId}/images")
    public List<ImageResourceDto> getAllByAdId(@PathVariable long adId) {
        List<ImageResource> imageResources = imageService.getAllByAdId(adId);

        return imageResources
                .stream()
                .map(imageResource -> new ImageResourceDto(imageResource.getId(),
                        imageResource.getAdId(),
                        imageResource.getIsPrimary(),
                        imageResource.getPosition(),
                        imageResource.getImageUrl())
                ).collect(Collectors.toList());
    }

    @ApiOperation(value = "Delete a single image")
    @DeleteMapping(value = "images/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable long imageId) {
        imageService.deleteImage(imageId);
    }

    @PostMapping(value = "ads/{adId}/images/image", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.OK)
    public ImageDto uploadImage(@PathVariable long adId,
                                @RequestPart MultipartFile image) {
        Image imageData = imageService.uploadImage(adId, image);

        return new ImageDto(imageData.getId(), imageData.getUrl());
    }

    @ApiOperation(value = "Upload image resources. ", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image resources are successfully uploaded."),
            @ApiResponse(code = 400, message = "Can't process the request. Invalid json data."),
    })
    @PostMapping(value = "ads/{adId}/imageResources", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void saveImageResources(@PathVariable long adId,
                                   @RequestBody
                                   @HasPrimaryImage
                                   @ValidatePositions
                                   @ValidImageIds
                                   @Size(min = 1, max = 10, message = "Can't process the request. Images number min = 1, max = 10")
                                   List<@Valid ImageResourceDto> imageResources) {
        validateIds(adId, imageResources);

        List<ImageResource> imageResourceList =
                imageResources.stream()
                        .map(dto -> new ImageResource(
                                dto.getId(),
                                dto.getAdId(),
                                dto.isPrimary(),
                                dto.getPosition(),
                                dto.getUrl())
                        ).collect(Collectors.toList());

        imageService.saveImageResources(adId, imageResourceList);
    }

    @GetMapping("images/unattached")
    public List<Image> getUnattachedImages() {
        return imageService.getAllUnattached();
    }

    @ApiOperation(value = "Delete all images of an advertisement")
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
                imageResources.add(new ImageResource(adId, true, i + 1, image));
                continue;
            }
            imageResources.add(new ImageResource(adId, false, i + 1, image));
        }

        return imageResources;
    }

    private void validateIds(long adId, List<ImageResourceDto> imageResources) {
        if (!imageResources.stream().allMatch(resource -> resource.getAdId() == adId)) {
            throw new AdvertisementIdsMismatchException("Advertisement id values are different");
        }
    }
}
