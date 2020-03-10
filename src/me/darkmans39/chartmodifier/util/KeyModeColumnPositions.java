package me.darkmans39.chartmodifier.util;

public enum KeyModeColumnPositions {

    KEY_1(256),
    KEY_2(128, 384),
    KEY_3(85, 256, 426),
    KEY_4(64, 192, 320, 448),
    KEY_5(51, 153, 256, 358, 460),
    KEY_6(42, 128, 213, 298, 384, 469),
    KEY_7(36, 109, 182, 256, 329, 402, 475),
    KEY_8(32, 96, 160, 224, 288, 352, 416, 480),
    KEY_9(28, 85, 142, 199, 256, 312, 369, 426, 483);

    private final int[] columnPositions;

    private KeyModeColumnPositions(int... positions) {
        this.columnPositions = positions;
    }

    public int[] getColumnPositions() {
        return columnPositions;
    }

    public int getColumnPosition(int keyExclusive) {
        return columnPositions[keyExclusive];
    }

    public static KeyModeColumnPositions getFromKeyMode(int keyMode) {

        switch (keyMode) {
        case 1:
            return KEY_1;
        case 2:
            return KEY_2;
        case 3:
            return KEY_3;
        case 4:
            return KEY_4;
        case 5:
            return KEY_5;
        case 6:
            return KEY_6;
        case 7:
            return KEY_7;
        case 8:
            return KEY_8;
        case 9:
            return KEY_9;
        default:
            return null;
        }

    }

}
