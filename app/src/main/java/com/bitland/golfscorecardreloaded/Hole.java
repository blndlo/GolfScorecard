/**To adhere to the Model-View-Controller (MVC) pattern, this class acts as the data model for our data object.
 * The data object is a golf hole which has a name (it's label) and the number of strokes for that particular hole.
 * The class is a simple Java class which consists of two member variables or fields , a constructor that initializes those fields
 * and getters and setters for those fields.*/
package com.bitland.golfscorecardreloaded;

public class Hole {

    private String mLabel;
    private int mStrokeCount;

    public Hole(String label, int strokeCount) {
        mLabel = label;
        mStrokeCount = strokeCount;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        mStrokeCount = strokeCount;
    }
}
