package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.teams;
import com.sena.crud_basic.model.user;

public class team_userDTO {
    private teams team_id;
    private user user_id;
    
    public team_userDTO() {
    }

    public team_userDTO(teams team_id, user user_id) {
        this.team_id = team_id;
        this.user_id = user_id;
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
