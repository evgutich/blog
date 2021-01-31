package com.leverx.blog.service;

import com.leverx.blog.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTagsCloud();
    Tag getOrCreateByName(String tagName);
}
