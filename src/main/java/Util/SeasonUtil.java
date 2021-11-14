package Util;

public class SeasonUtil {

    private String inicio_season;
    private String fim_season;

    public String getInicio_season() {
        return inicio_season;
    }

    public void setInicio_season(String inicio_season) {
        this.inicio_season = inicio_season;
    }

    public String getFim_season() {
        return fim_season;
    }

    public void setFim_season(String fim_season) {
        this.fim_season = fim_season;
    }

    public static String converteSeasonSubstringPaisesPrimarios(String season_substring){
        if (Integer.parseInt(season_substring) < 30){
            return "20" + season_substring;
        }
        return "19" + season_substring;
    }

    public static SeasonUtil converteSeasonSubstringPaisesSecundarios(String season){
        SeasonUtil s = new SeasonUtil();
        if (season.length() == 4){
            s.setInicio_season(season);
            s.setFim_season(season);
        }else {
            season = season.trim();
            s.setInicio_season(season.substring(0, 4));
            s.setFim_season(season.substring(5, 9));
        }
        return s;
    }
}
