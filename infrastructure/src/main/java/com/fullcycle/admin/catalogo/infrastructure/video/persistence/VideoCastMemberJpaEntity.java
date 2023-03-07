package com.fullcycle.admin.catalogo.infrastructure.video.persistence;



import com.fullcycle.admin.catalogo.domain.castmember.CastMemberID;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "VideoCastMember")
@Table(name = "videos_cast_members")
public class VideoCastMemberJpaEntity {

    @EmbeddedId
    private VideoCastMemberID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videoId")
    private VideoJpaEntity video;

    public VideoCastMemberJpaEntity() {
    }

    private VideoCastMemberJpaEntity(final VideoCastMemberID id, final VideoJpaEntity video) {
        this.id = id;
        this.video = video;
    }

    public static VideoCastMemberJpaEntity from(final VideoJpaEntity video, CastMemberID castMember) {
        return new VideoCastMemberJpaEntity(
                VideoCastMemberID.from(video.getId(), castMember.getValue()),
                video
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoCastMemberJpaEntity that = (VideoCastMemberJpaEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getVideo(), that.getVideo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVideo());
    }

    public VideoCastMemberID getId() {
        return id;
    }

    public void setId(VideoCastMemberID id) {
        this.id = id;
    }

    public VideoJpaEntity getVideo() {
        return video;
    }

    public void setVideo(VideoJpaEntity video) {
        this.video = video;
    }
}
