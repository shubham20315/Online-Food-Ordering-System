package com.iiitd.models;

public class Distance {

    // method to calculate distance between two latitudes and longitudes in kilometers
    public static double get(double lt1, double ln1, double lt2, double ln2)
    {
        lt1 = Math.toRadians(lt1);
        lt2 = Math.toRadians(lt2);
        ln1 = Math.toRadians(ln1);
        ln2 = Math.toRadians(ln2);

        return 2 * Math.asin(Math.sqrt((Math.pow(Math.sin((lt2 - lt1) / 2), 2)
                + Math.cos(lt1) * Math.cos(lt2)
                * Math.pow(Math.sin((ln2 - ln1) / 2),2)))) * 6371 ;

    }
}
