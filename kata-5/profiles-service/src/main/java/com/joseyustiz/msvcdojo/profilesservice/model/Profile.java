package com.joseyustiz.msvcdojo.profilesservice.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyustiz on 11/4/18 for project microservice-dojo-katas.
 */
@Document(collection = "profiles")
public class Profile {
    // id fits the standard name for a MongoDB id so it doesn’t require
    // any special annotation to tag it for Spring Data MongoDB.
    @Id
    @Setter private String id;

    @Getter
    @Indexed
    private String fullName;

    private List<String> photos;

    public Profile(String fullName) {
        this.setFullName(fullName);
    }

    public String toString() {
        if (StringUtils.isBlank(id))
            return String.format("Customer{id:%s, fullName:'%s'}", id, fullName);
        else
            return String.format("Customer{id:'%s', fullName:'%s'}", id, fullName);
    }

    public void setFullName(String fullName) {
        if(StringUtils.isBlank(fullName))
            throw new IllegalArgumentException("Key for the profile cannot be blank!");
        this.fullName = fullName;
    }

    public void addPhotoReference(String photoId) {
        this.photosList().add(photoId);
    }

    public Integer getPhotoCount() {
        return this.photosList().size();
    }

    public List<String> photosList() {
        if (this.photos == null)
            this.photos = new ArrayList<>();
        return this.photos;
    }

    public void setKey(String key) {
        if(StringUtils.isBlank(key))
            throw new IllegalArgumentException("Key for the profile cannot be blank!");
        this.id=key;
    }

    public String getKey() {
        return this.id;
    }
}
