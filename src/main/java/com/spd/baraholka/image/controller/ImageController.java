package com.spd.baraholka.image.controller;

import com.spd.baraholka.image.controller.dto.ImageResourceDto;
import com.spd.baraholka.image.persistance.entity.ImageResource;
import com.spd.baraholka.image.service.ImageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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

    @ApiOperation(value = "Upload single advertisement image to the server", response = ImageResourceDto.class)
    @PostMapping(value = "ads/{adId}/image", consumes = "multipart/form-data")
    public ImageResourceDto saveImage(@PathVariable long adId,
                                      @RequestParam(name = "isPrimary") boolean isPrimary,
                                      @Min(1) @Max(10)
                                      @RequestParam(name = "position")
                                      int position,
                                      @RequestPart MultipartFile image) {
        ImageResource imageResource = imageService.save(
                new ImageResource(adId, isPrimary, position, image)
        );

        return new ImageResourceDto(imageResource.getId(),
                                    imageResource.getAdId(),
                                    imageResource.getIsPrimary(),
                                    imageResource.getPosition(),
                                    imageResource.getImageUrl()
        );
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
    @DeleteMapping("images/{imageId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable long imageId) {
        imageService.deleteImage(imageId);
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
}
