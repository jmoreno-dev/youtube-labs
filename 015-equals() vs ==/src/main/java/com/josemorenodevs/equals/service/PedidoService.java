package com.josemorenodevs.equals.service;

import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    // [4. EL CASO REAL: IDS EN SPRING BOOT — 3:15]

    // INCORRECTO: Compara referencias de objetos Long (falla si id > 127)
    public boolean sonMismoIdIncorrecto(Long id1, Long id2) {
        if (id1 == id2) {
            return true;
        }
        return false;
    }

    // CORRECTO: Comparación segura frente a null con Objects.equals()
    public boolean sonMismoIdCorrecto(Long id1, Long id2) {
        if (Objects.equals(id1, id2)) {
            return true;
        }
        return false;
    }

    // ALTERNATIVA: Si tenemos garantizado que id1 no es null
    public boolean sonMismoIdConEquals(Long id1, Long id2) {
        if (id1.equals(id2)) {
            return true;
        }
        return false;
    }
}
