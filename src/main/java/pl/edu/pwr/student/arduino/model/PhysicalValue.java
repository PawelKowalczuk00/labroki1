package pl.edu.pwr.student.arduino.model;

/*
    Program: Arduino
    Autor: Paweł Kowalczuk
    Data: 12.10.2020

    Plik: PhysicalValue.java

 */

public class PhysicalValue {
    private String unit;
    private double value;

    public PhysicalValue() {
    }

    public PhysicalValue(double value, String unit) throws PhysicalValueException {
        this.value = value;
        assignUnit(unit.trim());
    }

    public PhysicalValue(String valueAndUnit) throws PhysicalValueException {
        if (valueAndUnit == null || valueAndUnit.trim().equals(""))
            throw new PhysicalValueException("No text specified.");

        valueAndUnit = valueAndUnit.trim().replace(',', '.');

        int valueBoundary = 0;
        while (valueBoundary < valueAndUnit.length() && !isLetter(valueAndUnit.charAt(valueBoundary))) {
            valueBoundary++;
        }
        assignValue(valueAndUnit.substring(0, valueBoundary));

        assignUnit(valueAndUnit.substring(valueBoundary));
    }

    private void assignValue(String potentialValue) throws PhysicalValueException {
        if (potentialValue == null || potentialValue.equals(""))
            throw new PhysicalValueException("No value specified.");
        double value;
        try {
            value = Double.parseDouble(potentialValue);
        }
        catch (NumberFormatException ex) {
            throw new PhysicalValueException("Invalid format for a double - <" + potentialValue+">");
        }
        this.value = value;
    }

    private void assignUnit(String potentialUnit) throws PhysicalValueException {
        if (potentialUnit == null || potentialUnit.equals(""))
            throw new PhysicalValueException("No unit specified.");
        for (int i = 0; i < potentialUnit.length(); i++) {
            if (!isLetter(potentialUnit.charAt(i)))
                throw new PhysicalValueException("Invalid unit type - <"+ potentialUnit+">");
        }
        this.unit = potentialUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    private boolean isLetter(char sign) {
        return ((sign >= 65 && sign <= 90) || (sign >= 97 && sign <= 122));
    }

    @Override
    public String toString() {
        return value+unit;
    }
}
