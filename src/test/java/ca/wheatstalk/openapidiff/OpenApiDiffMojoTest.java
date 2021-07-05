package ca.wheatstalk.openapidiff;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiDiffMojoTest {
    @Test
    void Should_NotThrow_When_SpecHasNoChanges() {
        final var oldSpec = new File("src/test/resources/oldspec.yaml").getAbsolutePath();

        final var mojo = new OpenApiDiffMojo();
        mojo.oldSpec = oldSpec;
        mojo.newSpec = oldSpec;
        mojo.failWhenIncompatible = true;

        assertDoesNotThrow(mojo::execute);
    }

    @Test
    void Should_NotThrow_When_SpecIsCompatible() {
        final var mojo = new OpenApiDiffMojo();
        mojo.oldSpec = new File("src/test/resources/oldspec.yaml").getAbsolutePath();
        mojo.newSpec = new File("src/test/resources/newspec.yaml").getAbsolutePath();
        mojo.failWhenIncompatible = true;

        assertDoesNotThrow(mojo::execute);
    }

    @Test
    void Should_MojoExecutionException_When_MissingOldSpec() {
        final var mojo = new OpenApiDiffMojo();
        mojo.oldSpec = new File("DOES_NOT_EXIST").getAbsolutePath();
        mojo.newSpec = new File("src/test/resources/newspec.yaml").getAbsolutePath();

        assertThrows(MojoExecutionException.class, mojo::execute);
    }

    @Test
    void Should_MojoExecutionException_When_MissingNewSpec() {
        final var mojo = new OpenApiDiffMojo();
        mojo.oldSpec = new File("src/test/resources/oldspec.yaml").getAbsolutePath();
        mojo.newSpec = new File("DOES_NOT_EXIST").getAbsolutePath();

        assertThrows(MojoExecutionException.class, mojo::execute);
    }

    @Test
    void Should_NotThrow_When_DefaultsAndSpecIsIncompatible() {
        final var mojo = new OpenApiDiffMojo();
        mojo.oldSpec = new File("src/test/resources/newspec.yaml").getAbsolutePath();
        mojo.newSpec = new File("src/test/resources/oldspec.yaml").getAbsolutePath();

        assertDoesNotThrow(mojo::execute);
    }

    @Test
    void Should_BackwardIncompatibilityException_When_WantsExceptionAndSpecIsIncompatible() {
        final var mojo = new OpenApiDiffMojo();
        mojo.oldSpec = new File("src/test/resources/newspec.yaml").getAbsolutePath();
        mojo.newSpec = new File("src/test/resources/oldspec.yaml").getAbsolutePath();
        mojo.failWhenIncompatible = true;

        assertThrows(BackwardIncompatibilityException.class, mojo::execute);
    }
}