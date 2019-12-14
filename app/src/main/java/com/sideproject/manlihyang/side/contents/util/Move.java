package com.sideproject.manlihyang.side.contents.util;

import com.sideproject.manlihyang.side.contents.view.main.MainActivity;
import com.sideproject.manlihyang.side.contents.view.onboarding.LoginActivity;
import com.sideproject.manlihyang.side.contents.view.onboarding.RegisterAccountActivity;

public enum Move {

    ToMainActivity(MainActivity.class.getName()),
    ToLoginActivity(LoginActivity.class.getName()),
    ToRegisterAccountActivity(RegisterAccountActivity.class.getName());

    private String name;

    Move(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
