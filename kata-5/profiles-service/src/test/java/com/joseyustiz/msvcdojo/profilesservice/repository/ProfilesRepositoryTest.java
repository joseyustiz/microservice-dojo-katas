package com.joseyustiz.msvcdojo.profilesservice.repository;

import com.joseyustiz.msvcdojo.profilesservice.model.Profile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyustiz on 11/4/18 for project microservice-dojo-katas.
 */
@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ProfilesRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ProfilesRepository profileRepository;
    final String JOSEYUSTIZ_FULLNAME = "Jose Yustiz";
    Profile joseYustizProfile;
    Profile username1Profile;
    Profile username2Profile;


    @Before
    public void setup() {
        joseYustizProfile = new Profile(JOSEYUSTIZ_FULLNAME);
        joseYustizProfile.setKey("jy");
        username1Profile = new Profile("username1");
        username1Profile.setKey("1");
        username2Profile = new Profile("username2");
        username2Profile.setKey("2");
        this.mongoTemplate.save(joseYustizProfile);
        this.mongoTemplate.save(username1Profile);
        this.mongoTemplate.save(username2Profile);

    }
    @Test
    public void testFindProfiletByKey() {
        Profile profile = profileRepository.findByKey("jy");
        assertThat(profile.getFullName()).isEqualTo(JOSEYUSTIZ_FULLNAME);

    }

    @Test
    public void testFindProfiletByFullName() {
        Iterable<Profile> profiles = profileRepository.findByFullName(JOSEYUSTIZ_FULLNAME);
        int count = 0;
        for (Profile profile : profiles) {
            assertThat(profile.getFullName()).isEqualTo(JOSEYUSTIZ_FULLNAME);
            count++;
        }
        assertThat(count).isEqualTo(1);
    }



}
