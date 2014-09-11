/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Eltrino LLC (http://eltrino.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.eltrino.gradle.php

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.Task
import com.eltrino.gradle.php.tasks.*
import com.eltrino.gradle.php.domain.*
import org.gradle.api.tasks.SourceSet
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.internal.file.FileResolver
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

import javax.inject.Inject

// @TODO Place logging messages

class PhpPlugin implements Plugin<Project> {
    private static Logger logger = Logging.getLogger(PhpPlugin)
    private Project project
    private final FileResolver fileResolver
    
    @Inject
    PhpPlugin(FileResolver fileResolver) {
        this.fileResolver = fileResolver
    }

    void apply(final Project project) {
        this.project = project
        project.extensions.create("php", PhpPluginExtension)
        project.plugins.apply(JavaBasePlugin.class)
        project.convention.getPlugin(JavaPluginConvention.class).sourceSets.all { SourceSet sourceSet ->
            sourceSet.convention.plugins.php = new DefaultPhpSourceSet(sourceSet.displayName, fileResolver)
            sourceSet.allSource.source(sourceSet.php)
        }

        createComplexTask('phpLint', PhpLintTask)
        createComplexTask('phpMd', PhpMdTask)
        project.task('test', type: PhpUnitTask, overwrite: true)

    }

    protected Task createComplexTask(name, type) {
        Task baseTask = project.task(name)
        project.sourceSets.all { SourceSet sourceSet ->
            Task task = project.task(sourceSet.getTaskName(name, null), type: type)
            task.source(sourceSet.php)
            baseTask.dependsOn task
            baseTask.group task.group
        }
        return baseTask
    }
}
