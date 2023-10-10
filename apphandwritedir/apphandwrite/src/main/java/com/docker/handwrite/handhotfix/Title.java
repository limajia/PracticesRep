package com.docker.handwrite.handhotfix;

public class Title {
    public String getTitle() {
        return "I'm a 2nd online hotfix title!";
    }
}



/*  app gradle 添加自动构建过程
    def patchPath = 'com/hencoder/pluginablehotfix/Title'

    task hotfix {
        doLast {
        exec {
        commandLine 'rm', '-r', './build/patch'
        }
        exec {
        commandLine 'mkdir', './build/patch'
        }
        exec {
        commandLine 'javac', "./src/main/java/${patchPath}.java", '-d', './build/patch'
        }
        exec {
        commandLine '/Users/rengwuxian/.android-sdk/build-tools/29.0.2/d8', "./build/patch/${patchPath}.class", '--output', './build/patch'
        }
        exec {
        commandLine 'mv', "./build/patch/classes.dex", './build/patch/hotfix.dex'
        }
        }
        }
 */
