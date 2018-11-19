package com.joseyustiz.msvcdojo.profilesservice.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyustiz on 11/4/18 for project microservice-dojo-katas.
 */
public class ProfileTest {
    @Test
    public void getCorrectToStringOfProfilesWithNullId(){
        Profile profile = new Profile("Jose Yustiz");
        String profileToString="Customer{id:null, fullName:\'Jose Yustiz\'}";
        assertThat(profile.toString()).isEqualTo(profileToString);
    }

    @Test
    public void getCorrectToStringOfProfilesWithNotNullId(){
        Profile profile = new Profile("Jose Yustiz");
        profile.setId("1");
        String profileToString="Customer{id:'1', fullName:\'Jose Yustiz\'}";
        assertThat(profile.toString()).isEqualTo(profileToString);
    }

    @Test
    public void getAmountPhotoOfAProfile(){
        Profile profile = new Profile("Jose Yustiz");
        profile.setId("1");

        profile.addPhotoReference("photo.jpg");
        assertThat(profile.getPhotoCount()).isEqualTo(1);
    }

    @Test
    public void setKeyUpdateTheIdOfTheProfile(){
        Profile profile = new Profile("Jose Yustiz");
        profile.setKey("a");

        assertThat(profile.getKey()).isEqualTo("a");
    }



    @Test(expected = IllegalArgumentException.class)
    public void fullNameCannotBeNull(){
        Profile profile = new Profile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fullNameCannotBeBlank(){
        Profile profile = new Profile("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void keyCannotBeNull(){
        Profile profile = new Profile("Jose Yustiz");
        profile.setKey(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void keyCannotBeBlank(){
        Profile profile = new Profile("Jose Yustiz");
        profile.setKey("");
    }
}
