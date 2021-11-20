package Model;

public class Jogo {
    private final String country;
    private final String league;
    private String inicio_season;
    private String fim_season;
    private final String date;
    private final String time;
    private final String home;
    private final String away;
    private final String hg;
    private final String ag;
    private final String res;
    private final String ph;
    private final String pd;
    private final String pa;
    private final String maxh;
    private final String maxd;
    private final String maxa;
    private final String avgh;
    private final String avgd;
    private final String avga;


    public Jogo(String country, String league, String inicio_season, String fim_season, String date, String time, String home, String away, String hg, String ag, String res, String ph, String pd, String pa, String maxh, String maxd, String maxa, String avgh, String avgd, String avga) {
        this.country = country;
        this.league = league;
        this.inicio_season = inicio_season;
        this.fim_season = fim_season;
        this.date = date;
        this.time = time;
        this.home = home;
        this.away = away;
        this.hg = hg;
        this.ag = ag;
        this.res = res;
        this.ph = ph;
        this.pd = pd;
        this.pa = pa;
        this.maxh = maxh;
        this.maxd = maxd;
        this.maxa = maxa;
        this.avgh = avgh;
        this.avgd = avgd;
        this.avga = avga;
    }

    public String getCountry() {
        return country;
    }

    public String getLeague() {
        return league;
    }

    public void setInicio_season(String inicio_season) {
        this.inicio_season = inicio_season;
    }

    public String getInicio_season() {
        return inicio_season;
    }

    public void setFim_season(String fim_season) {
        this.fim_season = fim_season;
    }

    public String getFim_season() {
        return fim_season;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }

    public String getHg() {
        return hg;
    }

    public String getAg() {
        return ag;
    }

    public String getRes() {
        return res;
    }

    public String getPh() {
        return ph;
    }

    public String getPd() {
        return pd;
    }

    public String getPa() {
        return pa;
    }

    public String getMaxh() {
        return maxh;
    }

    public String getMaxd() {
        return maxd;
    }

    public String getMaxa() {
        return maxa;
    }

    public String getAvgh() {
        return avgh;
    }

    public String getAvgd() {
        return avgd;
    }

    public String getAvga() {
        return avga;
    }
}