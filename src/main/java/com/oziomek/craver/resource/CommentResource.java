//package com.oziomek.craver.resource;
//
//import com.oziomek.craver.persistence.model.Comment;
//import com.oziomek.craver.service.CommentService;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/comments")
//@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//public class CommentResource {
//
//    private CommentService commentService = new CommentService();
//    private long messageId;
//    public CommentResource() {
//    }
//
//    public CommentResource(long messageId) {
//        this.messageId = messageId;
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public List<Comment> getXMLCommentsForMessage() {
//        if (messageId > 0) {
//            return commentService.getCommentsForMessage(messageId);
//        } else {
//            return null; // commentService.getAllComments();
//        }
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getJSONCommentsForMessage() {
//        List<Comment> comments = null;
//        if (messageId > 0) {
//            comments = commentService.getCommentsForMessage(messageId);
//        } else {
//            comments = null; //commentService.getAllComments();
//        }
//        return Response.ok(comments)
//                .build();
//    }
//
//    @POST
//    public Response addComment(Comment comment) {
//        Comment newComment = commentService.addComment(messageId, comment);
//        if (newComment != null) {
//            return Response.status(Response.Status.CREATED)
//                    .build();
//        } else {
//            return Response.status(Response.Status.NOT_ACCEPTABLE)
//                    .build();
//        }
//    }
//
//    @GET
//    @Path("/{commentId}")
//    public Response getComment(@PathParam("commentId") long commentId) {
//        Comment comment = null; //commentService.getCommentById(commentId);
//        return Response.ok(comment)
//                .build();
//    }
//
//    @PUT
//    @Path("/{commentId}")
//    public Response updateComment(@PathParam("commentId") long commentId, Comment comment) {
//        comment.setId(commentId);
//        Comment updatedComment = null; //commentService.updateComment(comment);
//        return Response.ok(updatedComment)
//                .build();
//    }
//
//    @DELETE
//    @Path("/{commentId}")
//    public Response deleteComment(@PathParam("commentId") long commentId) {
//        Comment deletedComment = null; //commentService.removeComment(commentId);
//        if (deletedComment != null) {
//            return Response.ok()
//                    .build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .build();
//        }
//    }
//}
