package quiz.mobile.hiliti.com.hiltimobileapp.json;

import quiz.mobile.hiliti.com.hiltimobileapp.constants.UrlEndpoints;

/**
 * Created by vaishu on 03-11-2015.
 */
public class Endpoints {
    public static String getTrainingUrl(){
        return UrlEndpoints.URL_TRAINING;
    }
    public static String getImageFromServer(String imagePath){
        return UrlEndpoints.GENERIC_URL_IMAGE_SERVER+imagePath;
    }

}
