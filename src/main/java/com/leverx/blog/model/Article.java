package com.leverx.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "title")
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotBlank(message = "*Please provide title")
    private String title;

    @Column(name = "text", columnDefinition = "TEXT")
    @NotBlank(message = "*Please write something")
    private String text;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id", nullable = false)
    private User author;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "upgrade_date")
    @UpdateTimestamp
    private LocalDateTime upgradeDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "articles_tags",
            joinColumns = {@JoinColumn(name = "articles_id")},
            inverseJoinColumns = {@JoinColumn(name = "tags_id")})
    private Set<Tag> tags;
}
