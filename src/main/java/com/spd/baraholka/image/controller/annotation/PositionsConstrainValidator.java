package com.spd.baraholka.image.controller.annotation;

import com.spd.baraholka.image.controller.dto.*;
import org.springframework.stereotype.*;

import javax.validation.*;
import java.util.*;
import java.util.stream.*;

@Component
public class PositionsConstrainValidator implements ConstraintValidator<ValidatePositions, List<ImageResourceDto>>  {

    @Override
    public boolean isValid(List<ImageResourceDto> resources, ConstraintValidatorContext context) {
        List<Integer> nums = resources.stream().map(ImageResourceDto::getPosition).collect(Collectors.toList());

        if (hasDuplicateNumbers(nums)) {
            return false;
        }

        return isSequential(nums);
    }

    private boolean hasDuplicateNumbers(List<Integer> nums) {
        return nums.stream().distinct().count() != nums.size();
    }

    private boolean isSequential(List<Integer> nums) {
        return new SequenceDeterminer(nums).determine();
    }

    private final class SequenceDeterminer {
        private final List<Integer> nums;
        private final int listSize;

        private SequenceDeterminer(List<Integer> nums) {
            this.nums = nums.stream().sorted().collect(Collectors.toList());
            this.listSize = nums.size();
        }

        private boolean determine() {
            if (listSize == 1) {
                return true;
            }

            for (int i = 0; i < (listSize - 1); i++) {
                if ((nums.get(i + 1) - nums.get(i)) != 1) {
                    return false;
                }
            }

            return true;
        }
    }
}
