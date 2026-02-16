package clinicapp.domain;

public final class DomainValidator {
    private DomainValidator() {
    }

    public static <T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " no puede ser null");
        }
        return value;
    }

    public static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " no puede ser vacío");
        }
        return value;
    }

    public static int requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " debe ser >= 0");
        }
        return value;
    }

    public static double requireRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " debe estar entre " + min + " y " + max);
        }
        return value;
    }
}
