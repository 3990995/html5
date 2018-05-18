package com.liao27.model.entity;

import com.liao27.model.Type;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * 游戏评论
 * Created by main on 2018/5/4.
 */
@Slf4j
@Data
@Entity
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    /**
     * 玩家昵称
     */
    @Column
    private String name;

    /**
     * 头像
     */
    @Column
    private String headImage;

    /**
     * 打分
     */
    @Column
    private Float star;

    /**
     * 评论类别
     */
    @Column(name = "type", length = 30)
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     * 评论内容
     */
    @Lob
    @Column
    private String content;

    /**
     * 游戏id
     */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * 评论id 父节点
     */
    @ManyToOne
    private Comment parent;

    /**
     * 评论 id 爷爷节点
     */
    @ManyToOne
    private Comment grandpa;

    /**
     * 儿子们
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Comment> comments;

    /**
     * 孙子们
     */
    @OneToMany(mappedBy = "grandpa", fetch = FetchType.LAZY)
    private List<Comment> grandchildren;

    /**
     * 评论时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_date", nullable = false)
    private Date commentTime;

    /**
     * 获取指定 commentid 的子评论
     *
     * @param grandpaId 孙子评论
     * @return 查询条件
     */
    public static Specification<Comment> buildCommentRequest(Long commentId, Long grandpaId) {
        return new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = builder.conjunction();
                if (commentId != null && commentId > 0) {
                    predicate.getExpressions().add(builder.equal(root.get("parent"), commentId));
                }
                if (grandpaId != null && grandpaId > 0) {
                    predicate.getExpressions().add(builder.equal(root.get("grandpa"), grandpaId));
                }
                return predicate;
            }
        };
    }

    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment c = (Comment) o;
        return id.equals(c.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (game != null ? game.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (grandpa != null ? grandpa.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (grandchildren != null ? grandchildren.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }
}
