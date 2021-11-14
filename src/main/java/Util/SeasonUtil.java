package Util;

public class SeasonUtil {

    public static String converteSeasonSubstringPaisesPrimarios(String season_substring){
        if (Integer.parseInt(season_substring) < 30){
            return "20" + season_substring;
        }
        return "19" + season_substring;
    }
}
