package com.fullcycle.admin.catalogo.domain.genre;


import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.exceptions.NotificationException;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GenreTest {

    @Test
    public void givenValidParams_whenCallNewGenre_shouldInstantiateAGenre() {
        final var expectedName = "Ação";
        final var expectedIsActive = true;
        final var expectedCategories = 0;

        final var actualGenre = Genre.newGenre(expectedName, expectedIsActive);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories().size());
        Assertions.assertNotNull(actualGenre.getCreatedAt());
        Assertions.assertNotNull(actualGenre.getUpdatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenInvalidNullName_whenCallNewGenreAndValidate_shouldReceiveAnError() {
        final String expectedName = null;
        final var expectedIsActive = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualException = Assertions.assertThrows(NotificationException.class, () ->{
            Genre.newGenre(expectedName, expectedIsActive);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidEmptyName_whenCallNewGenreAndValidate_shouldReceiveAnError() {
        final String expectedName = " ";
        final var expectedIsActive = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualException = Assertions.assertThrows(NotificationException.class, () ->{
            Genre.newGenre(expectedName, expectedIsActive);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenInvalidNameWithLengthGreaterThan255_whenCallNewGenreAndValidate_shouldReceiveAnError() {
        final String expectedName = """
                O que temos que ter sempre em mente é que o desafiador cenário globalizado prepara-nos para enfrentar situações atípicas decorrentes 
                dos conhecimentos estratégicos para atingir a excelência. Todavia, a determinação clara de objetivos cumpre um papel essencial na formulação do 
                remanejamento dos quadros funcionais. Desta maneira, o fenômeno da Internet causa impacto indireto na reavaliação dos relacionamentos verticais 
                entre as hierarquias. O empenho em analisar o surgimento do comércio virtual garante a contribuição de um grupo importante na determinação das direções 
                preferenciais no sentido do progresso. A certificação de metodologias que nos auxiliam a lidar com a estrutura atual da organização exige a precisão e a 
                definição das novas proposições.
                """;
        final var expectedIsActive = true;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 1 and 255 characters";

        final var actualException = Assertions.assertThrows(NotificationException.class, () ->{
            Genre.newGenre(expectedName, expectedIsActive);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnActiveGenre_whenCallDeactivate_shouldReceiveOk() {
        final var expectedName = "Ação";
        final var expectedIsActive = false;
        final var expectedCategories = 0;

        final var actualGenre = Genre.newGenre(expectedName, true);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertTrue(actualGenre.isActive());
        Assertions.assertNull(actualGenre.getDeletedAt());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.deactivate();

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories().size());
        Assertions.assertNotNull(actualGenre.getCreatedAt());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualGenre.getUpdatedAt()));
        Assertions.assertNotNull(actualGenre.getUpdatedAt());
        Assertions.assertNotNull(actualGenre.getDeletedAt());

    }

    @Test
    public void givenAnInactiveGenre_whenCallActivate_shouldReceiveOk() {
        final var expectedName = "Ação";
        final var expectedIsActive = true;
        final var expectedCategories = 0;

        final var actualGenre = Genre.newGenre(expectedName, false);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertFalse(actualGenre.isActive());
        Assertions.assertNotNull(actualGenre.getCreatedAt());
        Assertions.assertNotNull(actualGenre.getDeletedAt());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.activate();

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories().size());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualGenre.getUpdatedAt()));
        Assertions.assertNotNull(actualGenre.getUpdatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());

    }

    @Test
    public void givenAValidInactiveGenre_whenCallUpdateWithActivate_shouldReceiveGenreUpdated() {
        final var expectedName = "Ação";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(CategoryID.from("123"));

        final var actualGenre = Genre.newGenre("ação", false);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertFalse(actualGenre.isActive());
        Assertions.assertNotNull(actualGenre.getCreatedAt());
        Assertions.assertNotNull(actualGenre.getDeletedAt());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.update(expectedName, expectedIsActive, expectedCategories);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualGenre.getUpdatedAt()));
        Assertions.assertNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAValidActiveGenre_whenCallUpdateWithInactivate_shouldReceiveGenreUpdated() {
        final var expectedName = "Ação";
        final var expectedIsActive = false;
        final var expectedCategories = List.of(CategoryID.from("123"));

        final var actualGenre = Genre.newGenre("ação", true);

        Assertions.assertNotNull(actualGenre);
        Assertions.assertTrue(actualGenre.isActive());
        Assertions.assertNotNull(actualGenre.getCreatedAt());
        Assertions.assertNull(actualGenre.getDeletedAt());

        final var actualCreatedAt = actualGenre.getCreatedAt();
        final var actualUpdatedAt = actualGenre.getUpdatedAt();

        actualGenre.update(expectedName, expectedIsActive, expectedCategories);

        Assertions.assertNotNull(actualGenre.getId());
        Assertions.assertEquals(expectedName, actualGenre.getName());
        Assertions.assertEquals(expectedIsActive, actualGenre.isActive());
        Assertions.assertEquals(expectedCategories, actualGenre.getCategories());
        Assertions.assertEquals(actualCreatedAt, actualGenre.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualGenre.getUpdatedAt()));
        Assertions.assertNotNull(actualGenre.getDeletedAt());
    }

    @Test
    public void givenAValidGenre_whenCallUpdateWithEmptyName_shouldReceiveNotificationException() {
        final var expectedName = " ";
        final var expectedIsActive = true;
        final var expectedCategories = List.of(CategoryID.from("123"));
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualGenre = Genre.newGenre("ação", false);

        final var actualException = Assertions.assertThrows(NotificationException.class, () ->{
            actualGenre.update(expectedName, expectedIsActive, expectedCategories);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidGenre_whenCallUpdateWithNullName_shouldReceiveNotificationException() {
        final String expectedName = null;
        final var expectedIsActive = true;
        final var expectedCategories = List.of(CategoryID.from("123"));
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualGenre = Genre.newGenre("ação", false);

        final var actualException = Assertions.assertThrows(NotificationException.class, () ->{
            actualGenre.update(expectedName, expectedIsActive, expectedCategories);
        });

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
