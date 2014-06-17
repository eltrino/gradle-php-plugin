# Gradle PHP Plugin

This plugin provides basic tasks for PHP project. Currently this list includes

 - Syntax verification of all files in project

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
apply plugin: 'php'
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

## Todo

- Add more tasks ...
- Support different location for tools (instead of hardcoded vendor path) 
- Support exclude option for tasks
- Cover functionality by tests
