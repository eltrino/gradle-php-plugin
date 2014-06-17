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
package com.eltrino.gradle.php.tasks

import org.gradle.api.tasks.*

class PhpMdTask extends SourceTask {
    private String reportFile = "pmd.xml"
    String group = ''

    @TaskAction
    def run() {
        Process process = "vendor/bin/phpmd ${source.getAsPath().replace(':', ',')} xml codesize,unusedcode,naming,controversial,design --reportfile ${reportFile}".execute()
        process.waitFor()
        if (process.exitValue())
            println "Problems in source code were found. Check report in ${reportFile}"
        else
            println "No problems were found."
    }
}
