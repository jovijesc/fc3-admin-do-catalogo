package com.fullcycle.admin.catalogo.application.category.update;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    // 1. Teste do caminho feliz
    // 2. Teste passandouma propriedade inválida(name)
    // 3. Teste atualizando uma categoria para inativa
    // 4. Teste simulando um erro generico vindo do gateway
    // 5. Teste atualizar categoria passando ID inválido

    @Test
    public void ginveAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var aCategory =
                Category.newCategory( "Film", null, true);

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Category.with(aCategory)));

        when(categoryGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).findById(eq(expectedId));

        Mockito.verify(categoryGateway, Mockito.times(1)).update(argThat(
           aUpdateCategory ->
                   Objects.equals(expectedName, aUpdateCategory.getName())
                           && Objects.equals(expectedDescription, aUpdateCategory.getDescription())
                           && Objects.equals(expectedIsActive, aUpdateCategory.isActive())
                           && Objects.equals(expectedId, aUpdateCategory.getId())
                           && Objects.equals(aCategory.getCreatedAt(), aUpdateCategory.getCreatedAt())
                           && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
                           && Objects.isNull(aUpdateCategory.getDeletedAt())
        ));

    }

}
