package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberID;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.genre.GenreID;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Set;

public class VideoValidatorTest extends UnitTest {

    @Test
    public void givenNullTitle_whenCallsValidate_shouldReceiveError() {
        // given
        final String expectedTitle = null;
        final var expectedDescription = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be null";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }

    @Test
    public void givenEmptyTitle_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "";
        final var expectedDescription = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be empty";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }

    @Test
    public void givenTitleWithLengthGreaterThan255_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final var expectedDescription = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' must be between 1 and 255 characters";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }

    @Test
    public void givenEmptyDescription_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = "";
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }

    @Test
    public void givenDescriptionWithLengthGreaterThan4000_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 1 and 4000 characters";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }

    @Test
    public void givenNullLaunchedAt_whenCallsValidate_shouldReceiveError() {
        // given
        final String expectedTitle = "System Design Interviews";
        final var expectedDescription = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final Year expectedLaunchedAt = null;
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'launchedAt' should not be null";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }

    @Test
    public void givenNullRating_whenCallsValidate_shouldReceiveError() {
        // given
        final String expectedTitle = "System Design Interviews";
        final var expectedDescription = """
                What is a system design interview? A system design interview is a conversation between 2 engineers. 
                Given some vague requirements, these engineers will discuss and define more well-defined requirements, 
                followed by engineering designs that an engineering team can implement
                """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final Rating expectedRating = null;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'rating' should not be null";

        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate( new ThrowsValidationHandler())
        );


        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());

    }
}
