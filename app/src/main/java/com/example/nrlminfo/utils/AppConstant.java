package com.example.nrlminfo.utils;

import androidx.annotation.NonNull;


public class AppConstant {
    /*Constants added by Abdul*/
    /*Don't change any constant bcoz all are used in programing conditions*/

    public static final String language_english[] = {"English", "Hindi", "Bengali", "Urdu", "Gujarati", "Kannada", "Malayalam", "Odia", "Punjabi", "Assamese", "Mizo"};
    public static final String local_language[] = {"English", "हिन्दी", "বাঙালি", "اردو", "ગુજરાતી", "ಕನ್ನಡ", "മലയാളം", "ଓଡ଼ିଆ", "ਪੰਜਾਬੀ", " অসমীয়া", "Mizo"};
    public static final String language_id[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public static final String language_code[] = {"en", "hi", "bn", "ur", "gu", "kn", "ml", "or", "pa", "as"};

    /**
     * for local server
     * <p>
     * upaganands,  village code->
     */
  public static final String HTTP_TYPE = "http";
    public static final String IP_ADDRESS = "10.197.183.105:8080";
    public static final String NRLM_STATUS = "nrlmwebservice";

    /* for live demo*/

    /*upagiisrar   nrlm123*/
    /*public static final String HTTP_TYPE = "https";
    public static final String IP_ADDRESS = "nrlm.gov.in";
    public static final String NRLM_STATUS = "nrlmwebservicedemo";*/

    /*for live*/

   /* public static final String HTTP_TYPE = "https";
    public static final String IP_ADDRESS = "nrlm.gov.in";
    public static final String NRLM_STATUS = "nrlmwebservice";*/

    /*Api Hits*/
    public static final String BASE_URL = HTTP_TYPE + "://" + IP_ADDRESS + "/" + NRLM_STATUS + "/services/";
  /*  public static final String BASE_URL="https://jsonplaceholder.typicode.com/";*/


    /*NOTE: don't change above constants these are used in programming conditions. */

}
