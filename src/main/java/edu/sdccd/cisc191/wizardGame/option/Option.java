package edu.sdccd.cisc191.wizardGame.option;

public interface Option<T> {
    /**
     * Get the identifier of the object.
     *
     * @return The object identifier.
     */
    public String getId();

    @Override
    public String toString();

}
