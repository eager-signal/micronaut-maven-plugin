/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.maven.testresources;

import io.micronaut.maven.services.DependencyResolutionService;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.toolchain.ToolchainManager;

import javax.inject.Inject;

/**
 * Starts the Micronaut test resources server.
 */
@Mojo(name = StartTestResourcesServerMojo.NAME, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class StartTestResourcesServerMojo extends AbstractTestResourcesMojo {
    public static final String NAME = "start-testresources-service";

    private final MavenProject mavenProject;

    private final MavenSession mavenSession;

    private final DependencyResolutionService dependencyResolutionService;

    private final ToolchainManager toolchainManager;

    @Inject
    @SuppressWarnings("CdiInjectionPointsInspection")
    public StartTestResourcesServerMojo(MavenProject mavenProject,
                                        MavenSession mavenSession,
                                        DependencyResolutionService dependencyResolutionService,
                                        ToolchainManager toolchainManager) {
        this.mavenProject = mavenProject;
        this.mavenSession = mavenSession;
        this.dependencyResolutionService = dependencyResolutionService;
        this.toolchainManager = toolchainManager;
    }

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {
        TestResourcesHelper helper = new TestResourcesHelper(testResourcesEnabled, keepAlive, shared, buildDirectory,
                explicitPort, clientTimeout, mavenProject, mavenSession,
                dependencyResolutionService, toolchainManager, testResourcesVersion,
                classpathInference, testResourcesDependencies, sharedServerNamespace);
        helper.start();

    }

}