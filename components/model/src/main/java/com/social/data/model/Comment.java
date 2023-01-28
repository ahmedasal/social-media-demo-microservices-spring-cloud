package com.social.data.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    @Column(name = "comment_text")
    String commentText;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    //TODO self-Referencing
    @ManyToOne
    @JoinColumn(name = "comment_parent_id")
    Comment parentComment;
    @OneToMany(mappedBy = "parentComment")
    List<Comment> comments = new ArrayList<>();
    @Column(name = "create_date")
    Date createdDate;
    @Column(name = "update_date")
    Date updatedDate;

    public Comment() {
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post=" + post +
                ", commentText='" + commentText + '\'' +
                ", user=" + user +
                ", parentComment=" + parentComment +
                ", comments=" + comments +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
