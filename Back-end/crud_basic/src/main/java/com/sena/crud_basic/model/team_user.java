package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "team_user")
public class team_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int team_user_id;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    private teams team_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private user user_id;

    public team_user() {}

    public team_user(int team_user_id, teams team_id, user user_id) {
        this.team_user_id = team_user_id;
        this.team_id = team_id;
        this.user_id = user_id;
    }

    public int getTeam_user_id() {
        return team_user_id;
    }

    public void setTeam_user_id(int team_user_id) {
        this.team_user_id = team_user_id;
    }

    public teams getTeam_id() {
        return team_id;
    }

    public void setTeam_id(teams team_id) {
        this.team_id = team_id;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }
}