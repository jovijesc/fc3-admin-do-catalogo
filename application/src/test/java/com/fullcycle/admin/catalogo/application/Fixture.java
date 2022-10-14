package com.fullcycle.admin.catalogo.application;

import com.fullcycle.admin.catalogo.domain.castmember.CastMember;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberType;
import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.genre.Genre;
import com.fullcycle.admin.catalogo.domain.video.Rating;
import com.fullcycle.admin.catalogo.domain.video.Resource;
import com.github.javafaker.Faker;
import io.vavr.API;

import java.util.Arrays;

import static io.vavr.API.*;
import static io.vavr.API.Match.Case;

public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static Integer year() {
        return FAKER.random().nextInt(2020,2030);
    }

    public static Double duration() {
        return FAKER.options().option(120.0, 15.5, 35.5, 10.0, 2.0);
    }

    public static Boolean bool() {
        return FAKER.bool().bool();
    }

    public static String title() {
        return FAKER.options().option(
                "System Design na prática no Mercado Livre",
                "Não cometa esses erros ao trabalhar com microserviços",
                "Testes de mutação. Você não testa seu software corretamente"
        );
    }

    public static final class Categories {

        private static final Category AULAS =
                Category.newCategory("Aulas", "Alguma descrição", true);

        public static Category aulas() {
            return AULAS.clone();
        }
    }

    public static final class CastMembers {

        private static final CastMember WESLEY =
                CastMember.newMember("Wesley FullCycle", CastMemberType.ACTOR);

        private static final CastMember PAULO =
                CastMember.newMember("Paulo da Silva", CastMemberType.ACTOR);

        public static CastMemberType type() {
            return FAKER.options().option(CastMemberType.values());
        }

        public static CastMember wesley() {
            return CastMember.with(WESLEY);
        }

        public static CastMember paulo() {
            return CastMember.with(PAULO);
        }
    }

    public static final class Genres {

        private static final Genre TECH =
                Genre.newGenre("Technology", true);

        public static Genre tech() {
            return Genre.with(TECH);
        }
    }

    public static final class Videos {

        public static Rating rating() {
            return FAKER.options().option(Rating.values());
        }

        public static Resource resource(final Resource.Type type) {
            final String contentType = Match(type).of(
                    Case($(List(Resource.Type.VIDEO, Resource.Type.TRAILER)::contains), "video/mp4"),
                    Case($(), "image/jpg")
            );

            final byte[] content = "Conteudo".getBytes();

            return Resource.with(content, contentType, type.name().toLowerCase(), type);
        }


        public static String description() {
            return FAKER.options().option(
                    """
                            Mesmo um código ruim pode funcionar. Mas se ele não for limpo, pode acabar com uma empresa de desenvolvimento. 
                            Perdem-se a cada ano horas incontáveis e recursos importantes devido a um código mal escrito. 
                            Mas não precisa ser assim.O renomado especialista em software, Robert C. Martin, apresenta um paradigma revolucionário com Código limpo: 
                            Habilidades Práticas do Agile Software.
                            """,
                            """
                            O Programador Pragmático ilustra as melhores práticas e as principais armadilhas do desenvolvimento de software. 
                            Destinado a todos envolvidos com programação, de codificadores iniciantes a programadores experientes e gerentes responsáveis por projetos de software, 
                            apresenta lições simples que promovem rápidas melhorias na produtividade pessoal, precisão e satisfação profissional.
                            """
            );
        }
    }
}
