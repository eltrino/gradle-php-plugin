# Gradle PHP Plugin

This plugin provides basic tasks for PHP project. Currently this list includes

 - Syntax verification of all files in project
 - PHPMD execution
 - Run test through PHPUnit

## Usage

### Loading Plugin

Currently plugin not hosted anywere so you wiull need to install it in local Maven repository 

```
gradle build install
```

Add Maven Local repository 

```
buildscript {
    repositories {
        mavenLocal()
    }
}
```

Add plugin dependency

```
buildscript {
    dependencies {
        classpath group: 'com.eltrino.gradle', name: 'php-plugin', version: '1.0'
    }
}
```

Apply plugin itself

```
apply plugin: 'com.eltrino.gradle.php'
```

### Configuration

Specify where all PHP source could be found. Its done in same way as for Java or Scala source code. 

```
sourceSets {
    main {
        php {
            srcDir '.'
            include '**/*.php'
        }
    }
}
```

```
//optional
php{
    phpExecutableLocation = '{your-path-to-php(.exe)}' // defaults to 'php'
    phpUnitExecutableLocation='{your-path-to-phpunit(.bat)}' // defaults to 'vendor/bin/phpunit'
    pmdExecutableLocation = '{your-path-to-phpmd(.bat)}' // defaults to 'vendor/bin/phpmd'
    pmdReportFile = '{your-path-to-pmd.xml}' // defaults to 'pmd.xml'
}
```

## Todo

- Add more tasks ...
- Support exclude option for tasks
- Cover functionality by tests
