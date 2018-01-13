package com.oziomek.craver.service;

import com.oziomek.craver.persistence.database.DatabaseClass;
import com.oziomek.craver.persistence.model.Profile;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfileService {
    /*
     * Further implementation will provide connection with DB
     */

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("test", new Profile(1l,"test", "testName", "testSurname"));
        profiles.put("johny123", new Profile(2l, "johny123", "Jan", "Kowalski"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        Profile profile = profiles.get(profileName);
        if (profile == null) {
            throw new NotFoundException("Profile with name " + profileName + " not found");
        }
        return profile;
    }

    public Profile getProfileById(Long profileId) {
        for (Map.Entry<String, Profile> profile : profiles.entrySet()) {
            if (profile.getValue().getId() == profileId) {
                return profile.getValue();
            }
        }
        return profiles.get(profiles.keySet().toArray()[0]);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        if (profile.getDateCreated() == null) {
            profile.setDateCreated(new Date());
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getId() < 0) {
            return null;
        }
        if (profile.getDateCreated() == null) {
            profile.setDateCreated(new Date());
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(String profileName) {
        if (!profiles.containsKey(profileName)) {
            return null;
        }
        Profile profile = profiles.get(profileName);
        profile.setId(profiles.size() + 1);
        /*
         * Update operation
         */
        profiles.remove(profileName);
        profiles.put(profileName, profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }

    public Profile removeProfile(Profile profile) {
        return profiles.remove(profile.getProfileName());
    }
}
