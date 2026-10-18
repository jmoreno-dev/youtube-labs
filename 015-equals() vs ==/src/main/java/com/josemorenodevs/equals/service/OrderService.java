package com.josemorenodevs.equals.service;

import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // [4. REAL WORLD CASE: IDS IN SPRING BOOT — 3:15]

    // INCORRECT: Compares Long object references (fails if id > 127)
    public boolean isSameIdIncorrect(Long id1, Long id2) {
        if (id1 == id2) {
            return true;
        }
        return false;
    }

    // CORRECT: Null-safe comparison with Objects.equals()
    public boolean isSameIdCorrect(Long id1, Long id2) {
        if (Objects.equals(id1, id2)) {
            return true;
        }
        return false;
    }

    // ALTERNATIVE: If id1 is guaranteed never to be null
    public boolean isSameIdWithEquals(Long id1, Long id2) {
        if (id1.equals(id2)) {
            return true;
        }
        return false;
    }
}