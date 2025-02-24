package com.example.project5;

/**
 * Represents Sauce for the Pizza as an enumeration.
 * Each enum value is associated with a specific string name code.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */

public enum Sauce {

    /** Information for TOMATO(TOMATO), and ALFREDO(ALFREDO)*/
    TOMATO("TOMATO"),
    ALFREDO("ALFREDO");

    /** The full string name of the sauce. **/
    private final String name;

    /**
     * Sauce enum class constructor.
     * @param name String variable for sauce name.
     */
    Sauce(String name){
        this.name = name;
    }

    /**
     * Return the Sauce information as a String.
     * @return name String type of the sauce.
     */
    @Override
    public String toString(){
        return String.format("%s", this.name);
    }
}
