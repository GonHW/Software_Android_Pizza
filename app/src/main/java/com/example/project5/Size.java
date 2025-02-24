package com.example.project5;

/**
 * Represents Size of Pizza as an enumeration.
 * Each enum value is associated with a specific string name code.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */

public enum Size {

    /** Information for S(Small), M(Medium), and L(Large)*/
    S("SMALL"),
    M("MEDIUM"),
    L("LARGE");

    /** The full string name of the size. **/
    private final String name;

    /**
     * Size enum class constructor.
     * @param name String variable for size name.
     */
    Size(String name){
        this.name = name;
    }

    /**
     * Return the size information as a String.
     * @return name String type of the size.
     */
    @Override
    public String toString(){
        return String.format("%s", this.name);
    }



}
