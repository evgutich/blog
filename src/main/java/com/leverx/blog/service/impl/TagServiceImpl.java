package com.leverx.blog.service.impl;

import com.leverx.blog.model.Tag;
import com.leverx.blog.repository.TagRepository;
import com.leverx.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTagsCloud() {
        return null;
    }

    @Override
    public Tag getOrCreateByName(String tagName) {
        Optional<Tag> tag = tagRepository.findByName(tagName);
        if (tag.isEmpty()) {
            Tag createdTag = new Tag();
            createdTag.setName(tagName);
            return tagRepository.save(createdTag);
        }
        return tag.get();
    }
}
