package com.spd.baraholka.image.controller.annotation;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ValidatePositionsConstrainValidatorTest {

    @Test
    void test() {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertEquals(true, nums.stream().distinct().count() == nums.size());
        assertEquals(true, new SequenceDeterminer(nums).determine());
    }

    private final class SequenceDeterminer {
        private final List<Integer> nums;
        private final int listSize;

        private SequenceDeterminer(List<Integer> nums) {
            this.nums = nums.stream().sorted().collect(Collectors.toList());
            this.listSize = nums.size();
        }

        private boolean determine() {
            for (int i = 0; i < listSize; i++) {
                if (!isLast(i)) {
                    if (nums.get(i + 1) - nums.get(i) != 1) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean isLast(int i) {
            return i == (listSize - 1);
        }
    }
}