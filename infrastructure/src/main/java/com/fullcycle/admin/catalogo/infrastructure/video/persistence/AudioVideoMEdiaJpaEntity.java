package com.fullcycle.admin.catalogo.infrastructure.video.persistence;

import com.fullcycle.admin.catalogo.domain.video.AudioVideoMedia;
import com.fullcycle.admin.catalogo.domain.video.MediaStatus;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "AudioVideoMEdia")
@Table(name = "videos_video_media")
public class AudioVideoMEdiaJpaEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name", nullable = false)
    private String filePath;

    @Column(name = "name", nullable = false)
    private String encodePath;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaStatus status;

    public AudioVideoMEdiaJpaEntity() {}

    private AudioVideoMEdiaJpaEntity(
            final String id,
            final String name,
            final String filePath,
            final String encodePath,
            final MediaStatus status) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.encodePath = encodePath;
        this.status = status;
    }

    public static AudioVideoMEdiaJpaEntity from(final AudioVideoMedia media) {
        return new AudioVideoMEdiaJpaEntity(
                media.checksum(),
                media.name(),
                media.rawLocation(),
                media.encodedLocation(),
                media.status()
        );
    }

    public AudioVideoMedia toDomain() {
        return AudioVideoMedia.with(
                getId(),
                getName(),
                getFilePath(),
                getEncodePath(),
                getStatus()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getEncodePath() {
        return encodePath;
    }

    public void setEncodePath(String encodePath) {
        this.encodePath = encodePath;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }
}
