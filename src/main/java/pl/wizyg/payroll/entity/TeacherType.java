package pl.wizyg.payroll.entity;

import pl.wizyg.payroll.DTO.LocalizableEnum;

public enum TeacherType implements LocalizableEnum {
    INTERN("Stażysta"),
    CONTRACT("Kontraktowy"),
    APPOINTED("Mianowany"),
    CERTIFIED("Dyplomowany");

    private final String displayName;

    TeacherType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
