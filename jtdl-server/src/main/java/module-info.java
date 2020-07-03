open module moe.tristan.jtdl.server {

    requires java.annotation;

    requires immutables.styles;
    requires org.immutables.value;

    requires org.slf4j;

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires spring.webmvc;
    requires org.apache.tomcat.embed.core;

}
