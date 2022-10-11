package com.fullcycle.admin.catalogo.domain.video;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImageMediaTest {

    @Test
    public void givenValidParams_whenCallsNewImage_shouldReturnInstance() {
        // given
        final var expectedCheckSum = "abc";
        final var expectedName = "Banner.png";
        final var expectedLocation = "/image/ac";

        // when
        final var actualImage =
                ImageMedia.with(expectedCheckSum, expectedName, expectedLocation);

        // then
        Assertions.assertNotNull(actualImage);
        Assertions.assertEquals(expectedCheckSum, actualImage.checksum());
        Assertions.assertEquals(expectedName, actualImage.name());
        Assertions.assertEquals(expectedLocation, actualImage.location());
    }

    @Test
    public void givenTwoImagesWithSameChecksumAndLocation_whenCallsEquals_shouldReturnTrue() {
        // given
        final var expectedCheckSum = "abc";
        final var expectedLocation = "/image/ac";

        final var img1 =
                ImageMedia.with(expectedCheckSum, "Random", expectedLocation);

        final var img2 =
                ImageMedia.with(expectedCheckSum,"Simple", expectedLocation);


        // then
        Assertions.assertEquals(img1,img2);
        Assertions.assertNotSame(img1, img2);
    }

    @Test
    public void givenInvalidParams_whenCallsWith_shouldReturnError() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with(null, "Random", "/images")
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with("abc", null, "/images")
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with("abc", "Random", null)
        );
    }
}