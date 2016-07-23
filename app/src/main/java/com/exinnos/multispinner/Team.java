package com.exinnos.multispinner;

/**
 * Created by RAMPRASAD on 7/23/2016.
 */
public class Team {

    private int id;
    private String teamName;

    public Team(int id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
