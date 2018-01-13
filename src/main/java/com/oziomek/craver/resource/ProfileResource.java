package com.oziomek.craver.resource;

import com.oziomek.craver.persistence.model.Profile;
import com.oziomek.craver.service.ProfileService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/profiles")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ProfileResource {

    private ProfileService profileService = new ProfileService();

    @RolesAllowed("USER")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Profile> getXMLProfiles() {
        return profileService.getAllProfiles();
    }

    @RolesAllowed("USER")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        return Response.ok(profiles)
                .build();
    }

    @RolesAllowed("ADMIN")
    @POST
    public Response addProfile(Profile profile, @Context UriInfo uriInfo) {
        Profile newProfile = profileService.addProfile(profile);

        if (newProfile != null) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @RolesAllowed("USER")
    @GET
    @Path("/{profileName}")
    public Response getProfileByName(@PathParam("profileName") String profileName, @Context UriInfo uriInfo) {
        Profile profile = profileService.getProfile(profileName);
        return Response.ok(profile)
                .build();
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("/{profileName}")
    public Response updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        Profile updatedProfile = profileService.updateProfile(profile);
        return Response.ok(updatedProfile)
                .build();
    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/{profileName}")
    public  Response deleteProfile(@PathParam("profileName") String profileName) {
        Profile deletedProfile = profileService.removeProfile(profileName);
        if (deletedProfile != null) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
