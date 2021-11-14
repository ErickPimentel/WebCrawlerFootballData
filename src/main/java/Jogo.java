public class Jogo {
    private final String country;
    private final String league;
    private final String season;
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

    public Jogo(String country, String league, String season, String date, String time, String home, String away, String HG, String AG, String res, String PH, String PD, String PA, String maxH, String maxD, String maxA, String avgH, String avgD, String avgA) {
        this.country = country;
        this.league = league;
        this.season = season;
        this.date = date;
        this.time = time;
        this.home = home;
        this.away = away;
        this.hg = HG;
        this.ag = AG;
        this.res = res;
        this.ph = PH;
        this.pd = PD;
        this.pa = PA;
        this.maxh = maxH;
        this.maxd = maxD;
        this.maxa = maxA;
        this.avgh = avgH;
        this.avgd = avgD;
        this.avga = avgA;
    }

    public String getCountry() {
        return country;
    }

    public String getLeague() {
        return league;
    }

    public String getSeason() {
        return season;
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

    @Override
    public String toString() {
        return "Jogo{" +
                "country='" + country + '\'' +
                ", league='" + league + '\'' +
                ", season='" + season + '\'' +
                ", inicio_season='" + inicio_season + '\'' +
                ", fim_season='" + fim_season + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", home='" + home + '\'' +
                ", away='" + away + '\'' +
                ", hg='" + hg + '\'' +
                ", ag='" + ag + '\'' +
                ", res='" + res + '\'' +
                ", ph='" + ph + '\'' +
                ", pd='" + pd + '\'' +
                ", pa='" + pa + '\'' +
                ", maxh='" + maxh + '\'' +
                ", maxd='" + maxd + '\'' +
                ", maxa='" + maxa + '\'' +
                ", avgh='" + avgh + '\'' +
                ", avgd='" + avgd + '\'' +
                ", avga='" + avga + '\'' +
                '}';
    }
}
