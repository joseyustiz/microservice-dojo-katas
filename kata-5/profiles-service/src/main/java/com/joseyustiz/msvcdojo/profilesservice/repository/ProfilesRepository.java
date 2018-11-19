package com.joseyustiz.msvcdojo.profilesservice.repository;

import com.joseyustiz.msvcdojo.profilesservice.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by jyustiz on 11/4/18 for project microservice-dojo-katas.
 */
@RepositoryRestResource
public interface ProfilesRepository extends MongoRepository<Profile, String> {
    @Query("{ '_id' : ?0 }")
    Profile findByKey(String key);

    Iterable<Profile> findByFullName(@Param ("fullName")String fullName);
}
