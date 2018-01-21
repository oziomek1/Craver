package com.oziomek.craver.resource;

import com.oziomek.craver.persistence.model.Comment;
import com.oziomek.craver.persistence.model.Message;
import com.oziomek.craver.service.CommentService;
import com.oziomek.craver.service.MessageService;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MessageResource {

    private MessageService messageService = new MessageService();
    private CommentService commentService = new CommentService();

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getXMLMessages() {
        return messageService.getAllMessages();
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
        public Response getJSONMessages() {
        List<Message> messages = messageService.getAllMessages();
        return Response.ok(messages)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    private String getUriSelf(UriInfo uriInfo) {
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return uri.toString();
    }

    private String getUriProfile(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build();
        return uri.toString();
    }

    private String getUriComments(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("comments")
                .build();
        return uri.toString();
    }

    @PermitAll
    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        message.addLink(getUriSelf(uriInfo), "self");
        message.addLink(getUriProfile(uriInfo, message), "profile");
        message.addLink(getUriComments(uriInfo, message), "comments");

        if (newMessage != null) {
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }
    }

    @PermitAll
    @GET
    @Path("/{messageId}")
    public Response getMessageById(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(id);
        message.addLink(getUriSelf(uriInfo), "self");
        message.addLink(getUriProfile(uriInfo, message), "profile");
        message.addLink(getUriComments(uriInfo, message), "comments");
        return Response.ok(message)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PermitAll
    @PUT
    @Path("/{messageId}")
    public Response updateMessage(@PathParam("messageId") long id, Message message, @Context UriInfo uriInfo) {
        message.setId(id);
        Message updatedMessage = messageService.updateMessage(message);
        updatedMessage.addLink(getUriSelf(uriInfo), "self");
        updatedMessage.addLink(getUriProfile(uriInfo, message), "profile");
        updatedMessage.addLink(getUriComments(uriInfo, message), "comments");
        return Response.ok(updatedMessage)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PermitAll
    @DELETE
    @Path("/{messageId}")
    public Response deleteMessage(@PathParam("messageId") long id) {
        Message deletedMessage = messageService.removeMessage(id);
        if (deletedMessage != null) {
            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }
    }

    @PermitAll
    @GET
    @Path("/{messageId}/comments")
    @Produces(MediaType.APPLICATION_XML)
    public List<Comment> getXMLComments(@PathParam("messageId") long messageId) {
        return commentService.getCommentsForMessage(messageId);
    }

    @PermitAll
    @GET
    @Path("/{messageId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSONComments(@PathParam("messageId") long messageId) {
        List<Comment> comments = commentService.getCommentsForMessage(messageId);
        return Response.ok(comments)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PermitAll
    @POST
    @Path("/{messageId}/comments")
    public Response addComment(@PathParam("messageId") long messageId, Comment comment) {
        Comment newComment = commentService.addComment(messageId, comment);
        if (newComment != null) {
            messageService.increaseCommentCounter(messageId);
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }
    }

    @PermitAll
    @GET
    @Path("/{messageId}/comments/{commentId}")
    public Response getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        Comment comment = commentService.getCommentById(messageId, commentId);
        return Response.ok(comment)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PermitAll
    @PUT
    @Path("/{messageId}/comments/{commentId}")
    public Response updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        Comment updatedComment = commentService.updateComment(messageId, comment);
        return Response.ok(updatedComment)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PermitAll
    @DELETE
    @Path("/{messageId}/comments/{commentId}")
    public Response deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        Comment deletedComment = commentService.removeComment(messageId, commentId);
        if (deletedComment != null) {
            messageService.decreaseCommentCounter(messageId);
            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }
    }
}
