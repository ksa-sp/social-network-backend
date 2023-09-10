package ru.skillbox.socialnet.data.model.post;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post2tag")
public class Post2TagEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_post2tag_post"))
  private PostEntity post;

  @ManyToOne
  @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_post2tag_tag"))
  private TagEntity tag;
}

