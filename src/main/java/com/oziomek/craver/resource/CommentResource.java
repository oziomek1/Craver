package com.oziomek.craver.resource;

import com.oziomek.craver.persistence.model.Comment;
import com.oziomek.craver.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comments")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Comment> getXMLCommentsForMessage(@QueryParam("message") long messageId) {
        if (messageId > 0) {
            return commentService.getCommentsForMessage(messageId);
        } else {
            return commentService.getAllComments();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONCommentsForMessage(@QueryParam("message") long messageId) {
        List<Comment> comments = null;
        if (messageId > 0) {
            comments = commentService.getCommentsForMessage(messageId);
        } else {
            comments = commentService.getAllComments();
        }
        return Response.ok(comments)
                .build();
    }

    @POST
    public Response addComment(@QueryParam("message") long messageId, Comment comment) {
        Comment newComment = commentService.addComment(messageId, comment);
        if (newComment != null) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .build();
        }
    }

    @GET
    @Path("/{commentId}")
    public Response getComment(@QueryParam("message") long messageId, @PathParam("commentId") long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        return Response.ok(comment)
                .build();
    }

    @PUT
    @Path("/{commentId}")
    public Response updateComment(@PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        Comment updatedComment = commentService.updateComment(comment);
        return Response.ok(updatedComment)
                .build();
    }

    @DELETE
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("commentId") long commentId) {
        Comment deletedComment = commentService.removeComment(commentId);
        if (deletedComment != null) {
            return Response.ok()
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
