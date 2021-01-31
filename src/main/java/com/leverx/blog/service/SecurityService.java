package com.leverx.blog.service;

import com.leverx.blog.model.User;

public interface SecurityService {
    User getLoggedInUser();
}
