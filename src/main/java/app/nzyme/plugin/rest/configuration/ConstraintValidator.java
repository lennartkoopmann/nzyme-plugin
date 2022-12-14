package app.nzyme.plugin.rest.configuration;

public class ConstraintValidator {

    public static ConstraintValidationResult validate(Object value, ConfigurationEntryConstraint constraint) {
        if (value == null) {
            return ConstraintValidationResult.fail("Value is NULL.");
        }

        switch (constraint.type()) {
            case STRING_LENGTH:
                if (!(value instanceof String)) {
                    return ConstraintValidationResult.fail("Type [" + value.getClass().getCanonicalName() + "] is not STRING.");
                }

                String s = (String) value;
                StringLengthConstraint sc = (StringLengthConstraint) constraint.data();

                if (s.length() < sc.min()) {
                    return ConstraintValidationResult.fail("Value is too short.");
                }

                if (s.length() > sc.max()) {
                    return ConstraintValidationResult.fail("Value is too long.");
                }

                return ConstraintValidationResult.ok();
            case NUMBER_RANGE:
                if (!(value instanceof Number)) {
                    return ConstraintValidationResult.fail("Type [" + value.getClass().getCanonicalName() + "] is not NUMBER.");
                }

                Number n = (Number) value;
                NumberRangeConstraint nc = (NumberRangeConstraint) constraint.data();

                if (n.longValue() < nc.min()) {
                    return ConstraintValidationResult.fail("Value is too small.");
                }

                if (n.longValue() > nc.max()) {
                    return ConstraintValidationResult.fail("Value is too large.");
                }

                return ConstraintValidationResult.ok();
            case SIMPLE_BOOLEAN:
                if (!(value instanceof Boolean)) {
                    return ConstraintValidationResult.fail("Type [" + value.getClass().getCanonicalName() + "] is not BOOLEAN.");
                }

                return ConstraintValidationResult.ok();
            default:
                return ConstraintValidationResult.fail("Unsupported constraint type.");
        }
    }

}
