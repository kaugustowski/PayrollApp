package pl.wizyg.payroll.entity;

import pl.wizyg.payroll.DTO.LocalizableEnum;

public enum Education implements LocalizableEnum {
    HIGHER_WITH_PEDAGOGIC_PREP("Wyższe z przygotowaniem pedagogicznym"),
    HIGHER_WITHOUT_PEDAGOGIC_PREP("Wyższe bez przygotowania pedagogicznego"),
    OTHER("Inne");

    private final String displayName;

    Education(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
