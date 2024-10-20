package com.dezegenestudio.article_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_id_generator"
    )
    @SequenceGenerator(
            name = "article_id_generator",
            sequenceName = "article_id_sequence",
            allocationSize = 1
    )
    private Long id;
    private String title;
    private String author;
    private String date;
}
