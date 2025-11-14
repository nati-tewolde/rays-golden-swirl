package com.pluralsight.raysgoldenswirl.toppings;

public abstract class Topping {
    private String type;

    public Topping(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract double getPrice(String size);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Topping other = (Topping) obj;
        return type.equalsIgnoreCase(other.type);
    }

    @Override
    public int hashCode() {
        return type.toLowerCase().hashCode();
    }
}
