package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import com.fullcycle.admin.catalogo.domain.utils.IdUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AudioVideoMediaTest extends UnitTest {

    @Test
    public void givenValidParams_whenCallsNewAudioVideo_shouldReturnInstance() {
        // given
        final var expectedId = IdUtils.uuid();
        final var expectedCheckSum = "abc";
        final var expectedName = "Banner.png";
        final var expectedRawLocation = "/image/ac";
        final var expectedEncodedLocation = "/image/ac-encoded";
        final var expectedStatus = MediaStatus.PENDING;

        // when
        final var actualVideo =
                AudioVideoMedia.with(expectedId, expectedCheckSum, expectedName, expectedRawLocation, expectedEncodedLocation, expectedStatus);

        // then
        Assertions.assertNotNull(actualVideo);
        Assertions.assertEquals(expectedId, actualVideo.id());
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
                AudioVideoMedia.with(expectedCheckSum, "Random", expectedRawLocation);

        final var img2 =
                AudioVideoMedia.with(expectedCheckSum, "Simple", expectedRawLocation);


        // then
        Assertions.assertEquals(img1,img2);
        Assertions.assertNotSame(img1, img2);
    }

    @Test
    public void givenInvalidParams_whenCallsWith_shouldReturnError() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with(null, "56.", "Random", "/videos", "/videos", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id", "abc", null, "/videos", "/videos", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id","abc", "Random", null, "/videos", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id","abc", "Random", "/videos", null, MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id","abc", "Random", "/videos", "/videos", null)
        );
    }
}