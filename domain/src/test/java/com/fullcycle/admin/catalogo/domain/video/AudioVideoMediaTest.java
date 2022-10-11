package com.fullcycle.admin.catalogo.domain.video;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AudioVideoMediaTest {

    @Test
    public void givenValidParams_whenCallsNewAudioVideo_shouldReturnInstance() {
        // given
        final var expectedCheckSum = "abc";
        final var expectedName = "Banner.png";
        final var expectedRawLocation = "/image/ac";
        final var expectedEncodedLocation = "/image/ac-encoded";
        final var expectedStatus = MediaStatus.PENDING;

        // when
        final var actualVideo =
                AudioVideoMedia.with(expectedCheckSum, expectedName, expectedRawLocation, expectedEncodedLocation, expectedStatus);

        // then
        Assertions.assertNotNull(actualVideo);
        Assertions.assertEquals(expectedCheckSum, actualVideo.checksum());
        Assertions.assertEquals(expectedName, actualVideo.name());
        Assertions.assertEquals(expectedRawLocation, actualVideo.rawLocation());
        Assertions.assertEquals(expectedEncodedLocation, actualVideo.encodedLocation());
        Assertions.assertEquals(expectedStatus, actualVideo.status());
    }

    @Test
    public void givenTwoVideosWithSameChecksumAndLocation_whenCallsEquals_shouldReturnTrue() {
        // given
        final var expectedCheckSum = "abc";
        final var expectedRawLocation = "/image/ac";

        final var img1 =
                AudioVideoMedia.with(expectedCheckSum, "Random", expectedRawLocation, "", MediaStatus.PENDING);

        final var img2 =
                AudioVideoMedia.with(expectedCheckSum, "Simple", expectedRawLocation, "", MediaStatus.PENDING);


        // then
        Assertions.assertEquals(img1,img2);
        Assertions.assertNotSame(img1, img2);
    }

    @Test
    public void givenInvalidParams_whenCallsWith_shouldReturnError() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with(null, "Random", "/videos", "/videos", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", null, "/videos", "/videos", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", "Random", null, "/videos", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", "Random", "/videos", null, MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("abc", "Random", "/videos", "/videos", null)
        );
    }
}